package cerveja;

import java.util.List;

import android.widget.TextView;
import br.piaba.piabadroid.R;
import br.piaba.piabadroid.system.agent.Agent;
import br.piaba.piabadroid.system.world.action.impl.WorldAction;
import br.piaba.piabadroid.system.world.gridWorld.GridWorld;
import br.piaba.piabadroid.system.world.percepts.Percept;
import br.piaba.piabadroid.system.world.percepts.PerceptUtil;

public class CervejaWorld extends GridWorld{
	
	public void initWorld(){
		super.initWorld();
	}
	
	@Override
	public void cycle(){
		List<Percept> percepts = getPerceptUtil().getPerceptsByName("colocarLixoParaFora");
		tratarAcaoDosRobos(percepts);
	
		percepts = getPerceptUtil().getPerceptsByName("descansar");
		tratarAcaoDosRobos(percepts);
		
		percepts = getPerceptUtil().getPerceptsByName("lerLivro");
		tratarAcaoDosRobos(percepts);
		
		percepts = getPerceptUtil().getPerceptsByName("varrerCasa");
		tratarAcaoDosRobos(percepts);
	
		for(Agent agent : getWorldData().getAgents()){
			verificaOcioAgente(agent);
		}
		
		super.cycle();
	}
	
	private void verificaOcioAgente(Agent agent) {
		PerceptUtil agentRelatedPerceptUtil = agent.getMyPerceptsUtil();
		
		Percept colocarLixoParaFora = agentRelatedPerceptUtil.getUnicPerceptByName("colocarLixoParaFora");
		Percept descansar = agentRelatedPerceptUtil.getUnicPerceptByName("descansar");
		Percept lerLivro = agentRelatedPerceptUtil.getUnicPerceptByName("lerLivro");
		Percept varrerCasa = agentRelatedPerceptUtil.getUnicPerceptByName("varrerCasa");
		
		Percept percepcaoOcioso = getPerceptUtil().getUnicPercept("ocioso", agent.getName());
		
		if(colocarLixoParaFora == null &&
				descansar == null &&
				lerLivro == null &&
				varrerCasa == null){
						
			if(percepcaoOcioso == null){
				Percept percept = new Percept("ocioso", 0+"");
				percept.setRelatedAgent(agent.getName());
				getPerceptUtil().addPercept(percept);
			}else{
				aumentarProgressoDaTarefaDoAgente(percepcaoOcioso);
			}
		}else{
			aumentarProgressoDaTarefaDoAgente(colocarLixoParaFora);
			aumentarProgressoDaTarefaDoAgente(descansar);
			aumentarProgressoDaTarefaDoAgente(lerLivro);
			aumentarProgressoDaTarefaDoAgente(varrerCasa);
			
			getPerceptUtil().removePercept(percepcaoOcioso);
		}
	}

	private void aumentarProgressoDaTarefaDoAgente(Percept progressoAtual) {
		if(progressoAtual != null){
			int ciclosProgressoAtual = progressoAtual.getIntValue();
			ciclosProgressoAtual++;
			getPerceptUtil().updatePercept(progressoAtual.getName(), ciclosProgressoAtual + "", progressoAtual.getRelatedAgent());
		}
	}

	private void tratarAcaoDosRobos(List<Percept> acoesDosAgentesEmAndamento) {
		Percept perceptQtdCiclosQueUmaAcaoDeveDurar = getPerceptUtil().getUnicPerceptByName("qtdCiclosDuracaoAcao");
		int qtdCiclosQueUmaAcaoDeveDurar = perceptQtdCiclosQueUmaAcaoDeveDurar.getIntValue();
		
		for(Percept percept : acoesDosAgentesEmAndamento){
			int qtdCiclosAteAgora = percept.getIntValue();
			
			if(qtdCiclosAteAgora >= qtdCiclosQueUmaAcaoDeveDurar){
				getPerceptUtil().removePercept(percept);
			}else{
				int ciclosAteOMomento = percept.getIntValue();
				percept.setValue(ciclosAteOMomento++ + "");
			}
		}
	}

	@Override
	public void updateGUI() {
		activity.runOnUiThread(new Runnable(){

			public void run() {
				TextView deliverLog = (TextView) activity.findViewById(R.id.deliverLog);
				TextView drinkLog = (TextView) activity.findViewById(R.id.drinkerLog);
				
				String deliverStr = "";
				String deliverActions = "Ações do ciclo anterior: ";
				
				String drinkerStr = "";
				String drinkerActions = "Ações do ciclo anterior: ";
				
				for(Percept percept : getWorldData().getAgent("robo").getPerceptUtil().getPercepts()){
					deliverStr += percept.toString() + "\n";
				}
				
				for(WorldAction WorldAction : getWorldData().getAgent("robo").getOldActions()){
					deliverActions += WorldAction.toString() + ", ";
				}
				
				deliverActions += "\nAções do ciclo atual: ";
				
				for(Percept percept : getWorldData().getAgent("bebedor").getPerceptUtil().getPercepts()){
					drinkerStr += percept.toString() + "\n";
				}
				
				for(WorldAction WorldAction : getWorldData().getAgent("bebedor").getOldActions()){
					drinkerActions += WorldAction.toString() + ", ";
				}
				
				deliverLog.setText(deliverStr + "\n" + deliverActions);
				drinkLog.setText(drinkerStr + "\n" + drinkerActions);
				
				
			}
			
		});
	}
	
	
}
