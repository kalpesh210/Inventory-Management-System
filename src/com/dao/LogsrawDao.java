package com.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.model.Logsraw;
import com.model.RawMaterial;

@Component
public class LogsrawDao {
	@Autowired
	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	@Transactional //for making transaction with database like insert, delete, update
	public int insertlog(Logsraw logsRaw) {
	// insert
	 int i = (Integer) this.hibernateTemplate.save(logsRaw);
	return i;
	}
	public Logsraw getiname(String issuer_name)
	{
		Logsraw logsraw=this.hibernateTemplate.get(Logsraw.class, issuer_name);
		return logsraw;
	}
	public Logsraw getitemid(int itemid)
	{
		Logsraw logsraw=this.hibernateTemplate.get(Logsraw.class, itemid);
		return logsraw;
	}
	@Transactional
	public void updatelog(Logsraw logsraw)
	{
				this.hibernateTemplate.update(logsraw);
	}
	public List<Logsraw> getAllRawlog() {
		List<Logsraw> raw = this.hibernateTemplate.loadAll(Logsraw.class);
		return raw;
		}
}
