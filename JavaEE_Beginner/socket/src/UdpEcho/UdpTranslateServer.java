package UdpEcho;

import java.io.IOException;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

public class UdpTranslateServer extends UdpEchoServer {
    // 翻译是啥? 本质上就是 key -> value
    private Map<String, String> dict = new HashMap<>();

    public UdpTranslateServer(int port) throws SocketException {
        super(port);

        dict.put("cat", "小猫");
        dict.put("dog", "小狗");
        dict.put("fuck", "卧槽");
        // 在这里就可以填入很多很多的内容. 像有道这样的词典程序, 也就无非如此, 只不过这里有一个非常大的哈希表, 包含了几十万个单词.
    }

    // 重写 process 方法, 实现查询哈希表的操作
    @Override
    public String process(String request) {
        return dict.getOrDefault(request, "词在词典中未找到");
    }

    // start 方法和父类完全一样, 不用写了.

    public static void main(String[] args) throws IOException {
        UdpTranslateServer server = new UdpTranslateServer(9090);
        server.start();
    }
}