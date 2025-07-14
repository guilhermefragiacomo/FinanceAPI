package br.edu.ifsp.MyFinanceAPI.controller.handler;

import br.edu.ifsp.MyFinanceAPI.controller.command.GetTransactionsCommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GetTransactionHandler extends AbstractHandler {

    @Override
    protected boolean canHandle(HttpServletRequest request) {
        return request.getMethod().equals("GET") && 
        		(request.getPathInfo() == null || 
        		request.getPathInfo().equals("/"));
    }

    @Override
    protected void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        new GetTransactionsCommand().execute(request, response);
    }
}
