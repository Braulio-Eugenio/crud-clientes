package br.com.zup.pgg.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.beans.util.Cache;

import br.com.zup.pgg.model.Cliente;

@WebServlet(urlPatterns = "/clientes")
public class ClienteController extends HttpServlet {

	public static Map<String, Cliente> listaDeCliente = new HashMap<String, Cliente>();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter writer = resp.getWriter();
		writer.println("<html><body>");
		for (Cliente cliente : listaDeCliente.values()) {
			writer.println("Nome: " + cliente.getNome());
			writer.println("Idade: " + cliente.getIdade());
			writer.println("Email: " + cliente.getEmail());
			writer.println("Cpf: " + cliente.getCpf());
			writer.println("Telefone: " + cliente.getTelefone());
			writer.println("Endereco: " + cliente.getEndereco());

		}
		writer.println("</body><html>");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cliente cliente = new Cliente();
		cliente.setNome(request.getParameter("nome"));
		cliente.setIdade(Integer.parseInt(request.getParameter("idade")));
		cliente.setCpf(request.getParameter("cpf"));
		cliente.setEmail(request.getParameter("email"));
		cliente.setTelefone(request.getParameter("telefone"));
		cliente.setEndereco(request.getParameter("endereco"));
		listaDeCliente.put(cliente.getCpf(), cliente);
		response.getWriter()
				.print("nome :" + cliente.getNome() + "\nidade :" + cliente.getIdade() + "\ncpf :" + cliente.getCpf()
						+ "\nemail :" + cliente.getEmail() + "\ntelefone :" + cliente.getTelefone() + "\nendereco :"
						+ cliente.getEndereco());
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		

	}

}
