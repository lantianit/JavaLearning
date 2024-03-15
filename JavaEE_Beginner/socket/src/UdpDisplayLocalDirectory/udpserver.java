package UdpDisplayLocalDirectory;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;

public class udpserver {

    //服务器socket要绑定固定的端口
    private static final int PORT = 8888;

    //本地文件目录要展示的根路径
    private static final String BASE_PATH = "E:/TMP";

    public static void main(String[] args) throws IOException {
        // 1.创建服务端Datagramsocket，指定端口，可以发送及接收UDP数据报
        DatagramSocket socket = new DatagramSocket(PORT);
        //不停的接收客户端udp数据报
        while (true) {
            byte[] requestData = new byte[1024];
            DatagramPacket requestPacket = new DatagramPacket(requestData,requestData.length);
            System.out.println("------------------------");
            System.out.println("等待接收UDP数据包...");
            socket.receive(requestPacket);
            System.out.printf("客户端IP：%s%n",requestPacket.getAddress().getHostAddress());
            System.out.printf("客户端端口号：%s%n",requestPacket.getPort());
            // 7.接收到的数据作为请求，根据请求数据执行业务，并返回响应
            for (int i = 0; i < requestData.length; i++) {
                byte b = requestData[i];
                if (b == '\3') {
                    String request = new String(requestData,0,i);
                    System.out.printf("客户端请求的文件列表路径为：%s%n",BASE_PATH+request);
                    File dir = new File((BASE_PATH + request));
                    File[] children = dir.listFiles();
                    StringBuilder response = new StringBuilder();
                    if (children != null) {
                        for (File child : children) {
                            response.append(child.getName() + "\n");
                        }
                    }
                    response.append("\3");
                    byte[] responseData = response.toString().getBytes(StandardCharsets.UTF_8);
                    DatagramPacket responsePacket = new DatagramPacket(responseData, responseData.length,requestPacket.getSocketAddress());
                    socket.send(responsePacket);
                    break;
                }
            }
        }
    }
}