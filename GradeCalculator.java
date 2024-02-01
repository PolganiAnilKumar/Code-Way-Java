
import java.util.Scanner;

    public class GradeCalculator {

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter marks obtained (out of 100) in each subject:");

            // Assuming 5 subjects for this example
            int numSubjects = 5;
            int totalMarks = 0;

            // Step 1: Take marks obtained in each subject
            for (int i = 1; i <= numSubjects; i++) {
                System.out.print("Subject " + i + ": ");
                int marks = scanner.nextInt();

                // Validate marks (assumed valid input between 0 and 100)
                if (marks < 0 || marks > 100) {
                    System.out.println("Invalid marks. Marks should be between 0 and 100. Exiting program.");
                    return;
                }

                totalMarks += marks;
            }

            // Step 2: Calculate total marks
            double averagePercentage = (double) totalMarks / numSubjects;

            // Step 3: Calculate average percentage
            char grade = calculateGrade(averagePercentage);

            // Step 4: Grade Calculation

            // Step 5: Display Results
            System.out.println("\nResults:");
            System.out.println("Total Marks: " + totalMarks);
            System.out.println("Average Percentage: " + averagePercentage + "%");
            System.out.println("Grade: " + grade);

            scanner.close();
        }

        private static char calculateGrade(double averagePercentage) {
            if (averagePercentage >= 90) {
                return 'A';
            } else if (averagePercentage >= 80) {
                return 'B';
            } else if (averagePercentage >= 70) {
                return 'C';
            } else if (averagePercentage >= 60) {
                return 'D';
            } else {
                return 'F';
            }
        }
    }

