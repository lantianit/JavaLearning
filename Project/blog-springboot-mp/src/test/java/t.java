/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    public Node connect(Node root) {
        Queue<Node> queue = new ArrayDeque<Node>();
        queue.put(root);
        while (queue.size() > 0) {
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                    Node n = queue.element();

            }
        }
    }
}