package converter;

import com.sun.org.apache.xerces.internal.dom.DeferredElementImpl;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

public class IntegerNumberToStringConverterDDT {

    Document document;

    @Before
    public void initTestData(){
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            document = documentBuilder.parse("src/test/resources/testData.xml");
            document.getDocumentElement().normalize();
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace(System.out);
        } catch (SAXException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }

    @Test
    public void convertJUnitDataDrivenTest() throws Exception {

        NodeList nList = document.getElementsByTagName("test");

        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;

                String number = eElement.getElementsByTagName("number").item(0).getTextContent();
                String expectedResult = eElement.getElementsByTagName("expectedResult").item(0).getTextContent();

                BigInteger integer = new BigInteger(number);

                IntegerNumberToStringConverter converter = new IntegerNumberToStringConverter(integer);

                assertEquals("Test with " + number + " is failed! ", expectedResult, converter.convert());
            }
        }
    }
}
