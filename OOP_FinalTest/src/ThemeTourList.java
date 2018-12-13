import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ThemeTourList extends Service implements GetInfo {
	String Seq;
	String Title;

	public List<Service> getInfo() {

		return null;
	}

	public List<Service> searchTheme(String keyword, int pageNum) {
		List<Service> list = new ArrayList<Service>();
		int queryPage = 1;
		int data_cnt = 1;

		while (true) {
			try {
				String urlstr = "http://openapi.jejutour.go.kr:8080/openapi/service/TourCourseService/getTourCosList?serviceKey=hRyXqe36RGUiRmgK3ob5Gi2NfMGXkX7tnlqOKYjcgQZGLPHCZH4gEQhLwjAxbbJXqHk%2FCI5UYWkNo8uPq9PC9A%3D%3D"
						+ "&pageNo=" + queryPage++;

				DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
				Document doc = dBuilder.parse(urlstr);

				// root tag doc.getDocumentElement().normalize();

				NodeList nList = doc.getElementsByTagName("item");
				if (nList.getLength() == 0)
					break;
				
				for (int i = 0; i < nList.getLength(); i++) {
					Node nNode = nList.item(i);
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {
						ThemeTourList data = new ThemeTourList();

						Element eElement = (Element) nNode;

						if (getTagValue("ttTitle", eElement).matches(".*" + keyword + ".*") == false)
							continue;

						if (data_cnt > ((pageNum - 1) * 10) && data_cnt <= (pageNum * 10)) {

							data.Seq = getTagValue("ttSeq", eElement);
							data.Title = getTagValue("ttTitle", eElement);

							/* Debugging */
							System.out.println("######################");
							System.out.println("Sequence  : " + data.Seq);
							System.out.println("Title  : " + data.Title);

							list.add(data);
							
							data_cnt++;
						}
					}
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());

			}
		}
		return list;
	}

	public List<Service> getInfo(int pageNum) {
		List<Service> list = new ArrayList<Service>();

		try {
			String urlstr = "http://openapi.jejutour.go.kr:8080/openapi/service/TourCourseService/getTourCosList?serviceKey=hRyXqe36RGUiRmgK3ob5Gi2NfMGXkX7tnlqOKYjcgQZGLPHCZH4gEQhLwjAxbbJXqHk%2FCI5UYWkNo8uPq9PC9A%3D%3D"
					+ "&pageNo=" + pageNum;

			DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
			Document doc = dBuilder.parse(urlstr);

			// root tag doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("item");
			for (int i = 0; i < nList.getLength(); i++) {
				Node nNode = nList.item(i);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					ThemeTourList data = new ThemeTourList();

					Element eElement = (Element) nNode;

					data.Seq = getTagValue("ttSeq", eElement);
					data.Title = getTagValue("ttTitle", eElement);

					/* Debugging */
					System.out.println("######################");
					System.out.println("Sequence  : " + data.Seq);
					System.out.println("Title  : " + data.Title);

					list.add(data);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());

		}
		return list;
	}
}