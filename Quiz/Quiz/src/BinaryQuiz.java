import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;



public class BinaryQuiz extends Quiz {

    private String correctAnswer;

    public BinaryQuiz(){
    }

    @Override
    public void showQuestion(ResultSet rs) throws SQLException {
        String question = rs.getString("question");
        setCorrectAnswer(rs.getString("correctAnswer"));
        System.out.println(question);
        System.out.println("1. true");
        System.out.println("0. false");
    }

    @Override
    public Boolean isCorrectAnswer(String answer) {
        return answer.equals(correctAnswer);
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
