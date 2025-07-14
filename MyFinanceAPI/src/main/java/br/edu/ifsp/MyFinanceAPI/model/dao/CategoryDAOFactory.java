package br.edu.ifsp.MyFinanceAPI.model.dao;

public class CategoryDAOFactory {
	public CategoryDAO factory() {
		return new CategoryDAOImpl();
	}
}
