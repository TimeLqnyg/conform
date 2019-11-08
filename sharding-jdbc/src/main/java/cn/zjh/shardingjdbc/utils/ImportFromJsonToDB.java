package cn.zjh.shardingjdbc.utils;

import cn.zjh.shardingjdbc.model.Attendance;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ImportFromJsonToDB {

	public static void main(String[] args) {
		String filePath = "C:\\Users\\zjh\\Desktop\\attendance.json";
		File file = new File(filePath);
		String json = ImportFromJsonToDB.getJsonFile(file);
	}

	/**
	 * json文件
	 *
	 * @return json字符串
	 */
	public static String getJsonFile(File file) {

		StringBuffer fileContent = new StringBuffer();

		//region reader方式
		try (FileReader reader = new FileReader(file);
			 BufferedReader bufferedReader = new BufferedReader(reader);) {
			int size=0;
			char[] buffer = new char[1024];
			while ((size=bufferedReader.read(buffer) )!= -1) {
				fileContent.append(new String(buffer,0,size));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//endregion

		//region inputStream方式
//		try (BufferedInputStream input = new BufferedInputStream(new FileInputStream(file));){
//			byte[] buffer=new byte[1024];
//			while ((input.read(buffer)!=-1)){
//				fileContent.append(new String(buffer));
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

		//endregion
//		System.out.println(fileContent.toString());
		return fileContent.toString();
	}

	/**
	 * json转换为JavaBean
	 *
	 * @param json
	 * @param clazz
	 * @param <T>
	 * @return
	 */
	public static <T> T jsonConvertJavaBean(String json, Class<T> clazz) {
		T t = null;
		t = JSON.parseObject(json, clazz);
		//另一种方式转对象
		T dd = JSON.parseObject(json, new TypeReference<T>() {
		});
		return t;
	}

	/**
	 * javaBean转换为json
	 *
	 * @param object
	 * @return
	 */
	public static String javaBeanConvertJson(Object object) {
		String json = "";
		json = JSON.toJSONString(object);
		return json;
	}

	/**
	 * 复杂点的json
	 *
	 * @param json
	 * @param clazz
	 * @param <T>
	 * @return
	 */
	public static <T> List<T> jsonConvertJsonObject(String json, Class<T> clazz) {
		List<T> list = null;
		JSONObject jsonObject = JSON.parseObject(json);
		JSONArray records = jsonObject.getJSONArray("RECORDS");
		if (!records.isEmpty()) {
			list = records.toJavaList(clazz);
		}
		return list;

	}



}
