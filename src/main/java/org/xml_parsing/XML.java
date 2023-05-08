package org.xml_parsing;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.FileInputStream;
import java.io.IOException;

public class XML {
    static final String FOLDER = "src/main/resources/";
    static DocumentBuilderFactory factory;
    static DocumentBuilder builder;

    public static void main(String[] args) throws ParserConfigurationException {
        factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();
        try (var input = new FileInputStream(FOLDER + "books.xml"))
        {
            Document document = builder.parse(input);
        }
        catch (IOException | SAXException ioe) {
            throw new RuntimeException(ioe);
        }
    }
}
