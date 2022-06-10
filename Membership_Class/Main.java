package study0610;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


class Main extends Frame implements ActionListener 
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
	
	String loginId;
	
	Label lbTitle = new Label("���� ȭ��");		
	Label lbLogin = new Label("���� �α��� �ϼ̽��ϴ�.");		
	Button btnShow = new Button("ȸ����ü����");	
	Button btnJoin = new Button("ȸ�������ϱ�");
	Button btnEdit = new Button("ȸ�������ϱ�");
	Button btnDel = new Button("ȸ�������ϱ�");

	public Main(String loginId) 
	{
		super("����ȭ��");
		
		this.loginId = loginId;
		this.init();
		this.start();		
		this.setSize(500, 500);	
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
		lbTitle.setBounds(190, 70, 300, 50);		
		lbTitle.setFont(font25);
		
		this.add(btnShow);
		btnShow.setBounds(140, 130, 200, 50);
		btnShow.setFont(font20);

		this.add(btnJoin);
		btnJoin.setBounds(140, 190, 200, 50);
		btnJoin.setFont(font20);
		
		this.add(btnEdit);
		btnEdit.setBounds(140, 250, 200, 50);
		btnEdit.setFont(font20);
		
		this.add(btnDel);
		btnDel.setBounds(140, 310, 200, 50);
		btnDel.setFont(font20);
		
		this.add(lbLogin);
		lbLogin.setBounds(100, 380, 350, 50);
		lbLogin.setFont(font20);
		lbLogin.setText(loginId+"���� �α����ϼ̽��ϴ�.");
		//ó�� ���۽� ���̺� ��ȸ�ؼ� �ѷ��ֱ�
	}

	public void start() 
	{
		 btnShow.addActionListener(this); 
		 btnJoin.addActionListener(this);
		 btnEdit.addActionListener(this);
		 btnDel.addActionListener(this);
		
		this.addWindowListener(new WindowAdapter() 
		{
			public void windowClosing(WindowEvent e) 
			{
				System.exit(0);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == btnShow)
		{					
			ShowAll sa = new ShowAll();
		}
		else if(e.getSource() == btnJoin)
		{
			Join join = new Join();
		}
		else if(e.getSource() == btnEdit)
		{
			Edit edit = new Edit();
		}
		else if(e.getSource() == btnDel)
		{
			Del del = new Del();
		}
		
	}	
}

