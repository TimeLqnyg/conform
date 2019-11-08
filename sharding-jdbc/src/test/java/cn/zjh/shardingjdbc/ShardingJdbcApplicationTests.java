package cn.zjh.shardingjdbc;

import cn.zjh.shardingjdbc.model.Attendance;
import cn.zjh.shardingjdbc.model.User;
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
//        String filePath = "C:\\Users\\zjh\\Desktop\\attendance.json";
        String filePath="C:\\Users\\user001\\Desktop\\t_user0.json";
        File file = new File(filePath);
        String json = ImportFromJsonToDB.getJsonFile(file);
        JSONObject jsonObject = JSON.parseObject(json);
        JSONArray records = jsonObject.getJSONArray("RECORDS");
//        System.out.println(records.get(0).toString());
        List<User> list = ImportFromJsonToDB.jsonConvertJsonObject(json, User.class);
        list.forEach(l->{
            System.out.println(l.toString());
        });
    }

    SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");


    @Test
    public void testFormat(){
//        String filePath = "C:\\Users\\zjh\\Desktop\\attendance.json";
        String filePath="C:\\Users\\user001\\Desktop\\t_user0.json";
        File file = new File(filePath);
        String json = ImportFromJsonToDB.getJsonFile(file);
        System.out.println(json);
//        JSONObject jsonObject = JSON.parseObject(json);
//        List<User> list = ImportFromJsonToDB.jsonConvertJsonObject(json, User.class);
//        list.forEach(l->l.toString());
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

    @Test
    public void testJSONField(){
//        User user=new User();
//        user.setName("test");

        Attendance attendance=new Attendance();
        attendance.setCreateDate(new Date());
        System.out.println(ImportFromJsonToDB.javaBeanConvertJson(attendance));
    }

}
