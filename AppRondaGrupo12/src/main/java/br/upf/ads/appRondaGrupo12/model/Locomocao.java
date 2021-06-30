package br.upf.ads.appRondaGrupo12.model;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Locomocao
 *
 */
@Entity

public class Locomocao implements Serializable {

	   
	@Id
	private Long id;
	private String descricao;
	private String placa;
	private static final long serialVersionUID = 1L;

	public Locomocao() {
		super();
	}   
	
	
	
	
	public Locomocao(Long id, String descricao, String placa) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.placa = placa;
	}




	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}   
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}   
	public String getPlaca() {
		return this.placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}
   
}
