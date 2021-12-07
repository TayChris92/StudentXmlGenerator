import java.io.File;

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

public class StudentXmlGenerator {

    Document doc;

    public void generateXml() {
        try {

            // initialize document builder
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // define root element
            doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Users");
            doc.appendChild(rootElement);

            // add user to root element
            rootElement.appendChild(generateUserElement());

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("src/main/resources/users.xml"));

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);

            transformer.transform(source, result);

            System.out.println("File saved!");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }

    private Element generateUserElement() {
        // define user element
        Element user = doc.createElement("User");

        // define role element
        Element role = doc.createElement("Role");
        role.setAttribute("Id", "_1");
        role.setAttribute("Type", "Global");
        role.setAttribute("Action", "Assign");
        role.appendChild(doc.createCDATASection("Nutzer"));
        user.appendChild(role);

        // define login element
        Element login = doc.createElement("Login");
        login.appendChild(doc.createCDATASection("andre.schweigert"));
        user.appendChild(login);

        // define first name element
        Element firstName = doc.createElement("Firstname");
        firstName.appendChild(doc.createCDATASection("Andre"));
        user.appendChild(firstName);

        // define last name element
        Element lastName = doc.createElement("Lastname");
        lastName.appendChild(doc.createCDATASection("Andre"));
        user.appendChild(lastName);

        // define last name element
        Element matriculation = doc.createElement("Matriculation");
        matriculation.appendChild(doc.createCDATASection("data5"));
        user.appendChild(matriculation);

        // define time limit until element
        Element timeLimitUntil = doc.createElement("TimeLimitUntil");
        timeLimitUntil.appendChild(doc.createCDATASection("probalblysometime"));
        user.appendChild(timeLimitUntil);

        // define time limit unlimited element
        Element timeLimitUnlimited = doc.createElement("TimeLimitUnlimited");
        timeLimitUnlimited.appendChild(doc.createCDATASection("1"));
        user.appendChild(timeLimitUnlimited);

        // define time limit from element
        Element timeLimitFrom = doc.createElement("TimeLimitFrom");
        timeLimitFrom.appendChild(doc.createCDATASection("07.12.2021 00:00:00"));
        user.appendChild(timeLimitFrom);

        return user;

    }

}