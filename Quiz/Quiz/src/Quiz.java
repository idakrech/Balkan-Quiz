import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Quiz {

    /** METHOD FOR FETCHING A QUESTION FROM THE DATABASE */
    abstract void showQuestion(ResultSet rs) throws SQLException;

    /** METHOD FOR VALIDATING USER ANSWER */
    abstract Boolean isCorrectAnswer(String answer);
}
