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
				System.out.println("���α׷� ����.");
				break;	
			}
		}
	}
	
	void choice()
	{
		System.out.println("1.�������Է�");
		System.out.println("2.���������Ȯ��");
		System.out.println("3.����������Ȯ��");
		System.out.println("4.����");
		System.out.print("����:");
		sel = sc.nextInt();
		System.out.println();	
	}
	
	void infor()
	{
		System.out.print("���̸�: ");
		name = sc.next();
		System.out.print("��ID: ");
		id = sc.next();
		System.out.print("�����: ");
		lv = sc.next();
		System.out.println();
	}
	
	void lvcheck()
	{
		System.out.println("�����: "+lv);
		lvbene();
		System.out.println();
	}
	
	void lvbene()
	{
		if(lv.equals("Bronze"))
		{
			System.out.println("���ʽ�����Ʈ: "+point);
		}
		else if(lv.equals("Silver"))
		{
			System.out.println("���ʽ�����Ʈ: "+point*1.5);
		}
		else if(lv.equals("Gold"))
		{
			System.out.println("���ʽ�����Ʈ: "+point*2.0);
		}
		else if(lv.equals("VIP"))
		{
			System.out.println("���ʽ�����Ʈ: "+point*3.0);
			System.out.println("����: "+(coupon-2));
		}
		else if(lv.equals("VVIP"))
		{
			System.out.println("���ʽ�����Ʈ: "+point*5.0);
			System.out.println("����: "+coupon);
		}
		else if((lv.equals("")))
		{
			System.out.println("�������� �Է��ϼ���!");
		}
	}
	
	void privatecheck()
	{
		System.out.println("���̸�: "+name);
		System.out.println("��ID: "+id);
		System.out.println("�����: "+lv);
		System.out.println();
	}
	
	public static void main(String[] args) 
	{
		Customer_Registration customermain = new Customer_Registration();
	}
}



