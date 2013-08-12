package cerveja.actions;

import java.util.ArrayList;
import java.util.List;

import br.piaba.piabadroid.system.world.action.impl.WorldAction;
import br.piaba.piabadroid.system.world.percepts.Percept;
import br.piaba.piabadroid.system.world.percepts.PerceptUtil;

public class FicarOcioso extends WorldAction {

	@Override
	public List<Percept> action(PerceptUtil bbAgent) {
		List<Percept> percepcoesParaAtualizar = new ArrayList<Percept>();
		
		Percept ocioso = bbAgent.getUnicPercept("ocioso", getRequestorName());
		
		if(ocioso == null){
			ocioso = new Percept("ocioso", "0");
			ocioso.setRelatedAgent(getRequestorName());
		}else{
			ocioso.setValue(ocioso.getIntValue() + 1 + "");
		}
		
		percepcoesParaAtualizar.add(ocioso);
		percepcoesParaAtualizar.addAll(acoesConcluidas(bbAgent));
		return percepcoesParaAtualizar;
	}

	private List<Percept> acoesConcluidas(PerceptUtil bbAgent) {
		List<Percept> percepts = new ArrayList<Percept>();
		
		Percept varrerCasa = bbAgent.getUnicPercept("varrerCasa", getRequestorName());
		Percept descansar = bbAgent.getUnicPercept("descansar", getRequestorName());
		Percept colocarLixoParaFora = bbAgent.getUnicPercept("colocarLixoParaFora", getRequestorName());
		Percept lerLivro = bbAgent.getUnicPercept("lerLivro", getRequestorName());
		
		if(varrerCasa != null){
			varrerCasa.setToRemove(true);
			percepts.add(varrerCasa);
		}else if(descansar != null){
			descansar.setToRemove(true);
			percepts.add(descansar);
		}else if(colocarLixoParaFora != null){
			colocarLixoParaFora.setToRemove(true);
			percepts.add(colocarLixoParaFora);
		}else if(lerLivro != null){
			lerLivro.setToRemove(true);
			percepts.add(lerLivro);
		}
		
		Percept interrompeu = bbAgent.getUnicPercept("interrompeuAcao", getRequestorName());
		if(interrompeu != null){
			interrompeu.setToRemove(true);
			percepts.add(interrompeu);
		}
		
		return percepts;
	}

}
