package Study0511;

import java.util.Scanner;

public class Study107_aircon2 
{
	static int curTempo = 28; //현재온도
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
			System.out.println("현재온도: " + curTempo +"도");
			System.out.println("설정온도: " + setTem +"도");
			System.out.println("----------------");
			System.out.println("1.온도설정");
			System.out.println("2.에어컨가동");
			System.out.println("3.에어컨중단");
			System.out.println("4.종료");
			System.out.print("선택:"); 
			select = sc.nextInt();
			
			System.out.println();
			
			if(select == 1) 
			{
				System.out.print("설정온도:"); 
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
				System.out.println("프로그램 종료.");
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
				System.out.println("현재온도:" + Study107_aircon2.curTempo + "도");
				
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
