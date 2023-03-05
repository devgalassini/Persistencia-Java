package qintess.academiajava.jdbc.aplicacao;

import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

public class AppCadastroClientes {
	public static void main(String[] args) {
		try {
			//Definindo a String de conexão ao banco de dados.
			String conexao = "jdbc:mysql://localhost:3306/db_banco?useSSL=false";
			/* useSSL=false, para que o banco NÃO solicite o uso de um certificado.
			 * A partir da vesão 8 do java, foi ativado esse nível de segurança.
			 */
			
			//Habilitando driver de acesso ao MySql - Connector Java
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//Objetos de acesso a dados - Data Access Objects (DAO)
			//Interface Connection do java.sql
			Connection cn; //Não instancia porque é um interface
			PreparedStatement stmt;
			
			//Estabelecendo a conexão com o banco de dados
			cn = DriverManager.getConnection(conexao, "root", "@Password");
			//JOptionPane.showMessageDialog(null, "Conexão OK!");
			
			//Obtendo dados do usuário
			String cpf = JOptionPane.showInputDialog("Informe o CPF");
			String nome = JOptionPane.showInputDialog("Informe o nome");
			String data = JOptionPane.showInputDialog("Infoem a data de nascimento (dd/mm/aaaa)");
			Date dataNasc = new SimpleDateFormat("dd/MM/yyyy").parse(data);
			String telefone = JOptionPane.showInputDialog("Informe o telefone");
			String sexo = JOptionPane.showInputDialog("Informe o sexo (M, F, O)");
			
			//Escrevendo a instrução SQL para incluir um novo registro
			String sql = "INSERT INTO TB_CLIENTES"
					+ "(CPF, NOME, DATANASC, TELEFONE, SEXO)"
					+ "VALUES (?, ?, ?, ?, ?)"; //Instrução SQL parametrizada
			
			stmt = cn.prepareStatement(sql); //Manipular os dados
			stmt.setString(1, cpf);
			stmt.setString(2, nome);
			stmt.setDate(3, new java.sql.Date(dataNasc.getTime()));
			/*
			 * Aqui o método setDate quer receber uma data no formato Date da classe java.sql.Date
			 * portando é necessário converter
			 */
			stmt.setString(4, telefone);
			stmt.setString(5, sexo);
			
			stmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "Cliente incluído com sucesso!");
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}
}