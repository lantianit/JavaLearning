class ThreadSingleton_HungryHan {
    private static final ThreadSingleton_HungryHan instance = new ThreadSingleton_HungryHan();
    /*注意：构造方法也是private*/
    private ThreadSingleton_HungryHan() {}
    public static ThreadSingleton_HungryHan getInstance() {
        return instance;
   }
}