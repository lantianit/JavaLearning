import java.util.*;

class Main {

    public static int[] f;
    public static int[] cnt;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        f = new int[n+1];
        cnt = new int[n+1];
        for (int i = 1; i <= n; i++) {
            f[i] = i;
            cnt[i] = 1;
        }
        for (int i = 0; i < m; i++) {
            String s = scanner.next();
            if (s.equals("C")) {
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                int fa = find(a), fb = find(b);
                cnt[fa] += f[fb];
                f[fb] = f[fa];
            } else if (s.equals("Q1")) {
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                int fa = find(a), fb = find(b);
                if (fa == fb) {
                    System.out.println("Yes");
                } else {
                    System.out.println("No");
                }
            } else {
                int a = scanner.nextInt();
                int fa = find(a);
                System.out.println(cnt[fa]);
            }
        }
    }

    public static int find(int x) {
        if (x != f[x]) {
            return f[x] = find(f[x]);
        } else {
            return x;
        }
    }
}