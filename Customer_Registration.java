package Study0510;

import java.util.Scanner;

public class Customer_Registration
{
	Scanner sc = new Scanner(System.in);
	
	String name,id,lv;
	
	int sel;
	final int point=2000;
	final int coupon=5;
	
	Customer_Registration()
	{
		while(true)
		{
			choice();
			
			if(sel == 1)
			{
				infor();
			}
			else if(sel == 2)
			{
				lvcheck();
			}
			else if(sel == 3)
			{
				privatecheck();
			}
			else if(sel == 4)
			{
				System.out.println("프로그램 종료.");
				break;	
			}
		}
	}
	
	void choice()
	{
		System.out.println("1.고객정보입력");
		System.out.println("2.고객등급정보확인");
		System.out.println("3.고객개인정보확인");
		System.out.println("4.종료");
		System.out.print("선택:");
		sel = sc.nextInt();
		System.out.println();	
	}
	
	void infor()
	{
		System.out.print("고객이름: ");
		name = sc.next();
		System.out.print("고객ID: ");
		id = sc.next();
		System.out.print("고객등급: ");
		lv = sc.next();
		System.out.println();
	}
	
	void lvcheck()
	{
		System.out.println("고객등급: "+lv);
		lvbene();
		System.out.println();
	}
	
	void lvbene()
	{
		if(lv.equals("Bronze"))
		{
			System.out.println("보너스포인트: "+point);
		}
		else if(lv.equals("Silver"))
		{
			System.out.println("보너스포인트: "+point*1.5);
		}
		else if(lv.equals("Gold"))
		{
			System.out.println("보너스포인트: "+point*2.0);
		}
		else if(lv.equals("VIP"))
		{
			System.out.println("보너스포인트: "+point*3.0);
			System.out.println("쿠폰: "+(coupon-2));
		}
		else if(lv.equals("VVIP"))
		{
			System.out.println("보너스포인트: "+point*5.0);
			System.out.println("쿠폰: "+coupon);
		}
		else if((lv.equals("")))
		{
			System.out.println("고객정보를 입력하세요!");
		}
	}
	
	void privatecheck()
	{
		System.out.println("고객이름: "+name);
		System.out.println("고객ID: "+id);
		System.out.println("고객등급: "+lv);
		System.out.println();
	}
	
	public static void main(String[] args) 
	{
		Customer_Registration customermain = new Customer_Registration();
	}
}



