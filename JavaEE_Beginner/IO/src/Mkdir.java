import java.io.File;
import java.io.IOException;
public class Mkdir {
    public static void main(String[] args) throws IOException {
        File dir = new File("some-dir"); // 要求该目录不存在，才能看到相同的现象
        System.out.println(dir.isDirectory());
        System.out.println(dir.isFile());
        System.out.println(dir.mkdir());
        System.out.println(dir.isDirectory());
        System.out.println(dir.isFile());
   }
}