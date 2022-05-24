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
	
	String menu[] = {"콜라","사이다","우유","홍차","물","실론티"};
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
				System.out.println("프로그램 종료.");
				break;	
			}
		}
	}
	
	void mainmenu1()
	{
		System.out.println("[------------]");
		System.out.println("  현재잔액: "+currentmoney+"원");
		System.out.println("[------------]");

		System.out.println("1.메뉴선택");
		System.out.println("2.금액투입");
		System.out.println("3.잔돈반환");
		System.out.println("4.종료");
		System.out.print("선택:");
		sel = sc.nextInt();
		System.out.println();	
	}
	
	void menuchoice()
	{
		System.out.println("1번 메뉴: "+menu[0]);
		System.out.println("1번 메뉴가격: "+m1p);
		System.out.println("2번 메뉴: "+menu[1]);
		System.out.println("2번 메뉴가격: "+m2p);
		System.out.println("3번 메뉴: "+menu[2]);
		System.out.println("3번 메뉴가격: "+m3p);
		System.out.println("4번 메뉴: "+menu[3]);
		System.out.println("4번 메뉴가격: "+m4p);
		System.out.println("5번 메뉴: "+menu[4]);
		System.out.println("5번 메뉴가격: "+m5p);
		System.out.println("6번 메뉴: "+menu[5]);
		System.out.println("6번 메뉴가격: "+m6p);
		System.out.println();
		
		System.out.print("메뉴선택: ");
		selm = sc.nextInt();
		
		if(selm == 1)
		{
			System.out.println("1번 메뉴: "+menu[0]);
			System.out.println("1번 메뉴가격: "+m1p);
		}
		else if(selm == 2)
		{
			System.out.println("2번 메뉴: "+menu[1]);
			System.out.println("2번 메뉴가격: "+m2p);
		}
		else if(selm == 3)
		{
			System.out.println("3번 메뉴: "+menu[2]);
			System.out.println("3번 메뉴가격: "+m3p);
		}
		else if(selm == 4)
		{
			System.out.println("4번 메뉴: "+menu[3]);
			System.out.println("4번 메뉴가격: "+m4p);	
		}
		else if(selm == 5)
		{
			System.out.println("5번 메뉴가격: "+m5p);
			System.out.println("6번 메뉴: "+menu[5]);
		}
		else if(selm == 6)
		{
			System.out.println("6번 메뉴: "+menu[5]);
			System.out.println("6번 메뉴가격: "+m6p);
		}
		
		System.out.println();
	}
	
	void input()
	{
		System.out.println("금액을 투입하세요.");
		
		System.out.print("투입금액: ");
		
		inputmoney = sc.nextInt();
		currentmoney += inputmoney;
		
		System.out.println();
		
		if(selm==1)
		{
			currentmoney -= m1p;
			
			if(currentmoney>=0)
			{
				System.out.println("선택메뉴: "+menu[0]+"("+m1p+"원"+")"+" 구입완료!");
				
				System.out.println();
			}
			else if(currentmoney<0)
			{
				System.out.println("금액이 부족합니다!");
				System.out.println("부족한 금액: " +(-currentmoney));
				currentmoney = -currentmoney;
				System.out.println();	
			}
		}
		else if(selm==2)
		{
			currentmoney -= m2p;
			
			if(currentmoney>=0)
			{
				System.out.println("선택메뉴: "+menu[1]+"("+m2p+"원"+")"+" 구입완료!");
				
				System.out.println();
			}
			else if(currentmoney<0)
			{
				System.out.println("금액이 부족합니다!");
				System.out.println("부족한 금액: " +(-currentmoney));
				currentmoney = -currentmoney;
				System.out.println();	
			}
			
		}
		else if(selm==3)
		{
			currentmoney -= m3p;
			
			if(currentmoney>=0)
			{
				System.out.println("선택메뉴: "+menu[2]+"("+m3p+"원"+")"+" 구입완료!");
				
				System.out.println();
			}
			else if(currentmoney<0)
			{
				System.out.println("금액이 부족합니다!");
				System.out.println("부족한 금액: " +(-currentmoney));
				currentmoney = -currentmoney;
				System.out.println();	
			}
			
		}
		else if(selm==4)
		{
			currentmoney -= m4p;
			
			if(currentmoney>=0)
			{
				System.out.println("선택메뉴: "+menu[3]+"("+m4p+"원"+")"+" 구입완료!");
				
				System.out.println();
			}
			else if(currentmoney<0)
			{
				System.out.println("금액이 부족합니다!");
				System.out.println("부족한 금액: " +(-currentmoney));
				currentmoney = -currentmoney;
				System.out.println();	
			}
			
		}
		else if(selm==5)
		{
			currentmoney -= m5p;
			
			if(currentmoney>=0)
			{
				System.out.println("선택메뉴: "+menu[4]+"("+m5p+"원"+")"+" 구입완료!");
				
				System.out.println();
			}
			else if(currentmoney<0)
			{
				System.out.println("금액이 부족합니다!");
				System.out.println("부족한 금액: " +(-currentmoney));
				currentmoney = -currentmoney;
				System.out.println();	
			}
			
		}
		else if(selm==6)
		{
			currentmoney -= m6p;
			
			if(currentmoney>=0)
			{
				System.out.println("선택메뉴: "+menu[5]+"("+m6p+"원"+")"+" 구입완료!");
				
				System.out.println();
			}
			else if(currentmoney<0)
			{
				System.out.println("금액이 부족합니다!");
				System.out.println("부족한 금액: " +(-currentmoney));
				currentmoney = -currentmoney;
				System.out.println();	
			}
		}
	}
	
	void jandon()
	{
		if(currentmoney>=0)
		{
			System.out.println("반환금액: " +currentmoney);
			currentmoney = 0;
			System.out.println();	
		}	
	}
}

