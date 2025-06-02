import java.io.IOException;
import java.sql.*;
import java.util.Objects;
import java.util.Scanner;


public class Program {
    private String userName;
    private final String DB_USERNAME = "user0";
    private final String DB_PASSWORD = "pass";

    private  String userInput ="0";

    private UserScore userScore = new UserScore();
    private int score = userScore.getScore();

    public Program() {
    }

    private void setUserInput(String userInput){
        this.userInput=userInput;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    /** METHOD STARTING THE PROGRAM */
    public void start() throws IOException, SQLException {
        Scanner scanner = new Scanner(System.in);
        Connection con = getConnection(DB_USERNAME, DB_PASSWORD);
        System.out.println("Sign up a new user:");
        signUp(scanner);

        while(!Objects.equals(userInput, "Q")){
            showMenu();
            userInput = getQuizChoiceFromUser(scanner);
            switch (userInput) {
                case "A" -> {
                    initMultichoiceQuiz (scanner, con);
                    userScore.updateScoreTable(con, getUserName(), score, "Multichoice Quiz");
                }
                case "B" -> {
                    initBinaryQuiz(scanner, con);
                    userScore.updateScoreTable(con, getUserName(), score, "Binary Quiz");
                }
                case "C" -> userScore.displayScoreOfSpecificUser(con, scanner);
                case "D" -> userScore.displayScoresAboveSpecificNumber(con, scanner);
                case "Q" -> System.out.println("Bye!");
                default -> System.out.println("Enter in UPPERCASE one of the letters listed below.");
            }
        }
    }

      /** HELPING METHODS FOR THE STARTING METHOD */
      private void signUp(Scanner scanner) throws IOException, SQLException {
             setUserName(scanner.nextLine());
      }

        public void showMenu(){
            System.out.println("Enter a letter to choose an option:");
            System.out.println("A. Quiz: Capital cities of the Balkans");
            System.out.println("B. Quiz: Balkan coastlines");
            System.out.println("C. Show score of a specific user");
            System.out.println("D. Show scores above a specific number");
            System.out.println("Q. Quit program");
        }


        /** METHODS INITIALIZING THE QUIZZES */
        private void initMultichoiceQuiz(Scanner scanner, Connection con) throws SQLException {
            MultichoiceQuiz multichoiceQuiz = new MultichoiceQuiz();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT MultichoiceQuiz.id, MultichoiceQuiz.question, MultichoiceQuiz.answerA, MultichoiceQuiz.answerB, MultichoiceQuiz.answerC, MultichoiceQuiz.answerD, MultichoiceQuiz.correctAnswer FROM MultichoiceQuiz;");
            manageQuiz(multichoiceQuiz, scanner, rs, con);
        }

        private void initBinaryQuiz(Scanner scanner, Connection con)throws SQLException{
            BinaryQuiz binaryQuiz = new BinaryQuiz();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT BinaryQuiz.question, BinaryQuiz.correctAnswer FROM BinaryQuiz;");
            manageQuiz(binaryQuiz, scanner, rs, con);
        }


        /** HELPING METHOD FOR QUIZ-INITIALIZING METHODS */
        public void manageQuiz(Quiz quiz, Scanner scanner, ResultSet rs, Connection con) throws SQLException {
            while (rs.next()) {
                quiz.showQuestion(rs);
                String userInput= scanner.next();
                System.out.println(userInput);
                if (quiz.isCorrectAnswer(userInput)){
                    score += 1;
                    System.out.println("Your answer is correct! Would you like to");
                    System.out.println("A: Continue with the next question");
                    System.out.println("B: Exit current round");
                    System.out.println("Q: Quit the program");
                    String s = scanner.next();
                    if (s.equals("A") ){
                        continue;
                    }else if (s.equals("Q")){
                        setUserInput("Q");
                        break;
                    } else {
                        System.err.println("Invalid letter");
                        break;
                    }
                }else {
                    System.out.println("Your answer is wrong!");
                    System.out.println("Your final score is " + score);
                    System.out.println("You have following options:");
                    System.out.println("A. Continue with another round");
                    System.out.println("B. Show score board");
                    System.out.println("Q. Quit the program");
                    String s = scanner.next();
                    if (s.equals("A") ) {
                        break;
                    } else if (s.equals("B")){
                        userScore.displayScoreTable(con);
                        break;
                    }else if (s.equals("Q")){
                        setUserInput("Q");
                        break;
                    } else {
                        System.err.println("Invalid letter");
                        break;
                    }
                }
            }
        }


        /** OTHER GETTERS */
        private Connection getConnection(String userName, String password) throws SQLException {
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/quizDb", userName, password);
        }

    private String getQuizChoiceFromUser(Scanner scanner){
            String input = scanner.next();
            scanner.nextLine();
            return input;
        }

    }
