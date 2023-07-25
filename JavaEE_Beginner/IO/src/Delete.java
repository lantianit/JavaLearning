import java.io.File;
import java.io.IOException;
public class Delete {
    public static void main(String[] args) throws IOException {
        File file = new File("some-file.txt"); // 要求该文件不存在，才能看到相同的现象
        System.out.println(file.exists());
        System.out.println(file.createNewFile());
        System.out.println(file.exists());
        System.out.println(file.delete());
        System.out.println(file.exists());
   }
}