package study0525;

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

public class Lotto 
{
	public static void main(String[] args) 
	{
		Game g = new Game();

	}
}

class Game extends Frame implements ActionListener 
{
	int i = 0;
	int cnt = 7;
	int lotto[] = new int[6];
	String num[] = new String[6];
	String lotto2[] = new String[6];

	int j = 0;
	int n1;
	int cnt1 = 0;
	int[] lotto1 = new int[6];
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
	Label lbResult1 = new Label("대기");
	Label lbResult2 = new Label("대기");
	Label lbResult3 = new Label("대기");
	Label lbResult4 = new Label("대기");
	Label lbResult5 = new Label("대기");
	Label lbResult6 = new Label("대기");
	
	Label sixc = new Label("6개맞추면 1등: 10억");
	Label fivec = new Label("5개맞추면 2등: 1억");
	Label fourc = new Label("4개맞추면 3등: 1천만원");
	Label threec = new Label("3개맞추면 4등: 10만원");
	Label twoc = new Label("2개맞추면 5등: 5천원");
	Label onec = new Label("1개맞추면 6등: 꽝");
	Label zeroc = new Label("0개맞추면 7등: 꽝");
		
	Button btnStart = new Button("추첨 시작!!!");
	
	public Game() 
	{
		super("Lotto");
		
		this.init();
		
		this.start();
		
		this.setSize(400, 490);	
		
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

		Font font20 = new Font("SansSerif", Font.BOLD, 20);
		Font font15 = new Font("SansSerif", Font.BOLD, 15);
		Font font10 = new Font("SansSerif", Font.BOLD, 10);
		
		//타이틀
		this.add(lbTitle);
		lbTitle.setBounds(100, 50, 300, 30);		
		lbTitle.setFont(font20);
		
		this.add(lbMy);
		lbMy.setBounds(50, 100, 70, 30);
		lbMy.setFont(font15);
		
		this.add(btnStart);
		btnStart.setBounds(120, 220, 150, 30);
		btnStart.setFont(font15);
		
		this.add(sixc);
		sixc.setBounds(50, 260, 150, 30);
		this.add(fivec);
		fivec.setBounds(50, 290, 150, 30);
		this.add(fourc);
		fourc.setBounds(50, 320, 150, 30);
		this.add(threec);
		threec.setBounds(50, 350, 150, 30);
		this.add(twoc);
		twoc.setBounds(50, 380, 150, 30);
		this.add(onec);
		onec.setBounds(50, 410, 150, 30);
		this.add(zeroc);
		zeroc.setBounds(50, 440, 150, 30);
		
		this.add(tf1);
		tf1.setBounds(50, 140, 30, 30);
		this.add(tf2);
		tf2.setBounds(100, 140, 30, 30);
		this.add(tf3);
		tf3.setBounds(150, 140, 30, 30);
		this.add(tf4);
		tf4.setBounds(200, 140, 30, 30);
		this.add(tf5);
		tf5.setBounds(250, 140, 30, 30);
		this.add(tf6);
		tf6.setBounds(300, 140, 30, 30);
		
		this.add(lbResult1);
		lbResult1.setBounds(50, 180, 50, 30);
		lbResult1.setFont(font15);	
		this.add(lbResult2);
		lbResult2.setBounds(100, 180, 50, 30);
		lbResult2.setFont(font15);
		this.add(lbResult3);
		lbResult3.setBounds(150, 180, 50, 30);
		lbResult3.setFont(font15);
		this.add(lbResult4);
		lbResult4.setBounds(200, 180, 50, 30);
		lbResult4.setFont(font15);
		this.add(lbResult5);
		lbResult5.setBounds(250, 180, 50, 30);
		lbResult5.setFont(font15);
		this.add(lbResult6);
		lbResult6.setBounds(300, 180, 50, 30);
		lbResult6.setFont(font15);
		
	}

	public void start() 
	{
		
		btnStart.addActionListener(this);
		
		this.addWindowListener(new WindowAdapter() 
		{
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
			
		if(e.getSource() == btnStart) 
		{
			ran();
			
			num[i] = tf1.getText();
			lotto[i] = Integer.parseInt(num[i]);
			i++;
			
			num[i] = tf2.getText();
			lotto[i] = Integer.parseInt(num[i]);
			i++;
			
			num[i] = tf3.getText();
			lotto[i] = Integer.parseInt(num[i]);
			i++;
			
			num[i] = tf4.getText();
			lotto[i] = Integer.parseInt(num[i]);
			i++;
			
			num[i] = tf5.getText();
			lotto[i] = Integer.parseInt(num[i]);
			i++;
			
			num[i] = tf6.getText();
			lotto[i] = Integer.parseInt(num[i]);
			i++;
			
			ing();//3..2..1.. 표시하는 녀석...
			
			final Dialog dlg = new Dialog(this, "당첨확인", true);
			dlg.setLayout(null);	
			
			Font font15 = new Font("SansSerif", Font.BOLD, 15);
			Font font10 = new Font("SansSerif", Font.BOLD, 10);
			
			sorting();
			
			for(i=0;i<6;i++)
			{
				System.out.println("당첨번호"+(i+1)+"번");
				System.out.println(lotto1[i]);
				
				if(lotto[i] == lotto1[i])
				{
					cnt--;
				}
			}
			
			Label lbTitle = new Label("축하합니다"+cnt+"등당첨!");
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
			
			dlg.addWindowListener(new WindowAdapter() 
			{
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});
			
			dlg.add(lbTitle);
			lbTitle.setBounds(80, 50, 150, 30);		
			lbTitle.setFont(font15);
			
			dlg.add(bt);
			bt.setBounds(120, 100, 50, 30);		
			bt.setFont(font15);
			
			dlg.setLocation(xpos, 400);
			dlg.setSize(300, 150);
			dlg.setVisible(true);
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
				if(lotto1[j] == n1)
				{
					continue jump;
				}
			}
					
			lotto1[cnt1] = n1;
					
			cnt1++;
		}
		
	}
	
	void sorting()
	{
		Arrays.sort(lotto1);
		Arrays.sort(lotto);
	}
	
	void ing()
	{
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {			
		}
		
		lbResult1.setText("3...");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {			
		}
		
		lbResult1.setText("2...");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {			
		}
		
		lbResult1.setText("1...");
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {			
		}
		
		lbResult2.setText("3...");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {			
		}
		
		lbResult2.setText("2...");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {			
		}
		
		lbResult2.setText("1...");
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {			
		}
		
		lbResult3.setText("3...");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {			
		}
		
		lbResult3.setText("2...");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {			
		}
		
		lbResult3.setText("1...");
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {			
		}
		
		lbResult4.setText("3...");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {			
		}
		
		lbResult4.setText("2...");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {			
		}
		
		lbResult4.setText("1...");
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {			
		}
		
		lbResult5.setText("3...");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {			
		}
		
		lbResult5.setText("2...");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {			
		}
		
		lbResult5.setText("1...");
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {			
		}
		
		lbResult6.setText("3...");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {			
		}
		
		lbResult6.setText("2...");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {			
		}
		
		lbResult6.setText("1...");
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {			
		}
	}
}

