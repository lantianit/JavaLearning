import java.util.Arrays;

public class MyArrayList {

    public int[] elem;
    public int usedSize;//当前数组中 放了几个数据  记录一下
    public static final int DEFAULT_CAPACITY = 2;

    public MyArrayList() {
        this.elem = new int[DEFAULT_CAPACITY];
    }

    // 打印顺序表,只需要打印到usedSize下标就可以了
    public void display() {
        for (int i = 0; i < this.usedSize; i++) {
            System.out.print(this.elem[i]+" ");
        }
        System.out.println();
    }

    // 新增元素,默认在数组最后新增
    public void add(int data) {
        if(isFull()) {
            elem = Arrays.copyOf(elem,2*elem.length);
        }
        /*try {
            if(isFull()) {
                elem = Arrays.copyOf(elem,-1);
            }
        }catch (NegativeArraySizeException e) {
            e.printStackTrace();
            elem = Arrays.copyOf(elem,2*elem.length);
        }*/
        elem[usedSize++] = data;
    }

    public boolean isFull() {
        //这里usedSize 必须和 elem.length
        return usedSize == elem.length;
    }

    /**
     * 检查add元素的时候，pos位置是否合法
     * @param pos
     */
    private void checkAddPos(int pos) {
        if(pos < 0 || pos > usedSize) {
            throw new PosIndexNotLegalException("pos位置不合法");
        }
    }
    // 在 pos 位置新增元素
    public void add(int pos, int data) {
        try {
            checkAddPos(pos);
            if(isFull()) {
                //System.out.println("扩容");
                elem = Arrays.copyOf(elem,2*elem.length);
            }
            for (int i = usedSize-1; i >= pos ; i--) {
                elem[i+1] = elem[i];
            }
            elem[pos] = data;
            usedSize++;
        }catch (PosIndexNotLegalException e) {
            e.printStackTrace();
        }
    }
    // 判定是否包含某个元素
    public boolean contains(int toFind) {
        for (int i = 0; i < usedSize; i++) {
            if(elem[i] == toFind) {
                return true;
            }
        }
        return false;
    }
    // 查找某个元素对应的位置
    public int indexOf(int toFind) {
        for (int i = 0; i < usedSize; i++) {
            if(elem[i] == toFind) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 获取pos位置的数据 检查合法性
     * @param pos
     */
    private void checkGetPos(int pos) {
        if(pos < 0 || pos >= usedSize) {
            throw new PosIndexNotLegalException("pos位置不合法");
        }
    }

    // 获取 pos 位置的元素
    public int get(int pos) {
        checkGetPos(pos);
        return elem[pos];
    }

    // 给 pos 位置的元素设为 value 更新
    public void set(int pos, int value) {
        checkGetPos(pos);
        elem[pos] = value;
    }

    //删除第一次出现的关键字key
    public void remove(int key) {
        int index = indexOf(key);
        if(index == -1) {
            System.out.println("没有你要删除的数字！");
            return;
        }
        for (int i = index; i < usedSize-1; i++) {
            elem[i] = elem[i+1];
        }
        //elem[i] = null;
        usedSize--;
    }
    // 获取顺序表长度
    public int size() {
        return usedSize;
    }

    // 清空顺序表
    public void clear() {
       /* for (int i = 0; i < usedSize; i++) {
            elem[i] = null;
        }*/
        usedSize = 0;
    }

    public class PosIndexNotLegalException extends RuntimeException{
        public PosIndexNotLegalException() {

        }
        public PosIndexNotLegalException(String msg) {
            super(msg);
        }
    }

}

