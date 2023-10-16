package file;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * IO 操作演示
 */
public class FileCopyDemo {
    public static void main(String[] args) {
        String sourceFilePath = "source.txt";  // 输入文件路径
        String targetFilePath = "target.txt";  // 输出文件路径
        try {
            // 输入流
            FileInputStream inputStream = new FileInputStream(sourceFilePath);
            // 输出流
            FileOutputStream outputStream = new FileOutputStream(targetFilePath);
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            System.out.println("文件复制成功！");
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
