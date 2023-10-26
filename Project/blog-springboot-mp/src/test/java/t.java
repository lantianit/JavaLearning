class MyBlockingQueue {
    private int[] items;
    private volatile int head = 0;
    private volatile int tail = 0;
    private volatile int size = 0;

    public void put(int elem) throws InterruptedException {
        synchronized (this) {
            while (size >= items.length) {
                this.wait();
            }
            items[tail] = elem;
            tail++;
            if (tail >= items.length) {
                tail = 0;
            }
            size++;
            this.notify();
        }
    }

    public Integer take() throws InterruptedException {
        synchronized (this) {
            while (size == 0) {
                this.wait();
            }
        }

    }

}