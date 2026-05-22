package dao;

import db.DBConnection;
import model.Event;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventDAO {

    // Add new event
    public void addEvent(Event event) {
        String sql = "INSERT INTO events (title, description, event_date) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, event.getTitle());
            ps.setString(2, event.getDescription());
            ps.setString(3, event.getEventDate());
            ps.executeUpdate();
            System.out.println("Event added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get all events
    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();
        String sql = "SELECT * FROM events";
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                events.add(new Event(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("description"),
                    rs.getString("event_date")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }

    // Delete event by id
    public void deleteEvent(int id) {
        String sql = "DELETE FROM events WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Event deleted successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}