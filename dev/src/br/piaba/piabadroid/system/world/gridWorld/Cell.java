package br.piaba.piabadroid.system.world.gridWorld;

import java.util.ArrayList;
import java.util.List;

import br.piaba.piabadroid.system.agent.Agent;

public class Cell {

	private int x;
	private int y;
	
	private Agent agent;
	
	private List<Content> contents;
	
	private String guiRepresentation;
	
	
	public Cell(int x, int y, List<Content> contents) {
		this.agent = null;
		this.x = x;
		this.y = y;
		this.contents = contents;
		guiRepresentation = new String("");
	}
	
	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public Cell() {
		this(0,0,new ArrayList<Content>());
	}


	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Retorna uma string que será utilizada para representar o conteúdo da célula
	 * @return
	 * **/
	public String getGuiRepresentation() {
		return this.guiRepresentation;
	}
	
	public void setGuiRepresentation(String guiRepresentation){
		this.guiRepresentation = guiRepresentation;
	}
	
	public boolean contains(String contentName){
		for(Content content : contents){
			if(content.getNameContent().equals(contentName)){
				return true;
			}
		}
		
		return false;
	}
	
	public boolean hasAgent(){
		if(getAgent() != null){
			return true;
		}
		
		return false;
	}
	
}
