package model;

public class Member {
    
    private int id;
    private String name;
    private String email;
    private String phone;
    private String role;

    // Constructor
    public Member(int id, String name, String email, String phone, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.role = role;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getRole() { return role; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setRole(String role) { this.role = role; }

    @Override
    public String toString() {
        return "Member [id=" + id + ", name=" + name + 
               ", email=" + email + ", phone=" + phone + 
               ", role=" + role + "]";
    }
}
