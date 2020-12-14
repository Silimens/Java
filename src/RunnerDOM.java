import by.gsu.pms.Constants;
import by.gsu.pms.beans.Article;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.ContentHandler;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RunnerDOM {

    private static String getElem(Element elem, String elemName) { 
        final int ELEM_IND = 0;
        return elem.getElementsByTagName(elemName)
                .item(ELEM_IND).getTextContent();
    }

    public static void main(String[] args) {
        List<Article> articleList = new ArrayList<>();

        String title;
        String link;
        String description;
        String atomName;
        String atomUri;
        String category;
        String enclosure;
        String guid;
        String pubDate;

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new URL(Constants.URL).openStream());
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName(Constants.ITEM_ELEM); 

            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode; 
                    title = getElem(eElement, Constants.TITLE_ELEM);
                    link = getElem(eElement, Constants.LINK_ELEM);
                    description = getElem(eElement, Constants.DESCRIPTION_ELEM);
                    atomName = getElem(eElement, Constants.ATOM_NAME_ELEM_DOM);
                    atomUri = getElem(eElement, Constants.ATOM_URI_ELEM_DOM);
                    category = getElem(eElement, Constants.CATEGORY_ELEM);
                    enclosure = eElement.getAttribute(Constants.ENCLOSURE_ELEM); 
                    guid = getElem(eElement, Constants.GUID_ELEM);
                    pubDate = getElem(eElement, Constants.PUB_DATE_ELEM);
                    articleList.add(new Article(title, link, description, atomName, atomUri, category, enclosure, guid, pubDate));
                }
            }

            for(Article article : articleList){
                System.out.println(article);
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
