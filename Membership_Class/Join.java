package study0610;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


class Join extends Frame implements ActionListener 
{
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
	
	Label lbTitle = new Label("회원가입화면");	
	Label lbId = new Label("I  D");
	Label lbPw = new Label("P  W");
	Label lbPw2 = new Label("PW확인");
	Label lbName = new Label("이  름");
	Label lbHp = new Label("H  P");
	Label lbAddr = new Label("주  소");

	Button btnCheck = new Button("중복체크");	
	Button btnJoin = new Button("가입하기");
	
	TextField tfId  = new TextField(20);	
	TextField tfPw  = new TextField(20);
	TextField tfPw2  = new TextField(20);
	TextField tfName  = new TextField(20);
	TextField tfHp  = new TextField(20);
	TextField tfAddr  = new TextField(20);
	
	boolean idCheck=false;
	
	public Join() 
	{
		super("회원가입");
		this.init();
		this.start();		
		this.setSize(500, 600);	
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
		lbTitle.setBounds(150, 50, 300, 30);		
		lbTitle.setFont(font25);
		
		this.add(lbId);
		lbId.setBounds(70, 130, 50, 30);
		lbId.setFont(font20);
		
		this.add(tfId);
		tfId.setBounds(150, 130, 200, 30);
		tfId.setFont(font20);	
		
		this.add(btnCheck);
		btnCheck.setBounds(370, 130, 100, 30);
		btnCheck.setFont(font20);
		
		this.add(lbPw);
		lbPw.setBounds(70, 180, 50, 30);
		lbPw.setFont(font20);
		
		this.add(tfPw);
		tfPw.setBounds(150, 180, 200, 30);
		tfPw.setFont(font20);
		tfPw.setEchoChar('*');
		
		this.add(lbPw2);
		lbPw2.setBounds(70, 230, 70, 30);
		lbPw2.setFont(font15);	
		
		this.add(tfPw2);
		tfPw2.setBounds(150, 230, 200, 30);
		tfPw2.setFont(font20);
		tfPw2.setEchoChar('*');
		
		this.add(lbName);
		lbName.setBounds(70, 280, 70, 30);
		lbName.setFont(font20);
		
		this.add(tfName);
		tfName.setBounds(150, 280, 200, 30);
		tfName.setFont(font20);
	
		this.add(lbHp);
		lbHp.setBounds(70, 330, 70, 30);
		lbHp.setFont(font20);
		
		this.add(tfHp);
		tfHp.setBounds(150, 330, 200, 30);
		tfHp.setFont(font20);

		this.add(lbAddr);
		lbAddr.setBounds(70, 380, 70, 30);
		lbAddr.setFont(font20);
		
		this.add(tfAddr);
		tfAddr.setBounds(150, 380, 200, 30);
		tfAddr.setFont(font20);
		
		this.add(btnJoin);
		btnJoin.setBounds(150, 480, 150, 30);
		btnJoin.setFont(font20);
		
	}

	public void start() 
	{
		 btnCheck.addActionListener(this); 
		 btnJoin.addActionListener(this);
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == btnCheck)
		{
			String id = tfId.getText();
			
			selectCheck(id);
				
		}
		else if(e.getSource() == btnJoin)
		{
			//회원가입시 입력한 값을 일단 가져와서 저장.
			String id = tfId.getText();
			String pw = tfPw.getText();
			String pw2 = tfPw2.getText();
			String name = tfName.getText();
			String hp = tfHp.getText();
			String addr = tfAddr.getText();
			
			if(id.equals("")) 
			{
				dlgMsg("아이디를 입력해주세요.");
				return;
			}
			else if(pw.equals("")) 
			{
				dlgMsg("비번을 입력해주세요.");
				return;
			}
			else if(pw2.equals("")) {dlgMsg("비번확인을 입력해주세요.");return;}
			else if(name.equals("")) {dlgMsg("이름을 입력해주세요.");return;}
			else if(hp.equals("")) {dlgMsg("전번을 입력해주세요.");return;}
			else if(addr.equals("")) {dlgMsg("주소를 입력해주세요.");return;}
			
			if(!pw.equals(pw2)){dlgMsg("비번과 비번확인이 같지 않습니다.");return;}
			
			if(idCheck == false){dlgMsg("아이디 중복체크를 하세요!");return;}
			
			//입력된 정보를 디비에 넣기위해서 메서드로 전달함...
			insert(id,pw,name,hp,addr);
			
			dlgMsg("회원가입이 완료되었습니다!");
		}
		
	}
	//아이디 중복체크
	void selectCheck(String joinId)
	{
		//디비에서 정보를 가져와서 텍스트에이리어에 뿌려주기				
		try 
		{
			//디비 접속을 위한 conn변수 생성
			conn = DriverManager.getConnection(url, id, pass);
			//디비에연결
			stmt = conn.createStatement();
			//디비에서 조회한 모든 데이타를 가져와서 rs에 저장
			String query= "select * from members";
			rs = stmt.executeQuery(query);
			//디비에서 조회한 모든데이타가 저장된 r의 값을 한줄씩 체크해서 없을때까지 반복
			while (rs.next()) 
			{
				//한줄씩 반복하면서 result.에 누적해서 저장
				//getString은 테이블의 컬럼명 기준으로 데이타를 가져올수 있음.
				if(joinId.equals(rs.getString("id")))
				{
					dlgMsg("아이디가 중복됩니다!!");
					idCheck=false;
					return;					
				}				
					
			}	
			
			dlgMsg("가입가능한 아이디입니다!!");
			
			idCheck=true;
			
			rs.close();
		} 
		catch (SQLException error) 
		{
			System.err.println("error = " + error.toString());			
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
		
		bt.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				dlg.setVisible(false);
			}
		});
		
		dlg.addWindowListener(new WindowAdapter() 
		{
			public void windowClosing(WindowEvent e) 
			{
				dlg.setVisible(false);
			}
		});
		
		dlg.add(lbTitle);
		lbTitle.setBounds(60, 70, 250, 30);		
		lbTitle.setFont(font15);
		
		dlg.add(bt);
		bt.setBounds(100, 130, 100, 30);		
		bt.setFont(font15);
		
		dlg.setLocation(xpos, 400);
		dlg.add("South", pp);
		dlg.setSize(300, 200);
		dlg.setVisible(true);
	}
	
	void insert(String joinId, String joinPw, String joinName,String joinHp,String joinAddr)
	{
		try 
		{
			//디비 연결
			conn = DriverManager.getConnection(url, id, pass);
			//삽입될때 쿼리문
			String query = "insert into members values (null, ?, ?, ?, ?, ?)";

			//쿼리문 수행
			pstmt = conn.prepareStatement(query);	
			
			//pstmt에 삽입될 변수값을 탑재
			pstmt.setString(1, joinId);
			pstmt.setString(2, joinPw);
			pstmt.setString(3, joinName);
			pstmt.setString(4, joinHp);			
			pstmt.setString(5, joinAddr);
			pstmt.executeUpdate();
		} 
		catch (SQLException e1) 
		{
			
		}	
		//입력한 값이디비에 들어가고 나머지 보기좋게 입력창 초기화
		tfId.setText("");
		tfPw.setText("");
		tfPw2.setText("");
		tfName.setText("");
		tfHp.setText("");
		tfAddr.setText("");

	}	
}
