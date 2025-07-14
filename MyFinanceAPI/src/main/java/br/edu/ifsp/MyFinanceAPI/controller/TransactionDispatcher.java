package br.edu.ifsp.MyFinanceAPI.controller;

import java.util.HashMap;
import java.util.Map;

import br.edu.ifsp.MyFinanceAPI.controller.command.Command;
import br.edu.ifsp.MyFinanceAPI.controller.command.DeleteTransactionCommand;
import br.edu.ifsp.MyFinanceAPI.controller.command.GetByCategoryTransactionCommand;
import br.edu.ifsp.MyFinanceAPI.controller.command.GetByIdTransactionCommand;
import br.edu.ifsp.MyFinanceAPI.controller.command.GetByIdTransactionSummaryCommand;
import br.edu.ifsp.MyFinanceAPI.controller.command.GetTransactionsCommand;
import br.edu.ifsp.MyFinanceAPI.controller.command.InsertTransactionCommand;
import br.edu.ifsp.MyFinanceAPI.controller.command.UpdateTransactionCommand;
import jakarta.servlet.http.HttpServletRequest;

public class TransactionDispatcher {
	private final Map<String, Command> rotas = new HashMap<>();

	public TransactionDispatcher() {
		rotas.put("GET:/", new GetTransactionsCommand());
		rotas.put("GET:/id", new GetByIdTransactionCommand());
		rotas.put("GET:/category", new GetByCategoryTransactionCommand());
		rotas.put("GET:/summary/id", new GetByIdTransactionSummaryCommand());
		rotas.put("POST:/", new InsertTransactionCommand());
		rotas.put("PUT:/id", new UpdateTransactionCommand());
		rotas.put("DELETE:/id", new DeleteTransactionCommand());
	}

	public Command resolver(HttpServletRequest request) {
		String method = request.getMethod();
		String path = request.getPathInfo();
		if (path == null || path.equals("/")) {
			return rotas.get(method + ":/");
		} else if (path.matches("^/\\d+$")) {
			return rotas.get(method + ":/id");
		} else if (path.equals("/category$")) {
			return rotas.get(method + ":/category/id");
		} else if (path.matches("/summary$")) {
			return rotas.get(method + ":/summary/id");
		}
		return null;
	}
}
