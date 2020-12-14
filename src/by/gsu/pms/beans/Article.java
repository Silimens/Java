package by.gsu.pms.beans;

import by.gsu.pms.Constants;

public class Article {
    private String title;
    private String link;
    private String description;
    private String atomName;
    private String atomUri;
    private String category;
    private String enclosure;
    private String guid;
    private String pubDate;

    public Article() {
    }

    public Article(String title, String link, String description, String atomName, String atomUri, String category,
                   String enclosure, String guid, String pubDate) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.atomName = atomName;
        this.atomUri = atomUri;
        this.category = category;
        this.enclosure = enclosure;
        this.guid = guid;
        this.pubDate = pubDate;
    }

    @Override
    public String toString() {
        return title + Constants.DELIMITER + link + Constants.DELIMITER +
                description + Constants.DELIMITER + atomName + Constants.DELIMITER +
                atomUri + Constants.DELIMITER + category + Constants.DELIMITER + enclosure +
                Constants.DELIMITER + guid + Constants.DELIMITER + pubDate;
    }
}
