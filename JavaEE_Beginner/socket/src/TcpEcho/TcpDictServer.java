package TcpEcho;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TcpDictServer extends TcpEchoServer{
    // 定义一个字典
    private Map<String, String> dict = new HashMap<>();

    public TcpDictServer(int port) throws IOException {
        super(port);
        // 构造字典的内容
        dict.put("cat", "小猫");
        dict.put("dog", "小狗");
        dict.put("pig", "小猪");
        dict.put("haha", "哈哈");
    }

    @Override
    public String process(String request) {
        return dict.getOrDefault(request,"查无此词！");
    }

    public static void main(String[] args) throws IOException {
        TcpDictServer server = new TcpDictServer(9090);
        server.start();
    }
}