import java.util.Scanner;

public class GradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the number of subjects: ");
        int numSubjects = scanner.nextInt();
        
        int[] marks = new int[numSubjects];
        for (int i = 0; i < numSubjects; i++) {
            System.out.printf("Enter marks obtained in subject : " + (i+1)+" ");
            marks[i] = scanner.nextInt();
        }
        
        int totalMarks = 0;
        for (int mark : marks) {
            totalMarks += mark;
        }
    
        double averagePercentage = (double) totalMarks / numSubjects;
        
        char grade;
        switch ((int) averagePercentage / 10) {
            case 10:
                grade = 'o';
            case 9:
                grade = 'A';
                break;
            case 8:
                grade = 'B';
                break;
            case 7:
                grade = 'C';
                break;
            case 6:
                grade = 'D';
                break;
            case 5:
                grade = 'E';
                break;
            default:
                grade = 'F';
                break;
        }
        
    
        System.out.println("--- Results ---");
        System.out.println("Total Marks: " + totalMarks);
        System.out.println("Average Percentage:"+ averagePercentage);
        System.out.println("Grade: " + grade);
        
        scanner.close();
    }
}
