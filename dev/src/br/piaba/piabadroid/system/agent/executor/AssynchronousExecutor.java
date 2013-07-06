package br.piaba.piabadroid.system.agent.executor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.piaba.piabadroid.system.agent.Agent;
import br.piaba.piabadroid.system.agent.GenericAgent;
import br.piaba.piabadroid.system.world.action.impl.WorldAction;

public class AssynchronousExecutor extends GenericExecutor {

	private List<Thread> agentExecutors;
	private Map<Agent, List<WorldAction>> perceptsUpdate;
	
	public Map<Agent, List<WorldAction>> execute() {
		
		for(GenericAgent agent : getAgents()){
			agent.setExecute(true);
		}
		
		boolean inExecution = true;
		while(inExecution){
			inExecution = false;
			
			for(GenericAgent agent : getAgents()){
				if(agent.isExecute()){
					inExecution = true;
					break;
				}
			}
		}
		
		return perceptsUpdate;
	}
	
	public void init(){
		List<GenericAgent> agents = getAgents();
		perceptsUpdate = new HashMap<Agent, List<WorldAction>>();
		
		agentExecutors = new ArrayList<Thread>();
		
		for(GenericAgent agent : agents){
			List<WorldAction> actions = new ArrayList<WorldAction>();
			
 			agent.setActions(actions);
		}
	}

}
