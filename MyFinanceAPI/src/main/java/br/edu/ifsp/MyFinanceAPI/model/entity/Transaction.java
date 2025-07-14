package br.edu.ifsp.MyFinanceAPI.model.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
	private int id;
	private String description;
	private float value;
	private String type;
	private int category;
	private LocalDateTime date;
	
	private DateTimeFormatter formatter;
	
	public Transaction(String description, float value, String type, int category, LocalDateTime date) {
		setDescription(description);
		setValue(value);
		setType(type);
		setCategory(category);
		setDate(date);
		
		formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	}
	
	public Transaction(int id, String description, float value, String type, int category, LocalDateTime date) {
		setId(id);
		setDescription(description);
		setValue(value);
		setType(type);
		setCategory(category);
		setDate(date);
		
		formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	}
	
	public String getFormatedDate() {
		return date.format(formatter);
	}
	
	public static LocalDateTime getFormatedString(String dateString) {
		return LocalDateTime.parse(dateString);
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
	public String getType() {
		return type;
	}
	private void setType(String type) {
		this.type = type;
	}
	public int getCategory() {
		return category;
	}
	private void setCategory(int category) {
		this.category = category;
	}
	public LocalDateTime getDate() {
		return date;
	}
	private void setDate(LocalDateTime date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Transacao [id=" + id + ", description=" + description + ", value=" + value + ", type=" + type
				+ ", category=" + category + ", date=" + date + "]";
	}
}
