package TcpDisplayLocalDirectory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


class TcpClient {
    //服务端IP或域名q
    private static final String SERVER_HOST = "localhost";
    //服务端socket进程的端口号
    private static final int SERVER_PORT = 8888;
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("---------------");
            System.out.println("请输入要展示的目录:");
            String request = scanner.nextLine();
            Socket socket = new Socket(SERVER_HOST,SERVER_PORT);
            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(os,"UTF-8"));
            pw.println(request);
            pw.flush();
            System.out.println("接收到服务端响应:");
            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line) ;

            }
            socket.close();
        }
    }
}