
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;




import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import javax.imageio.ImageIO;

public class JPanelTest extends JFrame {
	public mainPanel mainpn = null;
	public JPanelThemeList panelThemeList = null;
	public JPanelThemeInfo panelThemeInfo = null;
	public JPanelEvent panelEvent = null;
	public JPanelEventInfo panelEventInfo = null;
	static JPanelTest win;

	/* Num is index of page or sequence
	 * It means unused value if Num is 0. */
	public void change(String panelName, int Num) {
		if (panelName.equals("mainPanel")) {
			getContentPane().removeAll();
			getContentPane().add(mainpn);
			revalidate();
			repaint();
		} else if (panelName.equals("panelThemeList")) {
			System.out.println("else if pagenum : " + Num);
			//panelThemeList.pageNum = pageNum;
			win.panelThemeList = new JPanelThemeList(win);
			getContentPane().removeAll();
			getContentPane().add(panelThemeList);
			revalidate();
			repaint();
		} else if (panelName.equals("panelThemeInfo")) {
			win.panelThemeInfo = new JPanelThemeInfo(win, Num);
			getContentPane().removeAll();
			getContentPane().add(panelThemeInfo);
			revalidate();
			repaint();
		} if (panelName.equals("panelEvent")) {
			win.panelEvent = new JPanelEvent(win);
			getContentPane().removeAll();
			getContentPane().add(panelEvent);
			revalidate();
			repaint();
		} 
	}
	
	public void change(String panelName, EventInfo info) {
		if (panelName.equals("panelEventInfo")) {
			win.panelEventInfo = new JPanelEventInfo(win, info);
			getContentPane().removeAll();
			getContentPane().add(panelEventInfo);
			revalidate();
			repaint();
		}
	}
	
	public static void main(String[] args) {
		win = new JPanelTest();
		
		win.setTitle("frame test");
		win.mainpn = new mainPanel(win);

		win.add(win.mainpn);
		win.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		win.setSize(400, 600);
		win.setVisible(true);
		
		// EventInfo.getEventInfo(1);
	}
	
}

class mainPanel extends JPanel {

	JFrame jFrame = new JFrame("제주여행");
	JButton[] btn = new JButton[6]; // 6개 생성
	String btnName[] = { "테마별 코스 추천", "주변 식당 찾기", "제주도 축제 정보", "제주도 날씨", "미정","미정"};
	private JPanelTest win;

	public mainPanel(JPanelTest win) {
		this.win = win;
		setLayout(null);
		// GridLayout 적용
		setLayout(new GridLayout(3, 2));

		// 컴포넌트 생성 및 추가하기
		for (int i = 0; i < btn.length; i++) {
			btn[i] = new JButton(btnName[i]);
			add(btn[i]);
		}
		btn[0].addActionListener(new MyActionListenerThemeList());
		btn[2].addActionListener(new MyActionListenerEvent());

		// 종료버튼에 대한 설정
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	class MyActionListenerThemeList implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("change panel");
			win.change("panelThemeList", 1);
		}
	}
	class MyActionListenerEvent implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("change panel");
			win.change("panelEvent", 1);
		}
	}
}

class JPanelEventInfo extends JPanel {
	private JPanelTest win;

	public JPanelEventInfo(JPanelTest win, EventInfo info) {
		this.win = win;
		setLayout(null);
		
		JLabel name = new JLabel(info.name);
		JLabel introduce = new JLabel("소개 : " + info.introduce);
		JLabel telephone = new JLabel("전화번호 : " + info.telephone);
		JLabel address = new JLabel("주소 : " + info.address);
		JLabel category = new JLabel(info.category);
		JLabel ceo = new JLabel("대표 : " + info.ceo);
		
		System.out.println(info.name + " " + info.introduce + " ");
		System.out.println(info.imgUrl);
		Image img = null;
		URL url;
		try {
			url = new URL(info.imgUrl);
			img = ImageIO.read(url);

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ImageIcon imgIcon = new ImageIcon(img);
		JLabel labelImg = new JLabel(imgIcon);
		/*
		add(labelImg);
		add(name);
		add(telephone);
		add(address);
		add(ceo);
		add(introduce);
		labelImg.setBounds(0, 20, 100, 100);
		labelImg.setBackground(Color.BLACK);
		name.setBounds(10, 300, 400, 50);
		telephone.setBounds(10, 360, 400, 50);
		address.setBounds(10, 420, 400, 50);
		ceo.setBounds(10, 480, 400, 50);
		introduce.setBounds(10, 540, 400, 50 );
		*/
		labelImg.setBounds(0, 20, 400, 150);
		add(labelImg);

		introduce.setText("<html><p style=\"width:300px\">" + (info.introduce == null ? "" : info.introduce) + "</p></html>");

		JLabel p = new JLabel();
		
		p.setText("<html><p style=\"width:300px\"><br>" + (info.name == null ? "" : info.name) + "<br><br>전화번호 : " + (info.telephone == null ? "" : info.telephone) + "<br><br>주소 : " + (info.address == null ? "" : info.address) + "<br><br>대표 : " + (info.ceo == null ? "" : info.ceo) + "<br><br>소개 : " + info.introduce + "<br></p></html>");
		p.setBounds(10, 180, 400, 300);
		add(p);
		
		JButton btnPrev = new JButton("뒤로가기");
		btnPrev.addActionListener(new MyActionListenerPrev());
		btnPrev.setBounds(140, 520, 120, 30);
		add(btnPrev);
		

	}
	private class MyActionListenerPrev implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("prev");

			win.change("panelEvent", 0);
		}
	}
}
/* 행사/축제 정보 */
class JPanelEvent extends JPanel {
	private JPanelTest win;
	static int gPageNum = 1;
	
	public JPanelEvent(JPanelTest win) {
		int y=30;
		EventInfo data = new EventInfo();
		List<EventInfo> list = new ArrayList<EventInfo>();
		list = GetOpenAPI.getEventInfo(gPageNum);

		/* 다음 목록이 없을 경우 이전 페이지 호출 */
		if (list.size() == 0) {
			gPageNum--;
			list = GetOpenAPI.getEventInfo(gPageNum);
		}
		System.out.println("Event Page num : " + gPageNum);
		this.win = win;
		setLayout(null);
		
		Iterator<EventInfo> it = list.iterator();

		while(it.hasNext()) {
			data = it.next();
			JLabel label = new JLabel(data.name);
			label.setBounds(20, y, 300, 20);
			label.addMouseListener(new printEventInfo(data));
			add(label);
			y += 45;
		}
		
		/* 버튼 생성 */
		JButton btnMain = new JButton("메인화면");
		JButton btnPrev = new JButton("이전");
		JButton btnNext = new JButton("다음");
		
		btnMain.addActionListener(new MyActionListener());
		btnPrev.addActionListener(new MyActionListenerPrev());
		btnNext.addActionListener(new MyActionListenerNext());
		
		btnMain.setBounds(15, 520, 120, 30);
		btnPrev.setBounds(140, 520, 120, 30);
		btnNext.setBounds(265, 520, 120, 30);

		add(btnMain);
		add(btnPrev);
		add(btnNext);
		
	}
	
	private class MyActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("change panel");
			win.change("mainPanel", 0);
		}
	}
	
	private class MyActionListenerNext implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("next");
			
			/* 다음 페이지에 내용이 없어도 API 에서 마지막 내용을 리턴함.
			 * 그래서 최대 2페이지만 보여주도록 강제함 
			 */
			if (gPageNum == 1)
				gPageNum++;
			win.change("panelEvent", gPageNum);
		}
	}
	
	private class MyActionListenerPrev implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("prev");
			if (gPageNum > 1)
				gPageNum--;
			win.change("panelEvent", gPageNum);
		}
	}
	
	private class printEventInfo implements MouseListener{
		 EventInfo info;
		 
		 public printEventInfo(EventInfo info) {
			 this.info = info;
		 }
		 @Override
		 public void mouseClicked(MouseEvent e){
			 System.out.println("click");
			 win.change("panelEventInfo", info);
		 }

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	 }
}

/* 테마별 투어 정보 */
class JPanelThemeList extends JPanel {
	private JPanelTest win;
	static int gPageNum = 1;

	public JPanelThemeList(JPanelTest win) {
		int y=30;
		ThemeTourList data = new ThemeTourList();
		List<ThemeTourList> list = new ArrayList<ThemeTourList>();
		list = GetOpenAPI.getTourList(gPageNum);
		
		/* 다음 목록이 없을 경우 이전 페이지 호출 */
		if (list.size() == 0) {
			gPageNum--;
			list = GetOpenAPI.getTourList(gPageNum);
		}
		System.out.println("size : " + list.size());

		this.win = win;
		setLayout(null);
		
		/* 리스트 출력 */
		Iterator<ThemeTourList> it = list.iterator();
		while(it.hasNext()) {
			data = it.next();
			System.out.println(data.Title);
			JLabel label = new JLabel(data.Title);
			label.setBounds(20, y, 300, 20);
			label.addMouseListener(new printTourInfo(data.Seq));
			//label.addMouseListener(l);
			add(label);
			y += 45;
		}
		
		/* 버튼 생성 */
		JButton btnMain = new JButton("메인화면");
		JButton btnPrev = new JButton("이전");
		JButton btnNext = new JButton("다음");
		
		btnMain.addActionListener(new MyActionListener());
		btnPrev.addActionListener(new MyActionListenerPrev());
		btnNext.addActionListener(new MyActionListenerNext());
		
		btnMain.setBounds(15, 520, 120, 30);
		btnPrev.setBounds(140, 520, 120, 30);
		btnNext.setBounds(265, 520, 120, 30);

		add(btnMain);
		add(btnPrev);
		add(btnNext);
		
		//gPageNum++;
	}
	private class MyActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("change panel");
			gPageNum = 1;
			win.change("mainPanel", 0);
		}
	}
	
	private class MyActionListenerNext implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("next");
				
			win.change("panelThemeList", ++gPageNum);
		}
	}
	
	private class MyActionListenerPrev implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("prev");
			if (gPageNum > 1)
				gPageNum--;
			win.change("panelThemeList", gPageNum);
		}
	}
	
	 class printTourInfo implements MouseListener{
		 String Seq;
		 
		 public printTourInfo(String Seq) {
			 this.Seq = Seq;
		 }
		 @Override
		 public void mouseClicked(MouseEvent e){
			 System.out.println("click");
			 System.out.println(e);
			 System.out.println(Seq);
			 win.change("panelThemeInfo", Integer.parseInt(Seq));
		 }

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	 }
}
class JPanelThemeInfo extends JPanel {
	private JPanelTest win;
	
	public JPanelThemeInfo(JPanelTest win, int Seq) {
		ThemeTourInfo data = new ThemeTourInfo();
		data = GetOpenAPI.getTourInfo(Seq);

		this.win = win;
		setLayout(null);
		
//		data.Contents.replace("\n", "<br>");
		
		JLabel title = new JLabel(data.Title);
		JLabel contents = new JLabel(data.Contents);
		JButton btnPrev = new JButton("뒤로가기");
		
		title.setBounds(10, 10, 300, 20);
		contents.setBounds(10, 20, 300, 200);
		btnPrev.setBounds(140, 520, 120, 30);
		contents.setText("<html><p style=\"width:450px\">" + data.Contents + "</p></html>");
		
		btnPrev.addActionListener(new MyActionListenerPrev());

		add(title);
		add(contents);
		add(btnPrev);
	}
	private class MyActionListenerPrev implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("prev");

			win.change("panelThemeList", 0);
		}
	}
}