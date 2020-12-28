package org.moy.spring.redis.client;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Main {

    public static void main(String[] args) {


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
