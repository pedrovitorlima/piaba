package cerveja;

import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import br.piaba.piabadroid.R;
import br.piaba.piabadroid.system.world.gui.GenericCycleUpdateGUI;
import br.piaba.piabadroid.system.world.percepts.Percept;

public class CervejaCycleUpdateGUI extends GenericCycleUpdateGUI{
	
	public void run() {
		TextView texto_progresso_robo_1 = (TextView) getActivity().findViewById(R.id.texto_progresso_robo_1);
		ProgressBar progresso_acao_robo1 = (ProgressBar) getActivity().findViewById(R.id.progresso_robo_1);
		ProgressBar stress_robo1 = (ProgressBar) getActivity().findViewById(R.id.stress_robo_1);
		
		String acaoAtualDoRobo1 = getAcaoAtual("robo_1");
		int valorDoProgressoAcaoRobo1 = getPorcentagemDeConcusaoDaAcaoAtual("robo_1");
		
		int nivelDeStressRobo1 = getNivelStress("robo_1");
		
		progresso_acao_robo1.setProgress(valorDoProgressoAcaoRobo1);
		texto_progresso_robo_1.setText(acaoAtualDoRobo1);
		stress_robo1.setProgress(nivelDeStressRobo1);
		
		TextView texto_progresso_robo_2 = (TextView) findViewById(R.id.texto_progresso_robo_2);
		ProgressBar progresso_acao_robo2 = (ProgressBar) findViewById(R.id.progresso_robo_2);
		ProgressBar stress_robo2 = (ProgressBar) getActivity().findViewById(R.id.stress_robo_2);
		
		String acaoAtualDoRobo2 = getAcaoAtual("robo_2");
		int valorDoProgressoAcaoRobo2 = getPorcentagemDeConcusaoDaAcaoAtual("robo_2");
		
		int nivelDeStressRobo2 = getNivelStress("robo_2");
		
		progresso_acao_robo2.setProgress(valorDoProgressoAcaoRobo2);
		texto_progresso_robo_2.setText(acaoAtualDoRobo2);
		stress_robo2.setProgress(nivelDeStressRobo2);
	
		ProgressBar beer = (ProgressBar) findViewById(R.id.nivel_beer);
		beer.setProgress(getQtdBeer());
		
		ImageView bebedor = (ImageView) findViewById(R.id.drinker);
		
		if(bebendoCerveja()){
			bebedor.setImageResource(R.drawable.drinker_drinking);
		}else{
			bebedor.setImageResource(R.drawable.drinker);					
		}
		
		
	}

	private int getNivelStress(String agente) {
		Percept distress = getWorldData().getAgent(agente).getPerceptUtil().getUnicPerceptByName("distress");
		
		if(distress == null){
			return 0;
		}
		
		return distress.getIntValue();
	}

	private int getQtdBeer() {
		Percept beer = getWorldData().getAgent("bebedor").getPerceptUtil().getUnicPerceptByName("beer");
		
		if(beer != null){
			return beer.getIntValue();
		}
		
		return 0;
	}

	private boolean bebendoCerveja() {
		Percept ocioso = getWorldData().getAgent("bebedor").getMyPerceptsUtil().getUnicPerceptByName("ocioso");
		
		return ocioso == null;
	}

	private int getPorcentagemDeConcusaoDaAcaoAtual(String agent) {
		Percept percepcaoQtdCiclosDuracaoAcao = getWorldData().getAgent(agent).getPerceptUtil().getUnicPerceptByName("qtdCiclosDuracaoAcao");
		double qtdCiclosDuracaoAcao = percepcaoQtdCiclosDuracaoAcao.getIntValue();
		
		Percept varrerCasa = getWorldData().getAgent(agent).getMyPerceptsUtil().getUnicPerceptByName("varrerCasa");
		Percept descansar = getWorldData().getAgent(agent).getMyPerceptsUtil().getUnicPerceptByName("descansar");
		Percept colocarLixoParaFora = getWorldData().getAgent(agent).getMyPerceptsUtil().getUnicPerceptByName("colocarLixoParaFora");
		Percept lerLivro = getWorldData().getAgent(agent).getMyPerceptsUtil().getUnicPerceptByName("lerLivro");
		Percept ocioso = getWorldData().getAgent(agent).getMyPerceptsUtil().getUnicPerceptByName("ocioso");
		double qtdCiclosDaAcaoAtual = 0;
		
		if(varrerCasa != null){
			qtdCiclosDaAcaoAtual = varrerCasa.getIntValue();
		}else if(descansar != null){
			qtdCiclosDaAcaoAtual = descansar.getIntValue();				
		}else if(colocarLixoParaFora != null){
			qtdCiclosDaAcaoAtual = colocarLixoParaFora.getIntValue();				
		}else if(lerLivro != null){
			qtdCiclosDaAcaoAtual = lerLivro.getIntValue();				
		}else if(ocioso != null){
			qtdCiclosDaAcaoAtual = ocioso.getIntValue();
			new Double(qtdCiclosDaAcaoAtual/3 * 100).intValue();
		}
					
		return new Double(qtdCiclosDaAcaoAtual/qtdCiclosDuracaoAcao * 100).intValue();
	}

	private String getAcaoAtual(String agent) {
		
		Percept varrerCasa = getWorldData().getAgent(agent).getMyPerceptsUtil().getUnicPerceptByName("varrerCasa");
		Percept descansar = getWorldData().getAgent(agent).getMyPerceptsUtil().getUnicPerceptByName("descansar");
		Percept colocarLixoParaFora = getWorldData().getAgent(agent).getMyPerceptsUtil().getUnicPerceptByName("colocarLixoParaFora");
		Percept lerLivro = getWorldData().getAgent(agent).getMyPerceptsUtil().getUnicPerceptByName("lerLivro");
		Percept ocioso = getWorldData().getAgent(agent).getMyPerceptsUtil().getUnicPerceptByName("ocioso");
		
		if(varrerCasa != null){
			return "Varrendo a casa...";
		}else if(descansar != null){
			return "Descansando...";
		}else if(colocarLixoParaFora != null){
			return "Reciclando lixo...";
		}else if(lerLivro != null){
			return "Estudando...";
		}else if(ocioso != null){
			return "Ocioso...";
		}
		
		return null;
	}
}
