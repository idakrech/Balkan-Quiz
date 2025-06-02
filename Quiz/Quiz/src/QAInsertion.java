import java.sql.*;
import java.util.Scanner;

public class QAInsertion {

    /** A SEPARATE CLASS FOR QUESTIONS- AND ANSWERS-INSERTION INTO A CHOSEN QUIZ' TABLE */

    public static void insertQuestionAndAnswersMultichoiceQuiz(Scanner scanner) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quizDb", "user0", "pass");
        Statement stmt = con.createStatement();
        System.out.println("Insert the table name (MultichoiceQuiz/BinaryQuiz):");
        String quizTableType = scanner.nextLine();
        if (quizTableType.equals("MultichoiceQuiz")) {
            String insertQandA = "INSERT INTO MultichoiceQuiz(question, answerA, answerB, answerC, answerD, correctAnswer) VALUES (" + readUserInputMultichoiceQuiz(scanner) + ");";
            stmt.executeUpdate(insertQandA);
            System.out.println("Insertion success");
        }
        else if (quizTableType.equals("BinaryQuiz")){
            String insertQandA = "INSERT INTO BinaryQuiz(question, correctAnswer) VALUES (" + readUserInputBinaryQuiz(scanner) + ");";
            stmt.executeUpdate(insertQandA);
            System.out.println("Insertion success");
        } else {
            System.out.println("Invalid table");
        }
    }

    private static String readUserInputMultichoiceQuiz(Scanner scanner) {
        String question = scanner.nextLine();
        String answerA = scanner.nextLine();
        String answerB = scanner.nextLine();
        String answerC = scanner.nextLine();
        String answerD = scanner.nextLine();
        String correctAnswer = scanner.nextLine();
        return question + ", " + answerA + ", " + answerB + ", " + answerC + ", " + answerD + ", " + correctAnswer;
    }

    private static String readUserInputBinaryQuiz(Scanner scanner) {
        String question = scanner.nextLine();
        boolean correctAnswer = Boolean.parseBoolean(scanner.nextLine());
        return question + ", " + correctAnswer;
    }


}
