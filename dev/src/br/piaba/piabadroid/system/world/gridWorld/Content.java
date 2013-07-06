package br.piaba.piabadroid.system.world.gridWorld;

public class Content {

	private String nameContent;
	private String valueContent;
	
	
	public Content(){
		this("","");
	}
	
	public Content(String nameContent, String valueContent){
		this.nameContent = nameContent;
		this.valueContent = valueContent;
	}
		
	public String getNameContent() {
		return nameContent;
	}
	
	public void setNameContent(String nameContent) {
		this.nameContent = nameContent;
	}
	
	public String getValueContent() {
		return valueContent;
	}
	
	public void setValueContent(String valueContent) {
		this.valueContent = valueContent;
	}
	
	
}
