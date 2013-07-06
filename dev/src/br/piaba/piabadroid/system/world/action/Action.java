package br.piaba.piabadroid.system.world.action;

import java.util.List;

import br.piaba.piabadroid.system.world.percepts.Percept;
import br.piaba.piabadroid.system.world.percepts.PerceptUtil;

/**
 * Representa uma Action executada.
 * Uma action possui um método para verificação e um método para execução das ações.
 * Em geral, as ações executadas alteram percepções
 * @author pedrovitorlima
 * **/
public interface Action {

	/**
	 * Verifica as pré-condições de execução da ação.
	 * @param bbAgent base de crenças do agente
	 * @return
	 * **/
	boolean verify(PerceptUtil bbAgent);

	/**
	 * Executa os passos da ação.
	 * @param bbAgent base de crenças do agente relacionado à ação
	 * 
	 * @return lista de percepções a serem alteradas
	 * **/
	List<Percept> action(PerceptUtil bbAgent);

}
