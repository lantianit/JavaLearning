import java.util.Arrays;

public class MyStack {

    public int[] elem;
    //当前栈 当中 存储的有效的数据个数  也可以当中  当前可以存放数据元素的下标
    public int usedSize;

    public static final int DEFAULT_CAPACITY = 10;

    public MyStack() {
        elem = new int[DEFAULT_CAPACITY];
    }
    /**
     * 压栈
     */
    public void push(int val) {
        //1、判断栈是否是满的
        if(isFull()) {
            elem = Arrays.copyOf(elem,2*elem.length);
        }
        //存放到当前的下标，同时usedSize需要自增
        elem[usedSize] = val;
        usedSize++;
    }

    /**
     * 判断 当前栈是否是满的
     * @return
     */
    public boolean isFull() {
        if(usedSize == elem.length) {
            return true;
        }
        return false;
    }

    /**
     * 删除栈顶元素
     * @return
     */
    public int pop(){
        if(isEmpty()) {
            throw new EmptyStackException("栈为空了！");
        }
        int oldVal = elem[usedSize-1];
        usedSize--;
        return oldVal;
    }

    /**
     * 是否为空
     * @return
     */
    public boolean isEmpty() {
        return usedSize == 0;
    }

    /**
     * 获取栈顶元素 但是不删除
     * @return
     */
    public int peek() {
        if(isEmpty()) {
            throw new EmptyStackException("栈为空了！");
        }
        return elem[usedSize-1];
    }

    public int getUsedSize() {
        return usedSize;
    }

    public class EmptyStackException extends RuntimeException{

        public EmptyStackException() {

        }
        public EmptyStackException(String msg) {
            super(msg);
        }
    }
}