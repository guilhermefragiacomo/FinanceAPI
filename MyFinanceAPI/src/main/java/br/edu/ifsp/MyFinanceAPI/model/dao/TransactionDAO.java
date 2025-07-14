package br.edu.ifsp.MyFinanceAPI.model.dao;

import java.util.List;

import br.edu.ifsp.MyFinanceAPI.model.entity.Summary;
import br.edu.ifsp.MyFinanceAPI.model.entity.Transaction;

public interface TransactionDAO {
	boolean insert(Transaction transaction);
	boolean deleteById(int id);
	boolean update(int old_transaction_id, Transaction new_transaction);
	List<Transaction> getAll();
	List<Transaction> getByCategory(int category_id);
	Transaction getById(int id);
	Summary getSummaryByCategory(int category);
}
