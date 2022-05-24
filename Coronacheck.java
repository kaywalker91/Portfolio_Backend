package study0513;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Coronacheck 
{

	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		
		int covidTot = 0; //확진자 총누적
		int covidTotJung = 0; //중증환자 총누적
		int covidDie = 0; ///사망자 총누적
		
		int select; 
		
		
		//초기에 파일을 읽어와서 총변수에 초기값을 넣어줘야함
		try
		{
            //파일 객체 생성
            File file = new File("C:\\aaa\\work\\covid.txt");
            //입력 스트림 생성
            FileReader filereader = new FileReader(file);
            //입력 버퍼 생성
            BufferedReader bufReader = new BufferedReader(filereader);
            
            String line = "";
            String data[]= {"","",""};
            int i=0;
            
            while((line = bufReader.readLine()) != null)
            {
            	data[i] = line;
            	//System.out.println("data["+i+"]:"+ data[i]);
            	i++;
            }
            System.out.println();
            System.out.println("[  현재누적통계  ]");
            System.out.println("확진자수:"+ data[0] + "명");
            System.out.println("중증환자:"+ data[1]+ "명");
            System.out.println("사망자:"+ data[2]+ "명");
            System.out.println();
            
        	covidTot = Integer.parseInt(data[0]);
        	covidTotJung = Integer.parseInt(data[1]);
        	covidDie = Integer.parseInt(data[2]);
        	
            bufReader.close();
        }
		catch (FileNotFoundException e) 
		{           
        }
		catch(IOException e)
		{           
        }
		
		while(true)
		{
			System.out.println("[  코로나 확진자 체크 프로그램  ]");
			System.out.println("1.입력하기");
			System.out.println("2.현황보기");
			System.out.println("3.종료");
			System.out.print("선택:"); select = sc.nextInt();
			
			if(select == 1) 	 
			{
		
				System.out.println("확진자수:"); int covid1 = sc.nextInt();
				System.out.println("중증환자:"); int covid2 = sc.nextInt();
				System.out.println("사망자:"); int covid3 = sc.nextInt();
				
				covidTot += covid1;  //입력한 확진자 총변수에 누적하기
				covidTotJung += covid2; //입력한 중증환자 총변수에 누적하기 
				covidDie += covid3;	 //입력한 사망자 총변수에 누적하기	
				
				//파일에 기록하기...				
				try{
		            //파일 객체 생성
		            File file = new File("C:\\aaa\\work\\covid.txt");
		            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
		            
		            if(file.isFile() && file.canWrite())
		            {
		                bufferedWriter.write(covidTot+"");		               
		                bufferedWriter.newLine();
		                bufferedWriter.write(covidTotJung+"");		               
		                bufferedWriter.newLine();
		                bufferedWriter.write(covidDie+"");		               
		                bufferedWriter.newLine();		                
		                bufferedWriter.close();
		            }
		        }
				catch (IOException e) 
				{
		            System.out.println(e);
		        }
				
			}
			else if(select == 2) 
			{
		        try
		        {
		            //파일 객체 생성
		            File file = new File("C:\\aaa\\work\\covid.txt");
		            //입력 스트림 생성
		            FileReader filereader = new FileReader(file);
		            //입력 버퍼 생성
		            BufferedReader bufReader = new BufferedReader(filereader);
		            
		            String line = "";
		            String data[]= {"","",""};
		            
		            int i=0;
		            
		            while((line = bufReader.readLine()) != null)
		            {
		            	data[i] = line;
		            	//System.out.println("data["+i+"]:"+ data[i]);
		            	i++;
		            }
		            System.out.println();
		            System.out.println("[  현재누적통계  ]");
		            System.out.println("확진자수:"+ data[0] + "명");
		            System.out.println("중증환자:"+ data[1]+ "명");
		            System.out.println("사망자:"+ data[2]+ "명");
		            System.out.println();
		            
	            	covidTot = Integer.parseInt(data[0]);
	            	covidTotJung = Integer.parseInt(data[1]);
	            	covidDie = Integer.parseInt(data[2]);
		            
		            bufReader.close();
		        }
		        catch (FileNotFoundException e) 
		        {           
		        }
		        catch(IOException e)
		        {           
		        }
			}
			else if(select == 3) {break;}
		}
		
		System.out.println("\n프로그램종료.");

	}

}
