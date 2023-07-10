class ThreadSingletonPattern_Idler_Singlethreaded {
    private static ThreadSingletonPattern_Idler_Singlethreaded instance = null;
    private ThreadSingletonPattern_Idler_Singlethreaded() {}
    public static ThreadSingletonPattern_Idler_Singlethreaded getInstance() {
        if (instance == null) {
            instance = new ThreadSingletonPattern_Idler_Singlethreaded();
        }
        return instance;
    }
}