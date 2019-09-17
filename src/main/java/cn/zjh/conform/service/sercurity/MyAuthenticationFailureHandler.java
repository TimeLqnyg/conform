package cn.zjh.conform.service.sercurity;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
	private ObjectMapper objectMapper=new ObjectMapper();
	@Override
	public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
		Map map=new HashMap();
		map.put("success",false);
		map.put("errorMsg",e.getMessage());
		String json = objectMapper.writeValueAsString(map);

		httpServletResponse.setContentType("text/json;charset=utf-8");
		httpServletResponse.getWriter().write(json);
	}
}
