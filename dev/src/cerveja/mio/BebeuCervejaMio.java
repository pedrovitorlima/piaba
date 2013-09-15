package cerveja.mio;

import br.piaba.piabadroid.system.agent.GenericAgent;
import br.piaba.piabadroid.system.world.action.impl.MioAction;
import br.piaba.piabadroid.system.world.percepts.Percept;

public class BebeuCervejaMio extends MioAction{

	@Override
	public void updateEmotions(GenericAgent agent) {
		Percept bebedor = agent.getPerceptUtil().getUnicPerceptByName("bebedor");
		if(bebedor != null && bebedor.getIntValue() == 1){
		
			Percept distress = agent.getMyEmotionalState().getUnicPerceptByName("distress");
			int distressValue = distress.getIntValue();
			
			Percept beer = agent.getPerceptUtil().getUnicPerceptByName("beer");
			int qtdBeer = beer.getIntValue();
			
			if(qtdBeer > 0){
				distressValue = distressValue -1;
				if(distressValue < 0){
					distressValue = 0;
				}
			}else{
				distressValue = distressValue +1;
				if(distressValue > 100){
					distressValue = 100;
				}
			}
			
			agent.getMyEmotionalState().updatePercept("distress", distressValue);
		}
		
	}
}
