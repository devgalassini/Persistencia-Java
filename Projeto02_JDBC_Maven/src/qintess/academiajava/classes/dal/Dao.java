package qintess.academiajava.classes.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;


public abstract class Dao<T> {
	protected Connection cn; //Protected modificador de acesso utilizado para Heran�as
	protected PreparedStatement stmt;
	protected ResultSet rs;
	
	private String conexao = "jdbc:mysql://localhost:3306/db_eventos?useSSL=false";
	
	//M�todo usado para abrir a conex�o
	protected void abrirConexao() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		cn = DriverManager.getConnection(conexao, "root", "@Password");
	}
	
	//M�todo usado para fechar a conex�o
	protected void fecharConexao() throws Exception {
		/*
		cn.close(); 
		N�o � uma boa pr�tica, j� que pode haver erro ao abrir conex�o. Ent�o � necess�rio verificar
		 */
		if(cn != null && !cn.isClosed()) {
			cn.close();
		}
	}
	//M�todo abstrato que recebe qualquer coisa como par�metro.
	//Todas as subclasses de DAO devem obrigatoriamente ter o m�todo incluir
	public abstract void incluir(T item) throws Exception; 
	
	//M�todo abstrato que recebe um ID e retorna o objeto referente ao ID, 
	//esse objeto � gen�rico e deve ser implementado nos herdeiros.
	public abstract T buscar(int pk) throws Exception;
	
	//M�todo abstrato que retorna uma Cole��o (List ou Set)
	public abstract Collection<T> listar() throws Exception;
}