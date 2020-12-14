import by.gsu.pms.Constants;
import by.gsu.pms.beans.Article;
import by.gsu.pms.handlers.ArticleReader;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RunnerSTaX {
    public static void main(String[] args) {
        List<Article> currencyList = new ArrayList<>();

         String title = "";
         String link = "";
         String description = "";
         String category = "";
         String atomName = "";
         String atomUri = "";
         String enclosure = "";
         String guid = "";
         String pubDate = "";

        try (ArticleReader reader = new ArticleReader(new URL(Constants.URL).openStream())) {

            while (reader.startElement(Constants.ITEM_ELEM, Constants.CHANNEL_ELEM)) {

                if (reader.startElement(Constants.TITLE_ELEM, Constants.ITEM_ELEM)) { // внутри тега ITEM достаем содежримое тега TITLE
                    title = reader.getText();
                }

                if (reader.startElement(Constants.LINK_ELEM, Constants.ITEM_ELEM)) {
                    link = reader.getText();
                }

                if (reader.startElement(Constants.DESCRIPTION_ELEM, Constants.ITEM_ELEM)) {
                    description = reader.getText();
                }

                if (reader.startElement(Constants.ATOM_NAME_ELEM, Constants.ATOM_PARENT_ELEM)) {
                    atomName = reader.getText();
                }

                if (reader.startElement(Constants.ATOM_URI_ELEM, Constants.ATOM_PARENT_ELEM)) {
                    atomUri = reader.getText();
                }

                if (reader.startElement(Constants.ENCLOSURE_ELEM, Constants.ITEM_ELEM)) {
                    enclosure = reader.getAttribute(Constants.ENCLOSURE_URL_ELEM);
                }

                if (reader.startElement(Constants.CATEGORY_ELEM, Constants.ITEM_ELEM)) {
                    category = reader.getText();
                }

                if (reader.startElement(Constants.ENCLOSURE_ELEM, Constants.ITEM_ELEM)) {
                    enclosure = reader.getAttribute(Constants.ENCLOSURE_ELEM);
                }

                if (reader.startElement(Constants.GUID_ELEM, Constants.ITEM_ELEM)) {
                    guid = reader.getText();
                }

                if (reader.startElement(Constants.PUB_DATE_ELEM, Constants.ITEM_ELEM)) {
                    pubDate = reader.getText();
                }

                currencyList.add(new Article(title, link, description, category, atomName, atomUri, enclosure, guid, pubDate));
            }

            for (Article article :currencyList) {
                System.out.println(article);
            }
        } catch (XMLStreamException | IOException | NumberFormatException e) {
            System.err.println(e.getMessage());
        }
    }
}
