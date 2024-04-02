import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class MyStack {

    public Queue<Integer> queueMain;
    public Queue<Integer> queueTemp;

    public MyStack() {
        Stack<Integer> s = new ArrayList<>();
        queueMain = new LinkedList<>();
        queueTemp = new LinkedList<>();
    }

    public void push(int x) {
        queueMain.add(x);
    }

    public int pop() {
        while (!queueMain.isEmpty()) {
            int x = queueMain.poll();
            queueTemp.add(x);
        }
        int x = queueTemp.poll();
        queueMain = queueTemp;
        queueTemp = new LinkedList<>();
        return x;
    }

    public int top() {
        while (!queueMain.isEmpty()) {
            int x = queueMain.poll();
            queueTemp.add(x);
        }
        int x = queueTemp.peek();
        queueMain = queueTemp;
        queueTemp = new LinkedList<>();
        return x;
    }

    public boolean empty() {
        return queueMain.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */