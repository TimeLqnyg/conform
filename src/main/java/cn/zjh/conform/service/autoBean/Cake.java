package cn.zjh.conform.service.autoBean;

import org.springframework.stereotype.Component;

@Component
public class Cake implements Dessert {

	private String name;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name=name;
	}
}