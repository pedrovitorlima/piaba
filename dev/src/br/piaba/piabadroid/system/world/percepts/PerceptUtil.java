package br.piaba.piabadroid.system.world.percepts;

import java.util.ArrayList;
import java.util.List;

import br.piaba.piabadroid.system.world.percepts.exceptions.PerceptNotFoundException;

/**
 * Classe Util que ajuda a gerenciar percepções
 * **/
public class PerceptUtil {

	private List<Percept> percepts;
	
	public PerceptUtil(List<Percept> percepts){
		this.percepts = percepts;
	}
	
	/**
	 * Retorna lista de percepções de acordo com um nome fornecido.
	 * Avalia todas as percepções para encontrar aquelas cujo padrão de nome seja respeitado.
	 * 
	 * @param name nome da percepção
	 * @return Lista de percepções com o nome informado
	 * **/
	public List<Percept> getPerceptsByName(String name){
		List<Percept> returned = new ArrayList<Percept>();
		
		for(Percept percept : percepts){
			if(name.equals(percept.getName())){
				returned.add(percept.clone());
			}
		}
		
		return returned;
	}
	
	/**
	 * Retorna percepção única de acordo com nome fornecido.
	 * Recomendado para se utilizar quando for sabido que existe apenas uma percepção com esse nome.
	 * É geralmente mais rápido que o {@link #getPerceptsByName(String)} pois só percorre a lista até encontrar
	 * a primeira ocorrência.
	 * 
	 *  @param name Nome da percepção
	 *  @return primeira ocorrência da percepção com esse nome
	 * **/
	public Percept getUnicPerceptByName(String name){
		
		Percept retorno = null;
		
		for(Percept percept : percepts){
			if(percept.getName().equals(name)){
				retorno = percept.clone();
				break;
			}
		}
		
		return retorno;
	}
	
	/**
	 * Retorna percepções de acordo com expressão regular de valor informado.
	 * Uma percepção é constituída por nome(param1, param2, param3, ..., paramN).
	 * O valor informado deve ser referente aos nomes. Ex:. x,y* informado trará todas
	 * as percepções que possuem o valor começando por x,y.
	 *
	 * @param pattern Padrão de valor a ser encontrado
	 * @return Lista de percepts cujo padrão de valor foi encontrado
	 * **/
	
	
	public List<Percept> getPerceptsByValuePattern(String regex){
		List<Percept> perceptsMatch = new ArrayList<Percept>();
		
		for(Percept percept : percepts){
			if(percept.getValue().matches(regex)){
				perceptsMatch.add(percept.clone());
			}
			
		}
		
		return perceptsMatch;
	}
	
	
	/**
	 * Retorna primeira percepção encontrada de acordo com expressão regular de valor informado.
	 * Uma percepção é constituída por nome(param1, param2, param3, ..., paramN).
	 * O valor informado deve ser referente aos nomes. Ex:. x,y* informado trará 
	 * a primera percepção encontrada cujo valor comece com x, y
	 *
	 * @param pattern Padrão de valor a ser encontrado
	 * @return primeira ocorrência encontrada cujo padrão de valor foi encontrado
	 * **/
	
	
	public Percept getUnicPerceptsByValuePattern(String regex){
		Percept retorno = null;
		
		
		for(Percept percept : percepts){
			if(percept.getValue().matches(regex)){
				retorno =  percept.clone();
			}
			
		}
		
		return retorno;
	}
	
	/**
	 * Retorna os valores de uma percepção (value1, value2, value3... valueN)
	 * como arrays de String
	 * **/
	public static String[] perceptValueAsArray(Percept percept){
		return percept.getValue().split(",");
	}

	/**
	 * Atualiza percepção
	 * @param namePercept Nome da percepção a ser atualizada
	 * @param valuePercept novo valor
	 * @throws Exception 
	 * **/
	public void updatePercept(String namePercept, String valuePercept) throws PerceptNotFoundException {
		
		boolean found = false;
		
		for(Percept percept : percepts){
			if(percept.getName().equals(namePercept)){
				percept.setValue(valuePercept);
				found = true;
				break;
			}
		}
		
		if(!found){
			throw new PerceptNotFoundException("Perception " + namePercept + " not found.");
		}
		
	}

	/**
	 * Remove percepção
	 * @param namePercept Nome da percepção a remover
	 * @return se conseguiu remover
	 * **/
	public boolean removePercept(String namePercept) {
		Percept perceptToRemove = null;
		
		for(Percept percept : percepts){
			if(percept.getName().equals(namePercept)){
				perceptToRemove = percept;
				break;
			}
		}
		
		if(perceptToRemove == null){
			return false;
		}else{
			return percepts.remove(perceptToRemove);
		}		
	}

	public boolean removePercept(Percept perceptToRemove){
		Percept perceptRemoved = null;
		for(Percept percept : getPercepts()){
			if(percept.equals(perceptToRemove)){
				perceptRemoved = percept;
				break;
			}
		}
		
		if(perceptRemoved == null){
			return false;
		}else{
			return percepts.remove(perceptToRemove);
		}
	}
	
	/**
	 * Adiciona percepção.
	 * 
	 * @param percept {@link Percept}
	 * **/
	public void addPercept(Percept percept) {
		
		this.percepts.add(percept.clone());
	}

	public void updatePercept(String namePercept, int valuePercept) throws PerceptNotFoundException {
		updatePercept(namePercept, valuePercept + "");
		
	}

	public List<Percept> getPercepts() {
		return this.percepts;
	}

	public void updatePercepts(List<Percept> percepts) {
		
		if(percepts != null){
			for(Percept percept : percepts){
				boolean found = false;
				
				for(Percept worldPercept : getPercepts()){
					
					if(percept.getName().equals(worldPercept.getName())){
						if(percept.getRelatedAgent().equals(worldPercept.getRelatedAgent()) && !percept.isSelf()) {
							worldPercept.setValue(percept.getValue());
							found = true;
							break;
						}
					}
					
				}
				
				if(!found){
					addPercept(percept);
				}
				
				if(percept.isToRemove()){
					removePercept(percept);
					continue;
				}
			}
			
		}
	
		
	}

	public Percept getUnicPercept(String namePercept, String relatedAgent) {
		for(Percept percept : getPercepts()){
			if(percept.getName().equals("ocioso")){
				System.err.println();
			}
			if(percept.getName().equals(namePercept) && percept.getRelatedAgent().equals(relatedAgent)){
				return percept;
			}
		}
		return null;
	}

	public void updatePercept(String namePercept, String valuePercept,
			String relatedAgent) {

		for(Percept percept : percepts){
			if(percept.getName().equals(namePercept) && percept.getRelatedAgent().equals(relatedAgent)){
				percept.setValue(valuePercept);
				break;
			}
		}
	}
}
