import java.io.*;
import java.util.Scanner;

class FileCopy {
    public static void main(String[] args) {
        // 实现文件复制.
        // 1. 先输入要复制哪个文件(源文件), 以及把这个文件复制到哪里去(目标文件)~~
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入源文件: ");
        // srcFile 形如 d:/cat.jpg
        File srcFile = new File(scanner.next());
        System.out.println("请输入目标文件: ");
        // destFile 形如 d:/cat2.jpg
        File destFile = new File(scanner.next());
        if (!srcFile.isFile()) {
            System.out.println("输入的源文件有误!");
            return;
        }
        if (!destFile.getParentFile().isDirectory()) {
            System.out.println("输入的目标文件有误!");
            return;
        }
        // 2. 打开源文件, 按照字节读取内容, 依次写入到目标文件中.
        try (FileInputStream inputStream = new FileInputStream(srcFile);
             OutputStream outputStream = new FileOutputStream(destFile);) {

            while (true) {
                int ret = inputStream.read();
                if (ret == -1) {
                    break;
                }
                outputStream.write(ret);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
