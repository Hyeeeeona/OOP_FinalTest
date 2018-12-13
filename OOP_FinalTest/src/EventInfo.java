import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class EventInfo extends Service implements GetInfo{
	String name;
	String introduce;
	String telephone;
	String address;
	String category;
	String imgUrl;
	String ceo;
	
	public List<Service> getInfo() {
		return null;
	}
	public  List<Service> getInfo(int pageNum) {
		List<Service> list = new ArrayList<Service>();

		try {
			String urlstr = "http://210.99.248.79/rest/ArtstreetService/getArtstreetList?"
					+ "authApiKey=DLVCGYUTQNLKFMU" + "&startPage=" + pageNum + "&pageSize=10";

			DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
			Document doc = dBuilder.parse(urlstr);
			
			// root tag doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("list");
			for (int i = 0; i < nList.getLength(); i++) {
				Node nNode = nList.item(i);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					EventInfo data = new EventInfo();

					Element eElement = (Element) nNode;
					data.address = getTagValue("address", eElement);
					data.category = getTagValue("category", eElement);
					data.imgUrl = getTagValue("img_url", eElement);
					data.ceo = getTagValue("ceo", eElement);
					data.introduce = getTagValue("introduce", eElement);
					data.name = getTagValue("name", eElement);
					data.telephone = getTagValue("telephone", eElement);

					System.out.println("######################");
					System.out.println("address : " + data.address);
					System.out.println("category : " + data.category);
					System.out.println("imgUrl : " + data.imgUrl);
					System.out.println("ceo : " + data.ceo);
					System.out.println("introduce : " + data.introduce);
					System.out.println("name : " + data.name);
					System.out.println("telephone : " + data.telephone);

					list.add(data);
				} 
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return list;
	}
}
