package study0610;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


class Edit extends Frame implements ActionListener 
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
	
	Label lbTitle = new Label("회원수정화면");	
	Label lbId = new Label("I D");

	Label lbName = new Label("이 름");
	Label lbHp = new Label("P W");
	Label lbAddr = new Label("주 소");

	Button btnCheck = new Button("찾기");	
	Button btnEdit = new Button("수정완료");
	
	TextField tfId  = new TextField(20);	

	TextField tfName  = new TextField(20);
	TextField tfHp  = new TextField(20);
	TextField tfAddr  = new TextField(20);
	
	public Edit() 
	{
		super("회원수정화면");
		this.init();
		this.start();		
		this.setSize(450, 450);	
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
		lbTitle.setBounds(140, 70, 300, 30);		
		lbTitle.setFont(font25);
		
		this.add(lbId);
		lbId.setBounds(50, 120, 70, 30);
		lbId.setFont(font20);
		
		this.add(tfId);
		tfId.setBounds(130, 120, 200, 30);
		tfId.setFont(font20);	
		
		this.add(btnCheck);
		btnCheck.setBounds(340, 120, 70, 30);
		btnCheck.setFont(font15);	

		this.add(lbName);
		lbName.setBounds(50, 170, 70, 30);
		lbName.setFont(font20);
		
		this.add(tfName);
		tfName.setBounds(130, 170, 200, 30);
		tfName.setFont(font20);
	
		this.add(lbHp);
		lbHp.setBounds(50, 220, 70, 30);
		lbHp.setFont(font20);
		
		this.add(tfHp);
		tfHp.setBounds(130, 220, 200, 30);
		tfHp.setFont(font20);

		this.add(lbAddr);
		lbAddr.setBounds(50, 270, 70, 30);
		lbAddr.setFont(font20);
		
		this.add(tfAddr);
		tfAddr.setBounds(130, 270, 200, 30);
		tfAddr.setFont(font20);
		
		this.add(btnEdit);
		btnEdit.setBounds(150, 340, 150, 40);
		btnEdit.setFont(font20);
		
	}

	public void start() 
	{
		 btnCheck.addActionListener(this); 
		 btnEdit.addActionListener(this); 
		
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
			
			if(id.equals("")) 
			{
				dlgMsg("아이디를 입력해주세요.");
				return;
			}
			//아이디로 해당 정보를 찾아서 입력창에 가져옴
			search(id);
			//아이디는 변경 불가능하게 막음
			tfId.setEnabled(false);
			
		}
		else if(e.getSource() == btnEdit)
		{
			String id = tfId.getText();
			String name = tfName.getText();
			String hp= tfHp.getText();
			String addr = tfAddr.getText();
			
			//현재 입력창에 가져온 정보로 업데이트하기
			if(id.equals("") || name.equals("") || hp.equals("") || addr.equals("")) 
			{
				dlgMsg("공백란를 입력해주세요.");
			}
			else
			{
				updateInfo();
			}
		}
		
	}
	
	void updateInfo()
	{
				
		String id = tfId.getText();
		String name = tfName.getText();
		String phone = tfHp.getText();
		String addr = tfAddr.getText();		
		
		String query = "update members set name = ?, phone = ?, addr = ? where id = ?";
		
		try 
		{
			PreparedStatement pstmt = conn.prepareStatement(query);		
			pstmt.setString(1, name);
			pstmt.setString(2, phone);
			pstmt.setString(3, addr);
			pstmt.setString(4, id);
			pstmt.executeUpdate();
			pstmt.close();
			System.err.println("회원 정보수정 완료!!");
		} 
		catch (SQLException ee) 
		{
			System.err.println("회원 정보수정 실패!!");
			
		}
		
	}
	
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
	
	void search(String editId)
	{
		//디비에서 정보를 가져와서 텍스트에이리어에 뿌려주기				
		try 
		{
			//디비 접속을 위한 conn변수 생성
			conn = DriverManager.getConnection(url, id, pass);
			//디비에연결
			stmt = conn.createStatement();
			//디비에서 조회한 모든 데이타를 가져와서 rs에 저장
			String idSelectQuery = "select * from members where id='"+editId+"'";
			rs = stmt.executeQuery(idSelectQuery);
			//디비에서 조회한 모든데이타가 저장된 r의 값을 한줄씩 체크해서 없을때까지 반복
			while (rs.next()) 
			{
				
				tfName.setText(rs.getString("name"));
				tfHp.setText(rs.getString("phone"));
				tfAddr.setText(rs.getString("addr"));						
					
			}

			rs.close();
		} 
		catch (SQLException error) 
		{
			System.err.println("error = " + error.toString());			
		}	
	}	
}

