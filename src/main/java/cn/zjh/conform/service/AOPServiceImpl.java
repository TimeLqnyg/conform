package cn.zjh.conform.service;

import cn.zjh.conform.aspect.CustomizeAnno;
import org.springframework.stereotype.Service;

@Service("aopService")
public class AOPServiceImpl implements AOPService {
	@Override
	@CustomizeAnno
	public String perform() {
		System.out.println("performing...");
		return "test aop";
	}

	@Override
	public String perform(Integer num,String msg) {
		return num.toString();
	}
}
