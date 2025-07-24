package br.edu.ifsp.MyFinanceAPI.controller.command;

import java.io.PrintWriter;
import java.util.List;
import java.util.HashMap;

import com.google.gson.Gson;

import br.edu.ifsp.MyFinanceAPI.model.dao.TransactionDAO;
import br.edu.ifsp.MyFinanceAPI.model.dao.TransactionDAOFactory;
import br.edu.ifsp.MyFinanceAPI.model.entity.Transaction;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GetTransactionByFiltersCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			TransactionDAO dao = new TransactionDAOFactory().factory();
			
			String path = request.getPathInfo();
			
			String categoryParam = request.getParameter("category");
		    String typeParam = request.getParameter("type");
		    String monthParam = request.getParameter("month");
		    
		    HashMap<String, Integer> params = new HashMap<>();
	
		    if (categoryParam != null) {
		    	params.put("category_id", Integer.parseInt(categoryParam));
		    }
		    if (typeParam != null) {
		    	params.put("type", Integer.parseInt(typeParam));
		    }
		    if (monthParam != null) {
		    	params.put("MONTH(date)", Integer.parseInt(monthParam));
		    }
	
			List<Transaction> list = dao.getByFilters(params);
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
