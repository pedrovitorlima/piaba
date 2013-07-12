package br.piaba.piabadroid.system.world.percepts;

import br.piaba.piabadroid.system.util.Converter;


/**
 * Classe que representa percepção do agente, que pode ser modificada ao longo da execução
 * ou definida no arquivo xml MASPercepts.xml
 * @author pedrovitorlima
 * **/
public class Percept {

	private String name = "";
	private String value = "";
	private boolean self;
	private String relatedAgent = "";
	private boolean toRemove = false;
	
	public Percept(String name, String value){
		this.name = name;
		this.value = value;
		this.self = false;
		this.relatedAgent = "";
	}
	
	public Percept(){
		this("", "");
	}
	
	public boolean isSelf() {
		return self;
	}

	public void setSelf(boolean self) {
		this.self = self;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getRelatedAgent() {
		return relatedAgent;
	}

	public void setRelatedAgent(String relatedAgent) {
		this.relatedAgent = relatedAgent;
	}
	
	public void setToRemove(boolean toRemove){
		this.toRemove = toRemove;
	}
	
	public boolean isToRemove(){
		return this.toRemove;
	}

	/**
	 * Retorna valor da percepão convertido para inteiro.
	 * 
	 * @return inteiro convertido através de {@link Integer#parseInt(String)}
	 * **/
	public int getIntValue(){
		return Converter.StrToInt(getValue());
	}
	
	@Override
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append(this.name);
		sb.append("(");
		sb.append(value);
		return sb.append(")").toString();
	}
	
	public Percept clone(){
		Percept cloned = new Percept();
		cloned.setValue(getValue());
		cloned.setName(getName());
		cloned.setRelatedAgent(getRelatedAgent());
		
		return cloned;
	}
	
	@Override
	public boolean equals(Object p){
		if(p == null){
			return false;
		}
		
		Percept otherPercept = (Percept) p;
		if(this.name.equals(otherPercept.getName()) &&
				this.value.equals(otherPercept.getValue()) &&
				this.self == otherPercept.isSelf() &&
				this.relatedAgent.equals(otherPercept.getRelatedAgent())){
			return true;
		}
		
		return false;
	}
		
}
