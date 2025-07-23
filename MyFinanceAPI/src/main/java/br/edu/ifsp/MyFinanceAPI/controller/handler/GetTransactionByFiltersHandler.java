package br.edu.ifsp.MyFinanceAPI.controller.handler;

import br.edu.ifsp.MyFinanceAPI.controller.command.GetByCategoryTransactionCommand;
import br.edu.ifsp.MyFinanceAPI.controller.command.GetTransactionByFiltersCommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GetTransactionByFiltersHandler extends AbstractHandler {

    @Override
    protected boolean canHandle(HttpServletRequest request) {
        return request.getMethod().equals("GET") && 
        		request.getPathInfo().equals("/filter/");
    }
    
    @Override
    protected void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        new GetTransactionByFiltersCommand().execute(request, response);
    }
}
