import java.io.File;
import java.io.IOException;
public class Mkdirs {
    public static void main(String[] args) throws IOException {
        File dir = new File("some-parent\\some-dir"); // some-parent 和 somedir 都不存在
        System.out.println(dir.isDirectory());
        System.out.println(dir.isFile());
        System.out.println(dir.mkdir());
        System.out.println(dir.mkdirs());
        System.out.println(dir.isDirectory());
        System.out.println(dir.isFile());
   }
}