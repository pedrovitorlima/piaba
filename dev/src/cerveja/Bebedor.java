package cerveja;

import java.util.ArrayList;
import java.util.List;

import br.piaba.piabadroid.system.agent.GenericAgent;
import br.piaba.piabadroid.system.world.action.Param;
import br.piaba.piabadroid.system.world.action.impl.WorldAction;
import br.piaba.piabadroid.system.world.percepts.Percept;
import cerveja.actions.DrinkWorldAction;
import cerveja.actions.FicarOcioso;

public class Bebedor extends GenericAgent{
	
	
	public void executeAgent() {
	
		if(existeCervejaParaBeber()){
			beberCerveja();
		}else{
			ficarOcioso();
		}
		
	}

	private void ficarOcioso() {
		FicarOcioso ficarOcioso = new FicarOcioso();
		addAction(ficarOcioso);
	}

	private boolean existeCervejaParaBeber() {
		Percept perceptBeer = getPerceptUtil().getUnicPerceptByName("beer");
		if(perceptBeer != null && perceptBeer.getIntValue() > 0){
			return true;
		}
		return false;
	}

	private void beberCerveja() {
		Percept velocity = getPerceptUtil().getUnicPerceptByName("velocity");
		
		Param parameter = new Param();
		parameter.setName("velocity");
		parameter.setValue(velocity.getValue());
		
		List<Param> parameters = new ArrayList<Param>();
		parameters.add(parameter);
		
		WorldAction drink = new DrinkWorldAction();
		drink.setParameters(parameters);
		
		addAction(drink);
	}

}
