package br.edu.ifsp.MyFinanceAPI.model.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
	private int id;
	private String description;
	private float value;
	private int type;
	private int category;
	private String date;
	
	public Transaction(String description, float value, int type, int category, String date) {
		setDescription(description);
		setValue(value);
		setType(type);
		setCategory(category);
		setDate(date);
	}
	
	public Transaction(int id, String description, float value, int type, int category, String date) {
		setId(id);
		setDescription(description);
		setValue(value);
		setType(type);
		setCategory(category);
		setDate(date);
	}
	
	public int getId() {
		return id;
	}
	private void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	private void setDescription(String description) {
		this.description = description;
	}
	public float getValue() {
		return value;
	}
	private void setValue(float value) {
		this.value = value;
	}
	public int getType() {
		return type;
	}
	private void setType(int type) {
		this.type = type;
	}
	public int getCategory() {
		return category;
	}
	private void setCategory(int category) {
		this.category = category;
	}
	public String getDate() {
		return date;
	}
	private void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Transacao [id=" + id + ", description=" + description + ", value=" + value + ", type=" + type
				+ ", category=" + category + ", date=" + date + "]";
	}
}
