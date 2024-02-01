import java.util.*;

class Quiz {
    private List<Question> questions;
    private int currentQuestionIndex;
    private int userScore;

    public Quiz(List<Question> questions) {
        this.questions = questions;
        this.currentQuestionIndex = 0;
        this.userScore = 0;
    }

    public void startQuiz() {
        Scanner scanner = new Scanner(System.in);

        for (Question question : questions) {
            System.out.println("\n" + question.getQuestionText());
            question.displayOptions();

            // Set a timer for each question (e.g., 10 seconds)
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("Time's up!");
                    showNextQuestion();
                }
            }, 10000); // 10000 milliseconds = 10 seconds

            // User input for answer
            System.out.print("Your choice: ");
            String userAnswer = scanner.nextLine();

            // Cancel the timer when the user submits an answer
            timer.cancel();

            // Check if the answer is correct
            if (question.isCorrect(userAnswer)) {
                System.out.println("Correct!");
                userScore++;
            } else {
                System.out.println("Incorrect. The correct answer was: " + question.getCorrectAnswer());
            }

            showNextQuestion();
        }

        // Display final result
        System.out.println("\nQuiz completed! Your final score: " + userScore + " out of " + questions.size());
        scanner.close();
    }

    private void showNextQuestion() {
        currentQuestionIndex++;
        if (currentQuestionIndex < questions.size()) {
            System.out.println("\nNext Question:");
        }
    }
}

class Question {
    private String questionText;
    private List<String> options;
    private String correctAnswer;

    public Question(String questionText, List<String> options, String correctAnswer) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void displayOptions() {
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }
    }

    public boolean isCorrect(String userAnswer) {
        return correctAnswer.equalsIgnoreCase(userAnswer.trim());
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}



