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
        String roleId = "_1";
        role.setAttribute("Id", roleId);
        String roleType = "Global";
        role.setAttribute("Type", roleType);
        String roleAction = "Assign";
        role.setAttribute("Action", roleAction);

        // abfrage ob Nutzer oder Gastzugang
        System.out.println("[1]Nutzerzugang oder [2]Gastzugang");
        boolean gastzugang = false;
        int roleassigment = scanner.nextInt();
        String roleTextContent = "";
        switch (roleassigment){
            case 1:
                roleTextContent = "Nutzer";
                break;
            case 2:
                roleTextContent = "Gast";
                gastzugang = true;
                break;
            default:
                System.out.printf("Falsche Eingabe!");
                break;// To-Do loop;

        }
        role.appendChild(doc.createCDATASection(roleTextContent));
        user.appendChild(role);


        /*   old
        System.out.println("Role text content"); // Nutzer oder Gast
        String roleTextContent = scanner.next();

        */

        //Wenn -> Abfrage auf Nutzer -> Fragen ob NUR Testzugänge erstellt werden sollen ;
        //                           -> Fragen ob ALLE Zugänge erstellt werden sollen
        //                                  -> Bei Testzugänge Login +".test" & Vorname Testuser[n]

        /*
                ID
                0   root
                1   Nutzer
                2   Testzugang
                3   Gastzugang




         */
        int usercreateID;

        System.out.println("Sollen NUR Testzugänge erstellt werden, oder ALLE Zugänge?");
        System.out.printf("NUR[1]      oder      ALLE[2]");
        usercreateID = scanner.nextInt();
        boolean testzugang = false;

        switch (usercreateID){
            case 1:
                //System.out.printf("Es werden im Verlauf zwei Testzugänge erstellt.");
                System.out.println("Es wird ein Testzugang erstellt!");
                testzugang = true;
                break;

            case 2:
                System.out.printf("Es wird ein Gastzugang erstellt!");
                gastzugang = true;
                break;
        }

        if(gastzugang=true){
            System.out.printf("Welcher Gastzugang soll erstellt werden ?\n");
            System.out.printf("[1] Lehrbeauftragte/Gastdozenten\n  ");
            System.out.printf("[2] Zugänge für Akademie der Gesundheits- & Pflegeberufe   [INFO FOLGT]\n");
            System.out.printf("[3] Zugänge für Akademie Für Ganztagspädagogik     [INFO FOLGT]\n");
            System.out.printf("                                             \n");
            System.out.printf(" ***************************************\n");
            System.out.printf("                                             \n");

        }

        /*if (usercreateID == 1){
            testzugang = true;
        } else {
            testzugang = false;
        }*/

        // define login element
            //implement Gast


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