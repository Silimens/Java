package by.gsu.pms.handlers;

import by.gsu.pms.beans.Article;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class ArticleHandler extends DefaultHandler {

    private enum ArticleEnum {
        ITEM("item") ,TITLE("title"), LINK("link"), DESCRIPTION("description"),
        ATOM_NAME("name"), ATOM_URI("uri"), CATEGORY("category"), ENCLOSURE("enclosure"),
        GUID("guid"), PUB_DATE("pubDate");
        private final String name;

        ArticleEnum(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    private List<Article> articleList = new ArrayList<>();
    private String articleEnum = "";
    private boolean inItem;
    private String title;
    private String link;
    private String description;
    private String atomName;
    private String atomUri;
    private String category;
    private String enclosure;
    private String guid;
    private String pubDate;

    @Override //переопределяем
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (localName.equals(ArticleEnum.ITEM.toString())) {
            inItem = true; // внутри тега ITEM
        }
        if (inItem) {
            articleEnum = localName;
            if (articleEnum.equals(ArticleEnum.ENCLOSURE.toString())) {
                enclosure = attributes.getValue(0); // если натыкаемся на тег enclosure то берем атрибут под индексом 0
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String str = new String(ch, start, length).trim();
        if (inItem) { //проверяем чтобы были внутри ITEM, а не другого тега
            if (articleEnum.equals(ArticleEnum.TITLE.toString())) {
                if (!str.isEmpty()) {
                    title = str;
                }
            }
            if (articleEnum.equals(ArticleEnum.LINK.toString())) {
                if (!str.isEmpty()) {
                    link = str;
                }
            }
            if (articleEnum.equals(ArticleEnum.DESCRIPTION.toString())) {
                if (!str.isEmpty()) {
                    description = str;
                }
            }
            if (articleEnum.equals(ArticleEnum.ATOM_NAME.toString())) {
                if (!str.isEmpty()) {
                    atomName = str;
                }
            }
            if (articleEnum.equals(ArticleEnum.ATOM_URI.toString())) {
                if (!str.isEmpty()) {
                    atomUri = str;
                }
            }
            if (articleEnum.equals(ArticleEnum.CATEGORY.toString())) {
                if (!str.isEmpty()) {
                    category = str;
                }
            }
            if (articleEnum.equals(ArticleEnum.GUID.toString())) {
                if (!str.isEmpty()) {
                    guid = str;
                }
            }
            if (articleEnum.equals(ArticleEnum.PUB_DATE.toString())) {
                if (!str.isEmpty()) {
                    pubDate = str;
                    Article result = new Article(title, link, description, atomName, atomUri, category,
                            enclosure, guid, pubDate);
                    articleList.add(result);
                    inItem = false;
                }
            }
        }
    }

    public List<Article> getArticleList() {
        return articleList;
    }
}
