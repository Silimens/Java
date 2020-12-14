import by.gsu.pms.Constants;
import by.gsu.pms.beans.Article;
import by.gsu.pms.handlers.ArticleHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.net.URL;

public class RunnerSAX {
    public static void main(String[] args) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setNamespaceAware(true);
        ArticleHandler handler = new ArticleHandler(); // Перемещиение по XML документу
        try {
            SAXParser parser = factory.newSAXParser();
            parser.parse(new InputSource(new URL(Constants.URL).openStream()), handler);
            for (Article article : handler.getArticleList()) {
                System.out.println(article);
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
