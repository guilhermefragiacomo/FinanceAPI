package br.edu.ifsp.MyFinanceAPI.controller.command;

import br.edu.ifsp.MyFinanceAPI.model.dao.CategoryDAO;
import br.edu.ifsp.MyFinanceAPI.model.dao.CategoryDAOFactory;
import br.edu.ifsp.MyFinanceAPI.model.dao.TransactionDAO;
import br.edu.ifsp.MyFinanceAPI.model.dao.TransactionDAOFactory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GetByIdTransactionSummaryCommand implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		TransactionDAO transaction_dao = new TransactionDAOFactory().factory();
		CategoryDAO category_dao = new CategoryDAOFactory().factory();
		
		
	}

}
