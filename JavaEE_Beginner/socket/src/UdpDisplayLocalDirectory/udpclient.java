package UdpDisplayLocalDirectory;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class udpclient {

    //服务端socket地址，包含域名或IP，及端口号
    private static final SocketAddress ADDRESS = new InetSocketAddress("1ocalhost",8888);

    public static void main(String[] args) throws IOException {
        // 4.创建客户端Datagramsocket，开启随机端口就行，可以发送及接收UDP数据报
        DatagramSocket socket = new DatagramSocket();

        //5-1.准备要发送的数据: 这里调整为键盘输入作为发送的内容
        Scanner scanner = new Scanner(System.in) ;
        while (true) {
            System.out.println("--------------------------");
            System.out.println("请输入要展示的目录");
            String request = scanner.nextLine() + "\3";
            byte[] requestData = request.getBytes(StandardCharsets.UTF_8);
            DatagramPacket requestPacket = new DatagramPacket(requestData,requestData.length,ADDRESS);
            socket.send(requestPacket);
            byte[] responseData = new byte[1024];
            DatagramPacket responsePacket = new DatagramPacket(responseData,responseData.length);
            socket.receive(responsePacket);
            System.out.println("该目录下的文件列表为:");
            int next = 0;
            for (int i = 0; i < responseData.length; i++) {
                byte b = responseData[i];
                if (b == '\3') {
                    break;
                }
                if (b == '\n') {
                    String fileName = new String(responseData, next, i-next);
                    System.out.println(fileName);
                    next = i+1;
                }
            }
        }
    }
}