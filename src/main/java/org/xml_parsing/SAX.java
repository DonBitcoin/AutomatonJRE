package org.xml_parsing;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Objects;

class CustomHandler extends DefaultHandler {
    private LinkedHashMap<String, LinkedHashMap<String, String>> List;
    private StringBuilder Value = new StringBuilder();
    private String LatestID;

    @Override
    public void startDocument() { List = new LinkedHashMap<>(); }

    @Override
    public void startElement(String uri, String localName,
                             String qName, Attributes attributes) {
        if (Objects.equals(qName, "book")) {
            LatestID = attributes.getValue("id");
            List.put(LatestID, new LinkedHashMap<>());
        }
    }

    @Override
    public void characters (char[] ch, int start, int length) {
        Value.append(ch, start, length);
    }

    @Override
    public void endElement (String uri, String localName, String qName) {
        if (! Objects.equals(qName, "book")) {
            // Appending new child block to <book id = LatestID> tree.
            List.get(LatestID).put(qName, Value.toString().strip());
            Value = new StringBuilder(); //Flushing previously gathered characters.
        }
    }

    // Setting up getter to retrieve parsed document-object
    public LinkedHashMap<String, LinkedHashMap<String, String>> getList() {
        return List;
    }
}

public class SAX {
    static final String FOLDER = "src/main/resources/";
    static SAXParserFactory factory;
    static SAXParser parser;
    static CustomHandler handler;

    public static void main(String[] args)
    {
        LinkedHashMap<String, LinkedHashMap<String, String>> results;

        try (var input = new FileInputStream(FOLDER + "books.xml")) {
            factory = SAXParserFactory.newDefaultInstance();
            parser = factory.newSAXParser();
            handler = new CustomHandler();

            parser.parse(input, handler);
            results = handler.getList();
        }
        catch (IOException | ParserConfigurationException | SAXException e) {
            throw new RuntimeException(e);
        }

        //Printing "results"
        results.forEach( (X, Y) -> {
            System.out.println("Current book to be displayed : id = " + X);
            Y.forEach( (I, J) -> System.out.printf("\t%S : %s%n", I, J));
        });
    }
}
