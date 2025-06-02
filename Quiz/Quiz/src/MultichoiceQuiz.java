import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MultichoiceQuiz extends Quiz {

    private String correctAnswer;

    public MultichoiceQuiz(){
    }

    @Override
    public void showQuestion(ResultSet rs) throws SQLException {
            String question = rs.getString("question");
            String answerA = rs.getString("answerA");
            String answerB = rs.getString("answerB");
            String answerC = rs.getString("answerC");
            String answerD = rs.getString("answerD");
            setCorrectAnswer(rs.getString("correctAnswer"));
            System.out.println(question);
            System.out.println("A. " + answerA);
            System.out.println("B. " + answerB);
            System.out.println("C. " + answerC);
            System.out.println("D. " + answerD);
        }

    @Override
    public Boolean isCorrectAnswer(String answer) {
        return answer.equals(correctAnswer);
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
