package br.edu.ifsp.MyFinanceAPI.controller.command;

import java.io.PrintWriter;

import com.google.gson.Gson;

import br.edu.ifsp.MyFinanceAPI.model.dao.TransactionDAO;
import br.edu.ifsp.MyFinanceAPI.model.dao.TransactionDAOFactory;
import br.edu.ifsp.MyFinanceAPI.model.entity.Summary;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GetByIdTransactionSummaryCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		TransactionDAO dao = new TransactionDAOFactory().factory();
		
		String path = request.getPathInfo();
		int id = Integer.parseInt(path.replaceAll("[^\\d]", ""));

		Summary summary = dao.getSummaryByCategory(id);
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(new Gson().toJson(summary));
		out.flush();
	}

}
