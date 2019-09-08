package cn.zjh.conform.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class AOPConfig {

	/**
	 * 自定义的注解 切面
	 * 		指定的注解
	 */
	@Pointcut("@annotation(cn.zjh.conform.aspect.CustomizeAnno)")
	public void custom_annotation(){}

	@Pointcut("execution(* cn.zjh.conform.service.AOPService.perform(..))&&within(cn.zjh.conform.service.*)")
	public void performance(){}

	@Pointcut("execution(String cn.zjh.conform.service.AOPService.perform(Integer,String))&&args(num,msg)" )
	public void performance02(Integer num,String msg){}

	private final static String pointCut="execution(String cn.zjh.conform.service.AOPService.perform(Integer,String))";

	@Around("performance()")
	public Object watchPerformance(ProceedingJoinPoint joinPoint){
		Object object=null;
		try {
			System.out.println("perform start");
			object=joinPoint.proceed();
			System.out.println("perform end");
		}catch (Throwable e){
			System.out.println("perform throwable");
		}finally {
			return object;
		}
	}

	@Before("performance02(num,msg)")
	public void beforePerformance02(Integer num,String msg){
		System.out.println(msg+num);
	}


	@Around(value = pointCut)
	public Object watchPerformance02(ProceedingJoinPoint joinPoint){

		Object object=null;
		try {
			Object[] args= joinPoint.getArgs();
			System.out.println("perform start");
			object=joinPoint.proceed();
			System.out.println(args[0].toString()+args[1].toString());
		}catch (Throwable e){
			System.out.println("perform throwable");
		}finally {
			return object;
		}

	}

	@Before("custom_annotation()")
	public void testCustomAnnotation(){
		System.out.println("test customize annotation");
	}
}
