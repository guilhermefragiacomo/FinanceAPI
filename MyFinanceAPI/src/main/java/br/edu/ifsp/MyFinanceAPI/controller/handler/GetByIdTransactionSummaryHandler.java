package br.edu.ifsp.MyFinanceAPI.controller.handler;

import br.edu.ifsp.MyFinanceAPI.controller.command.GetByIdTransactionSummaryCommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GetByIdTransactionSummaryHandler extends AbstractHandler {

    @Override
    protected boolean canHandle(HttpServletRequest request) {
        return request.getMethod().equals("GET") && 
        		request.getPathInfo().matches("/summary/[0-9]+");
    }
    
    @Override
    protected void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        new GetByIdTransactionSummaryCommand().execute(request, response);
    }
}
