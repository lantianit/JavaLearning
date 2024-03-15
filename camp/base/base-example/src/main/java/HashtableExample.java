import java.util.Hashtable;

public class HashtableExample {
    public static void main(String[] args) {
        // null -> 值本身为 null | 未赋值
        Hashtable hashtable = new Hashtable();
        hashtable.put(null, "javacn.site");
//        hashtable.put("javacn.site", null);
    }
}
