package cerveja;

import br.piaba.piabadroid.system.agent.GenericAgent;
import br.piaba.piabadroid.system.world.action.impl.WorldAction;
import br.piaba.piabadroid.system.world.percepts.Percept;
import cerveja.actions.ColocarLixoParaFora;
import cerveja.actions.Descansar;
import cerveja.actions.LerLivro;
import cerveja.actions.VarrerCasa;

public class RoboCerveja extends GenericAgent{

	public void executeAgent() {
		if(isDescansado()){
			fazerAlgumaCoisa();
		}
	}

	private void fazerAlgumaCoisa() {
		int numeroDaAcao = (int) (5*Math.random());
		
		switch (numeroDaAcao){
		case 1: 
			WorldAction varrerCasa = new VarrerCasa();
			addAction(varrerCasa);
			break;
		case 2:
			WorldAction descansar = new Descansar();
			addAction(descansar);
			break;
		case 3:
			WorldAction colocarLixoParaFora = new ColocarLixoParaFora();
			addAction(colocarLixoParaFora);
			break;
		case 4:
			WorldAction lerLivro = new LerLivro();
			addAction(lerLivro);
			break;
		}
			
	}

	private boolean isDescansado(){
		Percept ocioso = getOciosidade();
		if(ocioso != null){
			int qtdOciosidade = ocioso.getIntValue();
			if(qtdOciosidade >= 3){
				return true;
			}
		}
		
		return false;
	}
	
	private Percept getOciosidade() {
		Percept ocioso = getMyPerceptsUtil().getUnicPerceptByName("ocioso");
		return ocioso;
	}
	
	public void entregarCerveja(){
		
	}
	
	
}
