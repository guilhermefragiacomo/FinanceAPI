package br.edu.ifsp.MyFinanceAPI.model.dao;

import java.util.List;

import br.edu.ifsp.MyFinanceAPI.model.entity.Category;

public interface CategoryDAO {
	boolean insert(Category category);
	boolean delete(Category category);
	boolean update(Category old_category, Category new_category);
	Category getById(int id);
	List<Category> getAll();
}
