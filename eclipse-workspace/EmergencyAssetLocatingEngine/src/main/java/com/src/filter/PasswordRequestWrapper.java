package com.src.filter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class PasswordRequestWrapper extends HttpServletRequestWrapper {
	
	private String hashedPwd;

	public PasswordRequestWrapper(HttpServletRequest request,String hashedPwd) {
		super(request);
		this.hashedPwd = hashedPwd;
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getParameter(String name) {
		// TODO Auto-generated method stub
		if ("pwd".equals(name)) {
            return hashedPwd;
        }
        
		return super.getParameter(name);
	}

	@Override
	public Map<String, String[]> getParameterMap() {
		// TODO Auto-generated method stub
		Map<String, String[]> map = new HashMap<>(super.getParameterMap());
        map.put("pwd", new String[]{hashedPwd});
        return Collections.unmodifiableMap(map);
		
	}
	

}
