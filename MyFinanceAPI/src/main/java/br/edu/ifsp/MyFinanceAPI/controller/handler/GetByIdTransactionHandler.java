package br.edu.ifsp.MyFinanceAPI.controller.handler;

import br.edu.ifsp.MyFinanceAPI.controller.command.GetByIdTransactionCommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GetByIdTransactionHandler extends AbstractHandler {

    @Override
    protected boolean canHandle(HttpServletRequest request) {
        return request.getMethod().equals("GET") && 
        		request.getPathInfo().matches("^/\\d+$");
    }
    
    @Override
    protected void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        new GetByIdTransactionCommand().execute(request, response);
    }
}
