package qintess.academiajava.classes.models;

import java.util.Date;
import java.util.List;

public class Evento {
	private int id;
	private String descricao;
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
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
	public void setData(Date data) {
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
		return "ID: " + this.getId() + ", Descrição: " + this.getDescricao();
	}
	
	
	
	
	
}
