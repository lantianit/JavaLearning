import java.util.*;

class Main {

    public static int N = (int)2e5+10;
    public static int e[] = new int[N];
    public static int ne[] = new int[N];
    public static int h[] = new int[N];
    public static int idx = 0;
    public static boolean[] isUsed = new boolean[N];
    public static int ans;
    public static int maxMin;

    public static void add(int a,int b) {
        e[idx] = b; ne[idx] = h[a]; h[a] = idx++;
    }

    public static void main(String[] args) {
        ans = 0;
        maxMin = 0x3f3f3f3f;
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int i = 1; i <= n; i++) {
            h[i] = -1;
        }
        for (int i = 0; i < n; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            add(a,b);
            add(b,a);
        }
        dfs(1);
        System.out.print(ans);
    }

    public static int dfs(int root) {
        if (root == -1) {
            return 0;
        }
        int sum = 0;
        int max = 0;
        for (int i = h[root]; i != -1; i = ne[i]) {
            int cur = e[i];
            if (cur == -1) {
                continue;
            }
            int cnt = dfs(cur);
            sum += cnt;
            max = Math.max(max,cnt);
        }
        if (max < maxMin) {
            ans = root;
            maxMin = max;
        }
        return sum;
    }
}