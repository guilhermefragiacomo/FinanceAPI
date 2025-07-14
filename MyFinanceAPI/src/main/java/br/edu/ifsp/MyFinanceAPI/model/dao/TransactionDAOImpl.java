package br.edu.ifsp.MyFinanceAPI.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.MyFinanceAPI.model.dao.connection.DatabaseConnection;
import br.edu.ifsp.MyFinanceAPI.model.entity.Summary;
import br.edu.ifsp.MyFinanceAPI.model.entity.Transaction;

public class TransactionDAOImpl implements TransactionDAO {
	private static final String INSERT = "INSERT INTO transactions(description, value, type, category_id, date) VALUES (?,?,?,?,?)";
	private static final String DELETE = "DELETE FROM transactions WHERE id = ?";
	private static final String UPDATE = "UPDATE transactions SET description = ?, value = ?, type = ?, category_id = ?, date = ? WHERE id = ?";
	private static final String GET_BY_ID = "SELECT * FROM transactions WHERE id = ?";
	private static final String GET_BY_CATEGORY = "SELECT * FROM transactions WHERE category_id = ?";
	private static final String GET_ALL = "SELECT * FROM transactions";
	private static final String GET_REVENUE_EXPENSES_BY_CATEGORY = "SELECT SUM(value) AS \"value\", type FROM transactions WHERE category_id = ? GROUP BY type;";
	private static final String GET_SUMMARY = "SELECT SUM(value) AS \"value\", type FROM transactions GROUP BY type;";

	@Override
	public boolean insert(Transaction transaction) {
		int rows = 0;
		if(transaction != null) {
			try(Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(INSERT)){
				
				
				
				statement.setString(1, transaction.getDescription());
				statement.setFloat(2, transaction.getValue());
				statement.setString(3, transaction.getType());
				statement.setInt(4, transaction.getCategory());
				statement.setString(5, transaction.getDate());
				
				rows = statement.executeUpdate();
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return rows > 0;
	}
	@Override
	public boolean deleteById(int id) {
		int rows = 0;
		try( Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE)){
			
			statement.setInt(1, id);
			
			rows = statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows > 0;
	}
	@Override
	public boolean update(int old_transaction_id, Transaction new_transaction) {
		int rows = 0;
		if(new_transaction != null) {
			try(Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE)){
				
				statement.setString(1, new_transaction.getDescription());
				statement.setFloat(2, new_transaction.getValue());
				statement.setString(3, new_transaction.getType());
				statement.setInt(4, new_transaction.getCategory());
				statement.setString(5, new_transaction.getDate());
				statement.setInt(6, old_transaction_id);
				
				rows = statement.executeUpdate();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return rows > 0;
	}
	@Override
	public Transaction getById(int id) {
		Transaction transaction = null;
		try {
			Connection connection = DatabaseConnection.getConnection();
			
			PreparedStatement statement = connection.prepareStatement(GET_BY_ID);
			
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				transaction = new Transaction(result.getInt("id"), result.getString("description"), result.getFloat("value"), result.getString("type"), result.getInt("category_id"), result.getString("date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return transaction;
	}
	@Override
	public Summary getSummaryByCategory(int category) {
		List<Transaction> list = new ArrayList<>();
		Summary summary = null;
		try {
			Connection connection = DatabaseConnection.getConnection();
			
			PreparedStatement statement = connection.prepareStatement(GET_REVENUE_EXPENSES_BY_CATEGORY);
			statement.setInt(1, category);
			
			ResultSet resultSet = statement.executeQuery();
			
			float revenue = 0;
			float expense = 0;
			float current_balance = 0;
			while(resultSet.next()) {
				if (resultSet.getInt("type") == 1) {
					revenue = resultSet.getFloat("value");
				} else {
					expense = resultSet.getFloat("value");
				}
			}
			
			statement = connection.prepareStatement(GET_SUMMARY);
			
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				if (resultSet.getInt("type") == 1) {
					current_balance += resultSet.getFloat("value");
				} else {
					current_balance -= resultSet.getFloat("value");
				}
			}
			
			summary = new Summary(revenue, expense, category, current_balance);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return summary;
	}
	@Override
	public List<Transaction> getAll() {
		List<Transaction> list = new ArrayList<>();
		try {
			Connection connection = DatabaseConnection.getConnection();
			
			PreparedStatement statement = connection.prepareStatement(GET_ALL);
			
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				Transaction transaction = new Transaction(resultSet.getInt("id"), resultSet.getString("description"), resultSet.getFloat("value"), resultSet.getString("type"), resultSet.getInt("category_id"),resultSet.getString("date"));
				
				list.add(transaction);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public List<Transaction> getByCategory(int category_id) {
		List<Transaction> list = new ArrayList<>();
		try {
			Connection connection = DatabaseConnection.getConnection();
			
			PreparedStatement statement = connection.prepareStatement(GET_BY_CATEGORY);
			statement.setInt(1, category_id);
			
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				Transaction servico = new Transaction(resultSet.getInt("id"), resultSet.getString("description"), resultSet.getFloat("value"), resultSet.getString("type"), resultSet.getInt("category_id"),resultSet.getString("date"));
				
				list.add(servico);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
