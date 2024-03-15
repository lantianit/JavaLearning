import java.io.File;
import java.io.IOException;
public class GetIO {
    public static void main(String[] args) throws IOException {
        File file = new File("..\\hello-world.txt"); // 并不要求该文件真实存在
        System.out.println(file.getParent());
        System.out.println(file.getName());
        System.out.println(file.getPath());
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getCanonicalPath());
    }
}