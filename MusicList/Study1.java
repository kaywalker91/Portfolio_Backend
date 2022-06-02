package study0602_2;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Study1 {

	public static void main(String[] args) {
		Music f = new Music();
	}
}

class Music extends Frame implements ActionListener {
	//디비 접속 정보 
	Connection conn = null;
	String url = "jdbc:mysql://127.0.0.1:3306/study?useUnicode=true&characterEncoding=utf8";
	String id = "root";// ID
	String pass = "qwer";// 비밀번호	
	
	//디비 데이타 삽입용
	PreparedStatement pstmt = null;	
		
	//디비 조회용
	Statement stmt = null;
	ResultSet rs = null;
	
	
	String result="";//텍스트에이리어에 누적해서 한번에 출력하기위해서 
			

	
	//윈도우 중앙배치를 위한 변수 
	private Dimension dimen, dimen1;
	private int xpos, ypos;
	
	Button btnAdd = new Button("추가");	
	Button btnSort = new Button("정렬");
	Label lbTitle = new Label("내가 즐겨 듣는 노래");	
	Label lbMusicTitle = new Label("노래 제목:");
	Label lbSinger = new Label("가수이름  :");
	Label lbCount = new Label("들은횟수 : ");
	Label lbList = new Label("내가 들은 노래 리스트");
	TextField tfMusicTitle  = new TextField(20);	
	TextField tfSinger  = new TextField(20);
	TextField tfCount  = new TextField(20);
	
	TextArea taList = new TextArea();
	
	
	public Music() {
		super("내가 즐겨 듣는 노래");
		this.init();
		this.start();		
		this.setSize(420, 600);	
		dimen = Toolkit.getDefaultToolkit().getScreenSize();
		dimen1 = this.getSize();
		xpos = (int) (dimen.getWidth() / 2 - dimen1.getWidth() / 2);
		ypos = (int) (dimen.getHeight() / 2 - dimen1.getHeight() / 2);
		this.setLocation(xpos, ypos);
		this.setVisible(true);
		
		
		
		
		
		
	}

	public void init() {
		// 앱솔루트방식  : 직접 좌표 입력방식.
		this.setLayout(null);

		
		Font font20 = new Font("SansSerif", Font.BOLD, 20);
		Font font15 = new Font("SansSerif", Font.BOLD, 15);
		Font font10 = new Font("SansSerif", Font.BOLD, 10);
		
		//타이틀
		this.add(lbTitle);
		lbTitle.setBounds(100, 50, 300, 30);		
		lbTitle.setFont(font20);
		
		this.add(lbMusicTitle);
		lbMusicTitle.setBounds(50, 100, 100, 30);
		lbMusicTitle.setFont(font15);
		
		this.add(tfMusicTitle);
		tfMusicTitle.setBounds(170, 100, 150, 30);
		tfMusicTitle.setFont(font15);
	
		this.add(lbSinger);
		lbSinger.setBounds(50, 150, 100, 30);
		lbSinger.setFont(font15);
		
		this.add(tfSinger);
		tfSinger.setBounds(170, 150, 150, 30);
		tfSinger.setFont(font15);
		
		this.add(lbCount);
		lbCount.setBounds(50, 200, 100, 30);
		lbCount.setFont(font15);
		
		this.add(tfCount);
		tfCount.setBounds(170, 200, 150, 30);
		tfCount.setFont(font15);		
		
		
		this.add(btnAdd);
		btnAdd.setBounds(220, 250, 80, 30);
		btnAdd.setFont(font15);
		
		this.add(lbList);
		lbList.setBounds(50, 300, 170, 30);
		lbList.setFont(font15);
		
		this.add(btnSort);
		btnSort.setBounds(220, 300, 80, 30);
		btnSort.setFont(font15);
		
		this.add(taList);
		taList.setBounds(50, 350, 300, 200);
		taList.setFont(font15);

		//처음 시작시 테이블 조회해서 뿌려주기
		loadData();
		
	}

	public void start() {
		
		
		 btnAdd.addActionListener(this); 
		 btnSort.addActionListener(this); 
		
		
		
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnAdd)
		{
			result="";//삽입시 기존 결과가 유지되고있어서 초기화
			
			//입력한 텍스트필드값 변수에저장
			String title = tfMusicTitle.getText();
			String singer = tfSinger.getText();
			String count = tfCount.getText();
			

			try {
				//디비 연결
				conn = DriverManager.getConnection(url, id, pass);
				//삽입될때 쿼리문
				String query = "insert into music_list values (null, ?, ?, ?)";

				//쿼리문 수행
				pstmt = conn.prepareStatement(query);	
				
				//pstmt에 삽입될 변수값을 탑재
				pstmt.setString(1, title);
				pstmt.setString(2, singer);
				pstmt.setInt(3, Integer.parseInt(count));
				pstmt.executeUpdate();
			} catch (SQLException e1) {			
			}	
			
			//입력한 값이디비에 들어가고 나머지 보기좋게 입력창 초기화
			tfMusicTitle.setText("");
			tfSinger.setText("");
			tfCount.setText("");
			
			//테이블 조회해서 뿌려주기
			loadData();
			
			
		}
		else if(e.getSource() == btnSort)
		{
			result="< 제일 많이들은 순서로 정렬 > \n";//삽입시 기존 결과가 유지되고있어서 초기화
			
			//정렬된 데이타 뿌려주기
			loadDataDesc();
		}
		
	}
	
	void loadData()
	{
		//디비에서 정보를 가져와서 텍스트에이리어에 뿌려주기				
		try {
			//디비 접속을 위한 conn변수 생성
			conn = DriverManager.getConnection(url, id, pass);
			//디비에연결
			stmt = conn.createStatement();
			//디비에서 조회한 모든 데이타를 가져와서 rs에 저장
			rs = stmt.executeQuery("select * from music_list");
			//디비에서 조회한 모든데이타가 저장된 r의 값을 한줄씩 체크해서 없을때까지 반복
			while (rs.next()) {
				//한줄씩 반복하면서 result.에 누적해서 저장
				//getString은 테이블의 컬럼명 기준으로 데이타를 가져올수 있음.
				result += rs.getString("title") +"/"+ rs.getString("singer") + "/"+rs.getInt("count")+"\n";	
			}
			//한번에 화면에 뿌리기
			taList.setText(result);
			rs.close();
		} catch (SQLException error) {
			System.err.println("error = " + error.toString());			
		}	
	}
	void loadDataDesc()
	{
		//디비에서 정보를 가져와서 텍스트에이리어에 뿌려주기				
		try {
			//디비 접속을 위한 conn변수 생성
			conn = DriverManager.getConnection(url, id, pass);
			//디비에연결
			stmt = conn.createStatement();
			//디비에서 조회한 모든 데이타를 가져와서 rs에 저장
			rs = stmt.executeQuery("select * from music_list ORDER BY count DESC");
			//디비에서 조회한 모든데이타가 저장된 r의 값을 한줄씩 체크해서 없을때까지 반복
			while (rs.next()) {
				//한줄씩 반복하면서 result.에 누적해서 저장
				//getString은 테이블의 컬럼명 기준으로 데이타를 가져올수 있음.
				result += rs.getInt("count")+"회 들음! "+rs.getString("title") +" / "+ rs.getString("singer") +"\n";	
			}
			//한번에 화면에 뿌리기
			taList.setText(result);
			rs.close();
		} catch (SQLException error) {
			System.err.println("error = " + error.toString());			
		}	
	}
		
}

