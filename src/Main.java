import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int choice;

        do {

            System.out.println("\n===== MUSIC FESTIVAL MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Artist");
            System.out.println("2. View Artists");
            System.out.println("3. Update Artist");
            System.out.println("4. Delete Artist");
            System.out.println("5. Add Attendee");
            System.out.println("6. View Attendees");
            System.out.println("7. Update Attendee");
            System.out.println("8. Delete Attendee");
            System.out.println("9. Add Crowd Feedback");
            System.out.println("10. View Crowd Feedback");
            System.out.println("11. Book Ticket");
            System.out.println("12. View Tickets");
            System.out.println("13. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    addArtist();
                    break;

                case 2:
                    viewArtists();
                    break;

                case 3:
                    updateArtist();
                    break;

                case 4:
                    deleteArtist();
                    break;

                case 5:
                    addAttendee();
                    break;

                case 6:
                    viewAttendees();
                    break;

                case 7:
                    updateAttendee();
                    break;

                case 8:
                    deleteAttendee();
                    break;

                case 9:
                    addCrowdFeedback();
                    break;

                case 10:
                    viewCrowdFeedback();
                    break;

                case 11:
                    bookTicket();
                    break;

                case 12:
                    viewTickets();
                    break;

                case 13:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 13);

        sc.close();
    }


    // ==================== ARTIST ====================

    static void addArtist() {

        System.out.print("Enter Artist ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Artist Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Genre: ");
        String genre = sc.nextLine();

        System.out.print("Enter Country: ");
        String country = sc.nextLine();

        try {

            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO ARTISTS VALUES (?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, genre);
            ps.setString(4, country);

            ps.executeUpdate();

            System.out.println("Artist added successfully!");

            ps.close();
            con.close();

        } catch (Exception e) {

            System.out.println("Error: " + e.getMessage());
        }
    }


    static void viewArtists() {

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM ARTISTS";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            boolean found = false;

            System.out.println("\n===== ARTISTS =====");

            while (rs.next()) {

                found = true;

                System.out.println("Artist ID: " +
                        rs.getInt("ARTIST_ID"));

                System.out.println("Artist Name: " +
                        rs.getString("ARTIST_NAME"));

                System.out.println("Genre: " +
                        rs.getString("GENRE"));

                System.out.println("Country: " +
                        rs.getString("COUNTRY"));

                System.out.println("------------------------------");
            }

            if (!found) {
                System.out.println("No artists found!");
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {

            System.out.println("Error: " + e.getMessage());
        }
    }


    static void updateArtist() {

        System.out.print("Enter Artist ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter new Artist Name: ");
        String name = sc.nextLine();

        System.out.print("Enter new Genre: ");
        String genre = sc.nextLine();

        System.out.print("Enter new Country: ");
        String country = sc.nextLine();

        try {

            Connection con = DBConnection.getConnection();

            String sql =
                    "UPDATE ARTISTS SET ARTIST_NAME=?, GENRE=?, COUNTRY=? " +
                    "WHERE ARTIST_ID=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, genre);
            ps.setString(3, country);
            ps.setInt(4, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Artist updated successfully!");
            } else {
                System.out.println("Artist not found!");
            }

            ps.close();
            con.close();

        } catch (Exception e) {

            System.out.println("Error: " + e.getMessage());
        }
    }


    static void deleteArtist() {

        System.out.print("Enter Artist ID to delete: ");
        int id = sc.nextInt();
        sc.nextLine();

        try {

            Connection con = DBConnection.getConnection();

            String sql =
                    "DELETE FROM ARTISTS WHERE ARTIST_ID=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Artist deleted successfully!");
            } else {
                System.out.println("Artist not found!");
            }

            ps.close();
            con.close();

        } catch (Exception e) {

            System.out.println("Error: " + e.getMessage());
        }
    }


    // ==================== ATTENDEE ====================

    static void addAttendee() {

        System.out.print("Enter Attendee ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Attendee Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        System.out.print("Enter Phone: ");
        String phone = sc.nextLine();

        try {

            Connection con = DBConnection.getConnection();

            String sql =
                    "INSERT INTO ATTENDEES VALUES (?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, email);
            ps.setString(4, phone);

            ps.executeUpdate();

            System.out.println("Attendee added successfully!");

            ps.close();
            con.close();

        } catch (Exception e) {

            System.out.println("Error: " + e.getMessage());
        }
    }


    static void viewAttendees() {

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM ATTENDEES";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            boolean found = false;

            System.out.println("\n===== ATTENDEES =====");

            while (rs.next()) {

                found = true;

                System.out.println("Attendee ID: " +
                        rs.getInt("ATTENDEE_ID"));

                System.out.println("Attendee Name: " +
                        rs.getString("ATTENDEE_NAME"));

                System.out.println("Email: " +
                        rs.getString("EMAIL"));

                System.out.println("Phone: " +
                        rs.getString("PHONE"));

                System.out.println("------------------------------");
            }

            if (!found) {
                System.out.println("No attendees found!");
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {

            System.out.println("Error: " + e.getMessage());
        }
    }


    static void updateAttendee() {

        System.out.print("Enter Attendee ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter new Attendee Name: ");
        String name = sc.nextLine();

        System.out.print("Enter new Email: ");
        String email = sc.nextLine();

        System.out.print("Enter new Phone: ");
        String phone = sc.nextLine();

        try {

            Connection con = DBConnection.getConnection();

            String sql =
                    "UPDATE ATTENDEES SET ATTENDEE_NAME=?, EMAIL=?, PHONE=? " +
                    "WHERE ATTENDEE_ID=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setInt(4, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Attendee updated successfully!");
            } else {
                System.out.println("Attendee not found!");
            }

            ps.close();
            con.close();

        } catch (Exception e) {

            System.out.println("Error: " + e.getMessage());
        }
    }


    static void deleteAttendee() {

        System.out.print("Enter Attendee ID to delete: ");
        int id = sc.nextInt();
        sc.nextLine();

        try {

            Connection con = DBConnection.getConnection();

            String sql =
                    "DELETE FROM ATTENDEES WHERE ATTENDEE_ID=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Attendee deleted successfully!");
            } else {
                System.out.println("Attendee not found!");
            }

            ps.close();
            con.close();

        } catch (Exception e) {

            System.out.println("Error: " + e.getMessage());
        }
    }


    // ==================== MONGODB ====================

    static void addCrowdFeedback() {

        System.out.print("Enter attendee name: ");
        String attendeeName = sc.nextLine();

        System.out.print("Enter artist name: ");
        String artistName = sc.nextLine();

        System.out.print("Enter rating (1-5): ");
        int rating = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter comment: ");
        String comment = sc.nextLine();

        try {

            MongoDatabase database =
                    MongoDBConnection.database;

            MongoCollection<Document> collection =
                    database.getCollection("crowd_feedback");

            Document feedback =
                    new Document("attendeeName", attendeeName)
                    .append("artistName", artistName)
                    .append("rating", rating)
                    .append("comment", comment);

            collection.insertOne(feedback);

            System.out.println(
                    "Crowd feedback added successfully!");

        } catch (Exception e) {

            System.out.println(
                    "MongoDB Error: " + e.getMessage());
        }
    }


    static void viewCrowdFeedback() {

        try {

            MongoDatabase database =
                    MongoDBConnection.database;

            MongoCollection<Document> collection =
                    database.getCollection("crowd_feedback");

            boolean found = false;

            System.out.println("\n===== CROWD FEEDBACK =====");

            for (Document doc : collection.find()) {

                found = true;

                System.out.println("------------------------------");

                System.out.println("Attendee: " +
                        doc.getString("attendeeName"));

                System.out.println("Artist: " +
                        doc.getString("artistName"));

                System.out.println("Rating: " +
                        doc.getInteger("rating"));

                System.out.println("Comment: " +
                        doc.getString("comment"));
            }

            if (!found) {
                System.out.println("No crowd feedback found!");
            }

        } catch (Exception e) {

            System.out.println(
                    "MongoDB Error: " + e.getMessage());
        }
    }


    // ==================== TICKET BOOKING ====================

    static void bookTicket() {

        System.out.print("Enter Ticket ID: ");
        int ticketId = sc.nextInt();

        System.out.print("Enter Attendee ID: ");
        int attendeeId = sc.nextInt();

        System.out.print("Enter Ticket Tier ID: ");
        int tierId = sc.nextInt();
        sc.nextLine();

        Connection con = null;

        try {

            con = DBConnection.getConnection();

            // Start transaction
            con.setAutoCommit(false);


            // Check whether attendee exists

            String attendeeSql =
                    "SELECT ATTENDEE_ID " +
                    "FROM ATTENDEES " +
                    "WHERE ATTENDEE_ID=?";

            PreparedStatement attendeePs =
                    con.prepareStatement(attendeeSql);

            attendeePs.setInt(1, attendeeId);

            ResultSet attendeeRs =
                    attendeePs.executeQuery();

            if (!attendeeRs.next()) {

                System.out.println("Attendee not found!");

                attendeeRs.close();
                attendeePs.close();

                con.rollback();
                con.close();

                return;
            }

            attendeeRs.close();
            attendeePs.close();


            // Check ticket tier and availability

            String checkSql =
                    "SELECT AVAILABLE_QUANTITY " +
                    "FROM TICKET_TIERS " +
                    "WHERE TIER_ID=?";

            PreparedStatement checkPs =
                    con.prepareStatement(checkSql);

            checkPs.setInt(1, tierId);

            ResultSet rs =
                    checkPs.executeQuery();

            if (!rs.next()) {

                System.out.println("Ticket tier not found!");

                rs.close();
                checkPs.close();

                con.rollback();
                con.close();

                return;
            }

            int available =
                    rs.getInt("AVAILABLE_QUANTITY");

            rs.close();
            checkPs.close();


            // Check availability

            if (available <= 0) {

                System.out.println("Tickets sold out!");

                con.rollback();
                con.close();

                return;
            }


            // Insert ticket

            String insertSql =
                    "INSERT INTO TICKETS " +
                    "VALUES (?, ?, ?, SYSDATE)";

            PreparedStatement insertPs =
                    con.prepareStatement(insertSql);

            insertPs.setInt(1, ticketId);
            insertPs.setInt(2, attendeeId);
            insertPs.setInt(3, tierId);

            insertPs.executeUpdate();

            insertPs.close();


            // Reduce available quantity

            String updateSql =
                    "UPDATE TICKET_TIERS " +
                    "SET AVAILABLE_QUANTITY = " +
                    "AVAILABLE_QUANTITY - 1 " +
                    "WHERE TIER_ID=?";

            PreparedStatement updatePs =
                    con.prepareStatement(updateSql);

            updatePs.setInt(1, tierId);

            updatePs.executeUpdate();

            updatePs.close();


            // Everything successful

            con.commit();

            System.out.println(
                    "Ticket booked successfully!");

            con.close();

        } catch (Exception e) {

            System.out.println(
                    "Error: " + e.getMessage());

            try {

                if (con != null) {

                    con.rollback();
                    con.close();
                }

            } catch (Exception ex) {

                System.out.println(
                        "Rollback error: " +
                        ex.getMessage());
            }
        }
    }


    // ==================== VIEW TICKETS ====================

    static void viewTickets() {

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "SELECT T.TICKET_ID, " +
                    "T.ATTENDEE_ID, " +
                    "T.TIER_ID, " +
                    "TT.TIER_NAME, " +
                    "TT.PRICE, " +
                    "T.PURCHASE_DATE " +
                    "FROM TICKETS T " +
                    "JOIN TICKET_TIERS TT " +
                    "ON T.TIER_ID = TT.TIER_ID";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            boolean found = false;

            System.out.println("\n===== TICKETS =====");

            while (rs.next()) {

                found = true;

                System.out.println("Ticket ID: " +
                        rs.getInt("TICKET_ID"));

                System.out.println("Attendee ID: " +
                        rs.getInt("ATTENDEE_ID"));

                System.out.println("Tier ID: " +
                        rs.getInt("TIER_ID"));

                System.out.println("Tier: " +
                        rs.getString("TIER_NAME"));

                System.out.println("Price: ₹" +
                        rs.getDouble("PRICE"));

                System.out.println("Purchase Date: " +
                        rs.getDate("PURCHASE_DATE"));

                System.out.println("------------------------------");
            }

            if (!found) {
                System.out.println("No tickets found!");
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {

            System.out.println(
                    "Error: " + e.getMessage());
        }
    }
}