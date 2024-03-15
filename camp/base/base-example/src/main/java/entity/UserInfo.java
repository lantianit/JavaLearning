package entity;

import java.io.Serial;
import java.io.Serializable;

public class UserInfo implements Serializable {
    @Serial
    private static final long serialVersionUID = 8889201439088580487L;
    private int id;
    private String name;
    private String password;
    // 不被序列化的字段
    private transient String photo;
    // 忽略 Setter 和 Getter
}
