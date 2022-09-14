package com.lawcube.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.lawcube.helper.Interceptor;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(new Interceptor()).addPathPatterns("/**").excludePathPatterns("/UserController/**","/v3/api-docs",
                "/swagger-resources/**",
                "/swagger-ui.html",
                "/webjars/**","/swagger-ui/**");

	}

	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
		if (!registry.hasMappingForPattern("/webjars/**")) {
			registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
		}
		registry.addResourceHandler("/**").addResourceLocations(
				"classpath:/META-INF/resources/",
					"classpath:/resources/", "classpath:/static/", "classpath:/public/"  );
	
	}
}
