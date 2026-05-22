package server;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import dao.MemberDAO;
import dao.EventDAO;
import model.Member;
import model.Event;

import java.io.*;
import java.net.InetSocketAddress;
import java.util.List;

public class SimplerServer {

    public static void start() throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // Serve frontend static files
        server.createContext("/", (HttpExchange exchange) -> {
            String path = exchange.getRequestURI().getPath();
            if (path.equals("/")) path = "/index.html";

            InputStream file = SimplerServer.class.getResourceAsStream("/frontend" + path);
            if (file == null) {
                String notFound = "404 Not Found";
                exchange.sendResponseHeaders(404, notFound.length());
                exchange.getResponseBody().write(notFound.getBytes());
                exchange.getResponseBody().close();
                return;
            }

            if (path.endsWith(".html")) exchange.getResponseHeaders().add("Content-Type", "text/html");
            else if (path.endsWith(".css")) exchange.getResponseHeaders().add("Content-Type", "text/css");
            else if (path.endsWith(".js")) exchange.getResponseHeaders().add("Content-Type", "application/javascript");

            byte[] bytes = file.readAllBytes();
            exchange.sendResponseHeaders(200, bytes.length);
            exchange.getResponseBody().write(bytes);
            exchange.getResponseBody().close();
        });

        // Members endpoint
        server.createContext("/api/members", (HttpExchange exchange) -> {
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            exchange.getResponseHeaders().add("Content-Type", "application/json");

            MemberDAO memberDAO = new MemberDAO();
            List<Member> members = memberDAO.getAllMembers();

            StringBuilder json = new StringBuilder("[");
            for (int i = 0; i < members.size(); i++) {
                Member m = members.get(i);
                json.append("{")
                    .append("\"id\":").append(m.getId()).append(",")
                    .append("\"name\":\"").append(m.getName()).append("\",")
                    .append("\"email\":\"").append(m.getEmail()).append("\",")
                    .append("\"phone\":\"").append(m.getPhone()).append("\",")
                    .append("\"role\":\"").append(m.getRole()).append("\"")
                    .append("}");
                if (i < members.size() - 1) json.append(",");
            }
            json.append("]");

            byte[] response = json.toString().getBytes();
            exchange.sendResponseHeaders(200, response.length);
            exchange.getResponseBody().write(response);
            exchange.getResponseBody().close();
        });

        // Events endpoint
        server.createContext("/api/events", (HttpExchange exchange) -> {
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            exchange.getResponseHeaders().add("Content-Type", "application/json");

            EventDAO eventDAO = new EventDAO();
            List<Event> events = eventDAO.getAllEvents();

            StringBuilder json = new StringBuilder("[");
            for (int i = 0; i < events.size(); i++) {
                Event e = events.get(i);
                json.append("{")
                    .append("\"id\":").append(e.getId()).append(",")
                    .append("\"title\":\"").append(e.getTitle()).append("\",")
                    .append("\"description\":\"").append(e.getDescription()).append("\",")
                    .append("\"eventDate\":\"").append(e.getEventDate()).append("\"")
                    .append("}");
                if (i < events.size() - 1) json.append(",");
            }
            json.append("]");

            byte[] response = json.toString().getBytes();
            exchange.sendResponseHeaders(200, response.length);
            exchange.getResponseBody().write(response);
            exchange.getResponseBody().close();
        });

        server.start();
        System.out.println("Server started at http://localhost:8080");
    }
}