package j0015;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class WriteCity {

    private static final String PATH = WriteCity.class.getClassLoader().getResource("").getPath()+"r0015/";
    private static final String FILE_URL = PATH+"city.txt";
    private static final String XLS_URL = PATH+"test.xls";

    public static void main(String[] args) {
        String txtStr = readFile2String();
        Map<String,Object> listMap = stringToStructData(txtStr);
        writeWorkbook(getWorkbook(listMap));
    }

    /**
     * 写出工作簿
     * @param workbook
     */
    private static void writeWorkbook(HSSFWorkbook workbook){
        FileOutputStream fos = null;
        try{
            fos = new FileOutputStream(new File(XLS_URL));
            workbook.write(fos);
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if(fos != null){
                try {
                    fos.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 获取工作簿
     * @param dataMap
     * @return
     */
    private static HSSFWorkbook getWorkbook(Map<String,Object> dataMap){
        //新工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        //新工作表
        HSSFSheet sheet = workbook.createSheet();
        //遍历datamap
        Set<Map.Entry<String,Object>> entrySet = dataMap.entrySet();
        int rownum = 0;
        for(Map.Entry<String,Object> entry : entrySet){
            //获取key，value
            String key = entry.getKey();
            Object value = entry.getValue();
            //创建行
            HSSFRow row = sheet.createRow(rownum);
            //第一行第一列为key
            HSSFCell cell0 = row.createCell(0);
            cell0.setCellValue(key);
            //判断值类型
            if(value instanceof List){
                //列表
                List<String> list = (List<String>) value;
                int colnum = 1;
                for(String listvalue : list){
                    HSSFCell cell = row.createCell(colnum);
                    cell.setCellValue(listvalue);
                    colnum++;
                }
            } else if(value instanceof String){
                //字符串
                HSSFCell cell1 = row.createCell(1);
                cell1.setCellValue((String) value);
            }
            rownum++;
        }
        return workbook;
    }

    /**
     * 将字符串转换为格式化数据
     * @param txtStr
     * @return
     */
    private static Map<String,Object> stringToStructData(String txtStr){
        //{"1":["张三",150,120,100],"2":["李四",90,99,95],"3":["王五",60,66,68]}
        //获取大括号内的内容
        String content = txtStr.substring(txtStr.indexOf("{")+1,txtStr.lastIndexOf("}")).trim();
        //根据冒号切分，切分后第一组为key，最后一组为value，中间组根据逗号切分，数组最后一个即为key
        //最后会获得[key,value,key,value]形式的list
        List<String> keyAndValueList = new ArrayList<String>();
        String[] colonArr = content.split(":");
        for(int i=0; i<colonArr.length; i++){
            if(i == 0){
                keyAndValueList.add(colonArr[i].trim());
                continue;
            }
            if(i == colonArr.length-1){
                keyAndValueList.add(colonArr[i].trim());
                continue;
            }
            String value = colonArr[i].substring(0,colonArr[i].lastIndexOf(",")).trim();
            keyAndValueList.add(value);
            String nextKey = colonArr[i].substring(colonArr[i].lastIndexOf(",")+1).trim();
            keyAndValueList.add(nextKey);
        }

        Map<String,Object> listMap = new LinkedHashMap<String, Object>();
        //上次获取的key与本次获取的value组装成map内的key，value
        String lastkey = "";
        for(int i=0; i<keyAndValueList.size(); i++){
            if(i % 2 == 0){
                //key
                lastkey = getRealValue(keyAndValueList.get(i));
            } else {
                //value
                String value = keyAndValueList.get(i);
                if(value.trim().startsWith("[") && value.trim().endsWith("]")){
                    List<String> valueList = getList(value);
                    listMap.put(lastkey,valueList);
                } else {
                    listMap.put(lastkey,getRealValue(value));
                }
            }
        }
        return listMap;
    }

    /**
     * 从形式为[a,b,c]的值中获取列表
     * @param value
     * @return
     */
    private static List<String> getList(String value){
        List<String> list = new ArrayList<String>();
        value = value.substring(value.indexOf("[")+1,value.lastIndexOf("]"));
        String[] valueArr = value.split(",");
        for(String v : valueArr){
            list.add(getRealValue(v));
        }
        return list;
    }

    /**
     * 从引号中获取实际值
     * @return
     */
    private static String getRealValue(String value){
        if(value.trim().startsWith("\"") && value.trim().endsWith("\"")){
            value = value.substring(value.indexOf("\"")+1,value.lastIndexOf("\""));
        }
        return value;
    }


    private static String readFile2String(){
        File file = new File(FILE_URL);
        String txt = "";
        try {
            txt = FileUtils.readFileToString(file,"UTF-8");
        } catch (IOException e){
            e.printStackTrace();
        }
        return txt;
    }

}
