package cerveja.actions;

import java.util.ArrayList;
import java.util.List;

import br.piaba.piabadroid.system.world.action.impl.WorldAction;
import br.piaba.piabadroid.system.world.percepts.Percept;
import br.piaba.piabadroid.system.world.percepts.PerceptUtil;

public class VarrerCasa extends WorldAction {

	@Override
	public boolean verify(PerceptUtil bbAgent){
		Percept ocioso = bbAgent.getUnicPercept("ocioso", getRequestorName());
		Percept acaoEmExecucao = bbAgent.getUnicPercept("varrerCasa", getRequestorName());
		Percept qtdCiclosDuracaoAcao = bbAgent.getPerceptsByName("qtdCiclosDuracaoAcao").get(0);
		
		//Se o agente estiver descansado (ocioso a três ciclos), pode executar
		if(ocioso != null){
			if(ocioso.getIntValue() >= 3){
				return true;
			}
		}
		
		//Se a ação estiver em execução, mas ainda não chegou no limite, continua executando
		if(acaoEmExecucao != null){
			if(acaoEmExecucao.getIntValue() < qtdCiclosDuracaoAcao.getIntValue()){
				return true;
			}
		}
		
		
		return false;
	}
	
	@Override
	public List<Percept> action(PerceptUtil bbAgent) {
		Percept acaoEmExecucao = bbAgent.getUnicPercept("varrerCasa", getRequestorName());
		Percept ocioso = bbAgent.getUnicPercept("ocioso", getRequestorName());
		List<Percept> percepts = new ArrayList<Percept>();
		
		if(acaoEmExecucao == null){
			acaoEmExecucao = new Percept("varrerCasa", "0");
			acaoEmExecucao.setRelatedAgent(getRequestorName());
		}else{
			acaoEmExecucao.setValue(acaoEmExecucao.getIntValue() + 1 + "");
		}
		
		percepts.add(acaoEmExecucao);
		
		if(ocioso != null){
			ocioso.setToRemove(true);
			percepts.add(ocioso);
		}
		
		Percept interrompeu = bbAgent.getUnicPercept("interrompeuAcao", getRequestorName());
		if(interrompeu != null){
			interrompeu.setToRemove(true);
			percepts.add(interrompeu);
		}
				
		return percepts;
	}

}
