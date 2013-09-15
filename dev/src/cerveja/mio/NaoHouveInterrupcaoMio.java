package cerveja.mio;

import br.piaba.piabadroid.system.agent.GenericAgent;
import br.piaba.piabadroid.system.world.action.impl.MioAction;
import br.piaba.piabadroid.system.world.percepts.Percept;

public class NaoHouveInterrupcaoMio extends MioAction {

	@Override
	public boolean verify(GenericAgent agent) {
		Percept interrupcao = agent.getPerceptUtil().getUnicPercept("interrompeuAcao", agent.getName());
		Percept distress = agent.getMyEmotionalState().getUnicPerceptByName("distress");
		
		if(interrupcao != null && distress.getIntValue() - 1 >= 0){
			return true;
		}
		
		return false;
	}

	@Override
	public void updateEmotions(GenericAgent agent) {
		Percept distress = agent.getMyEmotionalState().getUnicPerceptByName("distress");
		
		agent.getMyEmotionalState().updatePercept("distress", distress.getIntValue() - 1);
	}

}
