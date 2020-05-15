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

import br.com.zup.pgg.model.Cliente;
import br.com.zup.pgg.regrasException.RegrasException;

@WebServlet(urlPatterns = "/clientes")
public class ClienteController extends HttpServlet {

	private static final String MENSSAGEM_DE_CARACTERE_INV�LIDO = "Quantidade de caractere inv�lido! por favor digite um nome com uma quantidade superior a 3 caracteres e inferior a 30 caracteres. ";
	public static Map<String, Cliente> listaDeCliente = new HashMap<String, Cliente>();

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter writer = resp.getWriter();
		String cpf = req.getParameter("cpf");

		if (cpf != null) {

			if (listaDeCliente.containsKey(cpf)) {

				Cliente clienteBuscado = listaDeCliente.get(cpf);
				writer.println("Nome: " + clienteBuscado.getNome());
				writer.println("Idade: " + clienteBuscado.getIdade());
				writer.println("Email: " + clienteBuscado.getEmail());
				writer.println("Cpf: " + clienteBuscado.getCpf());
				writer.println("Telefone: " + clienteBuscado.getTelefone());
				writer.println("Endereco: " + clienteBuscado.getEndereco());

			} else {
				writer.print("CPF N�O ENCONTRADO!");
			}

		} else {
			for (Cliente cliente : listaDeCliente.values()) {
				writer.println("Nome: " + cliente.getNome());
				writer.println("Idade: " + cliente.getIdade());
				writer.println("Email: " + cliente.getEmail());
				writer.println("Cpf: " + cliente.getCpf());
				writer.println("Telefone: " + cliente.getTelefone());
				writer.println("Endereco: " + cliente.getEndereco());
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		String cpf = request.getParameter("cpf");

		Cliente cliente = new Cliente();
		cliente.setNome(request.getParameter("nome"));
		cliente.setIdade(Integer.parseInt(request.getParameter("idade")));
		cliente.setCpf(request.getParameter("cpf"));
		cliente.setEmail(request.getParameter("email"));
		cliente.setTelefone(request.getParameter("telefone"));
		cliente.setEndereco(request.getParameter("endereco"));

		if (listaDeCliente.containsKey(cpf)) {
			writer.print("Cliente ja existente");
		} else {

			listaDeCliente.put(cliente.getCpf(), cliente);
			response.getWriter()
					.print("nome :" + cliente.getNome() + "\nidade :" + cliente.getIdade() + "\ncpf :"
							+ cliente.getCpf() + "\nemail :" + cliente.getEmail() + "\ntelefone :"
							+ cliente.getTelefone() + "\nendereco :" + cliente.getEndereco());
			writer.print("\nCliente inserido com sucesso!");
		}
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cpf = request.getParameter("cpf");
		Cliente clienteBuscado = listaDeCliente.get(cpf);
		PrintWriter writer = response.getWriter();

		if (cpf != null) {

			if (listaDeCliente.containsKey(cpf)) {

				clienteBuscado.setNome(request.getParameter("nome"));
				clienteBuscado.setIdade(Integer.parseInt(request.getParameter("idade")));
				clienteBuscado.setEmail(request.getParameter("email"));
				clienteBuscado.setTelefone(request.getParameter("telefone"));
				clienteBuscado.setEndereco(request.getParameter("endereco"));

				listaDeCliente.put(cpf, clienteBuscado);
				response.getWriter();
				writer.print("nome :" + clienteBuscado.getNome() + "\nidade :" + clienteBuscado.getIdade() + "\ncpf :"
						+ clienteBuscado.getCpf() + "\nemail :" + clienteBuscado.getEmail() + "\ntelefone :"
						+ clienteBuscado.getTelefone() + "\nendereco :" + clienteBuscado.getEndereco());

			} else {

				writer.print("CPF NAO ENCONTRADO!");
			}
			writer.print("Cliente Atualizado com sucesso!");
		}
	}

	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cpf = req.getParameter("cpf");
		listaDeCliente.remove(cpf);

	}
}
