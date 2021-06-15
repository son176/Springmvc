package com.shop.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
@Service
public class SessionUtils {
	 public static void addSession(HttpServletRequest request,String name,Object value) {
			HttpSession session=request.getSession();
	        session.setAttribute(name, value);
	    }
	    public static Object getSession(HttpServletRequest request,String name) {
	        HttpSession session=request.getSession();
	        return session.getAttribute(name);
	    }
	    public static void invalidate(HttpServletRequest request) {
	        HttpSession session=request.getSession();
	        session.removeAttribute("userId");
	        session.invalidate();
	    }
	    public static boolean isLogin(HttpServletRequest request) {
	        return getSession(request, "userId") != null;
	    }
	    public static String getLoginUser(HttpServletRequest request) {
	        Object userId=getSession(request, "userId");
	        return userId == null? null : userId.toString();
	    }
}
