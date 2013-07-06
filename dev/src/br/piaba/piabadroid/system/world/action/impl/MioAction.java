package br.piaba.piabadroid.system.world.action.impl;

import br.piaba.piabadroid.system.world.action.Mio;
import br.piaba.piabadroid.system.world.percepts.PerceptUtil;


public abstract class MioAction implements Mio{

	@Override
	public boolean verify(PerceptUtil bbAgent) {
		return true;
		
	}	
	
}
