package cn.zjh.simplewebsocket.service.rpc.server.info;

import java.io.Serializable;

public class RpcInfo implements Serializable {

	private static final long serialVersionUID = -1085607212575446777L;
	/**
	 * 报名
	 * 类名
	 * 方法名
	 * 参数
	 */

	private String packageName;
	private String className;
	private String methodName;
	private Object[] parameters;

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public Object[] getParameters() {
		return parameters;
	}

	public void setParameters(Object[] parameters) {
		this.parameters = parameters;
	}
}
