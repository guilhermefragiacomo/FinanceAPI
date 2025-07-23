package br.edu.ifsp.MyFinanceAPI.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.MyFinanceAPI.model.dao.connection.DatabaseConnection;
import br.edu.ifsp.MyFinanceAPI.model.entity.Category;
import br.edu.ifsp.MyFinanceAPI.model.entity.Transaction;

public class CategoryDAOImpl implements CategoryDAO {
	private static final String INSERT = "INSERT INTO category(name) VALUES (?)";
	private static final String DELETE = "DELETE FROM category WHERE id = ?";
	private static final String UPDATE = "UPDATE category SET name = ? WHERE id = ?";
	private static final String GET_BY_ID = "SELECT * FROM category WHERE id = ?";
	private static final String GET_ALL = "SELECT * FROM category";

	@Override
	public boolean insert(Category category) {
		int rows = 0;
		if(category != null) {
			try(Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(INSERT)){
				
				
				
				statement.setString(1, category.getName());
				
				rows = statement.executeUpdate();
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return rows > 0;
	}

	@Override
	public boolean delete(Category category) {
		int rows = 0;
		if (category != null) {
			try( Connection connection = DatabaseConnection.getConnection();
					PreparedStatement statement = connection.prepareStatement(DELETE)){
				
				statement.setInt(1, category.getId());
				
				rows = statement.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rows > 0;
	}

	@Override
	public boolean update(Category old_category, Category new_category) {
		int rows = 0;
		if(old_category != null && new_category != null) {
			try(Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE)){
				
				statement.setString(1, new_category.getName());
				statement.setInt(2, old_category.getId());
				
				rows = statement.executeUpdate();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return rows > 0;
	}

	@Override
	public Category getById(int id) {
		Category category = null;
		try {
			Connection connection = DatabaseConnection.getConnection();
			
			PreparedStatement statement = connection.prepareStatement(GET_BY_ID);
			
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				category = new Category(result.getInt("id"), result.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return category;
	}

	@Override
	public List<Category> getAll() {
		List<Category> list = new ArrayList<>();
		try {
			Connection connection = DatabaseConnection.getConnection();
			
			PreparedStatement statement = connection.prepareStatement(GET_ALL);
			
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				Category category = new Category(resultSet.getInt("id"), resultSet.getString("name"));
				
				list.add(category);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
