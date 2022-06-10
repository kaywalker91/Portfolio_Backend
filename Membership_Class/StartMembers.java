package study0610;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class StartMembers {

	public static void main(String[] args) {
		Login login = new Login();
	}
}

class Login extends Frame implements ActionListener {
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
	
	Label lbTitle = new Label("로그인화면");	
	Label lbId = new Label("I D: ");
	Label lbPw = new Label("PW: ");
	
	Button btnLogin = new Button("로그인");	
	Button btnJoin = new Button("회원가입");
	
	TextField tfId  = new TextField(20);	
	TextField tfPw  = new TextField(20);
	
	boolean loginCheck = false; //로그인 성공시 true 평소에 false
	
	public Login() 
	{
		super("로그인");
		this.init();
		this.start();		
		this.setSize(420, 400);	
		dimen = Toolkit.getDefaultToolkit().getScreenSize();
		dimen1 = this.getSize();
		xpos = (int) (dimen.getWidth() / 2 - dimen1.getWidth() / 2);
		ypos = (int) (dimen.getHeight() / 2 - dimen1.getHeight() / 2);
		this.setLocation(xpos, ypos);
		this.setVisible(true);
		
	}

	public void init() 
	{
		// 앱솔루트방식  : 직접 좌표 입력방식.
		this.setLayout(null);

		Font font25 = new Font("SansSerif", Font.BOLD, 25);
		Font font20 = new Font("SansSerif", Font.BOLD, 20);
		Font font15 = new Font("SansSerif", Font.BOLD, 15);
		Font font10 = new Font("SansSerif", Font.BOLD, 10);
		
		//타이틀
		this.add(lbTitle);
		lbTitle.setBounds(140, 50, 300, 30);		
		lbTitle.setFont(font25);
		
		this.add(lbId);
		lbId.setBounds(100, 120, 50, 30);
		lbId.setFont(font15);
		
		this.add(tfId);
		tfId.setBounds(150, 120, 150, 30);
		tfId.setFont(font15);
	
		this.add(lbPw);
		lbPw.setBounds(100, 170, 50, 30);
		lbPw.setFont(font15);
		
		this.add(tfPw);
		tfPw.setBounds(150, 170, 150, 30);
		tfPw.setFont(font15);
		tfPw.setEchoChar('*');
		
		this.add(btnLogin);
		btnLogin.setBounds(130, 250, 150, 30);
		btnLogin.setFont(font15);

		this.add(btnJoin);
		btnJoin.setBounds(130, 300, 150, 30);
		btnJoin.setFont(font15);
		
	}

	public void start() 
	{
		 btnLogin.addActionListener(this); 
		 btnJoin.addActionListener(this); 
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == btnLogin)
		{
			String id = tfId.getText();
			String pw = tfPw.getText();
			
			if(id.equals("")) {dlgMsg("아이디를 입력해주세요.");return;}
			if(pw.equals("")) {dlgMsg("비번을 입력해주세요.");return;}
			
			checkLogin(id, pw);
			
			if(loginCheck == true)
			{
				Main main = new Main(id);
				setVisible(false);
			}
			else
			{
				dlgMsg("아이디 비번이 일치하지않습니다!");
			}
		}
		else if(e.getSource() == btnJoin)
		{
			Join join = new Join();
		}
		
	}
	
	
	//뭔가 알림창으로 공통으로 쓰기위해서 만듬.
			void dlgMsg(String msg)
			{
				final Dialog dlg = new Dialog(this, "알림", true);
				dlg.setLayout(null);	
				
				Font font15 = new Font("SansSerif", Font.BOLD, 15);
				Font font10 = new Font("SansSerif", Font.BOLD, 10);
				
				Label lbTitle = new Label(msg);
				
				
				Button bt = new Button("확인");
				Panel pp = new Panel(new FlowLayout());
				
				pp.add(bt);
				
				bt.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dlg.setVisible(false);
					}
				});
				
				dlg.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent e) {
						dlg.setVisible(false);
					}
				});
				
				dlg.add(lbTitle);
				lbTitle.setBounds(30, 50, 250, 30);		
				lbTitle.setFont(font15);
				
				dlg.add(bt);
				bt.setBounds(100, 100, 100, 30);		
				bt.setFont(font15);
				
				dlg.setLocation(xpos, 400);
				dlg.add("South", pp);
				dlg.setSize(300, 200);
				dlg.setVisible(true);
			}
	void checkLogin(String loginId, String loginPw)
	{
		//디비에서 정보를 가져와서 텍스트에이리어에 뿌려주기				
		try 
		{
			//디비 접속을 위한 conn변수 생성
			conn = DriverManager.getConnection(url, id, pass);
			//디비에연결
			stmt = conn.createStatement();
			//디비에서 조회한 모든 데이타를 가져와서 rs에 저장
			
			String loginQuery = "select * from members where id='"+loginId+"' AND pw='"+loginPw+"'";
			rs = stmt.executeQuery(loginQuery);
			//디비에서 조회한 모든데이타가 저장된 r의 값을 한줄씩 체크해서 없을때까지 반복
			while (rs.next()) 
			{
				
				//rs에 값이 있다는것은 로그인아이디 비번이 일치하는 정보가 있다는것이므로 로그인성공!!!
				loginCheck =true;
				
			}

			rs.close();
		} 
		catch (SQLException error) 
		{
			System.err.println("error = " + error.toString());			
		}	
	}	
}

