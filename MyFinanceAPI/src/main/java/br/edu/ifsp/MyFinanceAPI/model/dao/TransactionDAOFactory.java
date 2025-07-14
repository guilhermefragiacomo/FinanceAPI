package br.edu.ifsp.MyFinanceAPI.model.dao;

public class TransactionDAOFactory {
	public TransactionDAO factory() {
		return new TransactionDAOImpl();
	}
}
