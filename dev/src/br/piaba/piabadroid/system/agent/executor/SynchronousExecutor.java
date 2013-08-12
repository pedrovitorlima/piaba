package br.piaba.piabadroid.system.agent.executor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.piaba.piabadroid.system.agent.Agent;
import br.piaba.piabadroid.system.agent.GenericAgent;
import br.piaba.piabadroid.system.world.action.impl.MioAction;
import br.piaba.piabadroid.system.world.action.impl.WorldAction;

public class SynchronousExecutor extends GenericExecutor {

	private Map<Agent, List<WorldAction>> perceptsUpdate;
	
	public Map<Agent, List<WorldAction>> execute() {
		
		for(GenericAgent agent : getAgents()){
			agent.executeAgent();
		}
		
		for(GenericAgent agent : getAgents()){
			for(MioAction mio : getMios()){
				if(getCycle() % mio.getCycleFrequency() == 0){
					if(mio.verify(agent)){
						mio.updateEmotions(agent);
					}
				}
			}
		}
		
		return perceptsUpdate;
	}
	
	public void init(){
		List<GenericAgent> agents = getAgents();
		perceptsUpdate = new HashMap<Agent, List<WorldAction>>();
		
		for(GenericAgent agent : agents){
			List<WorldAction> actions = new ArrayList<WorldAction>();
			
 			agent.setActions(actions);
 			perceptsUpdate.put(agent, actions);	
			
		}
	}

}
