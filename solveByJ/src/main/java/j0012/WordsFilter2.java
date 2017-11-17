package j0012;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordsFilter2 {

    private static final String PATH = WordsFilter2.class.getClassLoader().getResource("").getPath()+"r0012/";
    private static final String FILE_URL = PATH+"filtered_words.txt";

    public static void main(String[] args) {

        Pattern pattern = Pattern.compile(loadFileToRegexStr());

        Scanner scanner = new Scanner(System.in);
        System.out.println("input:");
        String inputStr = scanner.nextLine();

        Matcher matcher = pattern.matcher(inputStr);
        while(matcher.find()){
            String keyword = matcher.group();
            inputStr = inputStr.replace(keyword,getHoshiWord(keyword));
        }

        System.out.println(inputStr);

    }

    /**
     * 将匹配到的关键字转换为“**”
     * @param keyword
     * @return
     */
    public static String getHoshiWord(String keyword) {
        StringBuilder builder = new StringBuilder("");
        for(int i=0; i<keyword.length(); i++) {
            builder.append("*");
        }
        return builder.toString();
    }

    /**
     * 读取文件并拼装为正则表达式字符串
     * @return
     */
    public static String loadFileToRegexStr(){
        StringBuilder builder = new StringBuilder("");
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(FILE_URL)));

            String temp = "";
            while((temp = br.readLine()) != null){
                builder.append(temp.trim()+"|");
            }

            String regexStr = builder.toString();
            return regexStr.substring(0,regexStr.length()-1);
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if(br != null){
                try {
                    br.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return "";
    }

}
