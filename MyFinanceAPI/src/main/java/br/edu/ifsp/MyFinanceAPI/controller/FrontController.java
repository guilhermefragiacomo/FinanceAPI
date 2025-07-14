package br.edu.ifsp.MyFinanceAPI.controller;


import java.io.IOException;

import br.edu.ifsp.MyFinanceAPI.controller.handler.Handler;
import br.edu.ifsp.MyFinanceAPI.controller.handler.HandlerFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/transactions/*")
public class FrontController extends HttpServlet {
	private Handler chain;

	@Override
	public void init() {
		try {
			chain = HandlerFactory.criarCadeia();
		} catch (Exception e) {
			throw new RuntimeException("error initializing handler chain", e);
		}
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			chain.handle(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}
}
