package cn.zjh.conform.service;


import cn.zjh.conform.aspect.CustomizeAnno;

public interface AOPService {
	String perform();

	String perform(Integer num,String msg);
}
