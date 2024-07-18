import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

class Main {

    static int N = (int)1e5+10;
    static int[] myStack = new int[N];
    static int idx = 0;

    public static void main(String[] args) {

        Map<Character, Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        while (n-- != 0) {
            String s = scan.next();
            if (s.equals("push")) {
                int x = scan.nextInt();
                myStack[idx++] = x;
            } else if (s.equals("pop")) {
                idx--;
                System.out.println(myStack[idx]);
            } else if (s.equals("empty")) {
                if (idx == 0) {
                    System.out.println("Yes");
                } else {
                    System.out.println("No");
                }
            } else if (s.equals("query")) {
                System.out.println(myStack[idx-1]);
            }
        }

    }

}