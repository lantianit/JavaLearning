import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TcpEchoServer {
    // 代码中会涉及到多个 socket 对象. 使用不同的名字来区分.
    private ServerSocket listenSocket = null;

    public TcpEchoServer(int port) throws IOException {
        listenSocket = new ServerSocket(port);
    }

    public void start() throws IOException {
        System.out.println("服务器启动!");
        ExecutorService service = Executors.newCachedThreadPool();
        while (true) {
            // 1. 先调用 accept 来接受客户端的连接.
            Socket clientSocket = listenSocket.accept();
            // 2. 再处理这个连接. 这里应该要使用多线程. 每个客户端连上来都分配一个新的线程负责处理~
            //    此处使用多线程, 雀食能解决问题, 但是会导致频繁创建销毁多次线程!!
//            Thread t = new Thread(() -> {
//                try {
//                    processConnection(clientSocket);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            });
//            t.start();

            service.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        processConnection(clientSocket);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    private void processConnection(Socket clientSocket) throws IOException {
        System.out.printf("[%s:%d] 客户端上线!\n", clientSocket.getInetAddress().toString(), clientSocket.getPort());
        // 接下来就处理客户端的请求了.
        try (InputStream inputStream = clientSocket.getInputStream();
             OutputStream outputStream = clientSocket.getOutputStream()) {
            while (true) {
                // 1. 读取请求并解析.
                Scanner scanner = new Scanner(inputStream);
                if (!scanner.hasNext()) {
                    // 读完了, 连接可以断开了.
                    System.out.printf("[%s:%d] 客户端下线!\n", clientSocket.getInetAddress().toString(),
                            clientSocket.getPort());
                    break;
                }
                String request = scanner.next();
                // 2. 根据请求计算响应
                String response = process(request);
                // 3. 把响应写回给客户端
                PrintWriter printWriter = new PrintWriter(outputStream);
                printWriter.println(response);
                // 刷新缓冲区确保数据确实是通过网卡发送出去了.
                printWriter.flush();

                System.out.printf("[%s:%d] req: %s; resp: %s\n", clientSocket.getInetAddress().toString(),
                        clientSocket.getPort(), request, response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 为啥这个地方要关闭 socket ? 而前面的 listenSocket 以及 udp 程序中的 socket 为啥就没 close??
            clientSocket.close();
        }
    }

    public String process(String request) {
        return request;
    }

    public static void main(String[] args) throws IOException {
        TcpEchoServer server = new TcpEchoServer(9090);
        server.start();
    }
}
