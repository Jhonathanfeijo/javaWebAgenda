package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO;
import model.JavaBeans;

@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/select", "/update","/delete" })
public class Controller extends HttpServlet {

	private static final long serialVersionUID = 1L;
	DAO dao = new DAO();
	JavaBeans contato = new JavaBeans();

	public Controller() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String action = request.getServletPath();
		System.out.println(action);
		if (action.equals("/main")) {
			contatos(request, response);
		} else if (action.equals("/insert")) {
			novoContato(request, response);
		} else if (action.equals("/select")) {
			listarContato(request, response);
		} else if (action.equals("/update")) {
			updateContato(request, response);
		}else if(action.equals("/delete")) {
			deleteContato(request,response);
		}
		
	}

	protected void contatos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<JavaBeans> list = (ArrayList<JavaBeans>) dao.getList();
		for (JavaBeans contato : list) {
			System.out.println(contato.getId());
			System.out.println(contato.getNome());
			System.out.println(contato.getEmail());
			System.out.println(contato.getFone());
		}
		request.setAttribute("lista", list);
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		rd.forward(request, response);
	}

	protected void novoContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("cheguei ate aqui");
		this.contato.setNome(request.getParameter("nome"));
		this.contato.setEmail(request.getParameter("email"));
		this.contato.setFone(request.getParameter("fone"));
		dao.adiciona(contato);
		response.sendRedirect("main");

	}

	protected void listarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idText = request.getParameter("id");
		contato.setId(Long.parseLong(idText));
		contato.setNome(request.getParameter("nome"));
		contato.setEmail(request.getParameter("email"));
		contato.setFone(request.getParameter("fone"));
		dao.selecionaContato(contato);
		System.out.println(contato.getNome());
		request.setAttribute("id", contato.getId());
		request.setAttribute("nome", contato.getNome());
		request.setAttribute("email", contato.getEmail());
		request.setAttribute("fone", contato.getFone());
		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);

	}

	protected void updateContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.contato.setId(Long.parseLong(request.getParameter("id")));
		System.out.println(request.getParameter("id"));
		this.contato.setNome(request.getParameter("nome"));
		this.contato.setEmail(request.getParameter("email"));
		this.contato.setFone(request.getParameter("fone"));
		dao.edita(contato);
		response.sendRedirect("main");

	}

	protected void deleteContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		long id= Long.parseLong(request.getParameter("id"));
		dao.delete(id);
		response.sendRedirect("main");

	}
}
