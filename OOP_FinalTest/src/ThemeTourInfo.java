import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ThemeTourInfo extends Service {
	String Seq;
	String Title;
	String Contents;
	
	public ThemeTourInfo(int Seq) {
		//ThemeTourInfo data = new ThemeTourInfo();

		try {
			String urlstr = "http://openapi.jejutour.go.kr:8080/openapi/service/TourCourseService/getTourCosView?serviceKey=hRyXqe36RGUiRmgK3ob5Gi2NfMGXkX7tnlqOKYjcgQZGLPHCZH4gEQhLwjAxbbJXqHk%2FCI5UYWkNo8uPq9PC9A%3D%3D&SEQ="
					+ Seq;
			DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
			Document doc = dBuilder.parse(urlstr);

			// root tag doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("item");
			if (nList.getLength() != 0) {
				Node nNode = nList.item(0);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					System.out.println("######################");

					this.Seq = getTagValue("ttSeq", eElement);
					this.Title = getTagValue("ttTitle", eElement);
					this.Contents = getTagValue("ttContents", eElement);

					/* Debugging */
					System.out.println("Sequence  : " + this.Seq);
					System.out.println("Title  : " + this.Title);
					System.out.println("Contents  : " + this.Contents);

				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());

		}
	}
}