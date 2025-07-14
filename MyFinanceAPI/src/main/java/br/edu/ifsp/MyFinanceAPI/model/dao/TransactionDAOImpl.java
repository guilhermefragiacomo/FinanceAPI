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
	private static final String GET_ALL = "SELECT * FROM transactions";

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
				statement.setString(5, transaction.getFormatedDate());
				
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
	public boolean update(Transaction old_transaction, Transaction new_transaction) {
		int rows = 0;
		if(old_transaction != null && new_transaction != null) {
			try(Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE)){
				
				statement.setString(1, new_transaction.getDescription());
				statement.setFloat(2, new_transaction.getValue());
				statement.setString(3, new_transaction.getType());
				statement.setInt(4, new_transaction.getCategory());
				statement.setString(5, new_transaction.getFormatedDate());
				statement.setInt(6, old_transaction.getId());
				
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
				transaction = new Transaction(result.getInt("id"), result.getString("description"), result.getFloat("value"), result.getString("type"), result.getInt("category"), Transaction.getFormatedString(result.getString("date")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return transaction;
	}
	@Override
	public Summary getSummaryByCategory(String category) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Transaction> getAll() {
		List<Transaction> list = new ArrayList<>();
		try {
			Connection connection = DatabaseConnection.getConnection();
			
			PreparedStatement statement = connection.prepareStatement(GET_ALL);
			
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				Transaction servico = new Transaction(resultSet.getInt("id"), resultSet.getString("description"), resultSet.getFloat("value"), resultSet.getString("type"), resultSet.getInt("category_id"), Transaction.getFormatedString(resultSet.getString("date")));
				
				list.add(servico);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
