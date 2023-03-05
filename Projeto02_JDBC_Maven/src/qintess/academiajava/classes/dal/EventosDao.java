package qintess.academiajava.classes.dal;

import java.util.ArrayList;
import java.util.Collection;

import qintess.academiajava.classes.models.Evento;

//Aqui parâmetrizamos Dao com "Evento".
//Todas atribuições genéricas serão implementadas com Evento.
public class EventosDao extends Dao<Evento> {

	@Override
	public void incluir(Evento item) throws Exception {
		try {
			abrirConexao();
			String sql = "INSERT INTO TB_EVENTOS (DESCRICAO, RESPONSAVEL, DATA, PRECO)" + " VALUES (?, ?, ?, ?) ";
			stmt = cn.prepareStatement(sql);
			stmt.setString(1, item.getDescricao());
			stmt.setString(2, item.getResponsavel());
			stmt.setDate(3, new java.sql.Date(item.getData().getTime()));
			stmt.setDouble(4, item.getPreco());

			stmt.executeUpdate();
		} catch (Exception e) {
			// Não iremos tratar o erro aqui.
			// Vamos propagar o erro para próxima classe com o throw e;.
			throw e;
		} finally { // Garante que o método será executado
			fecharConexao();
		}
	}

	@Override
	public Evento buscar(int pk) throws Exception {
		Evento evento = null;

		try {
			abrirConexao();
			String sql = "SELECT * FROM TB_EVENTOS WHERE ID = ?";
			stmt = cn.prepareStatement(sql);
			stmt.setInt(1, pk);

			rs = stmt.executeQuery();
			if (rs.next()) { // Como a consulta só pode retornar um registro, utilizamos o if
				// POPUANDO O OBJETO
				evento = new Evento(); // Estanciando a variável evento, deixando de ser nulo.
				evento.setId(rs.getInt("ID")); // poderia utilizar o pk
				evento.setDescricao(rs.getString("DESCRICAO"));
				evento.setResponsavel(rs.getString("RESPONSAVEL"));
				evento.setData(rs.getDate("DATA"));
				evento.setPreco(rs.getDouble("PRECO"));
			}
		} catch (Exception e) {
			throw e;
		} finally {
			fecharConexao();
		}
		return evento;
	}

	@Override
	public Collection<Evento> listar() throws Exception {
		Collection<Evento> eventos = new ArrayList<Evento>();

		try {
			abrirConexao();

			String sql = "SELECT * FROM TB_EVENTOS";
			stmt = cn.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Evento evento = new Evento();
				evento.setId(rs.getInt("ID"));
				evento.setDescricao(rs.getString("DESCRICAO"));
				evento.setResponsavel(rs.getString("RESPONSAVEL"));
				evento.setData(rs.getDate("DATA"));
				evento.setPreco(rs.getDouble("PRECO"));

				eventos.add(evento);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			fecharConexao();
		}
		return eventos;
	}

	public int excluir(int pk) throws Exception {

		int registros = 0;
		try {
			abrirConexao();
			String sql = "DELETE FROM TB_EVENTOS WHERE ID = ?";
			stmt = cn.prepareStatement(sql);
			stmt.setInt(1, pk);
			registros = stmt.executeUpdate();

		} catch (Exception e) {
			throw e;
		} finally {
			fecharConexao();
		}
		return registros;
	}
}