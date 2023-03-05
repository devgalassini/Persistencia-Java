package qintess.academiajava.classes.dal;

import java.util.ArrayList;
import java.util.Collection;

import qintess.academiajava.classes.viewmodels.EventoConvidadosVM;

public class EventoConvidadosDao extends GenericDao{

	public Collection<EventoConvidadosVM> listar() throws Exception {
		Collection<EventoConvidadosVM> lista = new ArrayList<EventoConvidadosVM>();
		
		try {
			StringBuilder sb = new StringBuilder()
					.append("SELECT ")
					.append(" C.NOME AS NOME, ")
					.append(" C.EMAIL AS EMAIL,")
					.append(" E.DESCRICAO AS EVENTO")
					.append(" FROM ")
					.append(" TB_EVENTOS E, TB_CONVIDADOS C")
					.append(" WHERE")
					.append(" E.ID = C.IDEVENTO");
			
			stmt = cn.prepareStatement(sb.toString());
			rs = stmt.executeQuery();
			while (rs.next()) {
				EventoConvidadosVM vm = new EventoConvidadosVM();
				vm.setNome(rs.getString("NOME"));
				vm.setEmail(rs.getString("EMAIL"));
				vm.setEvento(rs.getString("EVENTO"));
				
				lista.add(vm);
			}
			
		} catch (Exception e) {
			throw e;
		} finally {
			fecharConexao();
		}
		
		return lista;
	}
}