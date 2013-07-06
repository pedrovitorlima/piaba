package br.piaba.piabadroid.system.agent.executor;

import java.util.List;

import br.piaba.piabadroid.system.agent.GenericAgent;
import br.piaba.piabadroid.system.world.action.impl.MioAction;

public abstract class GenericExecutor implements Executor {

	private List<GenericAgent> agents;
	private List<MioAction> mios;
	
	public void setAgents(List<GenericAgent> agents) {
		this.agents = agents;
	}

	public List<GenericAgent> getAgents(){
		return this.agents;
	}

	public List<MioAction> getMios() {
		return mios;
	}

	public void setMios(List<MioAction> mios) {
		this.mios = mios;
	}
	
	
	
}
