import java.util.Scanner;
public class Main{
    static int N = 100010,n,m,idx,hh,tt;
    static int[] h = new int[N],e = new int[N],ne = new int[N];
    static int[] d = new int[N],q = new int[N];
    //这个是单链表的模板
    public static void add(int a,int b){
        e[idx] = b;
        ne[idx] = h[a];
        h[a] = idx++;
    }
    public static int bfs(){
        hh = 0 ; tt = -1;
        d[1] = 0; // 第一个点是距离为0
        q[++tt] = 1; //然后将第一个点加进去
        //如果队列不是空的
        while(hh <= tt){
            int t = q[hh++]; //取出队头

            //然后遍历一下单链表
            for(int i = h[t] ; i != -1 ; i = ne[i]){

                int s = e[i]; //然后这个点用一个变量表示
                if(d[s] == -1){ //如果这个点是没走过的
                    d[s] = d[t] + 1; //然后将这个点距离加上1
                    q[++tt] = s;//然后将第二步的点加入到队尾中
                }
            }
        }
        return d[n]; //最后返回1到n的最短距离d


    }
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        m = scan.nextInt();
        //这里需要将距离跟头结点都赋值成-1
        for(int i = 1 ; i < N ; i++){
            h[i] = -1;
            d[i] = -1;
        }
        for(int i = 0 ; i < m ; i ++ ){
            int a = scan.nextInt();
            int b = scan.nextInt();
            add(a,b);
        }
        System.out.println(bfs());
    }
}
