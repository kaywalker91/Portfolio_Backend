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
		
		int covidTot = 0; //Ȯ���� �Ѵ���
		int covidTotJung = 0; //����ȯ�� �Ѵ���
		int covidDie = 0; ///����� �Ѵ���
		
		int select; 
		
		
		//�ʱ⿡ ������ �о�ͼ� �Ѻ����� �ʱⰪ�� �־������
		try
		{
            //���� ��ü ����
            File file = new File("C:\\aaa\\work\\covid.txt");
            //�Է� ��Ʈ�� ����
            FileReader filereader = new FileReader(file);
            //�Է� ���� ����
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
            System.out.println("[  ���紩�����  ]");
            System.out.println("Ȯ���ڼ�:"+ data[0] + "��");
            System.out.println("����ȯ��:"+ data[1]+ "��");
            System.out.println("�����:"+ data[2]+ "��");
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
			System.out.println("[  �ڷγ� Ȯ���� üũ ���α׷�  ]");
			System.out.println("1.�Է��ϱ�");
			System.out.println("2.��Ȳ����");
			System.out.println("3.����");
			System.out.print("����:"); select = sc.nextInt();
			
			if(select == 1) 	 
			{
		
				System.out.println("Ȯ���ڼ�:"); int covid1 = sc.nextInt();
				System.out.println("����ȯ��:"); int covid2 = sc.nextInt();
				System.out.println("�����:"); int covid3 = sc.nextInt();
				
				covidTot += covid1;  //�Է��� Ȯ���� �Ѻ����� �����ϱ�
				covidTotJung += covid2; //�Է��� ����ȯ�� �Ѻ����� �����ϱ� 
				covidDie += covid3;	 //�Է��� ����� �Ѻ����� �����ϱ�	
				
				//���Ͽ� ����ϱ�...				
				try{
		            //���� ��ü ����
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
		            //���� ��ü ����
		            File file = new File("C:\\aaa\\work\\covid.txt");
		            //�Է� ��Ʈ�� ����
		            FileReader filereader = new FileReader(file);
		            //�Է� ���� ����
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
		            System.out.println("[  ���紩�����  ]");
		            System.out.println("Ȯ���ڼ�:"+ data[0] + "��");
		            System.out.println("����ȯ��:"+ data[1]+ "��");
		            System.out.println("�����:"+ data[2]+ "��");
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
		
		System.out.println("\n���α׷�����.");

	}

}
