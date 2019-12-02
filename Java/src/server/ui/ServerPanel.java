package server.ui;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ServerPanel extends JPanel{

	//在线人数
	private JLabel userNum;
	//服务器信息
	private JScrollPane scrollPane; 
	private JTextArea info;

	public ServerPanel(){
		super();
		setLayout(new BorderLayout());
		
		info=new JTextArea();
		//禁止编辑
		info.setEditable(false);
		//自动换行
		info.setLineWrap(true);
		
		scrollPane=new JScrollPane(info);
		
		userNum=new JLabel("在线人数：0");
		
		add(scrollPane,BorderLayout.CENTER);
		add(userNum,BorderLayout.NORTH);
	}
	
	public void updateInfo(String str){
		info.append(str);
	}
	
	public void updateUserNum(String str){
		userNum.setText(str);
	}
	
}
