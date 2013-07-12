package cerveja;

import android.annotation.SuppressLint;
import android.widget.ProgressBar;
import android.widget.TextView;
import br.piaba.piabadroid.R;
import br.piaba.piabadroid.system.world.action.impl.WorldAction;
import br.piaba.piabadroid.system.world.gridWorld.GridWorld;
import br.piaba.piabadroid.system.world.percepts.Percept;

@SuppressLint("UseValueOf")
public class CervejaWorld extends GridWorld{
	
	public void initWorld(){
		super.initWorld();
	}
		

	@Override
	public void updateGUI() {
		activity.runOnUiThread(new Runnable(){

			public void run() {
				TextView texto_progresso_robo_1 = (TextView) activity.findViewById(R.id.texto_progresso_robo_1);
				ProgressBar progresso_acao_robo1 = (ProgressBar) activity.findViewById(R.id.progresso_robo_1);
				
				String acaoAtualDoRobo1 = getAcaoAtual("robo_1");
				int valorDoProgressoAcaoRobo1 = getPorcentagemDeConcusaoDaAcaoAtual("robo_1");
				
				progresso_acao_robo1.setProgress(valorDoProgressoAcaoRobo1);
				texto_progresso_robo_1.setText(acaoAtualDoRobo1);
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
			
		});
	}
	
	
}
