package br.com.servico;

import java.util.List;

import br.com.controle.EspecialidadeControle;
import br.com.modelo.Especialidade;


public class EspecialidadeServico {

	private EspecialidadeControle controller;

    private static EspecialidadeServico instance = new EspecialidadeServico();    

    public static EspecialidadeServico getInstance(){
          return instance;
    }

    public EspecialidadeServico(){
          controller = new EspecialidadeControle(); 
    }

    // métodos   

    public void cadastrarEspecialidade (Especialidade especialidade) throws Exception {
          controller.create(especialidade);
    }
   
    public void editarEspecialidade (Especialidade especialidade) throws Exception {
    	controller.edit(especialidade);
    }	

    public void excluirEspecialidade (Especialidade especialidade) throws Exception {
    	controller.destroy(especialidade);
    }	
     
    public void findEspByName(String nome) throws Exception {
    	controller.findByNome(nome);
    }	
    
    public Especialidade findEspecialidadeById(Integer id) throws Exception {
    	 return controller.recuperarPeloID(Especialidade.class,id);
    }
    
    public List<Especialidade> listarAllEspecialidades() throws Exception {
    	return controller.list(Especialidade.class);
    }
}

