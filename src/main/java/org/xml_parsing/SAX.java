package org.xml_parsing;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedHashMap;

class CustomHandler extends DefaultHandler {
    private java.util.LinkedHashMap<Integer, ?> nodeList;
    private StringBuilder nodeValue;
    private final String BOOK = "book";
    private final String AUTHOR = "author";
    private final String TITLE = "title";
    private final String GENRE = "genre";
    private final String PRICE = "price";
    private final String DATE = "publish_date";
    private final String DESCRIPTION = "description";

    @Override
    public void characters (char[] ch, int start, int length) {
        if (nodeValue == null)
            nodeValue = new StringBuilder();
        else
            nodeValue.append(ch, start, length);
    }
    @Override
    public void startDocument() {
        nodeList = new LinkedHashMap<>();
    }
    @Override
    public void startElement(String uri, String localName,
                             String qName, Attributes attributes)
    {
        /*TODO switch (qName) {
            case BOOK : nodeList.put(Integer.valueOf(attributes.getValue("#index")), ); break;
            case AUTHOR : break;
            case TITLE : break;
            case GENRE : break;
            case PRICE : break;
            case DATE : break;
            case DESCRIPTION : break;
            default:
                throw new IllegalStateException("Unexpected value: " + qName);
        }
        */
    }
    @Override
    public void endElement (String uri, String localName, String qName) {
        //TODO
    }

    public LinkedHashMap<Integer, ?> getNodeList() {
        return nodeList;
    }
};

public class SAX {
    static final String FOLDER = "src/main/resources/";
    public static void main(String[] args)
    {
        try (var input = new FileInputStream(FOLDER + "books.xml")) {

        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
