// Serve frontend static files
server.createContext("/", (HttpExchange exchange) -> {
    String path = exchange.getRequestURI().getPath();
    if (path.equals("/")) path = "/index.html";

    // Remove leading slash
    String resourcePath = "frontend" + path;
    InputStream file = Thread.currentThread().getContextClassLoader().getResourceAsStream(resourcePath);
    
    if (file == null) {
        // Try alternative
        file = ClassLoader.getSystemResourceAsStream(resourcePath);
    }

    if (file == null) {
        String notFound = "404 - File not found: " + resourcePath;
        byte[] msg = notFound.getBytes();
        exchange.sendResponseHeaders(404, msg.length);
        exchange.getResponseBody().write(msg);
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