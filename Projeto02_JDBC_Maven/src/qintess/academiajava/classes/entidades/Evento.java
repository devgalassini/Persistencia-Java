package qintess.academiajava.classes.entidades;

import java.util.Date;
import java.util.List;

public class Evento {
	
	//Mapeamento da tabela tb_eventos
	private int id;
	private String descricacao;
	private String responsavel;
	private Date data;
	private Double preco;
	private List<Convidado> convidados;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricacao() {
		return descricacao;
	}

	public void setDescricacao(String descricacao) {
		this.descricacao = descricacao;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	public Date getData() {
		return data;
	}

	public void setDate(Date data) {
		this.data = data;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public List<Convidado> getConvidados() {
		return convidados;
	}

	public void setConvidados(List<Convidado> convidados) {
		this.convidados = convidados;
	}
	
	@Override
	public String toString() {
		return "ID: " + this.getId() + " - Descrição: " + this.getDescricacao();
		
	}
	
	
	
	

}
