package br.com.servico;

import java.util.Date;
import java.util.List;

import br.com.controle.AgendaControle;
import br.com.modelo.Agenda;

public class AgendaServico {

	private AgendaControle controller;

    private static AgendaServico instance = new AgendaServico();    

    public static AgendaServico getInstance(){
          return instance;
    }

    public AgendaServico(){
          controller = new AgendaControle(); 
    }

    // Métodos   

    public void cadastrarAgenda (Agenda agenda) throws Exception {
          controller.create(agenda);
    }
   
    public void editarAgenda (Agenda agenda) throws Exception {
    	controller.edit(agenda);
    }	

    public void excluirAgenda (Agenda agenda) throws Exception {
    	controller.destroy(agenda);
    }		
    
    public Agenda findAgendaById (Integer id) throws Exception {
    	return controller.recuperarPeloID(Agenda.class,id);
    }
    
    public List<Agenda> listarAllAgenda () throws Exception {
    	return controller.list(Agenda.class);
    }
    
    public boolean findHorarios(Date data,int idmedico){
    	return controller.findHorarios(data,idmedico);

    }
    
    public List<Agenda> findPorMedicoData(Date data,int idmedico){
    	return controller.findPorMedicoData(data,idmedico);

    }
    
    public List<Agenda> findHorariosMedicoUBSEsp(Date data,int idmedico, int idesp, int idubs){
    	return controller.findHorariosMedicoUBSEsp(data, idmedico, idesp, idubs);
    }
    
    public List<Agenda> findHorariosLivres(Date data,int idmedico, int idesp, int idubs){
    	return controller.findHorariosLivres(data, idmedico, idesp, idubs);
    }
    
    public boolean findHorariosByUBS(Date data, int idmedico,int idubs){
    	return controller.findHorariosByUBS(data, idmedico, idubs);
    }
    
    public List<Agenda> horariosLivresPaciente(Date data, int idmedico, int idesp, int idubs, int idpaciente) {
    	return controller.horariosLivresPaciente(data, idmedico, idesp, idubs, idpaciente);
    }
    
}
