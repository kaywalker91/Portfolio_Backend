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
	//��� ���� ���� 
	Connection conn = null;
	String url = "jdbc:mysql://127.0.0.1:3306/study?useUnicode=true&characterEncoding=utf8";
	String id = "root";// ID
	String pass = "qwer";// ��й�ȣ	
	
	//��� ����Ÿ ���Կ�
	PreparedStatement pstmt = null;	
		
	//��� ��ȸ��
	Statement stmt = null;
	ResultSet rs = null;
	
	String result="";//�ؽ�Ʈ���̸�� �����ؼ� �ѹ��� ����ϱ����ؼ� 
			
	//������ �߾ӹ�ġ�� ���� ���� 
	private Dimension dimen, dimen1;
	private int xpos, ypos;
	
	Label lbTitle = new Label("ȸ������ȭ��");	
	Label lbId = new Label("I  D");
	Label lbPw = new Label("P  W");
	Label lbPw2 = new Label("PWȮ��");
	Label lbName = new Label("��  ��");
	Label lbHp = new Label("H  P");
	Label lbAddr = new Label("��  ��");

	Button btnCheck = new Button("�ߺ�üũ");	
	Button btnJoin = new Button("�����ϱ�");
	
	TextField tfId  = new TextField(20);	
	TextField tfPw  = new TextField(20);
	TextField tfPw2  = new TextField(20);
	TextField tfName  = new TextField(20);
	TextField tfHp  = new TextField(20);
	TextField tfAddr  = new TextField(20);
	
	boolean idCheck=false;
	
	public Join() 
	{
		super("ȸ������");
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
		// �ۼַ�Ʈ���  : ���� ��ǥ �Է¹��.
		this.setLayout(null);
		
		Font font25 = new Font("SansSerif", Font.BOLD, 25);
		Font font20 = new Font("SansSerif", Font.BOLD, 20);
		Font font15 = new Font("SansSerif", Font.BOLD, 15);
		Font font10 = new Font("SansSerif", Font.BOLD, 10);
		
		//Ÿ��Ʋ
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
			//ȸ�����Խ� �Է��� ���� �ϴ� �����ͼ� ����.
			String id = tfId.getText();
			String pw = tfPw.getText();
			String pw2 = tfPw2.getText();
			String name = tfName.getText();
			String hp = tfHp.getText();
			String addr = tfAddr.getText();
			
			if(id.equals("")) 
			{
				dlgMsg("���̵� �Է����ּ���.");
				return;
			}
			else if(pw.equals("")) 
			{
				dlgMsg("����� �Է����ּ���.");
				return;
			}
			else if(pw2.equals("")) {dlgMsg("���Ȯ���� �Է����ּ���.");return;}
			else if(name.equals("")) {dlgMsg("�̸��� �Է����ּ���.");return;}
			else if(hp.equals("")) {dlgMsg("������ �Է����ּ���.");return;}
			else if(addr.equals("")) {dlgMsg("�ּҸ� �Է����ּ���.");return;}
			
			if(!pw.equals(pw2)){dlgMsg("����� ���Ȯ���� ���� �ʽ��ϴ�.");return;}
			
			if(idCheck == false){dlgMsg("���̵� �ߺ�üũ�� �ϼ���!");return;}
			
			//�Էµ� ������ ��� �ֱ����ؼ� �޼���� ������...
			insert(id,pw,name,hp,addr);
			
			dlgMsg("ȸ�������� �Ϸ�Ǿ����ϴ�!");
		}
		
	}
	//���̵� �ߺ�üũ
	void selectCheck(String joinId)
	{
		//��񿡼� ������ �����ͼ� �ؽ�Ʈ���̸�� �ѷ��ֱ�				
		try 
		{
			//��� ������ ���� conn���� ����
			conn = DriverManager.getConnection(url, id, pass);
			//��񿡿���
			stmt = conn.createStatement();
			//��񿡼� ��ȸ�� ��� ����Ÿ�� �����ͼ� rs�� ����
			String query= "select * from members";
			rs = stmt.executeQuery(query);
			//��񿡼� ��ȸ�� ��絥��Ÿ�� ����� r�� ���� ���پ� üũ�ؼ� ���������� �ݺ�
			while (rs.next()) 
			{
				//���پ� �ݺ��ϸ鼭 result.�� �����ؼ� ����
				//getString�� ���̺��� �÷��� �������� ����Ÿ�� �����ü� ����.
				if(joinId.equals(rs.getString("id")))
				{
					dlgMsg("���̵� �ߺ��˴ϴ�!!");
					idCheck=false;
					return;					
				}				
					
			}	
			
			dlgMsg("���԰����� ���̵��Դϴ�!!");
			
			idCheck=true;
			
			rs.close();
		} 
		catch (SQLException error) 
		{
			System.err.println("error = " + error.toString());			
		}	
	}
	
	//���� �˸�â���� �������� �������ؼ� ����.
	void dlgMsg(String msg)
	{
		final Dialog dlg = new Dialog(this, "�˸�", true);
		dlg.setLayout(null);	
		
		Font font15 = new Font("SansSerif", Font.BOLD, 15);
		Font font10 = new Font("SansSerif", Font.BOLD, 10);
		
		Label lbTitle = new Label(msg);
		
		Button bt = new Button("Ȯ��");
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
			//��� ����
			conn = DriverManager.getConnection(url, id, pass);
			//���Եɶ� ������
			String query = "insert into members values (null, ?, ?, ?, ?, ?)";

			//������ ����
			pstmt = conn.prepareStatement(query);	
			
			//pstmt�� ���Ե� �������� ž��
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
		//�Է��� ���̵�� ���� ������ �������� �Է�â �ʱ�ȭ
		tfId.setText("");
		tfPw.setText("");
		tfPw2.setText("");
		tfName.setText("");
		tfHp.setText("");
		tfAddr.setText("");

	}	
}
