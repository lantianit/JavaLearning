
class Node {
    int x;
    Node next;
    public Node(int x) {
        this.x = x;
    }
}
public class c {
    static Node node1 = new Node(1);
    static Node node2 = new Node(2);
    static Node node3 = new Node(3);

    public static void main(String[] args) {
        node1.next = node2;
        node2.next = node3;
        Node n = node1;
        while (n != null) {
            System.out.println(n.x);
            n = n.next;
        }
        return;
    }
}
