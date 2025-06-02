import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class UserScore {

    private int score = 0;

    public int getScore() {
        return score;
    }


    /** METHOD FOR UPDATING THE SCORE BOARD AFTER EVERY ROUND */
    public void updateScoreTable(Connection con, String userName, int score, String topic) throws SQLException {
        Statement stmt = con.createStatement();
        int rs = stmt.executeUpdate("INSERT INTO Score(user, score, topic) VALUES (" + "'" + userName + "', '"+score+"', '"+topic+"');");
    }


    /** METHOD FOR SHOWING THE SCORE BOARD */
    public void displayScoreTable(Connection con) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Score");
        System.out.println("ID    USER    SCORE    TOPIC");
        while(rs.next()){
            int id = rs.getInt("id");
            String user = rs.getString("user");
            int score = rs.getInt("score");
            String topic = rs.getString("topic");
            System.out.println(id + "    " + user + "    " + score + "    " + topic);
        }
    }

    /** METHOD FOR DISPLAYING SCORES OF PARTICULAR USER */
    public void displayScoreOfSpecificUser(Connection con, Scanner scanner) throws SQLException {
        ArrayList<String> userList = new ArrayList<>();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT DISTINCT user FROM Score");
        int index = -1;
        while(rs.next()){
            index++;
            String user = rs.getString("user");
            userList.add(index, user);
        }
        for (int i = 0; i < userList.size(); i++) {
            System.out.println(i + ". " + userList.get(i));
        }
        System.out.println("Which user?");
        int userInput = scanner.nextInt();
        ResultSet rs2 = stmt.executeQuery("SELECT * FROM Score WHERE user LIKE '"+userList.get(userInput)+"';");
        while(rs2.next()){
            String user = rs2.getString("user");
            int score = rs2.getInt("score");
            String topic = rs2.getString("topic");
            System.out.println("USER    SCORE    TOPIC");
            System.out.println(user + "    " + score + "    " + topic);
        }
    }


    /** METHOD FOR DISPLAYING SCORES ABOVE A SPECIFIED LEVEL */
    public void displayScoresAboveSpecificNumber(Connection con, Scanner scanner) throws SQLException {
        System.out.println("Which quiz?");
        System.out.println("1. Capital cities of the Balkans");
        System.out.println("2. Balkan coastlines");
        int userChoice = scanner.nextInt();
        System.out.println("Enter the lower limit:");
        int limit = scanner.nextInt();
        Statement stmt = con.createStatement();
        if (userChoice == 1) {
            System.out.println("Capital cities of the Balkans Quiz' scores above " + limit + ":");
            ResultSet rs = stmt.executeQuery("SELECT * FROM Score WHERE (score >= " + limit + ") AND topic LIKE 'Multichoice Quiz';");
            while(rs.next()){
                String user = rs.getString("user");
                int score = rs.getInt("score");
                System.out.println(user + "    " + score);
            }
        } else if (userChoice == 2) {
            System.out.println("Balkan Coastlines Quiz' scores above " + limit + ":");
            ResultSet rs = stmt.executeQuery("SELECT * FROM Score WHERE (score >= " + limit + ") AND topic LIKE 'Binary Quiz';");
            while(rs.next()){
                String user = rs.getString("user");
                int score = rs.getInt("score");
                System.out.println(user + "    " + score);
            }
        }
    }
}
