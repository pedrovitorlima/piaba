package cerveja;

import java.util.ArrayList;
import java.util.List;

import br.piaba.piabadroid.system.agent.GenericAgent;
import br.piaba.piabadroid.system.world.action.Param;
import br.piaba.piabadroid.system.world.action.impl.WorldAction;
import br.piaba.piabadroid.system.world.percepts.Percept;
import cerveja.actions.DrinkWorldAction;

public class Bebedor extends GenericAgent{
	
	
	public void executeAgent() {
	
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
