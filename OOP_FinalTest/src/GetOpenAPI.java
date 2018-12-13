import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class GetOpenAPI {
	private static String getTagValue(String tag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
		Node nValue = (Node) nlList.item(0);
		if (nValue == null)
			return null;
		return nValue.getNodeValue();
	}

	public static List<Restaurants> getRestaurants(int pageNum) {
		List<Restaurants> list = new ArrayList<Restaurants>();

		try {
			String urlstr = "http://210.99.248.79/rest/ArtstreetService/getArtstreetList?"
					+ "authApiKey=DLVCGYUTQNLKFMU" + "&startPage=" + pageNum + "&pageSize=10";

			DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
			Document doc = dBuilder.parse(urlstr);

			// root tag doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("item");
			for (int i = 0; i < nList.getLength(); i++) {
				Node nNode = nList.item(i);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Restaurants data = new Restaurants();

					Element eElement = (Element) nNode;

					data.address = getTagValue("FAddr", eElement);
					data.area = getTagValue("FSi", eElement);
					data.menu = getTagValue("FMenu", eElement);
					data.telephone = getTagValue("FTel", eElement);
					data.name = getTagValue("FName", eElement);
					
					/* Debugging */
					System.out.println("######################");
					System.out.println("name : " + data.name);
					System.out.println("address : " + data.address);
					System.out.println("area : " + data.area);
					System.out.println("menu : " + data.menu);
					System.out.println("telephone : " + data.telephone);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());

		}
		return list;
	}

	/* int opt; 0:주소로 검색 1:메뉴로 검색 */
	public static List<Restaurants> searchRestaurants(String keyword, int pageNum, int opt) {
		List<Restaurants> list = new ArrayList<Restaurants>();

		while (true) {
			try {
				String urlstr = "http://210.99.248.79/rest/ArtstreetService/getArtstreetList?"
						+ "authApiKey=DLVCGYUTsdfsQNLKFMU" + "&startPage=" + pageNum++ + "&pageSize=0";

				DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
				Document doc = dBuilder.parse(urlstr);
				// root tag doc.getDocumentElement().normalize();

				NodeList nList = doc.getElementsByTagName("list");
				if (nList.getLength() == 0)
					break;

				int data_cnt = 0;

				for (int i = 0; i < nList.getLength(); i++) {
					Node nNode = nList.item(i);
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {

						Restaurants data = new Restaurants();

						Element eElement = (Element) nNode;

						if (opt == 0) {
							if (getTagValue("FAddr", eElement).matches(".*" + keyword + ".*") == false)
								continue;
						} else if (opt == 1) {
							if (getTagValue("FMenu", eElement).equals(keyword) == false)
								continue;
						}

						data_cnt++;

						/* page가 2일 경우 검색된 데이터 중 11~20에 해당하는 리스트만 넘겨주도록 함. */
						if (data_cnt > ((pageNum - 1) * 10) && data_cnt <= (pageNum * 10)) {
							data.address = getTagValue("FAddr", eElement);
							data.area = getTagValue("FSi", eElement);
							data.menu = getTagValue("FMenu", eElement);
							data.telephone = getTagValue("FTel", eElement);
							data.name = getTagValue("FName", eElement);

							/* Debugging */
							System.out.println("######################");
							System.out.println("name : " + data.name);
							System.out.println("address : " + data.address);
							System.out.println("area : " + data.area);
							System.out.println("menu : " + data.menu);
							System.out.println("telephone : " + data.telephone);

							list.add(data);
						}
					}
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());

			}
		}
		return list;
	}
}
