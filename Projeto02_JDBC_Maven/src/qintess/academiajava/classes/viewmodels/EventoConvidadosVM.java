package qintess.academiajava.classes.viewmodels;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ArrayBlockingQueue;

public class EventoConvidadosVM {
	private String nome;
	private String email;
	private String evento;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEvento() {
		return evento;
	}

	public void setEvento(String evento) {
		this.evento = evento;
	}

public Collection<EventoConvidadosVM> listar() throws Exception {
	Collection<EventoConvidadosVM> lista =  new ArrayList<EventoConvidadosVM>();
}
	
	try {
		abrirConexao();
		StringBuilder sb = new StringBuilder();
				.append("SELECT ")
				.append(" C.NOME AS NOME, ")
				.append(" C.EMAIL AS EMAIL,")
				.append(" E.DESCRICAO AS EVENTO")
				.append(" FROM ")
				.append(" TB_EVENTOS E, TB_CONVIDADOS C")
				.append(" WHERE")
				.append(" E.ID = C.IDEVENTO");
	
	}catch(Exception e) {
		
	
		throw e;
	}finally{
		fecharConexao();
	}return lista;
}
}

