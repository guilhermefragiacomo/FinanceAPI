package br.edu.ifsp.MyFinanceAPI.controller.command;

import java.io.PrintWriter;

import com.google.gson.Gson;

import br.edu.ifsp.MyFinanceAPI.model.dao.TransactionDAO;
import br.edu.ifsp.MyFinanceAPI.model.dao.TransactionDAOFactory;
import br.edu.ifsp.MyFinanceAPI.model.entity.Transaction;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteTransactionCommand implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		TransactionDAO dao = new TransactionDAOFactory().factory();

		String path = request.getPathInfo();
		int id = Integer.parseInt(path.replaceAll("[^\\d]", ""));

		if (dao.deleteById(id)) {
			response.setStatus(HttpServletResponse.SC_CREATED);
        } else {
        	response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
	}

}
