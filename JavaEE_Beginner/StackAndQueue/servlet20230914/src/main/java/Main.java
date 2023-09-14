import java.io.BufferedReader;
import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    public static int n,m;
    public static int[][] gap = new int[110][110];
    public static int[][] dist = new int[110][110];
    public static PII[] q = new PII[110*110];
    public static int hh,tt;
    public static Queue<Integer> queue = new ArrayDeque<>();
    public static void main(String[] args) throws IOException {
        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter wt = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] s = rd.readLine().split(" ");
        n = Integer.parseInt(s[0]);
        m = Integer.parseInt(s[1]);
        for (int i = 0; i < n; i++) {
            String[] st = rd.readLine().split(" ");
            for (int j = 0 ; j < m ;j ++ ) {
                gap[i][j] = Integer.parseInt(st[j]);
                dist[i][j] = -1;
            }
        }
        System.out.println(bfs());
        wt.close();
    }

    public static int  bfs(){
        hh = 0 ; tt = -1; //队列的头节点=0，尾节点 = 0；
        dist[0][0] = 0; // 我们首先站在的是第一个点，所以值距离设置为0
        q[++tt] = new PII(0,0); //然后将第一个点下标存入q队列中
        //利用向量的方法进行让他上下左右判断是否能够前进
        int[] dx = {-1,0,1,0};//上(-1,0) 下(1,0)
        int[] dy = {0,1,0,-1};//左(0,-1) 右(0,1)
        while(hh <= tt){
            PII t = q[hh++]; //每一次将头结点拿出来
            for(int i = 0 ; i < 4 ; i ++ ){//然后进行下一步要往哪里走，这里可能有多重选择可走
                int x = t.first + dx[i]; //这里进行x轴向量判断
                int y = t.second + dy[i];//这里进行y轴向量的判断
                //如果x，y满足在地图中不会越界，然后地图上的点g是0(表示可以走)，
                //然后这里是没走过的距离d是-1；
                if(x >= 0 && x < n && y >= 0 && y < m && gap[x][y] == 0 && dist[x][y] == -1){
                    //将现在可以走的点(x,y)加上上一个点计数距离的点加上一，就是现在走到的点的距离
                    dist[x][y] = dist[t.first][t.second] + 1;
                    q[++tt] = new PII(x,y);//然后将这一个可以走的点存入队列尾
                }
            }
        }
        return dist[n-1][m-1]; //最后返回的是地图走到尽头最后一个位置的位置统计的距离
    }
    static class  PII {
        int first;
        int second;
        public PII(int first,int second) {
            this.first = first;
            this.second = second;
        }
    }
}