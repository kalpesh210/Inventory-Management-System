package com.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.model.Logsprocess;
import com.model.Logsraw;

@Component
public class LogsprocessDao {
@Autowired
private HibernateTemplate hibernateTemplate;

public HibernateTemplate getHibernateTemplate() {
	return hibernateTemplate;
}

public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
	this.hibernateTemplate = hibernateTemplate;
}

@Transactional //for making transaction with database like insert, delete, update
public int insertlog(Logsprocess logsProcess) {
// insert
 int i = (Integer) this.hibernateTemplate.save(logsProcess);
return i;
}

public List<Logsprocess> getAllProcesslog() {
	List<Logsprocess> raw = this.hibernateTemplate.loadAll(Logsprocess.class);
	return raw;
	}
}
