package br.upf.ads.appRondaGrupo12.jpa;

import javax.persistence.EntityManager;

import br.upf.ads.appRondaGrupo12.model.Usuario;

public class InserirUsuario {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        EntityManager em = JpaUtil.getEntityManager();
      
        em.getTransaction().begin();
        em.merge(new Usuario(1, "danier", "danier", "123456"));
        em.getTransaction().commit();
       
	}

}
