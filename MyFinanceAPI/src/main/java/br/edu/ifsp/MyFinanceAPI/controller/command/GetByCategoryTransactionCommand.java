package br.edu.ifsp.MyFinanceAPI.controller.command;

import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;

import br.edu.ifsp.MyFinanceAPI.model.dao.TransactionDAO;
import br.edu.ifsp.MyFinanceAPI.model.dao.TransactionDAOFactory;
import br.edu.ifsp.MyFinanceAPI.model.entity.Transaction;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GetByCategoryTransactionCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			TransactionDAO dao = new TransactionDAOFactory().factory();
			
			String path = request.getPathInfo();
			int id;
			try {
				id = Integer.parseInt(path.replaceAll("[^\\d]", ""));
	        } catch (Exception e) {
	        	response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID da categoria inválido.");
	        	e.printStackTrace();
	        	return;
	        }
	
			List<Transaction> list = dao.getByCategory(id);
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			out.print(new Gson().toJson(list));
			out.flush();
			response.setStatus(HttpServletResponse.SC_OK);
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
	}

}
