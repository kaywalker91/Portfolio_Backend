package study0602;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.border.*;
import javax.swing.text.Caret;
 
public class Study152_SongList extends JFrame
{
    JLabel lbl,lb2,laName,laSinger,laCnt;
    JTextField name;
    JTextField singer;
    JTextField cnt;
    JPanel Panel1,Panel2,Panel3,btnPanel;
    JButton b1,b2;
    JTextArea content;
    String inputName= "";
    String inputSinger= "";
    String inputCnt= "";
    String result = "";
    
    int j = 0;
    int cnt1= 0;
  
    public Study152_SongList()
    {
          super( "내가 즐겨 들은 노래" );
          
          setLayout( new FlowLayout() );
      
          EtchedBorder eborder =  new EtchedBorder();
          lbl = new JLabel( "내가 즐겨 들은 노래" );
          lbl.setBorder(eborder);
          
          add(lbl);
        
          Panel1 = new JPanel();
          Panel2 = new JPanel();
          Panel3 = new JPanel();
          laName = new JLabel("노래제목:");
          laSinger = new JLabel("가수:");
          laCnt = new JLabel("들은횟수:");
          
          name = new JTextField(15);
          singer = new JTextField(15);
          cnt = new JTextField(15);
          Panel1.add(laName);
          Panel1.add(name);
          Panel2.add(laSinger);
          Panel2.add(singer);
          Panel3.add(laCnt);
          Panel3.add(cnt);
          
          btnPanel = new JPanel();
          b1 = new JButton("추가");
          b2 = new JButton("정렬");
          btnPanel.add(b1);
          btnPanel.add(b2);
          
          add(Panel1);
          add(Panel2);
          add(Panel3);
          add(btnPanel);
          
          lb2 = new JLabel( "내가 즐겨 들은 노래 리스트" );
          lb2.setBorder(eborder);
          add(lb2);
          
          content = new JTextArea(3,20);
          JScrollPane s= new JScrollPane(content);
          add(s);
          setSize(250,350);
          setVisible(true);
          
          setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        b1.addActionListener(new ActionListener() 
  		{
        	public void actionPerformed(ActionEvent e) 
    		{
    			if(e.getSource() == b1)
    	    	{
    				inputName = name.getText(); 
    				inputSinger = singer.getText();
    				inputCnt = cnt.getText(); 
    				result += inputName+" / "+inputSinger+" / "+inputCnt+"\n";
    				content.setText(result);
    				
    				name.setText("");
    				singer.setText("");
    				cnt.setText("");
    				
    				cnt1++;
    				
    	    	}				
    		}
      	});
        
        b2.addActionListener(new ActionListener() 
  		{
        	public void actionPerformed(ActionEvent e) 
    		{
    			if(e.getSource() == b2)
    	    	{	
    				
    	    	}				
    		}
      	});	
        
}
    
    public static void main( String args[] )
       { 
        	new Study152_SongList();
       }
}

