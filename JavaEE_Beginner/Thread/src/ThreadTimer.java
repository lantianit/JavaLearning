import java.util.Timer;
import java.util.TimerTask;

public class ThreadTimer {
    public static void main(String[] args) {
        // 标准库的定时器.
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("时间到, 快起床!");
            }
        }, 3000);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("时间到2!");
            }
        }, 4000);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("时间到3!");
            }
        }, 5000);

        System.out.println("开始计时!");
    }
}