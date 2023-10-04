package com.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.model.ProcessMaterial;
import com.model.RawMaterial;

@Component
public class ProcessMaterialDao {
	@Autowired
	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	@Transactional //for making transaction with database like insert, delete, update
	public int insert(ProcessMaterial processMaterial) {
	// insert
	 int i = (Integer) this.hibernateTemplate.save(processMaterial);
	return i;
	}
	
	public List<ProcessMaterial> getAllProcess() {
		List<ProcessMaterial> process = this.hibernateTemplate.loadAll(ProcessMaterial.class);
		return process;
		}
	
	@Transactional
	public void deleteProcess(int id)
	{
		ProcessMaterial processMaterial=this.hibernateTemplate.get(ProcessMaterial.class,id);
		this.hibernateTemplate.delete(processMaterial);
	}
	
	@Transactional
	public void update(ProcessMaterial processMaterial)
	{
				this.hibernateTemplate.update(processMaterial);
	}
	
	public ProcessMaterial getPro(int id)
	{
		ProcessMaterial processMaterial=this.hibernateTemplate.get(ProcessMaterial.class, id);
		return processMaterial;
	}

}
