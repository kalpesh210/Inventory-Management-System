package com.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.model.RawMaterial;
@Component
public class RawMaterialDao {
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	@Transactional //for making transaction with database like insert, delete, update
	public int insert(RawMaterial rawMaterial) {
	// insert
	 int i = (Integer) this.hibernateTemplate.save(rawMaterial);
	return i;
	}
	
	public List<RawMaterial> getAllRaw() {
		List<RawMaterial> raw = this.hibernateTemplate.loadAll(RawMaterial.class);
		return raw;
		}
	
	@Transactional
	public void deleteRaw(int id)
	{
		RawMaterial rawMaterial=this.hibernateTemplate.get(RawMaterial.class,id);
		this.hibernateTemplate.delete(rawMaterial);
	}
	
	@Transactional
	public void update(RawMaterial rawMaterial)
	{
				this.hibernateTemplate.update(rawMaterial);
	}
	
	public RawMaterial getRaw(int id)
	{
		RawMaterial rawMaterial=this.hibernateTemplate.get(RawMaterial.class, id);
		return rawMaterial;
	}

	

}
