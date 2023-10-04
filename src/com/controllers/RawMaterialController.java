package com.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.dao.LogsrawDao;
import com.dao.RawMaterialDao;
import com.model.Logsraw;
import com.model.RawMaterial;

@Controller
public class RawMaterialController {
	
	@RequestMapping("/rawadd")
	public String addrawmaterial(@ModelAttribute  RawMaterial rawMaterial, HttpServletRequest request)
	{
		ApplicationContext context =new ClassPathXmlApplicationContext("config.xml");
		RawMaterialDao rawMaterialDao = (RawMaterialDao) context.getBean("rawMaterialDao", RawMaterialDao.class);
		
		int i = rawMaterialDao.insert(rawMaterial);
		if(i>0)
		{
			return "dashboard";
		}
		else
		{
			return "";
		}
	
	}
	
	@RequestMapping("/Viewraw")
	public String viewrawpage(Model m)
	{
		ApplicationContext context =new ClassPathXmlApplicationContext("config.xml");
		RawMaterialDao rawMaterialDao = (RawMaterialDao) context.getBean("rawMaterialDao");
		List<RawMaterial> rawMaterial = rawMaterialDao.getAllRaw();
		m.addAttribute("rawMaterial", rawMaterial);
		return "viewrawm";
	}
	
	@RequestMapping(path="/delete/{id}", method=RequestMethod.GET)
	public RedirectView deleteRaw(@PathVariable("id") int id, HttpServletRequest request)
	{
		ApplicationContext context =new ClassPathXmlApplicationContext("config.xml");
		RawMaterialDao rawMaterialDao = (RawMaterialDao) context.getBean("rawMaterialDao");
		rawMaterialDao.deleteRaw(id);
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(request.getContextPath()+"/Viewraw");
		return redirectView;
	
}
	
	@RequestMapping(value="/updateR",method=RequestMethod.GET)
	public String updatepage(@RequestParam int id,Model m)
	{
		m.addAttribute("id", id);
		return "updateR";
	}
	@RequestMapping("/insertQ")
	public RedirectView updateRaw(HttpServletRequest request)
	{
		ApplicationContext c=new ClassPathXmlApplicationContext("config.xml");
		 RawMaterialDao rawMaterialDao=(RawMaterialDao) c.getBean("rawMaterialDao");
		 
		
		int new_quantity=Integer.parseInt(request.getParameter("quantity"));
		int id=Integer.parseInt(request.getParameter("id"));
		RawMaterial uprawMaterial=rawMaterialDao.getRaw(id);
		uprawMaterial.setQuantity(uprawMaterial.getQuantity()+new_quantity);
		
		rawMaterialDao.update(uprawMaterial);
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(request.getContextPath()+"/Viewraw");
		return redirectView;
	}
	
	@RequestMapping(value="/issueR",method=RequestMethod.GET)
	public String issuepage(@RequestParam int id,String name,int cost, Model m)
	{
		m.addAttribute("id", id);
		m.addAttribute("name",name);
		m.addAttribute("cost",cost);
		System.out.println(id);
		System.out.println(name);
		System.out.println(cost);
		return "issueR";
	}
	
	@RequestMapping("/issue")
	public RedirectView issueRaw(@ModelAttribute  Logsraw logsRaw, HttpServletRequest request)
	{
		ApplicationContext c=new ClassPathXmlApplicationContext("config.xml");
		 RawMaterialDao rawMaterialDao=(RawMaterialDao) c.getBean("rawMaterialDao");
		LogsrawDao logsrawDao= (LogsrawDao) c.getBean("logsrawDao");
		
		int new_quantity=Integer.parseInt(request.getParameter("quantity"));
		int id=Integer.parseInt(request.getParameter("itemid"));
		
		RawMaterial irawMaterial=rawMaterialDao.getRaw(id);
		if(irawMaterial.getQuantity()>=new_quantity)
		{
		irawMaterial.setQuantity(irawMaterial.getQuantity()-new_quantity);
		rawMaterialDao.update(irawMaterial);
		logsrawDao.insertlog(logsRaw);
		RedirectView redirectView =new RedirectView();
		redirectView.setUrl(request.getContextPath()+"/Viewraw");
		return redirectView;
		
	}
		else{
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(request.getContextPath()+"/issueR");
		return redirectView;
	}
}
	@RequestMapping("/Viewlogs")
	public String viewrawlogpage(Model m)
	{
		ApplicationContext context =new ClassPathXmlApplicationContext("config.xml");
		LogsrawDao logsrawDao = (LogsrawDao) context.getBean("logsrawDao");
		List<Logsraw> logsraw = logsrawDao.getAllRawlog();
		m.addAttribute("logsraw", logsraw);
		return "viewrawlog";
	}
	@RequestMapping("/unavailraw")
	public String unavailraw(Model m)
	{
		ApplicationContext context =new ClassPathXmlApplicationContext("config.xml");
		RawMaterialDao rawMaterialDao = (RawMaterialDao) context.getBean("rawMaterialDao");
		List<RawMaterial> rawMaterial = rawMaterialDao.getAllRaw();
		m.addAttribute("rawMaterial", rawMaterial);
		return "viewunavailableraw";
	}

	@RequestMapping(path="/delete1/{id}", method=RequestMethod.GET)
	public RedirectView deleteRaw1(@PathVariable("id") int id, HttpServletRequest request)
	{
		ApplicationContext context =new ClassPathXmlApplicationContext("config.xml");
		RawMaterialDao rawMaterialDao = (RawMaterialDao) context.getBean("rawMaterialDao");
		rawMaterialDao.deleteRaw(id);
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(request.getContextPath()+"/unavailraw");
		return redirectView;
	
}
}
