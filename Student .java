import java.util.ArrayList;
import java.util.Scanner;

class Course {
    String courseCode;
    String title;
    String description;
    int capacity;
    String schedule;

    Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
    }
}

class Student {
    String studentID;
    String name;
    ArrayList<Course> registeredCourses;

    Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }
}

public class CourseRegistrationSystem {
    static ArrayList<Course> courseDatabase = initializeCourseDatabase();
    static ArrayList<Student> studentDatabase = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. View Course Listing");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    displayCourseListing();
                    break;
                case 2:
                    registerForCourse();
                    break;
                case 3:
                    dropCourse();
                    break;
                case 4:
                    System.out.println("Exiting the system. Thank you!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static ArrayList<Course> initializeCourseDatabase() {
        ArrayList<Course> courses = new ArrayList<>();
        courses.add(new Course("CSCI101", "Introduction to Computer Science", "Fundamental concepts of programming", 30, "Mon/Wed 10:00 AM - 11:30 AM"));
        // Add more courses as needed
        return courses;
    }

    private static void displayCourseListing() {
        System.out.println("\nCourse Listing:");
        for (Course course : courseDatabase) {
            System.out.println("\nCourse Code: " + course.courseCode);
            System.out.println("Title: " + course.title);
            System.out.println("Description: " + course.description);
            System.out.println("Capacity: " + course.capacity);
            System.out.println("Schedule: " + course.schedule);
        }
    }

    private static void registerForCourse() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nEnter your Student ID: ");
        String studentID = scanner.nextLine();

        System.out.print("Enter the Course Code you want to register for: ");
        String courseCode = scanner.nextLine();

        Student student = findStudent(studentID);
        Course course = findCourse(courseCode);

        if (student != null && course != null && student.registeredCourses.size() < 5 && course.capacity > 0) {
            student.registeredCourses.add(course);
            course.capacity--;
            System.out.println("Registration successful!");
        } else {
            System.out.println("Registration failed. Please check your inputs or available slots.");
        }
    }

    private static void dropCourse() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nEnter your Student ID: ");
        String studentID = scanner.nextLine();

        System.out.print("Enter the Course Code you want to drop: ");
        String courseCode = scanner.nextLine();

        Student student = findStudent(studentID);
        Course course = findCourse(courseCode);

        if (student != null && course != null && student.registeredCourses.contains(course)) {
            student.registeredCourses.remove(course);
            course.capacity++;
            System.out.println("Course dropped successfully!");
        } else {
            System.out.println("Course drop failed. Please check your inputs or registration status.");
        }
    }

    private static Student findStudent(String studentID) {
        for (Student student : studentDatabase) {
            if (student.studentID.equals(studentID)) {
                return student;
            }
        }
        System.out.println("Student not found.");
        return null;
    }

    private static Course findCourse(String courseCode) {
        for (Course course : courseDatabase) {
            if (course.courseCode.equals(courseCode)) {
                return course;
            }
        }
        System.out.println("Course not found.");
        return null;
    }
}