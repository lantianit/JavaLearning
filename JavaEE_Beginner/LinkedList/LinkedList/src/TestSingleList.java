public class TestSingleList {

    public ListNode head;//表示存储当前链表的头节点的引用

    static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 通过穷举的方式 创建一个链表出来
     * 现在这样做  只是为了能够让初学者对这个结构更好的了解
     * 后期 我会改回来。
     */
    public void createList() {
        ListNode node1 = new ListNode(10);
        ListNode node2 = new ListNode(12);
        ListNode node3 = new ListNode(23);
        ListNode node4 = new ListNode(34);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        head = node1;
    }

    /**
     * 打印顺序表
     */
    public void display() {
        ListNode cur = this.head;
        while (cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    /**
     * 从指定节点开始打印链表
     *
     * @param newHead
     */
    public void display(ListNode newHead) {
        ListNode cur = newHead;
        while (cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    //查找是否包含关键字key是否在单链表当中
    public boolean contains(int key) {
        ListNode cur = head;
        while (cur != null) {
            if (cur.val == key) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    //得到单链表的长度
    public int size() {
        ListNode cur = head;
        int count = 0;
        while (cur != null) {
            count++;
            cur = cur.next;
        }
        return count;
    }

    //头插法: O(1)
    public void addFirst(int data) {
        ListNode node = new ListNode(data);
        node.next = head;
        head = node;
    }

    //尾插法  O(n)
    public void addLast(int data) {
        ListNode node = new ListNode(data);
        if (head == null) {
            head = node;
        } else {
            ListNode cur = head;
            while (cur.next != null) {
                cur = cur.next;
            }
            cur.next = node;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index > size()) {
            throw new IndexNotLegalException("index位置不合法！");
        }
    }

    public class IndexNotLegalException extends RuntimeException{
        public IndexNotLegalException() {

        }
        public IndexNotLegalException(String msg) {
            super(msg);
        }
    }

    //任意位置插入,第一个数据节点为0号下标
    public void addIndex(int index, int data) {
        checkIndex(index);
        if (index == 0) {
            addFirst(data);
            return;
        }
        if (index == size()) {
            addLast(data);
            return;
        }
        ///说明 不是头插 不是尾插 且 index合法
        ListNode node = new ListNode(data);
        ListNode cur = findIndexSubOfOne(index);
        node.next = cur.next;
        cur.next = node;
    }

    private ListNode findIndexSubOfOne(int index) {
        ListNode cur = head;
        while (index - 1 != 0) {
            cur = cur.next;
            index--;
        }
        return cur;
    }

    //删除第一次出现关键字为key的节点
    public void remove(int key) {
        //情况没处理
        if (head.val == key) {
            head = head.next;
            return;
        }
        ListNode cur = searchPrevOfKey(key);
        if (cur == null) return;
        ListNode del = cur.next;
        cur.next = del.next;
        //cur.next = cur.next.next;
    }

    /**
     * 找到关键字key的前驱
     *
     * @param key
     * @return
     */
    private ListNode searchPrevOfKey(int key) {
        ListNode cur = this.head;
        while (cur.next != null) {
            if (cur.next.val == key) {
                return cur;
            }
            cur = cur.next;
        }
        return null;
    }

    /**
     * 清空链表
     */
    public void clear() {
        //this.head = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode curNext = cur.next;
            cur.next = null;
            cur = curNext;
        }
        head = null;
    }

}