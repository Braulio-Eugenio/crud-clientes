package br.com.zup.pgg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.zup.pgg.conectionfactory.ConnectionFactory;
import br.com.zup.pgg.model.Cliente;

public class ClienteDao {

	Connection conn = ConnectionFactory.getConnection();

	public void insereCliente(Cliente cliente) {

		String sql = " insert into cliente " + "(nome, idade, cpf, email, telefone, endereco)"
				+ " values (?, ?, ?, ?, ?, ?)";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, cliente.getNome());
			stmt.setInt(2, cliente.getIdade());
			stmt.setString(3, cliente.getCpf());
			stmt.setString(4, cliente.getEmail());
			stmt.setString(5, cliente.getTelefone());
			stmt.setString(6, cliente.getEndereco());

			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List<Cliente> listaClientes() {
		List<Cliente> clienteLista = new ArrayList<Cliente>();
		String sql = "select * from cliente";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				Cliente cliente = new Cliente();
				cliente.setNome(rs.getString("nome"));
				cliente.setIdade(rs.getInt("idade"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setEmail(rs.getString("email"));
				cliente.setTelefone(rs.getString("telefone"));
				cliente.setEndereco(rs.getString("endereco"));

				clienteLista.add(cliente);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {

		}
		return clienteLista;

	}

	public List<Cliente> listaClientesPorCpf(String cpf) {
		List<Cliente> clienteLista = new ArrayList<Cliente>();
		String sql = "select cliente from cliente were=?";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				Cliente cliente = new Cliente();
				cliente.setNome(rs.getString("nome"));
				cliente.setIdade(rs.getInt("idade"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setEmail(rs.getString("email"));
				cliente.setTelefone(rs.getString("telefone"));
				cliente.setEndereco(rs.getString("endereco"));

				clienteLista.add(cliente);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.getErrorCode();
		}
		return clienteLista;

	}

	public void alteraCliente() {

	}

	public void deletaCliente(String cpf) {
		
		String sql = "delete from cliente where cpf=?";
		try {
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, cpf);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			
		}
	}
}
