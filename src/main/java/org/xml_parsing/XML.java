package org.xml_parsing;

import org.apache.commons.collections4.iterators.NodeListIterator;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
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

    public static void main(String[] args) throws ParserConfigurationException
    {
        factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();

        try (var input = new FileInputStream(FOLDER + "books.xml"))
        {
            Document document = builder.parse(input);
            document.getDocumentElement().normalize();

            NodeListIterator nodeIterator = new NodeListIterator(document.getElementsByTagName("book"));

            while (nodeIterator.hasNext())
            {
                Node node = nodeIterator.next();
                Node attribute = node.getAttributes().item(0);
                System.out.printf("<%s %s>%n", node.getNodeName(), attribute);

                NodeListIterator content = new NodeListIterator(node.getChildNodes());
                content.forEachRemaining( X -> {
                    if (! X.getNodeName().matches("#text".toLowerCase()) )
                        System.out.printf("<%s>\n\t%s\n", X.getNodeName(), X.getTextContent());
                });

                System.out.println();
            }
        }
        catch (IOException | SAXException ioe) {
            throw new RuntimeException(ioe);
        }
    }
}
