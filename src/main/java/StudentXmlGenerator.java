import java.io.File;
import java.util.Scanner;

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
    Scanner scanner = new Scanner(System.in);

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

            System.out.println("Would you like to add another user? y/n");
            boolean shouldPrompt = true;
            while (shouldPrompt) {
                String answer = scanner.next();
                if (answer.equalsIgnoreCase("y")) {
                    rootElement.appendChild(generateUserElement());
                    shouldPrompt = true;
                }
                else if (answer.equalsIgnoreCase("n")) {
                    shouldPrompt = false;
                }
                else if (!answer.equalsIgnoreCase("y") && !answer.equalsIgnoreCase("n")) {
                    System.out.println("y or n!");
                    shouldPrompt = true;
                }
            }


            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("src/main/resources/users.xml"));

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
        System.out.println("Language");
        String language = scanner.next();
        user.setAttribute("Language", language);
        System.out.println("Action");
        String action = scanner.next();
        user.setAttribute("Action", action);

        // define role element
        Element role = doc.createElement("Role");
        System.out.println("Role Id");
        String roleId = scanner.next();
        role.setAttribute("Id", roleId);
        System.out.println("Role Type");
        String roleType = scanner.next();
        role.setAttribute("Type", roleType);
        System.out.println("Role Action");
        String roleAction = scanner.next();
        role.setAttribute("Action", roleAction);
        System.out.println("Role text content");
        String roleTextContent = scanner.next();
        role.appendChild(doc.createCDATASection(roleTextContent));
        user.appendChild(role);

        // define login element
        Element login = doc.createElement("Login");
        System.out.println("Login");
        String loginTextContent = scanner.next();
        login.appendChild(doc.createCDATASection(loginTextContent));
        user.appendChild(login);

        // define first name element
        Element firstName = doc.createElement("Firstname");
        System.out.println("Firstname");
        String firstNameTextContent = scanner.next();
        firstName.appendChild(doc.createCDATASection(firstNameTextContent));
        user.appendChild(firstName);

        // define last name element
        Element lastName = doc.createElement("Lastname");
        System.out.println("Lastname");
        String lastNameTextName = scanner.next();
        lastName.appendChild(doc.createCDATASection(lastNameTextName));
        user.appendChild(lastName);

        // define last name element
        Element matriculation = doc.createElement("Matriculation");
        System.out.println("Matriculation");
        String matriculationTextContent = scanner.next();
        matriculation.appendChild(doc.createCDATASection(matriculationTextContent));
        user.appendChild(matriculation);

        // define time limit until element
        Element timeLimitUntil = doc.createElement("TimeLimitUntil");
        System.out.println("Time limit until");
        String timeLimitUntilTextContent = scanner.next();
        timeLimitUntil.appendChild(doc.createCDATASection(timeLimitUntilTextContent));
        user.appendChild(timeLimitUntil);

        // define time limit unlimited element
        Element timeLimitUnlimited = doc.createElement("TimeLimitUnlimited");
        System.out.println("Time limit unlimited");
        String timeLimitUnlimitedTextContent = scanner.next();
        timeLimitUnlimited.appendChild(doc.createCDATASection(timeLimitUnlimitedTextContent));
        user.appendChild(timeLimitUnlimited);

        // define time limit from element
        Element timeLimitFrom = doc.createElement("TimeLimitFrom");
        System.out.println("Time limit from");
        String timeLimitFromTextContent = scanner.next();
        timeLimitFrom.appendChild(doc.createCDATASection(timeLimitFromTextContent));
        user.appendChild(timeLimitFrom);

        return user;

    }

}