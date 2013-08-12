package cerveja.actions;

import java.util.ArrayList;
import java.util.List;

import br.piaba.piabadroid.system.world.action.Param;
import br.piaba.piabadroid.system.world.action.impl.WorldAction;
import br.piaba.piabadroid.system.world.percepts.Percept;
import br.piaba.piabadroid.system.world.percepts.PerceptUtil;


public class BeberCerveja extends WorldAction {

	@Override
	public boolean verify(PerceptUtil bbAgent) {
		Percept beer = bbAgent.getUnicPerceptByName("beer");
		int qtdBeer = beer.getIntValue();
		
		if(qtdBeer > 0){
			return true;
		}
		
		return false;
	}

	@Override
	public List<Percept> action(PerceptUtil bbAgent) {
		
		Percept perceptBeer = bbAgent.getUnicPerceptByName("beer");
		int qtdBeer = perceptBeer.getIntValue();
		
		Param velocity = getParameter("velocity");
		qtdBeer -= velocity.getIntValue();
		
		if(qtdBeer < 0){
			qtdBeer = 0;
		}
		
		perceptBeer.setValue(qtdBeer + "");
		
		List<Percept> percepts = new ArrayList<Percept>();
		percepts.add(perceptBeer);
		
		Percept ocioso = bbAgent.getUnicPercept("ocioso", getRequestorName());
		if(ocioso != null){
			ocioso.setToRemove(true);
			percepts.add(ocioso);
		}
		
		return percepts;	
	}

}
