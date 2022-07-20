package Practice;

import java.awt.*;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.Random;

public class Lotto_Auto 
{
	public static void main(String[] args) 
	{
		Game g = new Game();
	}
}

class Game extends Frame implements ActionListener 
{
	int i = 0;
	int j = 0;
	
	int rank = 7; //로또등수를 나타내는 변수
	int rank1,rank2,rank3,rank4,rank5,rank6,rank7;
	int pansu=0;
	String lottoNum[] = new String[6]; //로또번호를 TextField에서 입력받는 배열
	int lotto[] = new int[6];// lottoNum을 int형으로 변환하여 저장하는 배열
	
	int n1,n2; //랜덤변수를 저정하는 변수
	int cnt1 = 0;//로또번호 갯수만큼 랜덤변수를 생성하도록 만드는 변수
	int cnt2 = 0;//로또번호 갯수만큼 랜덤변수를 생성하도록 만드는 변수
	int[] lottoWin = new int[6];//로또당첨번호를 저장하는 배열
	int[] lottoA = new int[6];//자동로또번호를 저장하는 배열
	
	Random rd = new Random();
	
	private Dimension dimen, dimen1;
	private int xpos, ypos;
	
	Label lbTitle = new Label("로또 맞추기 게임");	
	Label lbMy = new Label("번호입력");
	TextField tf1  = new TextField(2);
	TextField tf2  = new TextField(2);
	TextField tf3  = new TextField(2);
	TextField tf4  = new TextField(2);
	TextField tf5  = new TextField(2);
	TextField tf6  = new TextField(2);
	
	Label sixc = new Label("6개맞추면 1등: 10억");
	Label fivec = new Label("5개맞추면 2등: 1억");
	Label fourc = new Label("4개맞추면 3등: 1천만원");
	Label threec = new Label("3개맞추면 4등: 10만원");
	Label twoc = new Label("2개맞추면 5등: 5천원");
	Label onec = new Label("1개맞추면 6등: 꽝");
	Label zeroc = new Label("0개맞추면 7등: 꽝");
		
	Button btnStart = new Button("추첨 시작");
	Button btnauto = new Button("자동 입력");
	Button btnRenew = new Button("초기화");
	
	public Game() 
	{
		super("Lotto");
		
		this.init();
		
		this.start();
		
		this.setSize(400, 550);	
		
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
		lbTitle.setBounds(100, 50, 300, 30);		
		lbTitle.setFont(font25);
		
		this.add(lbMy);
		lbMy.setBounds(50, 100, 70, 30);
		lbMy.setFont(font15);
		
		this.add(btnStart);
		btnStart.setBounds(50, 200, 80, 30);
		btnStart.setFont(font15);
		this.add(btnauto);
		btnauto.setBounds(150, 200, 80, 30);
		btnauto.setFont(font15);
		this.add(btnRenew);
		btnRenew.setBounds(250, 200, 80, 30);
		btnRenew.setFont(font15);
		
		this.add(tf1);
		tf1.setBounds(50, 140, 40, 40);
		tf1.setFont(font15);
		this.add(tf2);
		tf2.setBounds(100, 140, 40, 40);
		tf2.setFont(font15);
		this.add(tf3);
		tf3.setBounds(150, 140, 40, 40);
		tf3.setFont(font15);
		this.add(tf4);
		tf4.setBounds(200, 140, 40, 40);
		tf4.setFont(font15);
		this.add(tf5);
		tf5.setBounds(250, 140, 40, 40);
		tf5.setFont(font15);
		this.add(tf6);
		tf6.setBounds(300, 140, 40, 40);
		tf6.setFont(font15);
		
		this.add(sixc);
		sixc.setBounds(50, 260, 180, 30);
		sixc.setFont(font15);
		this.add(fivec);
		fivec.setBounds(50, 290, 180, 30);
		fivec.setFont(font15);
		this.add(fourc);
		fourc.setBounds(50, 320, 180, 30);
		fourc.setFont(font15);
		this.add(threec);
		threec.setBounds(50, 350, 180, 30);
		threec.setFont(font15);
		this.add(twoc);
		twoc.setBounds(50, 380, 180, 30);
		twoc.setFont(font15);
		this.add(onec);
		onec.setBounds(50, 410, 180, 30);
		onec.setFont(font15);
		this.add(zeroc);
		zeroc.setBounds(50, 440, 180, 30);
		zeroc.setFont(font15);
		
	}

	public void start() 
	{
		
		btnStart.addActionListener(this);
		btnauto.addActionListener(this);
		btnRenew.addActionListener(this);
		
		this.addWindowListener(new WindowAdapter() 
		{
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == btnStart) 
		{
			ran();
			
			lottoNum[i] = tf1.getText();
			lotto[i] = Integer.parseInt(lottoNum[i]);
			i++;
			
			lottoNum[i] = tf2.getText();
			lotto[i] = Integer.parseInt(lottoNum[i]);
			i++;
			
			lottoNum[i] = tf3.getText();
			lotto[i] = Integer.parseInt(lottoNum[i]);
			i++;
			
			lottoNum[i] = tf4.getText();
			lotto[i] = Integer.parseInt(lottoNum[i]);
			i++;
			
			lottoNum[i] = tf5.getText();
			lotto[i] = Integer.parseInt(lottoNum[i]);
			i++;
			
			lottoNum[i] = tf6.getText();
			lotto[i] = Integer.parseInt(lottoNum[i]);
			i++;
			
			final Dialog dlg = new Dialog(this, "당첨확인", true);
			dlg.setLayout(null);	
			
			Font font20 = new Font("고딕", Font.BOLD, 20);
			Font font15 = new Font("SansSerif", Font.BOLD, 15);
			Font font10 = new Font("SansSerif", Font.BOLD, 10);
			
			sorting();
			
			for(i=0;i<6;i++)
			{
				System.out.println("당첨번호"+(i+1)+"번");
				System.out.println(lottoWin[i]);
				
				if(lotto[i] == lottoWin[i])
				{
					rank--;
				}
				
			}
			
			if(rank == 1) {
				rank1 += 1;
			}else if(rank == 2) {
				rank2 += 1;
			}else if(rank == 3) {
				rank3 += 1;
			}else if(rank == 4) {
				rank4 += 1;
			}else if(rank == 5) {
				rank5 += 1;
			}else if(rank == 6) {
				rank6 += 1;
			}else if(rank == 7) {
				rank7 += 1;
			}
			
			pansu++;
			
			Label lbTitle0 = new Label("현재"+pansu+"번째 게임중!");
			Label lbTitle = new Label("축하합니다 "+rank+"등당첨!");
			Label lbTitle1 = new Label("1등"+(rank1)+"번 당첨!");
			Label lbTitle2 = new Label("2등"+(rank2)+"번 당첨!");
			Label lbTitle3 = new Label("3등"+(rank3)+"번 당첨!");
			Label lbTitle4 = new Label("4등"+(rank4)+"번 당첨!");
			Label lbTitle5 = new Label("5등"+(rank5)+"번 당첨!");
			Label lbTitle6 = new Label("6등"+(rank6)+"번 당첨!");
			Label lbTitle7 = new Label("7등"+(rank7)+"번 당첨!");
			
			Button bt = new Button("확인");
			
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
			
			dlg.add(lbTitle0);
			lbTitle0.setBounds(80, 50, 200, 30);		
			lbTitle0.setFont(font20);
			
			dlg.add(lbTitle);
			lbTitle.setBounds(100, 90, 150, 30);		
			lbTitle.setFont(font15);
			
			dlg.add(lbTitle1);
			lbTitle1.setBounds(120, 130, 150, 30);		
			lbTitle1.setFont(font15);
			dlg.add(lbTitle2);
			lbTitle2.setBounds(120, 170, 150, 30);		
			lbTitle2.setFont(font15);
			dlg.add(lbTitle3);
			lbTitle3.setBounds(120, 210, 150, 30);		
			lbTitle3.setFont(font15);
			dlg.add(lbTitle4);
			lbTitle4.setBounds(120, 250, 150, 30);		
			lbTitle4.setFont(font15);
			dlg.add(lbTitle5);
			lbTitle5.setBounds(120, 290, 150, 30);		
			lbTitle5.setFont(font15);
			dlg.add(lbTitle6);
			lbTitle6.setBounds(120, 330, 150, 30);		
			lbTitle6.setFont(font15);
			dlg.add(lbTitle7);
			lbTitle7.setBounds(120, 370, 150, 30);		
			lbTitle7.setFont(font15);
			
			dlg.add(bt);
			bt.setBounds(120, 420, 70, 30);		
			bt.setFont(font15);
			
			dlg.setLocation(xpos, 400);
			dlg.setSize(350, 500);
			dlg.setVisible(true);
		}
		
		if(e.getSource() == btnauto) 
		{
			ran2();
			
			tf1.setText(lottoA[0]+"");
			tf2.setText(lottoA[1]+"");
			tf3.setText(lottoA[2]+"");
			tf4.setText(lottoA[3]+"");
			tf5.setText(lottoA[4]+"");
			tf6.setText(lottoA[5]+"");
			
			sorting2();
			
		}
		
		if(e.getSource() == btnRenew) 
		{
			n1 = 0;
			n2 = 0;
			cnt1 = 0;
			cnt2 = 0;
			i=0;
			rank = 7;
			
			for(int i=0; i<6 ;i++) {
				
				lottoA[i] = 0;
				
			}
			
			tf1.setText(lottoA[0]+"");
			tf2.setText(lottoA[1]+"");
			tf3.setText(lottoA[2]+"");
			tf4.setText(lottoA[3]+"");
			tf5.setText(lottoA[4]+"");
			tf6.setText(lottoA[5]+"");
			
			for(int i=0; i<6 ;i++) {
				
				lottoWin[i] = 0;
				
			}
					
		}
		
	}
	
	void ran()
	{
		jump:
			
		while(cnt1<6)
		{
			n1 = rd.nextInt(45)+1;
					
			for(int j=0; j<cnt1; j++)
			{
				if(lottoWin[j] == n1)
				{
					continue jump;
				}
			}
					
			lottoWin[cnt1] = n1;
					
			cnt1++;
		}
		
	}
	
	void ran2()
	{
		jump:
			
		while(cnt2<6)
		{
			n2 = rd.nextInt(45)+1;
					
			for(int k=0; k<cnt2; k++)
			{
				if(lottoA[j] == n2)
				{
					continue jump;
				}
			}
					
			lottoA[cnt2] = n2;
					
			cnt2++;
		}
		
	}
	
	void sorting()
	{
		Arrays.sort(lottoWin);
		Arrays.sort(lotto);
	}
	
	void sorting2()
	{
		Arrays.sort(lottoA);
	}
	
}

