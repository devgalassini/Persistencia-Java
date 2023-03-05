package qintess.academiajava.aplicacao;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.swing.JOptionPane;

import qintess.academiajava.classes.dal.EventosDao;
import qintess.academiajava.classes.models.Evento;
import qintess.academiajava.repositorio.Repositorio;

public class AppEventos {
	public static void main(String[] args) {
		while(true) {//estrutura de repetição infinita
			String opcoes = "Escolha uma opçao:\n\n 1 - incluir\n2- buscar"
					+ "\n3- listar\n4- excluir\n5 - sair";
			int opcao = Integer.parseInt(JOptionPane.showInputDialog(opcoes));
			
			switch (opcao) {
				case 1:  incluirEvento(); break;
				case 2: buscarEVento(); break;
				case 3: listarEventos(); break;
				case 4: excluirEvento(); break;
				default: return;
			}
		}
	}
	
	private static void incluirEvento() {
		try {
			String descricao = JOptionPane.showInputDialog("Informe a Descrição:");
			String reponsavel = JOptionPane.showInputDialog("Informe o responsável:");
			Date data = new SimpleDateFormat("dd/MM/yyyy")
					.parse(JOptionPane.showInputDialog("Informe a data (dd/mm/aaaa)"));
			double preco = Double.parseDouble(JOptionPane.showInputDialog("Informe o preco"));
			
			Evento evento = new Evento();
			evento.setDescricao(descricao);
			evento.setResponsavel(reponsavel);
			evento.setData(data);
			evento.setPreco(preco);
			
			//instancia da classe EventosDao
			EventosDao dao = Repositorio.getEventosDao();
			dao.incluir(evento);
			
			JOptionPane.showMessageDialog(null, "Evento incluído com sucesso.");
						
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}
	
	private static void buscarEVento() {
		try {
			int id = Integer.parseInt(JOptionPane.showInputDialog("Informe o ID do evento:"));
			Evento evento = Repositorio.getEventosDao().buscar(id);
			
			if(evento == null) {
				JOptionPane.showMessageDialog(null, "Nenhum evento com este ID. ");
			}else {
				String resposta = "Dados do evento:" +
						"\nID: " + evento.getId() +
						"\nDescrição: " + evento.getDescricao() +
						"\nResponsável: " + evento.getResponsavel() + 
						"\nData: " + new SimpleDateFormat("dd/MM/yyyy").format(evento.getData()) + 
						"\nPreco: " + NumberFormat.getCurrencyInstance().format(evento.getPreco());
				
				JOptionPane.showMessageDialog(null, resposta);						
						
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
		
	}
	
	private static void listarEventos() {
		try {
			Collection<Evento> eventos = Repositorio.getEventosDao().listar();
			System.out.println("Lista de Eventos: ");
			eventos.forEach(s -> System.out.println(s));
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}
	
	private static void excluirEvento() {
		try {
			int id = Integer.parseInt(JOptionPane
					.showInputDialog("Informe o ID evento a ser removido"));
			int n = new EventosDao().excluir(id);
			JOptionPane.showMessageDialog(null, "Eventos excluídos: " + n);
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}
	
}
