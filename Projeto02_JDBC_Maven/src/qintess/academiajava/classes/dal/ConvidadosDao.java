package qintess.academiajava.classes.dal;

import java.util.ArrayList;
import java.util.Collection;

import qintess.academiajava.classes.models.Convidado;
import qintess.academiajava.repositorio.Repositorio;

public class ConvidadosDao extends Dao<Convidado> {

	@Override
	public void incluir(Convidado item) throws Exception {
		try {
			abrirConexao();
			String sql = "INSERT INTO TB_CONVIDADOS (NOME, IDADE, EMAIL, IDEVENTO)" + "VALUES (?, ?, ?, ?)";
			stmt = cn.prepareStatement(sql);
			
			stmt.setString(1, item.getNome());
			stmt.setInt(2, item.getIdade());
			stmt.setString(3, item.getEmail());
			stmt.setInt(4, item.getEvento().getId());
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			fecharConexao();

		}

	}

	@Override
	public Convidado buscar(int pk) throws Exception {
		return null;
	}

	@Override
	public Collection<Convidado> listar() throws Exception {
		return listarConvidados();
	}


	public Collection<Convidado> listar(int idevento) throws Exception {
		return listarConvidados(idevento);
	}

	private Collection<Convidado> listarConvidados(int... idevento) throws Exception {
		Collection<Convidado> convidados = new ArrayList<Convidado>();

		try {
						
			String sql = "SLECT * FROM TB_CONVIDADO"; 
			if(idevento.length == 1) {
				sql +=" WHERE IDEVENTO = ?";				
			} 
			
			abrirConexao();
			stmt = cn.prepareStatement(sql);
			if(idevento.length == 1) {
				stmt.setInt(1, idevento[0]);
			}
				rs = stmt.executeQuery();
				while(rs.next()) {
					Convidado convidado = new Convidado();
					convidado.setId(rs.getInt("ID"));
					convidado.setNome(rs.getString("NOME"));
					convidado.setIdade(rs.getInt("IDADE"));
					convidado.setEmail(rs.getString("EMAIL"));
					convidado.setEvento(Repositorio.getEventosDao()
							.buscar(rs.getInt("IDEVENTO")));
					// incluir o evento ao qual este convidado pertence
					convidados.add(convidado);
					
				}
		} catch (Exception e) {
			throw e;
		} finally {
			fecharConexao();
					
		}
		
		
		return null;
		
	}

}