import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuizApp {
    public static void main(String[] args) {
        // Create quiz questions
        List<Question> quizQuestions = new ArrayList<>();
        quizQuestions.add(new Question("What is the capital of France?", Arrays.asList("A. Berlin", "B. Paris", "C. Rome", "D. Madrid"), "B"));
        quizQuestions.add(new Question("Which planet is known as the Red Planet?", Arrays.asList("A. Earth", "B. Mars", "C. Jupiter", "D. Venus"), "B"));
        // Add more questions as needed

        // Create and start the quiz
        Quiz quiz = new Quiz(quizQuestions);
        quiz.startQuiz();
    }
}

