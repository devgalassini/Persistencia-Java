package qintess.academiajava.jdbc.aplicacao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import javax.swing.JOptionPane;

public class AppConsultaClientes {
	public static void main(String[] args) {
		try {
			//Definindo a String de conex�o ao banco de dados.
			String conexao = "jdbc:mysql://localhost:3306/db_banco?useSSL=false";
			/* useSSL=false, para que o banco N�O solicite o uso de um certificado.
			 * A partir da ves�o 8 do java, foi ativado esse n�vel de seguran�a.
			 */
			
			//Habilitando driver de acesso ao MySql - Connector Java
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//Objetos de acesso a dados - Data Access Objects (DAO)
			//Interface Connection do java.sql
			Connection cn; //N�o instancia porque � um interface
			PreparedStatement stmt;
			ResultSet rs;//Estabelecer / referenciar consultas
			
			//Estabelecendo a conex�o com o banco de dados
			cn = DriverManager.getConnection(conexao, "root", "");
			
			//Escrevendo uma instru��o SQL de consulta
			String sql = "SELECT * FROM TB_CLIENTES";
			stmt = cn.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			
			
			while(rs.next()) {
				String cpf = rs.getString("CPF");
				String nome = rs.getString("NOME");
				Date datanasc = rs.getDate("DATANASC");
				String telefone = rs.getString("TELEFONE");
				String sexo = rs.getString("SEXO");
				
				String resposta = "CPF: " + cpf +
						"\nNome: " + nome + 
						"\nData Nasc: " + datanasc +
						"\nTelefone: " + telefone + 
						"\nSexo: " + sexo;
				System.out.println(resposta);
				System.out.println("-".repeat(20));
			}
			
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}
}
