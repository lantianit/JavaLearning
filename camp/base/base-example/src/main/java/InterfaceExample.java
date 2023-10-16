public interface InterfaceExample {
//    protected void protectedMethod();

    void method();

    public void publicMethod();

    // 默认方法
    default void defaultMethod() {
        System.out.println("This is a default method in the interface.");
    }

}
