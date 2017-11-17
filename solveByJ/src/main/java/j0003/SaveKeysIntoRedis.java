package j0003;

import j0001.GenerateKey;
import redis.clients.jedis.Jedis;

import java.util.List;

public class SaveKeysIntoRedis {

    private static final String HOST = "localhost";
    private static final int PORT = 12453;

    public static void main(String[] args) {

        Jedis jedis = new Jedis(HOST,PORT);

        String[] keysArr = GenerateKey.geneKeys(200);

        //list存入
        final String listName = "keylist";
        jedis.del(listName);
        for(String key : keysArr){
            jedis.rpush(listName,key);
        }

        //list取出
        List<String> keysList = jedis.lrange(listName,0,-1);

        System.out.println(keysList);


    }

}
