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
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			TransactionDAO dao = new TransactionDAOFactory().factory();
	
			String path = request.getPathInfo();
			int id;
			try {
				id = Integer.parseInt(path.replaceAll("[^\\d]", ""));
	        } catch (Exception e) {
	        	response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID da transação inválido.");
	        	e.printStackTrace();
	        	return;
	        }
	
			if (dao.deleteById(id)) {
				response.setStatus(HttpServletResponse.SC_CREATED);
	        } else {
	        	response.setStatus(HttpServletResponse.SC_NOT_FOUND);
	        }
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
	}

}
