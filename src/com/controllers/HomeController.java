package com.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dao.RawMaterialDao;
import com.model.RawMaterial;
@Controller
public class HomeController {
	@RequestMapping("/")
	public String home()
	{
	System.out.println("This is home url..");
	return "index";
	}
	@RequestMapping("/dashboard")
	public String dashhome()
	{
	System.out.println("This is home url..");
	return "dashboard";
	}
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request)
	{
	String email = request.getParameter("email");
	String pass = request.getParameter("pass");
	if(email.equals("kalpesh@gmail.com") && pass.equals("1234"))
	{
			return "dashboard";
	}
	else
	{
		return "index";
	}
	}
	
	@RequestMapping("/Addraw")
	public String addraw()
	{
		return "addraw";
	}
	
	@RequestMapping("/Addprocess")
	public String addprocess()
	{
		return "addpro";
	}
	
	
	
}
