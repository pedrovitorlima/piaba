package br.piaba.piabadroid.system.world.action.impl;

import br.piaba.piabadroid.system.agent.GenericAgent;
import br.piaba.piabadroid.system.world.action.Mio;


public abstract class MioAction implements Mio{
	
	private int cycleFrequency = 0;

	@Override
	public boolean verify(GenericAgent bbAgent) {
		return true;
		
	}

	public int getCycleFrequency() {
		return cycleFrequency;
	}

	public void setCycleFrequency(int cycleFrequency) {
		this.cycleFrequency = cycleFrequency;
	}	

	
}
