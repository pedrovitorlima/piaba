package br.piaba.piabadroid.system.agent.executor;

import java.util.List;
import java.util.Map;

import br.piaba.piabadroid.system.agent.Agent;
import br.piaba.piabadroid.system.agent.GenericAgent;
import br.piaba.piabadroid.system.world.action.impl.WorldAction;

public interface Executor {

	public Map<Agent, List<WorldAction>> execute();
	
	public void setAgents(List<GenericAgent> agents);
	
	public void init();
}
