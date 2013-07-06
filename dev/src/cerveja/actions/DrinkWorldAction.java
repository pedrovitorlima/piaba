package cerveja.actions;

import java.util.ArrayList;
import java.util.List;

import br.piaba.piabadroid.system.world.action.Param;
import br.piaba.piabadroid.system.world.action.impl.WorldAction;
import br.piaba.piabadroid.system.world.percepts.Percept;
import br.piaba.piabadroid.system.world.percepts.PerceptUtil;


public class DrinkWorldAction extends WorldAction {

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
		int qtdBeer = bbAgent.getUnicPerceptByName("beer").getIntValue();
		
		Param velocity = getParameter("velocity");
		qtdBeer -= velocity.getIntValue();
		
		if(qtdBeer < 0){
			qtdBeer = 0;
		}
		
		List<Percept> percepts = new ArrayList<Percept>();
		percepts.add(new Percept("beer", new Integer(qtdBeer).toString()));
		return percepts;	
	}

}
