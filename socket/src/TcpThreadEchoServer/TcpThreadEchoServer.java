package TcpThreadEchoServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * 编写 TCP 版本的多线程服务器
 * 
 */
public class TcpThreadEchoServer {
    // 用于服务器的Socket
    ServerSocket socket;

    // 构造方法指定端口号
    public TcpThreadEchoServer (int port) throws IOException {
        socket = new ServerSocket(port);
    }

    // 启动服务
    public void start () throws IOException {
        System.out.println("服务已启动.");
        while (true) {
            // 服务器启动后就开始接收客户端连接
            Socket clientSocket = socket.accept();
            // 处理接收，每建立一个连接就创建一个线程去处理请求
            Thread t = new Thread(() -> {
                processConnections (clientSocket);
            });
            t.start();
        }
    }

    private void processConnections(Socket clientSocket) {
        // 打印连接信息
        System.out.printf("[%s:%d] 客户端已连接.\n", clientSocket.getInetAddress().toString(), clientSocket.getPort());
        // 读写数据
        try (InputStream inputStream = clientSocket.getInputStream()) {
            try (OutputStream outputStream = clientSocket.getOutputStream()) {
                // 用Scanner处理更方便
                Scanner scanner = new Scanner(inputStream);
                // 循环获取请求
                while (true) {
                    // 如果没有下一个数据就结束
                    if (!scanner.hasNext()) {
                        System.out.printf("[%s:%d] 客户端断开连接.\n", clientSocket.getInetAddress().toString(), clientSocket.getPort());
                        break;
                    }
                    String request = scanner.next();
                    // 处理数据
                    String response = process (request);
                    // 把处理结果响应给客户端
                    // 为了方便起见用PrintWriter把outputStream
                    PrintWriter writer = new PrintWriter(outputStream);
                    writer.println(response);
                    // 强制刷新缓冲区
                    writer.flush();
                    // 打印日志
                    System.out.printf("[%s:%d] request : %s, response : %s\n", clientSocket.getInetAddress().toString(),
                            clientSocket.getPort(), request, response);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭Socket
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String process(String request) {
        // echo直接返回
        return  request;
    }

    public static void main(String[] args) throws IOException {
        TcpThreadEchoServer server = new TcpThreadEchoServer(9090);
        server.start();
    }
}