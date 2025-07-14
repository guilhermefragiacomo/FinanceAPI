package br.edu.ifsp.MyFinanceAPI.controller.handler;

import br.edu.ifsp.MyFinanceAPI.controller.command.InsertTransactionCommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class InsertTransactionHandler extends AbstractHandler {
	@Override
    protected boolean canHandle(HttpServletRequest request) {
        return request.getMethod().equals("POST") && 
        		(request.getPathInfo() == null || 
        		request.getPathInfo().equals("/"));
    }
    
    @Override
    protected void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        new InsertTransactionCommand().execute(request, response);
    }
}
