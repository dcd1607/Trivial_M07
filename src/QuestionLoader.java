import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class QuestionLoader {
    public static List<Question> loadQuestionsFromXML(String filePath) {
        List<Question> questions = new ArrayList<>();
        try {
            File file = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("pregunta");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String texto = eElement.getElementsByTagName("text").item(0).getTextContent();
                    NodeList respuestasList = eElement.getElementsByTagName("respostes").item(0).getChildNodes();
                    List<String> respuestas = new ArrayList<>();
                    int correctaIndex = -1;

                    for (int i = 0; i < respuestasList.getLength(); i++) {
                        Node respuestaNode = respuestasList.item(i);
                        if (respuestaNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element respuestaElement = (Element) respuestaNode;
                            respuestas.add(respuestaElement.getTextContent());
                            if (respuestaElement.getTagName().equals("correcta")) {
                                correctaIndex = respuestas.size() - 1;
                            }
                        }
                    }
                    questions.add(new Question(texto, respuestas, correctaIndex));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return questions;
    }
}
