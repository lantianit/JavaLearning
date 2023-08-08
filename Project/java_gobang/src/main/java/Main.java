import java.util.Arrays;
import java.util.Scanner;

class Student{
    int id;
    int grade;
    public Student(int id,int grade){
        this.id = id;
        this.grade = grade;
    }
}

class Main{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Student[] students = new Student[n];
        for(int i = 0; i < n; i++){
            int id = scanner.nextInt();
            int grade = scanner.nextInt();
            students[i] = new Student(id,grade);
        }
        Arrays.sort(students,(o1, o2) -> {
            return o1.grade-o2.grade;
        });
        for (Student x : students){
            System.out.println(x.id + " " + x.grade);
        }
    }
}