import java.io.BufferedReader;
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
	public static ThemeTourInfo getTourInfo(int Seq) {
		ThemeTourInfo data = new ThemeTourInfo();

		try {
			String urlstr = "http://openapi.jejutour.go.kr:8080/openapi/service/TourCourseService/getTourCosView?serviceKey=hRyXqe36RGUiRmgK3ob5Gi2NfMGXkX7tnlqOKYjcgQZGLPHCZH4gEQhLwjAxbbJXqHk%2FCI5UYWkNo8uPq9PC9A%3D%3D&SEQ="
					+ Seq;
			DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
			Document doc = dBuilder.parse(urlstr);

			// root tag doc.getDocumentElement().normalize();
						System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

						NodeList nList = doc.getElementsByTagName("item");
						System.out.println("SEcuen" + Seq);
						System.out.println("파싱할 리스트 수 : " + nList.getLength()); // 파싱할 리스트 수 : 5
						if (nList.getLength() != 0) {
							Node nNode = nList.item(0);
							if (nNode.getNodeType() == Node.ELEMENT_NODE) {
							//	list[temp] = new ThemeTourList();

								Element eElement = (Element) nNode;
								System.out.println("######################");
								//System.out.println(eElement.getTextContent());
								System.out.println("123123");
								//list[temp].Seq = Integer.parseInt(getTagValue("ttSeq", eElement));
								
								data.Seq = getTagValue("ttSeq", eElement);
								data.Title = getTagValue("ttTitle", eElement);
								data.Contents = getTagValue("ttContents", eElement);

								System.out.println("Sequence  : " + data.Seq);
								System.out.println("Title  : " + data.Title);
								System.out.println("Contents  : " + data.Contents);

								
							} // for end
						}
		} catch (Exception e) {
			System.out.println(e.getMessage());

		}
		
		
		return data;
	}
	public static List<ThemeTourList> getTourList(int pageNum) {
		//String[] list = new String[10];
		//ThemeTourList list[] = new ThemeTourList[10];
		List<ThemeTourList> list = new ArrayList<ThemeTourList>();
		
		System.out.println("getTourList()");
		
		try {
			String urlstr = "http://openapi.jejutour.go.kr:8080/openapi/service/TourCourseService/getTourCosList?serviceKey=hRyXqe36RGUiRmgK3ob5Gi2NfMGXkX7tnlqOKYjcgQZGLPHCZH4gEQhLwjAxbbJXqHk%2FCI5UYWkNo8uPq9PC9A%3D%3D"
					+ "&pageNo=" + pageNum;

			System.out.println("page number : " + pageNum);
			DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
			Document doc = dBuilder.parse(urlstr);

			// root tag doc.getDocumentElement().normalize();
			System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

			NodeList nList = doc.getElementsByTagName("item");
			System.out.println("파싱할 리스트 수 : " + nList.getLength()); // 파싱할 리스트 수 : 5
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				//	list[temp] = new ThemeTourList();
					ThemeTourList data = new ThemeTourList();

					Element eElement = (Element) nNode;
					System.out.println("######################");
					//System.out.println(eElement.getTextContent());
					System.out.println("123123");
					//list[temp].Seq = Integer.parseInt(getTagValue("ttSeq", eElement));
					
					data.Seq = getTagValue("ttSeq", eElement);
					data.Title = getTagValue("ttTitle", eElement);
					System.out.println("Sequence  : " + data.Seq);
					System.out.println("Title  : " + data.Title);
					
					list.add(data);
				} // for end
			} // if end }catch(Exception e){ System.out.println(e.getMessage());

		} catch (Exception e) {
			System.out.println(e.getMessage());

		}
		return list;
	}

	public static List<EventInfo> getEventInfo(int pageNum) {
		//String[] list = new String[10];
		//EventInfo list[] = new EventInfo[10];
		List<EventInfo> list = new ArrayList<EventInfo>();

		try {
			String urlstr = "http://210.99.248.79/rest/ArtstreetService/getArtstreetList?"
					+ "authApiKey=DLVCGYUTQNLKFMU" 
					+ "&startPage=" + pageNum + "&pageSize=10";
System.out.println(urlstr);
			DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
			Document doc = dBuilder.parse(urlstr);
			System.out.println("페이지넘버 : " +pageNum);
			// root tag doc.getDocumentElement().normalize();
			System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

			NodeList nList = doc.getElementsByTagName("list");
			System.out.println("파싱할 리스트 수 : " + nList.getLength()); // 파싱할 리스트 수 : 5
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					EventInfo data = new EventInfo();

					Element eElement = (Element) nNode;
					System.out.println("######################");
					data.address = getTagValue("address", eElement);
					data.category = getTagValue("category", eElement);
					data.imgUrl = getTagValue("img_url", eElement);
					data.ceo = getTagValue("ceo", eElement);
					data.introduce = getTagValue("introduce", eElement);
					data.name = getTagValue("name", eElement);
					data.telephone = getTagValue("telephone", eElement);

					System.out.println("address : " + getTagValue("address", eElement));
					System.out.println("category : " + getTagValue("category", eElement));
					System.out.println("ceo : " + getTagValue("ceo", eElement));
					System.out.println("img_url : " + getTagValue("img_url", eElement));
					System.out.println("introduce : " + getTagValue("introduce", eElement));
					System.out.println("name : " + getTagValue("name", eElement));
					System.out.println("telephone : " + getTagValue("telephone", eElement));

					list.add(data);
				} // for end
			} // if end }catch(Exception e){ System.out.println(e.getMessage());

		} catch (Exception e) {
			System.out.println(e.getMessage());

		}
		return list;
	}
}
