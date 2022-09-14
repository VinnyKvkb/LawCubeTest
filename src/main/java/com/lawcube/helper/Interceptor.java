package com.lawcube.helper;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Interceptor  extends HandlerInterceptorAdapter{
	@Autowired
	JwtTokenUtil jwtTokenUtil=new JwtTokenUtil();
	
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        
		Cookie[] cookies = request.getCookies();
		 String token=null;
		if (cookies != null) {
		 for (Cookie cookie : cookies) {
		   if (cookie.getName().equals("jwt")) {
		     //do something
		     //value can be retrieved using #cookie.getValue()
			  token= cookie.getValue();
			  log.info("if token----->"+token);
		    }
		  }
		}else {
			log.info("else----->"+token);
			throw new AccessDeniedException("User Not Authorized");
		}
		
		log.info("token----->"+token);
		try {
			if (token != null&& !token.isEmpty()) {
				String email=  jwtTokenUtil.validateToken(token);
				log.info("email----->"+email);
			        if (email != null) {
			        	log.info("email----->"+email);
			        	  CurrentRequestData.setCurrentUser(email);
			        	//resDto.setEmail(email);
			        	//return new ResponseEntity<Object>(resDto, HttpStatus.OK); 
			        	return true;
			        } else {			          
			        	
						//resDto.setMessage("Not authorized");
						//return new ResponseEntity<Object>(resDto, HttpStatus.FORBIDDEN); 
			        	return false;
			        }
			    
			    } else {		
					//resDto.setMessage("no token provided");
				//	return new ResponseEntity<Object>(resDto, HttpStatus.BAD_REQUEST); 
			    	return false;
			    }
		} catch (Exception e) {
			e.printStackTrace();
			return false;
			//resDto.setMessage("failed to authenticate token");
			//return new ResponseEntity<Object>(resDto, HttpStatus.BAD_REQUEST); 
		}
    }
 
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, 
            Object handler, ModelAndView modelAndView) throws Exception {  
    }
 
   
}

