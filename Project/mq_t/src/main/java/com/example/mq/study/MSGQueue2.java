import com.example.mq.mqserver.core.Binding;
import com.example.mq.mqserver.core.Exchange;
import com.example.mq.mqserver.core.MSGQueue;
import com.example.mq.mqserver.core.Message;

import java.util.concurrent.ConcurrentHashMap;

class StudyMemoryDataCenter {

    // key 是 exchangeName, value 是 Exchange 对象
    private ConcurrentHashMap<String, Exchange> exchangeMap = new ConcurrentHashMap<>();
    // key 是 queueName, value 是 MSGQueue 对象
    private ConcurrentHashMap<String, MSGQueue> queueMap = new ConcurrentHashMap<>();
    // 第一个 key 是 exchangeName, 第二个 key 是 queueName
    private ConcurrentHashMap<String, ConcurrentHashMap<String, Binding>> bindingsMap = new ConcurrentHashMap<>();
    // key 是 messageId, value 是 Message 对象
    private ConcurrentHashMap<String, Message> messageMap = new ConcurrentHashMap<>();

    private ConcurrentHashMap<String, ConcurrentHashMap<String, Message>> queueMessageWaitAckMap = new ConcurrentHashMap<>();
    
    public void insertExchange(Exchange exchange) {
        exchangeMap.put(exchange.getName(), exchange);
    }
    
    public Exchange getExchange(String exchangeName) {
        return exchangeMap.get(exchangeName);
    }
    
    public void deleteExchange(String exchangeName) {
        exchangeMap.remove(exchangeName);
    }
    
    public void insertBinding(Binding binding) {
        ConcurrentHashMap<String, Binding> bindingMap = bindingsMap.computeIfAbsent(binding.getExchangeName(),
                k -> new ConcurrentHashMap<>());
        synchronized (bindingMap) {
            if (bindingMap.get(binding.getQueueName()) != null) {
                
            }
        }
    }
    
}