package cn.cal.javase.mseesgemodel.v1;

import java.util.List;

public class ConsumerV1 {

    public void consum(String key) {


        while (true) {

            if(QueueV1.isEmpty(key)) {
                sleep();
            } else {
                String msg = QueueV1.getMsg(key);
                System.out.println("取到消息============"+msg);
            }
        }

    }

    private void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
