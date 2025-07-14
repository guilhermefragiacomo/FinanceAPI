package br.edu.ifsp.MyFinanceAPI.controller.handler;

import br.edu.ifsp.MyFinanceAPI.controller.command.DeleteTransactionCommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteTransactionHandler extends AbstractHandler {

    @Override
    protected boolean canHandle(HttpServletRequest request) {
        return request.getMethod().equals("DELETE") && 
        		request.getPathInfo().matches("^/\\d+$");
    }
    
    @Override
    protected void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        new DeleteTransactionCommand().execute(request, response);
    }
}
