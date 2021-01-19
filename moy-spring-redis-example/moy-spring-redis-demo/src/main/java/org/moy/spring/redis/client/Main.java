package org.moy.spring.redis.client;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        HashSet<String> hashSet = new HashSet<>();
        hashSet.add("10.118.159.20:16370");
        hashSet.add("10.118.159.12:36370");
        hashSet.add("10.118.159.22:46370");
        JedisSentinelPool jedisSentinelPool =
                new JedisSentinelPool("master_rt",
                        hashSet,
                        jedisPoolConfig,
                        2000,
                        "zaq11@WSX",
                        10);

        Jedis resource = jedisSentinelPool.getResource();
        Map<String, String> map = resource.hgetAll("twms_review_status_tray_code_QG0110000125");
        System.out.println(map);


//        test();
    }

    private static void test() {
        try (JedisPool pool = new JedisPool();
             Jedis jedis = pool.getResource();) {
            String lcCode = "HAZ1";
            String namespace = lcCode + ":";
            for (int i = 0; i <= 20; i++) {
                String trayCode = "trayCode" + i;
                jedis.zadd(lcCode, System.currentTimeMillis() + i, trayCode);
                Map<String, String> hash = new HashMap<>();
                hash.put("status1", "1");
                hash.put("status2", "1");
                hash.put("status3", "1");
                hash.put("trayCode", trayCode);
                jedis.hmset(namespace + trayCode, hash);
            }

            Set<String> lcCodeList = jedis.zrange(lcCode, -10, -1);
            System.out.println(lcCodeList);
            for (String each : lcCodeList) {
                System.out.println(jedis.hgetAll(namespace + each));
            }
        }
    }
}
