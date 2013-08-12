package br.piaba.piabadroid.system.agent;

import java.util.ArrayList;
import java.util.List;

import br.piaba.piabadroid.system.world.action.impl.WorldAction;
import br.piaba.piabadroid.system.world.percepts.Percept;
import br.piaba.piabadroid.system.world.percepts.PerceptUtil;

/**
 * Classe de agente genérica que implementa Agent.
 * @author pedrovitorlima
 * **/
public abstract class GenericAgent implements Agent{

	/**
	 * Nome do agente
	 * **/
	private String name;
	
	private int cycle = 0;
	
	private PerceptUtil perceptUtil;
	
	private boolean execute;
	
	private String type;
	
	private List<WorldAction> oldActions;
	
	private static final String emotions[] = {"satisfaction", "fear", "relief", "disapointment", "joy", "distress", "gratification", "remorse", "gratitude", "anger"};
	
	public void init(){
		execute = false;
		oldActions = new ArrayList<WorldAction>();
		perceptUtil = new PerceptUtil(getPercepts());
	}
	
	public void setExecute(boolean execute){
		this.execute = execute;
	}
	
	public boolean isExecute(){
		return this.execute;
	}
	
	public PerceptUtil getPerceptUtil(){
		return this.perceptUtil;
	}
	
	public PerceptUtil getMyPerceptsUtil(){
		List<Percept> allMyPercepts = new ArrayList<Percept>();
		for(Percept myPercept : getPerceptUtil().getPercepts()){
			if(myPercept.getRelatedAgent().equals(this.name)){
				allMyPercepts.add(myPercept);
			}
		}
		
		return new PerceptUtil(allMyPercepts);
		
	}
	
	public PerceptUtil getMyEmotionalState(){
		List<Percept> allMyEmotionalState = new ArrayList<Percept>();
		
		for(Percept myPercept : getPercepts()){
			if(myPercept.isSelf() && isEmotion(myPercept)){
				allMyEmotionalState.add(myPercept);
			}
		}
		
		return new PerceptUtil(allMyEmotionalState);
	}
	
	private boolean isEmotion(Percept myPercept) {
		for(int i=0; i<emotions.length; i++){
			if(emotions[i].equals(myPercept.getName().toLowerCase())){
				return true;
			}
		}
		return false;
	}

	/**
	 * Referência para o mapa de ações que serão processadas pelo mundo.
	 * **/
	private List<WorldAction> actions;
		
	
	public void setChanceExecution() {
		
	}

	public void setCycleExecution() {
		
		
	}
	
	public List<WorldAction> getActions() {
		return actions;
	}

	public void setActions(List<WorldAction> actions) {
		this.actions = actions;
	}
	
	public void addAction(WorldAction action){
		action.setRequestorName(getName());
		this.actions.add(action);
	}
	
	/**
	 * Método que deve ser chamado ao final de cada ciclo para definir as ações a serem 
	 * executadas pelo mundo.
	 * 
	 * @param defineActions Ações que serão definidas para execução no mundo, nesse ciclo
	 * 
	 * **/
	public void defineActions(ArrayList<WorldAction> defineActions){
		actions.clear();
		actions.addAll(defineActions);
	}

	/**
	 * Percepções do agente
	 * @see Percept
	 * **/
	private List<Percept> percepts;
	
	/**
	 * Configura o nome do agente
	 * @param name Nome do agente
	 * **/
	public void setName(String name){
		this.name = name;
	}
	
	
	/**
	 * Retorna o nome do agente
	 * @return nome do agente
	 * **/
	public String getName(){
		return this.name;
	}
	
	/**
	 * Retorna percepções do agente
	 * @return Lista de percepções
	 * @see #percepts
	 * **/
	public List<Percept> getPercepts(){
		return this.percepts;
	}

	/**
	 * Define percepções do agente
	 * @param percepts Lista de {@link Percept}
	 * **/ 
	public void setPercepts(List<Percept> percepts){
		this.percepts = percepts;
	}
	
	public void perceptWorld(List<Percept> worldPercepts){
		for(Percept worldPercept : worldPercepts){
			String namePercept = worldPercept.getName();
			String valuePercept = worldPercept.getValue();
			String worldPerceptRelatedAgent = worldPercept.getRelatedAgent();
			
			//Adiciona as percepções ao agente, caso ainda não tenha
			boolean hasPercept = false;
			for(Percept agentPercept : getPercepts()){
				String agentPerceptRelatedAgent = agentPercept.getRelatedAgent();
				
				if(agentPercept.getName().equals(namePercept) && agentPerceptRelatedAgent.equals(worldPerceptRelatedAgent)){
					hasPercept = true;
					break;
				}
			}
			
			if(!hasPercept){
				getPerceptUtil().addPercept(worldPercept.clone());
			}else{
				//Atualiza percepções existentes
				Percept unicPercept = getPerceptUtil().getUnicPercept(namePercept, worldPerceptRelatedAgent);
				if(unicPercept != null){
					try{
						getPerceptUtil().updatePercept(namePercept, valuePercept, worldPerceptRelatedAgent);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
			
		}
		
		//Remove as percepções do agente que não existem no mundo
		List<Percept> perceptsToRemove = new ArrayList<Percept>();
		for(Percept percept : getPercepts()){
			
			if(!percept.isSelf()){
				boolean hasPercept = false;
				
				for(Percept worldPercept : worldPercepts){
					if(worldPercept.equals(percept)){
						hasPercept = true;
						break;
					}
				}
				
				if(!hasPercept){
					perceptsToRemove.add(percept);
				}
			}
		}
		
		for(Percept percept : perceptsToRemove){
			getPerceptUtil().removePercept(percept);
		}
		
		
	}
	
	
	/**
	 * Construtor padrão. Instancia {@link #percepts}
	 * **/
	public GenericAgent(){
		percepts = new ArrayList<Percept>();
	}
	
	public void run() {
		while(true){
			if(execute){
				executeAgent();
				cycle++;
				execute = false;
			}
		}
	}
	
	public int getCycle(){
		return this.cycle;
	}
	
	public void addOldAction(WorldAction action){
		this.oldActions.add(action);
	}
	
	public void clearOldActions(){
		this.oldActions.clear();
	}
	
	public void clearActions(){
		this.actions.clear();
	}
	
	@Override
	public List<WorldAction> getOldActions(){
		return this.oldActions;
	}
	
	@Override
	public String getType(){
		return this.type;
	}
	
	public void setType(String type){
		this.type = type;
	}
}
