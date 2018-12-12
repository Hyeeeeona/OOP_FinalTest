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

			NodeList nList = doc.getElementsByTagName("item");
			if (nList.getLength() != 0) {
				Node nNode = nList.item(0);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					System.out.println("######################");

					data.Seq = getTagValue("ttSeq", eElement);
					data.Title = getTagValue("ttTitle", eElement);
					data.Contents = getTagValue("ttContents", eElement);

					/* Debugging */
					System.out.println("Sequence  : " + data.Seq);
					System.out.println("Title  : " + data.Title);
					System.out.println("Contents  : " + data.Contents);

				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());

		}

		return data;
	}

	public static List<ThemeTourList> getTourList(int pageNum) {
		List<ThemeTourList> list = new ArrayList<ThemeTourList>();

		System.out.println("getTourList()");

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

	public static List<EventInfo> getEventInfo(int pageNum) {
		List<EventInfo> list = new ArrayList<EventInfo>();

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

	/* 현재 날씨와 n시간 후의 날씨가 다른 API로 제공되기 때문에
	 * 현재 날씨 호출 후 3, 6, 9시간 후의 날씨를 호출한다.
	 */
	public static Weather[] getWeather() {
		final int WeatherBufSize = 4;
		Weather data[] = new Weather[WeatherBufSize];

		try {
			String urlstr = "http://api.openweathermap.org/data/2.5/weather?q=jeju&appid=394332de5333ced44096aa2f2932fb42&mode=xml";

			DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
			Document doc = dBuilder.parse(urlstr);
			// root tag doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("current");

			if (nList.getLength() == 0)
				return data;

			Node nNode = nList.item(0);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				data[0] = new Weather();

				Element eElement = (Element) nNode;

				NodeList subList = eElement.getElementsByTagName("temperature");
				Element subEl = (Element) subList.item(0);
				data[0].temp = (String.format("%.1f", (Double.parseDouble(subEl.getAttributes().getNamedItem("value").getNodeValue()) - 273.15))) + "℃";

				//data[0].temp = Double.parseDouble(subEl.getAttributes().getNamedItem("value").getNodeValue());
				subList = eElement.getElementsByTagName("humidity");
				subEl = (Element) subList.item(0);

				data[0].humidity = Integer.parseInt(subEl.getAttributes().getNamedItem("value").getNodeValue());
				subList = eElement.getElementsByTagName("clouds");
				subEl = (Element) subList.item(0);

				data[0].cloud = subEl.getAttributes().getNamedItem("value").getNodeValue() + "%";
				subList = eElement.getElementsByTagName("weather");
				subEl = (Element) subList.item(0);
				data[0].icon = subEl.getAttributes().getNamedItem("icon").getNodeValue();

				/* Debugging */
				System.out.println("######################");
				System.out.println("temperature : " + data[0].temp);
				System.out.println("humidity : " + data[0].humidity);
				System.out.println("cloud : " + data[0].cloud);
				System.out.println("icon : " + data[0].icon);

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		try {
			String urlstr = "http://api.openweathermap.org/data/2.5/forecast?q=jeju&appid=394332de5333ced44096aa2f2932fb42&mode=xml";

			DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
			Document doc = dBuilder.parse(urlstr);
			// root tag doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("time");

			if (nList.getLength() == 0)
				return data;

			/* 3시간 뒤, 6시간 뒤, 9시간 뒤의 날씨 데이터를 가져온다 */
			for (int i = 0; i < WeatherBufSize - 1; i++) {
				Node nNode = nList.item(i);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					data[i+1] = new Weather();

					Element eElement = (Element) nNode;

					NodeList subList = eElement.getElementsByTagName("temperature");
					Element subEl = (Element) subList.item(0);
					data[i+1].temp = (String.format("%.1f", (Double.parseDouble(subEl.getAttributes().getNamedItem("value").getNodeValue()) - 273.15))) + "℃";
					
					subList = eElement.getElementsByTagName("humidity");
					subEl = (Element) subList.item(0);
					data[i+1].humidity = Integer.parseInt(subEl.getAttributes().getNamedItem("value").getNodeValue());
					
					subList = eElement.getElementsByTagName("clouds");
					subEl = (Element) subList.item(0);
					data[i+1].cloud = subEl.getAttributes().getNamedItem("value").getNodeValue() + "%";
					
					subList = eElement.getElementsByTagName("symbol");
					subEl = (Element) subList.item(0);
					data[i+1].main = subEl.getAttributes().getNamedItem("name").getNodeValue();
					
					/* Debugging */
					System.out.println("######################");
					System.out.println("temperature : " + data[i+1].temp);
					System.out.println("humidity : " + data[i+1].humidity);
					System.out.println("cloud : " + data[i+1].cloud);
					System.out.println("main : " + data[i+1].main);

				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return data;
	}
}
