package testng;

public class CalUtil {
    // 实现加法操作
    public static int add(int x, int y) {
        return x + y;
    }

    // 实现除法操作
    public static double div(int x, int y) {
        return x / y;
    }

    public static void main(String[] args) {
        // 1+1=2
        int result1 = CalUtil.add(1, 1);
        System.out.println("result1=" + result1);

        // 10+20=30
        int result2 = CalUtil.add(10, 20);
        System.out.println("result2=" + result2);

        // 100+200=300
        int result3 = CalUtil.add(100, 200);
        System.out.println("result3=" + result3);
    }
}
