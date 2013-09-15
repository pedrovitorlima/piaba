package cerveja.mio;

import br.piaba.piabadroid.system.agent.GenericAgent;
import br.piaba.piabadroid.system.world.action.impl.MioAction;
import br.piaba.piabadroid.system.world.percepts.Percept;


public class HouveInterrupcaoMio extends MioAction {

	@Override
	public boolean verify(GenericAgent agent) {
		Percept interrupcao = agent.getPerceptUtil().getUnicPercept("interrompeuAcao", agent.getName());
		
		if(interrupcao != null){
			return true;
		}
		
		return false;
	}

	@Override
	public void updateEmotions(GenericAgent agent) {
		Percept distress = agent.getMyEmotionalState().getUnicPerceptByName("distress");
		
		agent.getPerceptUtil().updatePercept("distress", distress.getIntValue() + 10 + "");
	}

}
