package br.piaba.piabadroid.system.world.action;

import br.piaba.piabadroid.system.agent.GenericAgent;

public interface Mio {

	boolean verify(GenericAgent agent);
	
	void updateEmotions(GenericAgent agent);
}
