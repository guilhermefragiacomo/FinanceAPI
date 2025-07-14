package br.edu.ifsp.MyFinanceAPI.controller.handler;

import br.edu.ifsp.MyFinanceAPI.controller.command.UpdateTransactionCommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UpdateTransactionHandler extends AbstractHandler {
	@Override
    protected boolean canHandle(HttpServletRequest request) {
        return request.getMethod().equals("PUT") && 
        		request.getPathInfo().matches("^/\\d+$");
    }
    
    @Override
    protected void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        new UpdateTransactionCommand().execute(request, response);
    }
}
