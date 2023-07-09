public class ThreadStateTransfer {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            for (int i = 0; i < 1000_0000; i++) {
           }
       }, "李四");
        System.out.println(t.getName() + ": " + t.getState());;
        t.start();
        while (t.isAlive()) {
            System.out.println(t.getName() + ": " + t.getState());;
       }
        System.out.println(t.getName() + ": " + t.getState());;
   }
}