import dao.MemberDAO;
import dao.EventDAO;
import model.Member;
import model.Event;
import server.SimplerServer;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        // Start the HTTP server
        SimplerServer.start();

        MemberDAO memberDAO = new MemberDAO();
        EventDAO eventDAO = new EventDAO();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== ElevateX Club =====");
            System.out.println("1. Add Member");
            System.out.println("2. View All Members");
            System.out.println("3. Delete Member");
            System.out.println("4. Add Event");
            System.out.println("5. View All Events");
            System.out.println("6. Delete Event");
            System.out.println("0. Exit");
            System.out.print("Choose: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    System.out.print("Phone: ");
                    String phone = sc.nextLine();
                    System.out.print("Role: ");
                    String role = sc.nextLine();
                    memberDAO.addMember(new Member(0, name, email, phone, role));
                    break;

                case 2:
                    System.out.println("\n--- Members ---");
                    for (Member m : memberDAO.getAllMembers()) {
                        System.out.println(m);
                    }
                    break;

                case 3:
                    System.out.print("Enter Member ID to delete: ");
                    int mid = sc.nextInt();
                    memberDAO.deleteMember(mid);
                    break;

                case 4:
                    System.out.print("Event Title: ");
                    String title = sc.nextLine();
                    System.out.print("Description: ");
                    String desc = sc.nextLine();
                    System.out.print("Date (YYYY-MM-DD): ");
                    String date = sc.nextLine();
                    eventDAO.addEvent(new Event(0, title, desc, date));
                    break;

                case 5:
                    System.out.println("\n--- Events ---");
                    for (Event e : eventDAO.getAllEvents()) {
                        System.out.println(e);
                    }
                    break;

                case 6:
                    System.out.print("Enter Event ID to delete: ");
                    int eid = sc.nextInt();
                    eventDAO.deleteEvent(eid);
                    break;

                case 0:
                    System.out.println("Goodbye!");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
    
}
