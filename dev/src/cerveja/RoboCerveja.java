package cerveja;

import br.piaba.piabadroid.system.agent.GenericAgent;
import br.piaba.piabadroid.system.world.action.impl.WorldAction;
import br.piaba.piabadroid.system.world.percepts.Percept;
import cerveja.actions.ColocarLixoParaFora;
import cerveja.actions.Descansar;
import cerveja.actions.FicarOcioso;
import cerveja.actions.LerLivro;
import cerveja.actions.VarrerCasa;

public class RoboCerveja extends GenericAgent{

	public void executeAgent() {
		WorldAction acaoQueEstaFazendoNoMomento = getAcaoQueEstaFazendoNoMomento();
		
		if(acaoQueEstaFazendoNoMomento != null){
			addAction(acaoQueEstaFazendoNoMomento);
		}else{
			if(isDescansado()){
				fazerAlgumaCoisa();
			}else{
				WorldAction ficarOcioso = new FicarOcioso();
				addAction(ficarOcioso);
			}
		}
	}

	private WorldAction getAcaoQueEstaFazendoNoMomento() {
		WorldAction retorno = null;
		
		Percept varrerCasa = getMyPerceptsUtil().getUnicPerceptByName("varrerCasa");
		Percept descansar = getMyPerceptsUtil().getUnicPerceptByName("descansar");
		Percept colocarLixoParaFora = getMyPerceptsUtil().getUnicPerceptByName("colocarLixoParaFora");
		Percept lerLivro = getMyPerceptsUtil().getUnicPerceptByName("lerLivro");
		
		if(varrerCasa != null && !verificaSeAcaoEstaConcluida(varrerCasa)){
			retorno = new VarrerCasa();
		}else if(descansar != null && !verificaSeAcaoEstaConcluida(descansar)){
			retorno = new Descansar();
		}else if(colocarLixoParaFora != null && !verificaSeAcaoEstaConcluida(colocarLixoParaFora)){
			retorno = new ColocarLixoParaFora();
		}else if(lerLivro != null && !verificaSeAcaoEstaConcluida(lerLivro)){
			retorno = new LerLivro();
		}
		
		return retorno;
	}

	private boolean verificaSeAcaoEstaConcluida(Percept percepcaoDaAcao) {
		if(percepcaoDaAcao != null){
			Percept perceptQtdCiclosDuracaoAcao = getPerceptUtil().getPerceptsByName("qtdCiclosDuracaoAcao").get(0);
			int qtdCiclosDuracao = perceptQtdCiclosDuracaoAcao.getIntValue();
			
			if(percepcaoDaAcao.getIntValue() >= qtdCiclosDuracao){
				return true; //Ação concluiu sua execução
			}
		}	
		return false;
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
		}else{
			return false;
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
