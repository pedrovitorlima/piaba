package br.piaba.piabadroid.system.world.action;

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

import br.piaba.piabadroid.system.world.action.impl.MioAction;

public class InitMios {

	public static List<MioAction> readMios(InputStream fileStream) {
		List<MioAction> mios = new ArrayList<MioAction>();

		try {

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(new InputSource(fileStream));
			doc.getDocumentElement().normalize();

			NodeList nodeList = doc.getElementsByTagName("mios");

			/** Assign textview array lenght by arraylist size */
			for (int i = 0; i < nodeList.getLength(); i++) {

				Node node = nodeList.item(i);

				Element fstElmnt = (Element) node;
				NodeList mioList = fstElmnt.getElementsByTagName("mios");

				for (int j = 0; j < mioList.getLength(); j++) {
					Node agent = mioList.item(j);

					NodeList mioClassList = ((Element) agent)
							.getElementsByTagName("mio-class");

					for (int k = 0; k < mioClassList.getLength(); k++) {
						Node mioNode = mioClassList.item(k);

						Class klass = null;

						try {
							klass = Class.forName(mioNode.getTextContent());
							MioAction mio = (MioAction) klass.newInstance();
							mios.add(mio);
						} catch (ClassNotFoundException e) {
							System.err
									.println("Não foi possível encontrar a classe "
											+ mioNode.getTextContent()
											+ "para o agente especificado. =>"
											+ e.getMessage());
							return mios;
						}

					}

				}
			}
		} catch (Exception e) {
			System.out.println("XML Pasing Excpetion = " + e);
		}

		return mios;
	}
}
