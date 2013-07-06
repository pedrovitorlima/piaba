package br.piaba.piabadroid.system.world.action;

import br.piaba.piabadroid.system.util.Converter;

public class Param {

	private String name;
	
	private String value;
	
	public Param(){
		this("","");
	}
	
	public Param(String name, String value){
		this.name = name;
		this.value = value;
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
	
	public int getIntValue(){
		return Converter.StrToInt(this.value);
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public String toString(){
		return getName() + " : " + getValue();
	}
}
