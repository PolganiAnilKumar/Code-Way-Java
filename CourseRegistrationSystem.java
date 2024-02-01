import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Course {
    private String courseCode;
    private String title;
    private String description;
    private int capacity;
    private String schedule;
    private int occupiedSlots;

    public Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.occupiedSlots = 0;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getSchedule() {
        return schedule;
    }

    public int getOccupiedSlots() {
        return occupiedSlots;
    }

    public void occupySlot() {
        occupiedSlots++;
    }

    public void releaseSlot() {
        occupiedSlots--;
    }
}

class Student {
    private int studentID;
    private String name;
    private List<Course> registeredCourses;

    public Student(int studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public int getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public void registerForCourse(Course course) {
        registeredCourses.add(course);
        course.occupySlot();
    }

    public void dropCourse(Course course) {
        registeredCourses.remove(course);
        course.releaseSlot();
    }
}

public class CourseRegistrationSystem {
    public static void main(String[] args) {
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("CSCI101", "Introduction to Computer Science", "Fundamental concepts of programming", 30, "MWF 10:00 AM - 11:30 AM"));
        courses.add(new Course("MATH201", "Calculus I", "Limits, derivatives, and integrals", 25, "TTH 1:00 PM - 2:30 PM"));
        // Add more courses as needed

        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "Alice"));
        students.add(new Student(2, "Bob"));
        // Add more students as needed

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nOptions:");
            System.out.println("1. Display Course Listing");
            System.out.println("2. Display Student Information");
            System.out.println("3. Register for a Course");
            System.out.println("4. Drop a Course");
            System.out.println("5. Exit");

            System.out.print("Enter your choice (1-5): ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    displayCourseListing(courses);
                    break;
                case 2:
                    displayStudentInformation(students);
                    break;
                case 3:
                    registerForCourse(students, courses, scanner);
                    break;
                case 4:
                    dropCourse(students, courses, scanner);
                    break;
                case 5:
                    System.out.println("Exiting. Thank you!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }
    }

    private static void displayCourseListing(List<Course> courses) {
        System.out.println("\nCourse Listing:");
        for (Course course : courses) {
            System.out.println(course.getCourseCode() + " - " + course.getTitle() +
                    " (Capacity: " + (course.getCapacity() - course.getOccupiedSlots()) + "/" + course.getCapacity() + ")");
        }
    }

    private static void displayStudentInformation(List<Student> students) {
        System.out.println("\nStudent Information:");
        for (Student student : students) {
            System.out.println(student.getStudentID() + " - " + student.getName() +
                    " (Registered Courses: " + student.getRegisteredCourses().size() + ")");
        }
    }

    private static void registerForCourse(List<Student> students, List<Course> courses, Scanner scanner) {
        displayStudentInformation(students);
        System.out.print("Enter student ID: ");
        int studentID = scanner.nextInt();

        Student student = findStudent(students, studentID);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        displayCourseListing(courses);
        System.out.print("Enter course code to register: ");
        String courseCode = scanner.next();

        Course course = findCourse(courses, courseCode);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        if (course.getOccupiedSlots() < course.getCapacity()) {
            student.registerForCourse(course);
            System.out.println("Registration successful.");
        } else {
            System.out.println("Course is full. Registration unsuccessful.");
        }
    }

    private static void dropCourse(List<Student> students, List<Course> courses, Scanner scanner) {
        displayStudentInformation(students);
        System.out.print("Enter student ID: ");
        int studentID = scanner.nextInt();

        Student student = findStudent(students, studentID);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println("\nCourses registered by " + student.getName() + ":");
        List<Course> registeredCourses = student.getRegisteredCourses();
        for (Course registeredCourse : registeredCourses) {
            System.out.println(registeredCourse.getCourseCode() + " - " + registeredCourse.getTitle());
        }

        System.out.print("Enter course code to drop: ");
        String courseCode = scanner.next();

        Course course = findCourse(registeredCourses, courseCode);
        if (course == null) {
            System.out.println("Course not found in registered courses.");
            return;
        }

        student.dropCourse(course);
        System.out.println("Course dropped successfully.");
    }

    private static Student findStudent(List<Student> students, int studentID) {
        for (Student student : students) {
            if (student.getStudentID() == studentID) {
                return student;
            }
        }
        return null;
    }

    private static Course findCourse(List<Course> courses, String courseCode) {
        for (Course course : courses) {
            if (course.getCourseCode().equalsIgnoreCase(courseCode)) {
                return course;
            }
        }
        return null;
    }
}
