package study0520;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.*;
import javax.swing.text.Caret;
 
public class Contact extends JFrame
{
    JLabel lbl,laname,laphone;
    JTextField name;
    JTextField phone;
    JPanel Panel1,Panel2,btnPanel;
    JButton b1,b2;
    JTextArea content;
    String inputname= "";
    String inputphone= "";
    String result = "";
  
    public Contact()
    {
          super( "연락처등록" );
          
          setLayout( new FlowLayout() );
      
          EtchedBorder eborder =  new EtchedBorder();
          lbl = new JLabel( "연락처 내용 입력" );
          lbl.setBorder(eborder);
          
          add(lbl);
        
          Panel1 = new JPanel();
          Panel2 = new JPanel();
          laname = new JLabel("이        름");
          laphone = new JLabel("전화번호");
          
          name = new JTextField(15);
          phone = new JTextField(15);
          Panel1.add(laname);
          Panel1.add(name);
          Panel2.add(laphone);
          Panel2.add(phone);
          
          btnPanel = new JPanel();
          b1 = new JButton("등록");
          b2 = new JButton("취소");
          btnPanel.add(b1);
          btnPanel.add(b2);
          
          add(Panel1);
          add(Panel2);
          add(btnPanel);
          
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
    				inputname = name.getText(); 
    				inputphone = phone.getText(); 
    				result += inputname+" / "+inputphone+"\n";
    				content.setText(result);
    				
    				name.setText("");
    				phone.setText("");
    	    	}				
    		}
      	});	
        
}
    
    public static void main( String args[] )
       { 
        	new Contact();
       }
}
