package Study0510;

import java.util.Scanner;

public class VendingMachine2 
{

	public static void main(String[] args) 
	{
		Menu1 menu1 = new Menu1();

	}

}

class Menu1
{
	Scanner sc = new Scanner(System.in);
	
	String menu[] = {"�ݶ�","���̴�","����","ȫ��","��","�Ƿ�Ƽ"};
	int m1p=1000,m2p=2000,m3p=1500,m4p=1200,m5p=3000,m6p=1700;
	int currentmoney=0,inputmoney;
	int sel,selm;
	
	Menu1()
	{
		while(true)
		{
			mainmenu1();
			
			if(sel == 1)
			{
				menuchoice();
			}
			else if(sel == 2)
			{
				input();
			}
			else if(sel == 3)
			{
				jandon();
			}
			else if(sel == 4)
			{
				System.out.println("���α׷� ����.");
				break;	
			}
		}
	}
	
	void mainmenu1()
	{
		System.out.println("[------------]");
		System.out.println("  �����ܾ�: "+currentmoney+"��");
		System.out.println("[------------]");

		System.out.println("1.�޴�����");
		System.out.println("2.�ݾ�����");
		System.out.println("3.�ܵ���ȯ");
		System.out.println("4.����");
		System.out.print("����:");
		sel = sc.nextInt();
		System.out.println();	
	}
	
	void menuchoice()
	{
		System.out.println("1�� �޴�: "+menu[0]);
		System.out.println("1�� �޴�����: "+m1p);
		System.out.println("2�� �޴�: "+menu[1]);
		System.out.println("2�� �޴�����: "+m2p);
		System.out.println("3�� �޴�: "+menu[2]);
		System.out.println("3�� �޴�����: "+m3p);
		System.out.println("4�� �޴�: "+menu[3]);
		System.out.println("4�� �޴�����: "+m4p);
		System.out.println("5�� �޴�: "+menu[4]);
		System.out.println("5�� �޴�����: "+m5p);
		System.out.println("6�� �޴�: "+menu[5]);
		System.out.println("6�� �޴�����: "+m6p);
		System.out.println();
		
		System.out.print("�޴�����: ");
		selm = sc.nextInt();
		
		if(selm == 1)
		{
			System.out.println("1�� �޴�: "+menu[0]);
			System.out.println("1�� �޴�����: "+m1p);
		}
		else if(selm == 2)
		{
			System.out.println("2�� �޴�: "+menu[1]);
			System.out.println("2�� �޴�����: "+m2p);
		}
		else if(selm == 3)
		{
			System.out.println("3�� �޴�: "+menu[2]);
			System.out.println("3�� �޴�����: "+m3p);
		}
		else if(selm == 4)
		{
			System.out.println("4�� �޴�: "+menu[3]);
			System.out.println("4�� �޴�����: "+m4p);	
		}
		else if(selm == 5)
		{
			System.out.println("5�� �޴�����: "+m5p);
			System.out.println("6�� �޴�: "+menu[5]);
		}
		else if(selm == 6)
		{
			System.out.println("6�� �޴�: "+menu[5]);
			System.out.println("6�� �޴�����: "+m6p);
		}
		
		System.out.println();
	}
	
	void input()
	{
		System.out.println("�ݾ��� �����ϼ���.");
		
		System.out.print("���Աݾ�: ");
		
		inputmoney = sc.nextInt();
		currentmoney += inputmoney;
		
		System.out.println();
		
		if(selm==1)
		{
			currentmoney -= m1p;
			
			if(currentmoney>=0)
			{
				System.out.println("���ø޴�: "+menu[0]+"("+m1p+"��"+")"+" ���ԿϷ�!");
				
				System.out.println();
			}
			else if(currentmoney<0)
			{
				System.out.println("�ݾ��� �����մϴ�!");
				System.out.println("������ �ݾ�: " +(-currentmoney));
				currentmoney = -currentmoney;
				System.out.println();	
			}
		}
		else if(selm==2)
		{
			currentmoney -= m2p;
			
			if(currentmoney>=0)
			{
				System.out.println("���ø޴�: "+menu[1]+"("+m2p+"��"+")"+" ���ԿϷ�!");
				
				System.out.println();
			}
			else if(currentmoney<0)
			{
				System.out.println("�ݾ��� �����մϴ�!");
				System.out.println("������ �ݾ�: " +(-currentmoney));
				currentmoney = -currentmoney;
				System.out.println();	
			}
			
		}
		else if(selm==3)
		{
			currentmoney -= m3p;
			
			if(currentmoney>=0)
			{
				System.out.println("���ø޴�: "+menu[2]+"("+m3p+"��"+")"+" ���ԿϷ�!");
				
				System.out.println();
			}
			else if(currentmoney<0)
			{
				System.out.println("�ݾ��� �����մϴ�!");
				System.out.println("������ �ݾ�: " +(-currentmoney));
				currentmoney = -currentmoney;
				System.out.println();	
			}
			
		}
		else if(selm==4)
		{
			currentmoney -= m4p;
			
			if(currentmoney>=0)
			{
				System.out.println("���ø޴�: "+menu[3]+"("+m4p+"��"+")"+" ���ԿϷ�!");
				
				System.out.println();
			}
			else if(currentmoney<0)
			{
				System.out.println("�ݾ��� �����մϴ�!");
				System.out.println("������ �ݾ�: " +(-currentmoney));
				currentmoney = -currentmoney;
				System.out.println();	
			}
			
		}
		else if(selm==5)
		{
			currentmoney -= m5p;
			
			if(currentmoney>=0)
			{
				System.out.println("���ø޴�: "+menu[4]+"("+m5p+"��"+")"+" ���ԿϷ�!");
				
				System.out.println();
			}
			else if(currentmoney<0)
			{
				System.out.println("�ݾ��� �����մϴ�!");
				System.out.println("������ �ݾ�: " +(-currentmoney));
				currentmoney = -currentmoney;
				System.out.println();	
			}
			
		}
		else if(selm==6)
		{
			currentmoney -= m6p;
			
			if(currentmoney>=0)
			{
				System.out.println("���ø޴�: "+menu[5]+"("+m6p+"��"+")"+" ���ԿϷ�!");
				
				System.out.println();
			}
			else if(currentmoney<0)
			{
				System.out.println("�ݾ��� �����մϴ�!");
				System.out.println("������ �ݾ�: " +(-currentmoney));
				currentmoney = -currentmoney;
				System.out.println();	
			}
		}
	}
	
	void jandon()
	{
		if(currentmoney>=0)
		{
			System.out.println("��ȯ�ݾ�: " +currentmoney);
			currentmoney = 0;
			System.out.println();	
		}	
	}
}

