package br.piaba.piabadroid.system.world;

import java.util.List;

import br.piaba.piabadroid.system.world.action.Param;

/**
 * Interface utilizada para designar interfaces gráficas com o usuário referente ao mundo.
 * @author pedrovitorlima
 * 
 * **/
public interface WorldGUI {

	/**
	 * Método utilizado para atualizar interface gráfica. 
	 * **/
	public void updateGUI(List<Param> parameters);
	
}
