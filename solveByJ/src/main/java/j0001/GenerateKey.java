package j0001;

import java.util.Arrays;
import java.util.UUID;

public class GenerateKey {

    public static void main(String[] args) {
       String[] keysArr =  geneKeys(200);
       System.out.println(Arrays.toString(keysArr));
    }

    /**
     * 生成一组UUID
     * @param num
     * @return
     */
    public static String[] geneKeys(int num){
        String[] keys = new String[num];
        for(int i=0;i<num;i++){
            keys[i] = UUID.randomUUID().toString();
        }
        return keys;
    }

}
