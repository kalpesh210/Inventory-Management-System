package com.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;
@Entity
@Table(name="logs_raw")
@Component
public class Logsraw {
	@Id
	private int id;
	private String name;
	private int quantity;
	private String issuer_name;
	private int itemid;
	private String date;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getIssuer_name() {
		return issuer_name;
	}
	public void setIssuer_name(String issuer_name) {
		this.issuer_name = issuer_name;
	}
	public int getItemid() {
		return itemid;
	}
	public void setItemid(int itemid) {
		this.itemid = itemid;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Logsraw(int id, String name, int quantity, String issuer_name, int itemid, String date) {
		super();
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.issuer_name = issuer_name;
		this.itemid = itemid;
		this.date = date;
	}
	public Logsraw() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Logsraw [id=" + id + ", name=" + name + ", quantity=" + quantity + ", issuer_name=" + issuer_name
				+ ", itemid=" + itemid + ", date=" + date + "]";
	}
		
}
