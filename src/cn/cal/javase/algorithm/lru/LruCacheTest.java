package cn.cal.javase.algorithm.lru;

public class LruCacheTest {

    // 画图辅助理解
    public static void main(String[] args) {
        LruCache<String, Integer> cache = new LruCache<>(3);

        cache.put("A", 1);
        cache.put("B", null);
        cache.put("C", 3);
        cache.put("D", 4);
        System.out.println(cache);

        System.out.println(cache.get("B"));
        System.out.println(cache.put("C", 33));
        cache.put("E", 5);
        System.out.println(cache);

        System.out.println(cache.remove("C"));
        cache.put("F", 6);
        cache.put("G", 7);
        System.out.println(cache);
    }

}
