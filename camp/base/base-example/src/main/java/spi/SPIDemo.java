package spi;

import java.util.ServiceLoader;

public class SPIDemo {
    public static void main(String[] args) {
        ServiceLoader<MessageService> loader = ServiceLoader.load(MessageService.class);
        for (MessageService service : loader) {
            service.sendMessage("Hello, SPI!");
        }
    }
}