import java.util.*;

class Main {

    public static void main(String[] args) {
        Scanner scann = new Scanner(System.in);
        int n = scann.nextInt();
        int m = scann.nextInt();
        int k = scann.nextInt();

        int[] d = new int[n+2];
        Arrays.fill(d,0x3f3f3f3f);

        List<Edge> list = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            int a = scann.nextInt();
            int b = scann.nextInt();
            int dist = scann.nextInt();
            Edge e = new Edge();
            e.a = a; e.b = b; e.d = dist;
            list.add(e);
        }

        d[1] = 0;

        for (int i = 0; i < k; i++) {
            int[] temp = Arrays.copyOf(d,d.length);
            for (Edge e : list) {
                int a = e.a;
                int b = e.b;
                int dist = e.d;
                if (temp[b] > d[a] + dist) {
                    temp[b] = d[a] + dist;
                }
            }
            d = temp;
        }

        if (d[n] >= (int)0x3f3f3f3f/2) {
            System.out.print("impossible");
        } else {
            System.out.print(d[n]);
        }

    }

    static class Edge {
        int a;
        int b;
        int d;
    }

}