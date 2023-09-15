import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        pair[] range = new pair[n+1];
        for (int i = 0; i < n; i++) {
            range[i] = new pair();
            range[i].l = scanner.nextInt();
            range[i].r = scanner.nextInt();
        }
        Arrays.sort(range,new Comparator<pair>(){
          @Override
          public int compare(pair o1,pair o2) {
              if (o1.l == o2.l) {
                  return o1.r - o2.r;
              } else {
                  return o1.l - o2.l;
              }
          }
        });
        int st = (int)-2e9-1;
        int ed = (int)-2e9-1;
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (ed < range[i].l) {
                st = range[i].l;
                ed = range[i].r;
                cnt++;
            } else {
                ed = Math.max(ed,range[i].r);
            }
        }
        System.out.print(cnt);
    }
    static class pair {
        int l;
        int r;
    }
}