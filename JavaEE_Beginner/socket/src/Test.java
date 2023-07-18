class A{
    private int i;
    public A(){
        System.out.println("构造函数");
    }
}

class B extends A{
    private int j;
    public B(int j){
        this.j = j;
    }
}

class C{
    public static void main(String[] args) {
        B b = new B(10);
    }
}