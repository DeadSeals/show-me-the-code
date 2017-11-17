package j0004;

import java.io.*;
import java.util.*;

public class CountWords {

    private static final String PATH = CountWords.class.getClassLoader().getResource("").getPath()+"r0004/";
    private static final String FILE_URL = PATH+"test.txt";

    public static void main(String[] args) {

        String fileStr = "";

        fileStr = readFiletoString(FILE_URL);

        String regex = "[\\s\\,\\.\\(\\)]+|\\-{2}";
        String[] wordArr = fileStr.split(regex);

        Map<String,Integer> wordMap = new HashMap<String, Integer>();
        for(String str : wordArr){
            str = str.toLowerCase();
            if(wordMap.containsKey(str)){
                Integer count = wordMap.get(str);
                wordMap.put(str, count+1);
            } else {
                wordMap.put(str, 1);
            }
        }

        List<Word> wordList = wordMapToWordList(wordMap);
        Collections.sort(wordList);
        for(Word word: wordList){
            System.out.println(word.toString());
        }

    }

    /**
     * 输入流读取文件内容
     * @param fileUrl
     * @return
     */
    public static String readFiletoString(String fileUrl){

        File file = new File(fileUrl);
        BufferedReader br = null;

        StringBuilder builder = new StringBuilder("");

        try{
            br = new BufferedReader(
                    new InputStreamReader(new FileInputStream(file)));

            String temp = "";
            while((temp = br.readLine()) != null){
                builder.append(temp);
            }
        } catch (IOException e){
            System.out.println("load file error:"+e.getMessage());
            e.printStackTrace();
            return "";
        } finally {
            if(br != null){
                try {
                    br.close();
                } catch (IOException e){
                    System.out.println("close stream error:"+e.getMessage());
                    e.printStackTrace();
                }
            }
        }
        return builder.toString();
    }

    /**
     * k，v形式的map转换为word对象list
     * @param wordMap
     * @return
     */
    public static List<Word> wordMapToWordList(Map<String,Integer> wordMap){
       List<Word> wordList = new ArrayList<Word>();
       Set<Map.Entry<String,Integer>> entrySet  =  wordMap.entrySet();
       for(Map.Entry<String,Integer> entry : entrySet){
           Word word = new Word(entry.getKey(),entry.getValue());
           wordList.add(word);
       }
       return wordList;
    }

}
