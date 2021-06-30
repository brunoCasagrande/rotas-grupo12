package br.upf.ads.appRondaGrupo12.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.upf.ads.appRondaGrupo12.jpa.JpaUtil;
import br.upf.ads.appRondaGrupo12.model.Locomocao;
import br.upf.ads.appRondaGrupo12.model.Pessoa;
import br.upf.ads.appRondaGrupo12.model.Ronda;
import br.upf.ads.appRondaGrupo12.uteis.Upload;
import net.iamvegan.multipartrequest.HttpServletMultipartRequest;

/**
 * Servlet implementation class LocomocaoCon
 */
@WebServlet("/Privada/Locomocao/LocomocaoCon")
public class LocomocaoCon extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LocomocaoCon() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request = new HttpServletMultipartRequest(
				request,
				HttpServletMultipartRequest.MAX_CONTENT_LENGTH,
				HttpServletMultipartRequest.SAVE_TO_TMPDIR,
				HttpServletMultipartRequest.IGNORE_ON_MAX_LENGTH,
				HttpServletMultipartRequest.DEFAULT_ENCODING);		
		
		
		if (request.getParameter("incluir") != null) {
			incluir(request, response);
		} else if (request.getParameter("alterar") != null) {
			alterar(request, response);
		} else if (request.getParameter("excluir") != null) {
            excluir(request, response);			
		} else if (request.getParameter("gravar") != null) {
			gravar(request, response);			
		} else if (request.getParameter("cancelar") != null) {
			cancelar(request, response);		
				
		} else {
			listar(request, response);
		}
		
	}
	
	
	private void listarLocomocao(HttpServletRequest request, HttpServletResponse response, Long idLocomocao) {
		try {
			EntityManager em = JpaUtil.getEntityManager();
			Locomocao obj = em.find(Locomocao.class, idLocomocao);
			request.setAttribute("obj", obj);
			List<Locomocao> pessoas = em.createQuery("from Locomocao order by id").getResultList();
			request.setAttribute("locomocao", pessoas);
			em.close();
			request.getRequestDispatcher("LocomocaoForm.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}	
	
	
	private void incluirLocomocao(HttpServletRequest request, HttpServletResponse response) {
		EntityManager em = JpaUtil.getEntityManager(); 
		em.getTransaction().begin(); 	

		Locomocao r = em.find(Locomocao.class, Long.parseLong(request.getParameter("idLocomocao")));

		em.merge(r); 
		em.getTransaction().commit(); 	
		em.close();

	}	
	
	private void excluirLocomocao(HttpServletRequest request, HttpServletResponse response) {
		EntityManager em = JpaUtil.getEntityManager(); 
		em.getTransaction().begin(); 
		Locomocao r = em.find(Locomocao.class, Long.parseLong(request.getParameter("idLocomocao")));

		em.merge(r); 
		em.getTransaction().commit(); 	
		em.close();

		
	}		
	
	private void listar(HttpServletRequest request, HttpServletResponse response) {
		try {
			EntityManager em = JpaUtil.getEntityManager();
			List<Locomocao> lista = em.createQuery("from Locomocao").getResultList(); 
			request.setAttribute("lista", lista);
			em.close();
			request.getRequestDispatcher("LocomocaoList.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	private void cancelar(HttpServletRequest request, HttpServletResponse response) {
		listar(request, response);		
	}

	private void gravar(HttpServletRequest request, HttpServletResponse response) {
		EntityManager em = JpaUtil.getEntityManager(); 
		Locomocao p = new Locomocao(
				   		Integer.parseInt(request.getParameter("id")), 
		request.getParameter("decricao"), 
		request.getParameter("placa"));
		// ----------------------------------------------------------------------------------
		em.getTransaction().begin(); 	
		em.merge(p); 					
		em.getTransaction().commit(); 	
		em.close();
		listar(request, response);
	}

	private void excluir(HttpServletRequest request, HttpServletResponse response) {
		EntityManager em = JpaUtil.getEntityManager(); 
		em.getTransaction().begin(); 	
		em.remove(em.find(Locomocao.class, Integer.parseInt(request.getParameter("excluir"))));	
		em.getTransaction().commit(); 	
		em.close();
		listar(request, response);
	}

	private void alterar(HttpServletRequest request, HttpServletResponse response) {
		try {
			EntityManager em = JpaUtil.getEntityManager();
			Locomocao obj = em.find(Locomocao.class, Integer.parseInt(request.getParameter("alterar")));
			request.setAttribute("obj", obj);
			em.close();
			request.getRequestDispatcher("LocomocaoForm.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	private void incluir(HttpServletRequest request, HttpServletResponse response) {
		try {
			Locomocao obj = new Locomocao();
			request.setAttribute("obj", obj);
			request.getRequestDispatcher("LocomocaoForm.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} 		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
