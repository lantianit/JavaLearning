
import com.example.java_gobang.game.OnlineUserManager;
import com.example.java_gobang.game.RoomManager;
import com.example.java_gobang.model.User;
import com.example.java_gobang.model.UserMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.UUID;

// 这个类就表示一个游戏房间
class Room {
    private String roomId;

    private User user1;
    private User user2;
    private  int whiteUser;

    private static final int MAX_ROW = 15;
    private static final int MAX_COL = 15;
    private int[][] board = new int[MAX_ROW][MAX_COL];
    private ObjectMapper objectMapper = new ObjectMapper();
    private OnlineUserManager onlineUserManager;
    private RoomManager roomManager;
    private UserMapper userMapper;
}
