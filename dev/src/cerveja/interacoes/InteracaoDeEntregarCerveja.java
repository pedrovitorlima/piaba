package cerveja.interacoes;

import android.view.View;
import android.view.View.OnClickListener;
import br.piaba.piabadroid.system.agent.Agent;
import cerveja.RoboCerveja;


public class InteracaoDeEntregarCerveja implements OnClickListener{

	private Agent agent;
	
	public InteracaoDeEntregarCerveja(Agent agent){
		this.agent = agent;
	}
	
	@Override
	public void onClick(View v) {
		RoboCerveja robo = (RoboCerveja) agent;
		
		robo.solicitarQueAgenteEntregueCerveja();
	}

	

}
