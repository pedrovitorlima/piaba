package br.piaba.piabadroid.system.world.action;

import br.piaba.piabadroid.system.world.percepts.PerceptUtil;

public interface Mio {

	boolean verify(PerceptUtil bbAgent);
	
	void updateEmotions(PerceptUtil bbAgent);
}
