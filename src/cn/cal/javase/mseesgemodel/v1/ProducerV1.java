package cn.cal.javase.mseesgemodel.v1;

import java.util.List;

public class ProducerV1 {

    private String key;

    public ProducerV1(String key) {
        this.key = key;
    }

    public void product(String msg){
        List<String> v1 = QueueV1.getQueue(key);
        v1.add(msg);
        System.out.println("发送消息============"+msg);
    }

}
