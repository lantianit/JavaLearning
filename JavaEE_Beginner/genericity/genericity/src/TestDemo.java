class MyArray<T> {
    public T[] array = (T[])new Object[10];//1
    public T getPos(int pos) { 
        return this.array[pos]; 
    }
    public void setVal(int pos,T val) { 
        this.array[pos] = val; 
    } 
}
public class TestDemo {
    public static void main(String[] args) { 
        MyArray<Integer> myArray = new MyArray<>();//2
        myArray.setVal(0,10);
        myArray.setVal(1,12); 
        int ret = myArray.getPos(1);//3 
        System.out.println(ret);
        myArray.setVal(2,"bit");//4 
        } 
}