package br.piaba.piabadroid.system.world;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import br.piaba.piabadroid.system.agent.executor.AssynchronousExecutor;
import br.piaba.piabadroid.system.agent.executor.GenericExecutor;
import br.piaba.piabadroid.system.agent.executor.SynchronousExecutor;
import br.piaba.piabadroid.system.world.percepts.Percept;


/**
 * Classe responsável por carregar o mundo de acordo com os parâmetros definidos
 * no arquivo XML.
 * 
 * @author pedrovitorlima
 * **/
public class InitWorld {
	public static final String ASSYNCHRONOUS = "ASSYNCHRONOUS";
	public static final String SYNCHRONOUS = "SYNCHRONOUS";
	

	public static GenericWorld readWorld(InputStream fileStream){
		GenericWorld world = null;
		try {

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(new InputSource(fileStream));
			doc.getDocumentElement().normalize();

			Node worldNode = doc.getElementsByTagName("world").item(0);
			
			Node cyclesByStepNode = ((Element) worldNode).getElementsByTagName("cycles-by-step").item(0);
			
			String cyclesByStep = cyclesByStepNode.getTextContent();
			
			Node agentExecutor = ((Element) worldNode).getElementsByTagName("agent-executor").item(0);
			Node worldExecutorClass = ((Element) agentExecutor).getElementsByTagName("agent-executor-type").item(0);
			
			GenericExecutor executor = null;
			if(worldExecutorClass.getTextContent().toUpperCase().equals(ASSYNCHRONOUS)){
				executor = new AssynchronousExecutor();
			}else if(worldExecutorClass.getTextContent().toUpperCase().equals(SYNCHRONOUS)){
				executor = new SynchronousExecutor();
			}
			
			Node nodeWorldClass = ((Element) worldNode).getElementsByTagName("world-class").item(0);
			String worldKlass = nodeWorldClass.getTextContent();
			
			Class klass = null;
			try {
				klass = Class.forName(worldKlass);
			} catch (ClassNotFoundException e) {
				System.err.println("A classe " + worldKlass
						+ " para o mundo definido não foi encontrada. =>"
						+ e.getMessage());
				
				return world;
			}
			
			try {
				world = (GenericWorld) klass.newInstance();
			} catch (InstantiationException e) {
				System.err.println("Não foi possível instanciar o mundo " + klass
						+ ". =>" + e.getMessage());
			}
	
			world.getWorldData().setCyclesByStep(Integer.parseInt(cyclesByStep));
			
			NodeList perceptsList = ((Element) worldNode).getElementsByTagName("percepts");
			NodeList perceptList = ((Element)perceptsList.item(0)).getElementsByTagName("percept");
					
			List<Percept> percepts = new ArrayList<Percept>();
					
			for(int k=0; k < perceptList.getLength(); k++){
				Node perceptNode = perceptList.item(k);
				
				NodeList perceptNodeList = ((Element) perceptNode).getElementsByTagName("percept-name");
				String perceptName = perceptNodeList.item(0).getTextContent();
				
				NodeList perceptValueList = ((Element) perceptNode).getElementsByTagName("percept-value");
				String perceptValue = perceptValueList.item(0).getTextContent();
				
				percepts.add(new Percept(perceptName, perceptValue));
			}
			
			world.getWorldData().setPercepts(percepts);
			world.getWorldData().setExecutor(executor);
			
		} catch (Exception e) {
 			System.out.println("XML Pasing Excpetion = " + e);
		}
		
		return world;
	}
}
