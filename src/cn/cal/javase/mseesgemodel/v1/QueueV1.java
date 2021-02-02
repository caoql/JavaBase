package cn.cal.javase.mseesgemodel.v1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueueV1 {

    private static Map<String, List<String>> queueMap = new HashMap<>();

    public static List<String> getQueue(String key){
        List<String> list = queueMap.get(key);
        if(list == null) {
            list = new ArrayList<>();
            queueMap.put(key,list);
        }
        return list;
    }

    public static boolean isEmpty(String key) {
        List<String> queue = getQueue(key);
        return queue.size() == 0;
    }

    public static String getMsg(String key){
        String msg = "";
        List<String> queue = getQueue(key);
        if(queue.size() > 0) {
            msg = queue.get(0);
            queue.remove(0);
        }
        return msg;
    }
}
