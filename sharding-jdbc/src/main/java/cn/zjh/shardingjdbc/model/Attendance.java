package cn.zjh.shardingjdbc.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

@Data
public class Attendance {
	private Integer id;
	private String employeeId;
	private Integer realAttendance;
	private Integer shouldAttendance;
	private Boolean flag;
	@JSONField(format = "d/M/yyyy HH:mm:ss")
	private Date createDate;
}
