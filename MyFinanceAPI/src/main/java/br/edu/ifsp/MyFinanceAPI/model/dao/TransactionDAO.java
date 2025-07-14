package br.edu.ifsp.MyFinanceAPI.model.dao;

import java.util.List;

import br.edu.ifsp.MyFinanceAPI.model.entity.Summary;
import br.edu.ifsp.MyFinanceAPI.model.entity.Transaction;

public interface TransactionDAO {
	boolean insert(Transaction transaction);
	boolean deleteById(int id);
	boolean update(Transaction old_transaction, Transaction new_transaction);
	List<Transaction> getAll();
	Transaction getById(int id);
	Summary getSummaryByCategory(String category);
}
