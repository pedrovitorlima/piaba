package br.piaba.piabadroid.system.world.model;

import java.util.ArrayList;
import java.util.List;

import br.piaba.piabadroid.system.agent.Agent;
import br.piaba.piabadroid.system.agent.GenericAgent;
import br.piaba.piabadroid.system.agent.executor.Executor;
import br.piaba.piabadroid.system.agent.executor.GenericExecutor;
import br.piaba.piabadroid.system.world.WorldGUI;
import br.piaba.piabadroid.system.world.percepts.Percept;

public class WorldData {

	private List<GenericAgent> agents;
	private List<Percept> percepts;
	private List<Thread> threads = new ArrayList<Thread>();
	private GenericExecutor executor;
	private WorldGUI worldGUI;
	private int cyclesByStep = 1;
	
	public List<GenericAgent> getAgents() {
		return agents;
	}
	public void setAgents(List<GenericAgent> agents) {
		this.agents = agents;
	}
	public List<Percept> getPercepts() {
		return percepts;
	}
	public void setPercepts(List<Percept> percepts) {
		this.percepts = percepts;
	}
	public List<Thread> getThreads() {
		return threads;
	}
	public void setThreads(List<Thread> threads) {
		this.threads = threads;
	}
	public GenericExecutor getExecutor() {
		return executor;
	}
	public void setExecutor(GenericExecutor executor) {
		this.executor = executor;
	}
	public WorldGUI getWorldGUI() {
		return worldGUI;
	}
	public void setWorldGUI(WorldGUI worldGUI) {
		this.worldGUI = worldGUI;
	}
	public int getCyclesByStep() {
		return cyclesByStep;
	}
	public void setCyclesByStep(int cyclesByStep) {
		this.cyclesByStep = cyclesByStep;
	}
	public List<Percept> getWorldPercepts(){
		return this.percepts;
	}
	public Agent getAgent(String string) {
		for(Agent agent : getAgents()){
			if(agent.getName().equals(string)){
				return agent;
			}
		}
		
		return null;
	}
	
	
}
