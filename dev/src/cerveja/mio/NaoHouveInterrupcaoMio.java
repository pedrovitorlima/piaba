package cerveja.mio;

import br.piaba.piabadroid.system.agent.GenericAgent;
import br.piaba.piabadroid.system.world.action.impl.MioAction;
import br.piaba.piabadroid.system.world.percepts.Percept;

public class NaoHouveInterrupcaoMio extends MioAction {

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
		
		int novoValor = 0;
		
		if(distress.getIntValue() - 10 > 0){
			novoValor = distress.getIntValue() - 10;
		}
//		agent.getMyEmotionalState().updatePercept("distress", novoValor);
	}

}
