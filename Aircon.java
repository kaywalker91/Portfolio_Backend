package Study0511;

import java.util.Scanner;

public class Study107_aircon2 
{
	static int curTempo = 28; //����µ�
	static int setTem = 20;
	static boolean chec = true;
	static boolean chec1 = true;
	static int select;

	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in );
		
		while(chec1 == true)
		{
			System.out.println("----------------");
			System.out.println("����µ�: " + curTempo +"��");
			System.out.println("�����µ�: " + setTem +"��");
			System.out.println("----------------");
			System.out.println("1.�µ�����");
			System.out.println("2.����������");
			System.out.println("3.�������ߴ�");
			System.out.println("4.����");
			System.out.print("����:"); 
			select = sc.nextInt();
			
			System.out.println();
			
			if(select == 1) 
			{
				System.out.print("�����µ�:"); 
				int temp = sc.nextInt();
				setTem = temp;
			}
			else if(select == 2) 
			{
				chec1 = false;
				
				Temp t = new Temp();
				Thread th = new Thread(t);
				th.start();
				
				if(curTempo == setTem)
				{
					Study107_aircon2.chec1 = true;
				}
			}
			else if(select == 4) 
			{
				System.out.println("���α׷� ����.");
				break;
			}
		}	
		
	}
}
class Tem extends Thread
{
	public void run() 
	{		
		while(true)
		{
			if(Study107_aircon2.chec == true)
			{
				int tempT = (int)(Math.random()*2);
				Study107_aircon2.curTempo = Study107_aircon2.curTempo - tempT; 
		
				try 
				{
					Thread.sleep(1000);						
				} 
				catch (InterruptedException e) 
				{}
				System.out.println();
				System.out.println("����µ�:" + Study107_aircon2.curTempo + "��");
				
				if(Study107_aircon2.select == 3) 
				{
					Study107_aircon2.chec = false;
				}
				
				if(Study107_aircon2.curTempo==Study107_aircon2.setTem)
				{
					Study107_aircon2.chec = false;
				}
			}	
		}
	}	
}
