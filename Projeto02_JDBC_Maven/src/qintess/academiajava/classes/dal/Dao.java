package qintess.academiajava.classes.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;


public abstract class Dao<T> {
	protected Connection cn; //Protected modificador de acesso utilizado para Heranças
	protected PreparedStatement stmt;
	protected ResultSet rs;
	
	private String conexao = "jdbc:mysql://localhost:3306/db_eventos?useSSL=false";
	
	//Método usado para abrir a conexão
	protected void abrirConexao() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		cn = DriverManager.getConnection(conexao, "root", "@Password");
	}
	
	//Método usado para fechar a conexão
	protected void fecharConexao() throws Exception {
		/*
		cn.close(); 
		Não é uma boa prática, já que pode haver erro ao abrir conexão. Então é necessário verificar
		 */
		if(cn != null && !cn.isClosed()) {
			cn.close();
		}
	}
	//Método abstrato que recebe qualquer coisa como parâmetro.
	//Todas as subclasses de DAO devem obrigatoriamente ter o método incluir
	public abstract void incluir(T item) throws Exception; 
	
	//Método abstrato que recebe um ID e retorna o objeto referente ao ID, 
	//esse objeto é genérico e deve ser implementado nos herdeiros.
	public abstract T buscar(int pk) throws Exception;
	
	//Método abstrato que retorna uma Coleção (List ou Set)
	public abstract Collection<T> listar() throws Exception;
}