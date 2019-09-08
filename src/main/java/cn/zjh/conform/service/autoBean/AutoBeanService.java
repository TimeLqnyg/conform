package cn.zjh.conform.service.autoBean;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AutoBeanService {

	private Dessert dessert;

	@Autowired
	private IceCream iceCream;

	@Autowired
	@Qualifier("cold")
	public void setDessert(Dessert dessert){
		this.dessert=dessert;
	}

	public String write(){
		return dessert.getName();
	}

	public void beanHash(){
		System.out.println(iceCream.hashCode());
	}

	public void setName(){
		iceCream.setName("cake");
	}

	public String getName(){
		return iceCream.getName();
	}
}
