package br.piaba.piabadroid.system.world.action.impl;

import java.util.ArrayList;
import java.util.List;

import br.piaba.piabadroid.system.agent.Agent;
import br.piaba.piabadroid.system.world.action.Action;
import br.piaba.piabadroid.system.world.action.Param;
import br.piaba.piabadroid.system.world.percepts.PerceptUtil;

/**
 * Representa uma internal action. A solicitação de execução dessa ação é realizada
 * pelos agentes, e como resultado uma lista de percepções do mundo alteradas é retornada para o mundo.
 * 
 * @author pedrovitorlima
 * **/
public abstract class WorldAction implements Action{
	
	private boolean executed;
	private List<Param> parameters = new ArrayList<Param>();
	private String requestorName;
	
	public boolean isExecuted() {
		return executed;
	}

	public void setExecuted(boolean executed) {
		this.executed = executed;
	}
	
	@Override
	public boolean verify(PerceptUtil bbAgent){
		return true;
	}
	
	public void addParam(Param param){
		getParameters().add(param);
	}
			
	public List<Param> getParameters() {
		return parameters;
	}

	public void setParameters(List<Param> parameters) {
		this.parameters = parameters;
	}
	
	
	public String getRequestorName() {
		return requestorName;
	}

	public void setRequestorName(String requestorName) {
		this.requestorName = requestorName;
	}

	public Param getParameter(String name){
		for(Param param : parameters){
			if(param.getName().equals(name)){
				return param;
			}
		}
		
		return null;
	}

	public String toString(){
		StringBuffer str = new StringBuffer();

		str.append(getClass().toString());
		str.append("(");
		
		int count = 0;
		for(Param param : getParameters()){
			if(count > 0){
				str.append(", ");
			}
			
			str.append(param);
			count++;
		}
		
		str.append(")");
		str.append(" => ");
		str.append(executed);
		
				
		return str.toString();
	}
}
