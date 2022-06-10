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
	
	Label lbTitle = new Label("�α���ȭ��");	
	Label lbId = new Label("I D: ");
	Label lbPw = new Label("PW: ");
	
	Button btnLogin = new Button("�α���");	
	Button btnJoin = new Button("ȸ������");
	
	TextField tfId  = new TextField(20);	
	TextField tfPw  = new TextField(20);
	
	boolean loginCheck = false; //�α��� ������ true ��ҿ� false
	
	public Login() 
	{
		super("�α���");
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
		// �ۼַ�Ʈ���  : ���� ��ǥ �Է¹��.
		this.setLayout(null);

		Font font25 = new Font("SansSerif", Font.BOLD, 25);
		Font font20 = new Font("SansSerif", Font.BOLD, 20);
		Font font15 = new Font("SansSerif", Font.BOLD, 15);
		Font font10 = new Font("SansSerif", Font.BOLD, 10);
		
		//Ÿ��Ʋ
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
			
			if(id.equals("")) {dlgMsg("���̵� �Է����ּ���.");return;}
			if(pw.equals("")) {dlgMsg("����� �Է����ּ���.");return;}
			
			checkLogin(id, pw);
			
			if(loginCheck == true)
			{
				Main main = new Main(id);
				setVisible(false);
			}
			else
			{
				dlgMsg("���̵� ����� ��ġ�����ʽ��ϴ�!");
			}
		}
		else if(e.getSource() == btnJoin)
		{
			Join join = new Join();
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
		//��񿡼� ������ �����ͼ� �ؽ�Ʈ���̸�� �ѷ��ֱ�				
		try 
		{
			//��� ������ ���� conn���� ����
			conn = DriverManager.getConnection(url, id, pass);
			//��񿡿���
			stmt = conn.createStatement();
			//��񿡼� ��ȸ�� ��� ����Ÿ�� �����ͼ� rs�� ����
			
			String loginQuery = "select * from members where id='"+loginId+"' AND pw='"+loginPw+"'";
			rs = stmt.executeQuery(loginQuery);
			//��񿡼� ��ȸ�� ��絥��Ÿ�� ����� r�� ���� ���پ� üũ�ؼ� ���������� �ݺ�
			while (rs.next()) 
			{
				
				//rs�� ���� �ִٴ°��� �α��ξ��̵� ����� ��ġ�ϴ� ������ �ִٴ°��̹Ƿ� �α��μ���!!!
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

