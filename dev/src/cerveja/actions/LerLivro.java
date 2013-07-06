package cerveja.actions;

import java.util.ArrayList;
import java.util.List;

import br.piaba.piabadroid.system.world.action.impl.WorldAction;
import br.piaba.piabadroid.system.world.percepts.Percept;
import br.piaba.piabadroid.system.world.percepts.PerceptUtil;

public class LerLivro extends WorldAction {

	@Override
	public List<Percept> action(PerceptUtil bbAgent) {
		List<Percept> percepts = new ArrayList<Percept>();
		percepts.add(new Percept("lerLivro", "0"));
	
		return percepts;
	}

}
