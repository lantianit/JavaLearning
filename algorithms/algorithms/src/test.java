import java.util.*;
class test{
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();//n次操作
        int m = scan.nextInt();//m次询问
        int N = 300010;//表示需要用到的下标数量，因为一开始可能重复，所以按照最大可能开最大的数组；
        int[] a =  new int[N]; //用来存值,从一开始的值，因为要用到前缀和，所以0不操作；
        int[] s = new int[N];//用来存前缀和，从一开始进行记录a数组；
        List<Integer> alls = new ArrayList<>();//用来存所有的下标，x,l,r;
        //因为可能会重复乱序，所以需要进行去重，排序；
        List<Pair> add = new ArrayList<>();//用来存n次操作
        List<Pair> query = new ArrayList<>();//用来存m次询问
        //输入n次操作，每次操作存入add集合中，然后将下标x存入alls集合中
        for(int i = 0 ; i < n ; i ++ ){
            int x = scan.nextInt();
            int c = scan.nextInt();
            add.add(new Pair(x,c));
            alls.add(x);
        }
        //输入m次询问，每次询问存入query集合中，因为l,r是求和的下标区间和，所以l,r都存入alls集合中。
        for(int i = 0 ; i < m ; i ++ ){
            int l = scan.nextInt();
            int r = scan.nextInt();
            query.add(new Pair(l,r));
            alls.add(l);
            alls.add(r);
        }

        Collections.sort(alls);   //排序，现在alls集合中存的是x，l，r所有值
        int unique = unique(alls);  //这一步是去重，因为l，x，r中可能有重复的数；
        alls = alls.subList(0,unique);  //将去重之后的alls的长度范围中的值重新赋值给alls集合中。

        //增强for循环 for(元素类型 变量名 ： 数组或者集合) 缺点：无下标，简单。
        for(Pair item : add){
            int index = find(item.first,alls);//
            a[index] += item.second;//
        }

        for(int i = 1 ; i <= alls.size() ; i ++ ) s[i] = s[i-1] + a[i]; //这是前缀和公式代码

        for(Pair item : query){
            int l = find(item.first,alls); // 
            int r = find(item.second,alls); // 
            System.out.println(s[r] - s[l-1]); // 
        }

    }
    //去重（只要符合是第一个数或者后面一个数不等于前面一个数就是不重复的数）
    public static int unique(List<Integer> list){
        int j = 0;
        for(int i = 0 ; i <= list.size() - 1; i ++ ){
            if(i == 0 || list.get(i) != list.get(i-1)){
                list.set(j,list.get(i)); //将不重复之后的数一个一个重新存入list中。
                j ++ ;
            }
        }
        return j;
    }
    //二分查找（在集合中查找你现在的下标是在什么位置，因为需要符合我们要用的前缀和公式，要让下标不是从0输出，最低的下标是1，符合前缀和的从1开始，所以输出的值加1）
    public static int find(int x ,List<Integer> list){
        int l  = 0;
        int r = list.size() - 1;
        while(l < r){
            int mid = ( l + r )/ 2;
            if(list.get(mid) >= x) r = mid;
            else l = mid + 1;
        }
        return l + 1;
    }
}
//这是一个Pair类，用来存操作的类
class Pair{
    int first;
    int second;
    public Pair(int x,int c){
        this.first = x;
        this.second = c;
    }
}
