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

import com.dao.LogsprocessDao;
import com.dao.LogsrawDao;
import com.dao.ProcessMaterialDao;
import com.dao.RawMaterialDao;
import com.model.Logsprocess;
import com.model.Logsraw;
import com.model.ProcessMaterial;
import com.model.RawMaterial;

@Controller
public class ProcessMaterialController {
	@RequestMapping("/proadd")
	public String addpromaterial(@ModelAttribute  ProcessMaterial processMaterial, HttpServletRequest request)
	{
		ApplicationContext context =new ClassPathXmlApplicationContext("config.xml");
		ProcessMaterialDao processMaterialDao = (ProcessMaterialDao) context.getBean("processMaterialDao", ProcessMaterialDao.class);
		
		int i = processMaterialDao.insert(processMaterial);
		if(i>0)
		{
			return "dashboard";
		}
		else
		{
			return "";
		}
	}
	
	@RequestMapping("/Viewprocess")
	public String viewpropage(Model m)
	{
		ApplicationContext context =new ClassPathXmlApplicationContext("config.xml");
		ProcessMaterialDao processMaterialDao = (ProcessMaterialDao) context.getBean("processMaterialDao");
		List<ProcessMaterial> processMaterial = processMaterialDao.getAllProcess();
		m.addAttribute("processMaterial", processMaterial);
		return "viewpro";
	}
	
	@RequestMapping(path="/deletepro/{id}", method=RequestMethod.GET)
	public RedirectView deleteRaw(@PathVariable("id") int id, HttpServletRequest request)
	{
		ApplicationContext context =new ClassPathXmlApplicationContext("config.xml");
		ProcessMaterialDao processMaterialDao = (ProcessMaterialDao) context.getBean("processMaterialDao");
		processMaterialDao.deleteProcess(id);
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(request.getContextPath()+"/Viewprocess");
		return redirectView;
	
}
	@RequestMapping(value="/updateP",method=RequestMethod.GET)
	public String updatepage(@RequestParam int id,Model m)
	{
		m.addAttribute("id", id);
		return "updateP";
	}
	@RequestMapping("/insertP")
	public RedirectView updatePro(HttpServletRequest request)
	{
		ApplicationContext c=new ClassPathXmlApplicationContext("config.xml");
		 ProcessMaterialDao processMaterialDao=(ProcessMaterialDao) c.getBean("processMaterialDao");
		 
		
		int new_quantity=Integer.parseInt(request.getParameter("quantity"));
		int id=Integer.parseInt(request.getParameter("id"));
		ProcessMaterial upproMaterial=processMaterialDao.getPro(id);
		upproMaterial.setQuantity(upproMaterial.getQuantity()+new_quantity);
		
		processMaterialDao.update(upproMaterial);
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(request.getContextPath()+"/Viewprocess");
		return redirectView;
	}
	
	@RequestMapping(value="/issueP",method=RequestMethod.GET)
	public String issuepage(@RequestParam int id,String name, Model m)
	{
		m.addAttribute("id", id);
		m.addAttribute("name",name);
		System.out.println(id);
		System.out.println(name);
		return "issueP";
	}
	
	@RequestMapping("/issuep")
	public RedirectView issuePro(@ModelAttribute  Logsprocess logsProcess, HttpServletRequest request)
	{
		ApplicationContext c=new ClassPathXmlApplicationContext("config.xml");
		 ProcessMaterialDao processMaterialDao=(ProcessMaterialDao) c.getBean("processMaterialDao");
		LogsprocessDao logsprocessDao= (LogsprocessDao) c.getBean("logsprocessDao");
		
		int new_quantity=Integer.parseInt(request.getParameter("quantity"));
		int id=Integer.parseInt(request.getParameter("itemid"));
		
		ProcessMaterial irawMaterial=processMaterialDao.getPro(id);
		if(irawMaterial.getQuantity()>=new_quantity)
		{
		irawMaterial.setQuantity(irawMaterial.getQuantity()-new_quantity);
		processMaterialDao.update(irawMaterial);
		logsprocessDao.insertlog(logsProcess);
		RedirectView redirectView =new RedirectView();
		redirectView.setUrl(request.getContextPath()+"/Viewprocess");
		return redirectView;
		
	}
		else{
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(request.getContextPath()+"/issueP");
		return redirectView;
	}
}
	
	@RequestMapping("/Viewlogspro")
	public String viewrawlogpage(Model m)
	{
		ApplicationContext context =new ClassPathXmlApplicationContext("config.xml");
		LogsprocessDao logsprocessDao = (LogsprocessDao) context.getBean("logsprocessDao");
		List<Logsprocess> logsprocess = logsprocessDao.getAllProcesslog();
		m.addAttribute("logsprocess", logsprocess);
		return "viewprolog";
	}
	@RequestMapping("/unavailpro")
	public String unavailpro(Model m)
	{
		ApplicationContext context =new ClassPathXmlApplicationContext("config.xml");
		ProcessMaterialDao processMaterialDao = (ProcessMaterialDao) context.getBean("processMaterialDao");
		List<ProcessMaterial> processMaterial = processMaterialDao.getAllProcess();
		m.addAttribute("processMaterial", processMaterial);
		return "viewunavailablepro";
	}

	@RequestMapping(path="/deletepro1/{id}", method=RequestMethod.GET)
	public RedirectView deleteRaw1(@PathVariable("id") int id, HttpServletRequest request)
	{
		ApplicationContext context =new ClassPathXmlApplicationContext("config.xml");
		ProcessMaterialDao processMaterialDao = (ProcessMaterialDao) context.getBean("processMaterialDao");
		processMaterialDao.deleteProcess(id);
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(request.getContextPath()+"/unavailpro");
		return redirectView;
	
}
}
