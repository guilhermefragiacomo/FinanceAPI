package br.edu.ifsp.MyFinanceAPI.controller.command;

import java.io.BufferedReader;
import java.lang.reflect.Type;
import java.time.LocalDate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import br.edu.ifsp.MyFinanceAPI.model.dao.CategoryDAO;
import br.edu.ifsp.MyFinanceAPI.model.dao.CategoryDAOFactory;
import br.edu.ifsp.MyFinanceAPI.model.dao.TransactionDAO;
import br.edu.ifsp.MyFinanceAPI.model.dao.TransactionDAOFactory;
import br.edu.ifsp.MyFinanceAPI.model.entity.Category;
import br.edu.ifsp.MyFinanceAPI.model.entity.Transaction;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class InsertCategoryCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		CategoryDAO dao = new CategoryDAOFactory().factory();
		
		BufferedReader reader = request.getReader();
        Gson gson = new GsonBuilder()
			    .registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
			        public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			                throws JsonParseException {
			            return LocalDate.parse(json.getAsString());
			        }
			    }).create();

        Category transaction = gson.fromJson(reader, Category.class);
        
        if (dao.insert(transaction)) {
        	response.setStatus(HttpServletResponse.SC_CREATED);
        } else {
        	response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
	}

}
