package org.xml_parsing;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Objects;

class CustomHandler extends DefaultHandler {
    private LinkedHashMap<Integer, HashMap<String, String>> nodeList;
    private HashMap<String, String> nodeTree;
    private StringBuilder nodeValue;
    private Integer nodeLatest;

    @Override
    public void startDocument() { nodeList = new LinkedHashMap<>(); }

    @Override
    public void startElement(String uri, String localName,
                             String qName, Attributes attributes)
    {
        if (Objects.equals(qName, "catalog")) return;
        if (Objects.equals(qName, "book")) {
            nodeLatest = Integer.valueOf(attributes.getValue("#index"));
            nodeList.put(nodeLatest, new HashMap<>());
        }
        else nodeTree.put(qName, nodeValue.toString().strip());
    }

    @Override
    public void characters (char[] ch, int start, int length) {
        nodeValue = (nodeValue != null) ?
                nodeValue.append(ch, start, length):
                new StringBuilder();
    }

    @Override
    public void endElement (String uri, String localName, String qName) {
        if (Objects.equals(qName, "book"))
            nodeTree = nodeList.get(nodeLatest);
    }
}

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
