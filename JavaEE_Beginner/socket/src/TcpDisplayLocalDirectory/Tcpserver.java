package TcpDisplayLocalDirectory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;



class Tcpserver {
    //服务器socket要绑定固定的端口
    private static final int PORT = 8888;

    //本地文件目录要展示的根路径
    private static final String BASE_PATH = "E:/TMP";

    public static void main(String[] args) throws IOException {
        //1.创建一个服务端serversocket，用于收发TCP报文
        ServerSocket server = new ServerSocket(PORT);
        // 不停的等待客户端连接
        while (true) {
            System.out.println("-------------");
            System.out.println("等待客户端建立TCP连接...");
            Socket socket = server.accept();
            System.out.printf("客户端IP: %s%n",socket.getInetAddress().getHostAddress());
            System.out.printf("客户端端口号: %s%n",socket.getPort());
            //5.接收客户端的数据，需要从客户端socket中的输入流获取
            InputStream is = socket.getInputStream();
            //为了方便获取字符串 内容，可以将以上字节流包装为字符流
            BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
            //客户端请求只发送一行数据，我们也只需要读取一行
            String request = br.readLine();
            //6.根据请求处理业务:本地目录根路径+请求路径，作为要展示的目录，列出下一级子文件
            System.out.printf("客户端请求的文件列表路径为: %s%n",BASE_PATH + request);
            File dir = new File(BASE_PATH + request);
            File[] children = dir.listFiles();
            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(os,"UTF-8"));
            if(children != null){
                for (File child : children) {
                    pw.println(child.getName());
                }
            }
            pw.flush() ;
            socket.close();
        }
    }


}