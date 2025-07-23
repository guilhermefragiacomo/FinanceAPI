package br.edu.ifsp.MyFinanceAPI.controller.command;

import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;

import br.edu.ifsp.MyFinanceAPI.model.dao.CategoryDAO;
import br.edu.ifsp.MyFinanceAPI.model.dao.CategoryDAOFactory;
import br.edu.ifsp.MyFinanceAPI.model.entity.Category;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GetCategoriesCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		CategoryDAO dao = new CategoryDAOFactory().factory();
		List<Category> all = dao.getAll();
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(new Gson().toJson(all));
		out.flush();
	}

}
