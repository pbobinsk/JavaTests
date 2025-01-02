package com.example.xml;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.NodeIterator;
import org.xml.sax.SAXException;

import com.example.Person;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

/**
 *
 * @author DELL
 */
public class XMLjavaExample {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        // TODO code application logic here
        
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        
        Person person = new Person();
        person.setBirthdate(simpleDateFormat.parse("1973-05-08"));
        person.setFirstName("Piotr");
        person.setLastName("Bobiński");

        System.out.println(person);
        
  
        XmlMapper xmlMapper = new XmlMapper();
        String xml = xmlMapper.writeValueAsString(person);
        System.out.println(xml);
        
        xmlMapper.writeValue(new File("person.xml"), person);
        
        Person per = xmlMapper.readValue(xml, Person.class);

        System.out.println(per);

        Person perFromFile = xmlMapper.readValue(new File("person.xml"), Person.class);

        System.out.println(perFromFile);
  
        System.out.println();
        System.out.println("DOM Example - Read");
        System.out.println();
        JavaXmlDomReadEx();

        System.out.println();
        System.out.println("DOM Example - Read using iterator");
        System.out.println();
        JavaXmlDomReadElements();
        
        System.out.println();
        System.out.println("DOM Example - Write");
        System.out.println();
        JavaXmlDomWrite();
    }



    public static void JavaXmlDomReadEx() throws SAXException,
            IOException, ParserConfigurationException {

        File xmlFile = new File("users.xml");
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);

        doc.getDocumentElement().normalize();

        System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

        NodeList nList = doc.getElementsByTagName("user");

        for (int i = 0; i < nList.getLength(); i++) {

            Node nNode = nList.item(i);

            System.out.println("\nCurrent Element: " + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element elem = (Element) nNode;

                String uid = elem.getAttribute("id");

                Node node1 = elem.getElementsByTagName("firstname").item(0);
                String fname = node1.getTextContent();

                Node node2 = elem.getElementsByTagName("lastname").item(0);
                String lname = node2.getTextContent();

                Node node3 = elem.getElementsByTagName("occupation").item(0);
                String occup = node3.getTextContent();

                System.out.printf("User id: %s%n", uid);
                System.out.printf("First name: %s%n", fname);
                System.out.printf("Last name: %s%n", lname);
                System.out.printf("Occupation: %s%n", occup);
            }
        }
    }
    
    public static void JavaXmlDomReadElements () throws ParserConfigurationException,
            SAXException, IOException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder loader = factory.newDocumentBuilder();
        Document document = loader.parse("continents.xml");

        DocumentTraversal trav = (DocumentTraversal) document;

        NodeIterator it = trav.createNodeIterator(document.getDocumentElement(), 
                NodeFilter.SHOW_ELEMENT, null, true);

        int c = 1;
        
        for (Node node = it.nextNode(); node != null;
                node = it.nextNode()) {

            String name = node.getNodeName();
            
            System.out.printf("%d %s%n", c, name);
            c++;
        }
    }
    
    public static void JavaXmlDomWrite() throws ParserConfigurationException,
            TransformerException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();
        
        Element root = doc.createElementNS("zetcode.com", "users");
        doc.appendChild(root);

        root.appendChild(createUser(doc, "1", "Robert", "Brown", "programmer"));
        root.appendChild(createUser(doc, "2", "Pamela", "Kyle", "writer"));
        root.appendChild(createUser(doc, "3", "Peter", "Smith", "teacher"));

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transf = transformerFactory.newTransformer();
        
        transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transf.setOutputProperty(OutputKeys.INDENT, "yes");
        transf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        
        DOMSource source = new DOMSource(doc);

        File myFile = new File("usersWritten.xml");
        
        StreamResult console = new StreamResult(System.out);
        StreamResult file = new StreamResult(myFile);

        transf.transform(source, console);
        transf.transform(source, file);
    }

    private static Node createUser(Document doc, String id, String firstName, 
            String lastName, String occupation) {
        
        Element user = doc.createElement("user");

        user.setAttribute("id", id);
        user.appendChild(createUserElement(doc, "firstname", firstName));
        user.appendChild(createUserElement(doc, "lastname", lastName));
        user.appendChild(createUserElement(doc, "occupation", occupation));

        return user;
    }

    private static Node createUserElement(Document doc, String name, 
            String value) {

        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));

        return node;
    }
    
}

    


