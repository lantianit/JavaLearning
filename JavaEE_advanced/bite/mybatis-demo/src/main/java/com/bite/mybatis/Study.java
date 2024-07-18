

import java.util.*;

class ain {

    static int N = (int)1e5+10;
    static int[] e = new int [N];static int[] ne = new int[N];static int[] h = new int[N];
    static int idx = 1;

    public static void add(int a,int b) {
        e[idx] = b; ne[idx] = h[a]; h[a] = idx++;
    }

    public static void main(String[] args) {
        
        TreeSet<Integer> set = new TreeSet<>();
        set.stream().sorted();

        int head = -1;

        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        while (n-- != 0) {
            String s = scan.next();
            if (s.equals("H")) {
                int x = scan.nextInt();
                e[idx] = x;
                ne[idx] = head;
                head = idx++;
            } else if (s.equals("I")) {
                int k = scan.nextInt();
                int x = scan.nextInt();
                e[idx] = x;
                ne[idx] = ne[k];
                ne[k] = idx++;
            } else if (s.equals("D")) {
                int k = scan.nextInt();
                int temp = head;
                if (head == k) {
                    head = ne[k];
                    continue;
                }
                while (temp != -1 && ne[temp] != k) {
                    temp = ne[temp];
                }
                if (temp != -1 && ne[temp] == k) {
                    ne[temp] = ne[k];
                }
            }
        }

        int cur = head;
        while (cur != -1) {
            System.out.print(e[cur] + " ");
            cur = ne[cur];
        }

    }

}