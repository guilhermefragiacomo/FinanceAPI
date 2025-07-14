package br.edu.ifsp.MyFinanceAPI.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;

import br.edu.ifsp.MyFinanceAPI.model.dao.*;
import br.edu.ifsp.MyFinanceAPI.model.entity.Transaction;

public class GetTransactionsCommand implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		TransactionDAO dao = new TransactionDAOFactory().factory();
		List<Transaction> all = dao.getAll();
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(new Gson().toJson(all));
		out.flush();
	}

}
