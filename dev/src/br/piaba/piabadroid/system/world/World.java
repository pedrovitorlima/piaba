package br.piaba.piabadroid.system.world;



/**
 * Classe que representa o mundo que, nesse caso, se aplica a uma representação
 * do ambiente de trabalho dos funcionários da equipe.
 * 
 * @author pedrovitorlima
 * **/
public interface World {

	
	/**
	 * Inicializa parâmetros do {@link World}, lendo os eventos internos e externos
	 * definodos nos arquivos XML para o aplicativo.
	 * Entre outras coisas, cria dependência Agent->World e World->Agent.
	 * 
	 * **/
	public void initWorld();
	
	public void updateGUI();
	
}
