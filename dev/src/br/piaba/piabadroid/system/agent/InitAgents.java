package br.piaba.piabadroid.system.agent;

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

import br.piaba.piabadroid.system.world.percepts.Percept;

/**
 * Monta agentes do sistema a partir de arquivo XML.
 * @author pedrovitorlima
 * **/
public class InitAgents {

	/**
	 * Realiza leitura dos agentes através do arquivo XML MASAgents.xml.
	 * 
	 * @param fileStream instância de {@link InputStream} referenciando o arquivo XML
	 * @return lista de {@link GenericAgent}
	 * **/
	public static List<GenericAgent> readAgents(InputStream fileStream) {
		List<GenericAgent> agents = new ArrayList<GenericAgent>();
		
		try {

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(new InputSource(fileStream));
			doc.getDocumentElement().normalize();

			NodeList nodeList = doc.getElementsByTagName("agents");
			
			
			/** Assign textview array lenght by arraylist size */
			for (int i = 0; i < nodeList.getLength(); i++) {

				Node node = nodeList.item(i);

				Element fstElmnt = (Element) node;
				NodeList agentList = fstElmnt.getElementsByTagName("agent");
				
				for (int j = 0; j < agentList.getLength(); j++) {
					Node agent = agentList.item(j);
					
					NodeList agentNameList = ((Element) agent).getElementsByTagName("agent-name");
					NodeList agentClassList = ((Element) agent).getElementsByTagName("agent-class");
					
					String agentName = agentNameList.item(0).getTextContent();
					String agentClass = agentClassList.item(0).getTextContent();
					
					NodeList perceptsList = ((Element) agent).getElementsByTagName("percepts");
					NodeList perceptList = ((Element)perceptsList.item(0)).getElementsByTagName("percept");
					
					List<Percept> percepts = new ArrayList<Percept>();
					
					for(int k=0; k < perceptList.getLength(); k++){
						Node perceptNode = perceptList.item(k);
						
						NodeList perceptNodeList = ((Element) perceptNode).getElementsByTagName("percept-name");
						String perceptName = perceptNodeList.item(0).getTextContent();
						
						NodeList perceptValueList = ((Element) perceptNode).getElementsByTagName("percept-value");
						String perceptValue = perceptValueList.item(0).getTextContent();
						
						Percept percept = new Percept(perceptName, perceptValue);
						percept.setSelf(true);
						percepts.add(percept);
					}
					
					Class klass = null;
				    try{
					   klass = Class.forName(agentClass);
				    }catch(ClassNotFoundException e){
					   System.err.println("Não foi possível encontrar a classe "+ agentClass +"para o agente especificado. =>" + e.getMessage());
					   return agents;
				    }
				    
				    GenericAgent genericAgent = null;
				    try{
				    	genericAgent = (GenericAgent) klass.newInstance();
				    }catch(InstantiationException e){
					   System.err.println("Não foi possível instanciar a classe " + klass + ". =>" + e.getMessage());
				    }
				    
				    genericAgent.setName(agentName);
				    genericAgent.setPercepts(percepts);
				    String separatedClassName[] = agentClass.split("\\.");
				    genericAgent.setType(separatedClassName[separatedClassName.length - 1]);
				    agents.add(genericAgent);
				}
			}
		} catch (Exception e) {
 			System.out.println("XML Pasing Excpetion = " + e);
		}
		
		return agents;

	}
}
