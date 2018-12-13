import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Weather extends Service implements GetInfo{
	String main;
	String icon;
	String iconUrl;
	String temp;
	int humidity;
	String cloud;
	
	public List<Service> getInfo(int pageNum) {
		return null;
	}
	/* 현재 날씨와 n시간 후의 날씨가 다른 API로 제공되기 때문에
	 * 현재 날씨 호출 후 3, 6, 9시간 후의 날씨를 호출한다.
	 */
	public List<Service> getInfo() {
		final int WeatherBufSize = 4;
		List<Service> list = new ArrayList<Service>();
		Weather data = new Weather();

		try {
			String urlstr = "http://api.openweathermap.org/data/2.5/weather?q=jeju&appid=394332de5333ced44096aa2f2932fb42&mode=xml";

			DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
			Document doc = dBuilder.parse(urlstr);
			// root tag doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("current");

			if (nList.getLength() == 0)
				return list;

			Node nNode = nList.item(0);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				data = new Weather();

				Element eElement = (Element) nNode;

				NodeList subList = eElement.getElementsByTagName("temperature");
				Element subEl = (Element) subList.item(0);
				data.temp = (String.format("%.1f", (Double.parseDouble(subEl.getAttributes().getNamedItem("value").getNodeValue()) - 273.15))) + "℃";

				//data[0].temp = Double.parseDouble(subEl.getAttributes().getNamedItem("value").getNodeValue());
				subList = eElement.getElementsByTagName("humidity");
				subEl = (Element) subList.item(0);

				data.humidity = Integer.parseInt(subEl.getAttributes().getNamedItem("value").getNodeValue());
				subList = eElement.getElementsByTagName("clouds");
				subEl = (Element) subList.item(0);

				data.cloud = subEl.getAttributes().getNamedItem("value").getNodeValue() + "%";
				subList = eElement.getElementsByTagName("weather");
				subEl = (Element) subList.item(0);
				data.icon = subEl.getAttributes().getNamedItem("icon").getNodeValue();

				/* Debugging */
				System.out.println("######################");
				System.out.println("temperature : " + data.temp);
				System.out.println("humidity : " + data.humidity);
				System.out.println("cloud : " + data.cloud);
				System.out.println("icon : " + data.icon);
				
				list.add(data);
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
				return list;

			/* 3시간 뒤, 6시간 뒤, 9시간 뒤의 날씨 데이터를 가져온다 */
			for (int i = 0; i < WeatherBufSize - 1; i++) {
				Node nNode = nList.item(i);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					data = new Weather();

					Element eElement = (Element) nNode;

					NodeList subList = eElement.getElementsByTagName("temperature");
					Element subEl = (Element) subList.item(0);
					data.temp = (String.format("%.1f", (Double.parseDouble(subEl.getAttributes().getNamedItem("value").getNodeValue()) - 273.15))) + "℃";
					
					subList = eElement.getElementsByTagName("humidity");
					subEl = (Element) subList.item(0);
					data.humidity = Integer.parseInt(subEl.getAttributes().getNamedItem("value").getNodeValue());
					
					subList = eElement.getElementsByTagName("clouds");
					subEl = (Element) subList.item(0);
					data.cloud = subEl.getAttributes().getNamedItem("value").getNodeValue() + "%";
					
					subList = eElement.getElementsByTagName("symbol");
					subEl = (Element) subList.item(0);
					data.main = subEl.getAttributes().getNamedItem("name").getNodeValue();
					
					/* Debugging */
					System.out.println("######################");
					System.out.println("temperature : " + data.temp);
					System.out.println("humidity : " + data.humidity);
					System.out.println("cloud : " + data.cloud);
					System.out.println("main : " + data.main);

					list.add(data);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return list;
	}
}
