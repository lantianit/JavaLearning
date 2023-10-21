import java.io.Serializable;

class S {
    int x;
}

class T implements Serializable {
    S s;
}

class s {
    public static void main(String[] args) {
        T t = new T();
        System.out.println(t.s);
    }
}
