package cn.zjh.shardingjdbc;

import cn.zjh.shardingjdbc.model.Attendance;
import cn.zjh.shardingjdbc.utils.ImportFromJsonToDB;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
class ShardingJdbcApplicationTests {

    @Test
    void contextLoads() {
        String filePath = "C:\\Users\\zjh\\Desktop\\attendance.json";
        File file = new File(filePath);
        String json = ImportFromJsonToDB.getJsonFile(file);
        JSONObject jsonObject = JSON.parseObject(json);
        JSONArray records = jsonObject.getJSONArray("RECORDS");
        System.out.println(records.get(0).toString());
//        List<Attendance> list = ImportFromJsonToDB.jsonConvertJsonObject(json, Attendance.class);
//        list.forEach(l->l.toString());
    }

    SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");


    @Test
    public void testFormat(){
        String filePath = "C:\\Users\\zjh\\Desktop\\attendance.json";
        File file = new File(filePath);
        String json = ImportFromJsonToDB.getJsonFile(file);
        JSONObject jsonObject = JSON.parseObject(json);
        List<Attendance> list = ImportFromJsonToDB.jsonConvertJsonObject(json, Attendance.class);
        list.forEach(l->l.toString());
    }

    @Test
    public void testDateFormat(){
        try {
            Date date=dateFormat.parse("1/5/2019");
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
