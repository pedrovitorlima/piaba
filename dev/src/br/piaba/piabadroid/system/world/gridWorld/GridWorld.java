package br.piaba.piabadroid.system.world.gridWorld;

import java.util.ArrayList;

import android.graphics.Point;
import br.piaba.piabadroid.system.agent.Agent;
import br.piaba.piabadroid.system.world.GenericWorld;
import br.piaba.piabadroid.system.world.percepts.Percept;
import br.piaba.piabadroid.system.world.percepts.PerceptUtil;

/**
 * Representação de um mundo em grid
 * @author pedrovitorlima
 * **/
public abstract class GridWorld extends GenericWorld{
	
	private Cell cells[][];
	
	public GridWorld(){	
		super();
	}
	
	public Cell[][] getCells(){
		return this.cells;
	}
	
	/**
	 * Retorna o tamanho do tabuleiro (L x L)
	 * 
	 * @return valor do tamanho, correspondente à crença L das percepções iniciais do mundo
	 * **/
	public int getL(){
		try{
			
			return Integer.parseInt(getPerceptUtil().getUnicPerceptByName("l").getValue());
			
		}catch(NullPointerException e){
			System.err.println("Perception L not found.");
			e.printStackTrace();
		}catch(IllegalArgumentException e){
			System.err.println("Perception L has a incorret type. Only int is aceptable.");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	@Override
	public void initWorld(){
		super.initWorld();
		createCells();
		
	}
	
	private void createCells(){
		int l = getL();
		cells = new Cell[l][l];
		
		for(int y=0; y<l; y++){
			for(int x=0; x<l; x++){
				Cell cell = new Cell(x,y,new ArrayList<Content>());
				cells[x][y] = cell;
				Percept percept = new Percept("cell", x + "," + "y");
				getPerceptUtil().addPercept(percept);
			}
		}
		
		//Insere agentes em suas posições
		for(Agent agent : getWorldData().getAgents()){
			Percept percept = agent.getPerceptUtil().getUnicPerceptByName("initialPosition");
			String array[] = PerceptUtil.perceptValueAsArray(percept);
			
			int xAgent = Integer.parseInt(array[0]);
			int yAgent = Integer.parseInt(array[1]);
			
			cells[xAgent][yAgent].setAgent(agent);
		}
	}
	
	public void move(Point origin, Point destiny){
		Agent agent = cells[origin.x][origin.y].getAgent();
		boolean busy = cells[destiny.x][destiny.y].hasAgent();
		
		if(agent != null && !busy){
			cells[origin.x][origin.y].setAgent(null);
			cells[destiny.x][destiny.y].setAgent(agent);
			
			if(getWorldData().getWorldGUI() != null){
				//Chamar método para atualizar interface gráfica
			}
		}
	}

}
