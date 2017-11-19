package j0013;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DownloadPics {

    private static final String PATH = DownloadPics.class.getClassLoader().getResource("").getPath()+"r0013/";
    private static final String DIR_PATH = PATH+"pics/";
    private static final String WEB_URL = "http://tieba.baidu.com/p/2166231880";
    private static final String FILE_URL = PATH+"html.txt";

    public static void main(String[] args) {

        String html = "";
        File htmlFile = new File(FILE_URL);
        if(htmlFile.exists()){
            html = readHtmlFromFile();
        } else {
            html = getHtmlString();
        }

        System.out.println("截取图片地址：");
        List<String> urlList = new ArrayList<>();
        String regex = "<img[\\s\\n]+pic_type=\"0\"[\\s\\n]+class=\"BDE_Image\"[\\s\\n]+src=\"([\\s\\S]+?)\"";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(html);
        while(matcher.find()){
            String url = matcher.group(1);
            urlList.add(url);
        }
        for(String url : urlList){
            System.out.println(url);
        }
        System.out.println("共"+urlList.size()+"条");

        batchDownload(urlList);
    }

    /**
     * 批量下载图片
     * @param urlList
     */
    public static void batchDownload(List<String> urlList){
        System.out.println("开始下载...");
        int count = 1;
        int sum = urlList.size();
        for(String url : urlList){
            System.out.println("正在下载第"+count+"/"+sum+"个文件...");
            String suffix = url.substring(url.lastIndexOf("."));
            String filename = System.currentTimeMillis()+suffix;
            File file = new File(DIR_PATH+filename);
            try {
                FileUtils.copyURLToFile(new URL(url),file);
            } catch (IOException e){
                System.out.println("下载过程中出错:"+e.getMessage()+"，跳过...");
                continue;
            }
            System.out.println("第"+count+"/"+sum+"个文件下载完成...");
            count++;
        }
        System.out.println("下载结束");
    }

    /**
     * 从文件中读html，方便调试
     * @return
     */
    public static String readHtmlFromFile(){
        try {
            String html = FileUtils.readFileToString(new File(FILE_URL),"UTF-8");
            return html;
        } catch (IOException e){
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 读取html字符串
     * @return
     */
    public static String getHtmlString(){
        BufferedReader reader = null;
        StringBuilder builder = new StringBuilder("");
        try{
            URL url = new URL(WEB_URL);
            URLConnection connection = url.openConnection();
            connection.connect();
            System.out.println("连接成功");

            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String temp = "";
            while((temp = reader.readLine()) != null){
                builder.append(temp);
            }
            System.out.println("读取完成");

            FileUtils.writeStringToFile(new File(FILE_URL),builder.toString(),"UTF-8",false);
            System.out.println("文件已保存");

        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return builder.toString();
    }

}
