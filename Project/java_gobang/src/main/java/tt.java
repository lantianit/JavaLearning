

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

@Component
class OnlineUserManager {
    private ConcurrentHashMap<Integer,WebSocketSession> gameHall
}
