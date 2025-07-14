package br.edu.ifsp.MyFinanceAPI.model.entity;

public class Summary {
	private float total_revenue;
	private float total_expense;
	private String category;
	private float current_balance;
	
	public Summary(float total_revenue, float total_expense, String category, float current_balance) {
		setTotal_revenue(total_revenue);
		setTotal_expense(total_expense);
		setCategory(category);
		setCurrent_balance(current_balance);
	}
	
	public float getTotal_revenue() {
		return total_revenue;
	}
	private void setTotal_revenue(float total_revenue) {
		this.total_revenue = total_revenue;
	}
	public float getTotal_expense() {
		return total_expense;
	}
	private void setTotal_expense(float total_expense) {
		this.total_expense = total_expense;
	}
	public String getCategory() {
		return category;
	}
	private void setCategory(String category) {
		this.category = category;
	}
	public float getCurrent_balance() {
		return current_balance;
	}
	private void setCurrent_balance(float current_balance) {
		this.current_balance = current_balance;
	}
}
