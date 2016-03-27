package xwh.jPiano;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import xwh.jPiano.util.ImgUtil;

/**
 * 修改键对话框。
 * @author xwh817
 *
 */
public class Win_KeySetting extends JDialog{
	private static final long serialVersionUID = 1L;

	private Key_Keyboard key = null;
	
	JLabel p_show = new JLabel();	//显示音符
	JComboBox combo_note = new JComboBox();
	JComboBox combo_b = new JComboBox();
	JComboBox combo_v = new JComboBox();
	
	boolean is_b = false;	//是否为降半音

	KeySettingListener comboListener = new KeySettingListener();
	
	//显示当前修改的键的各参数。
	public void setKey(Key_Keyboard key){
		this.key = key;
		
		comboListener.keyNum = key.getKeyNum();
		
		if(key.getIcon()==null){
			this.combo_note.setSelectedIndex(0);
			this.combo_b.setSelectedIndex(0);
			this.combo_v.setSelectedIndex(3);
	
			this.p_show.setIcon(ImgUtil.getImageIcon(("keyboardKeys/60.gif")));		
			
			return;
		}
		
		this.p_show.setIcon(key.getIcon());
		
		int a = key.getKeyNum()%12;
		int node = 0;	//唱名
		switch(a){
		case 0:
		case 1: node=1;break;
		case 2:
		case 3: node=2;break;
		case 4: node=3;break;
		case 5:
		case 6: node=4;break;
		case 7:
		case 8: node=5;break;
		case 9:
		case 10: node=6;break;
		case 11: node=7;break;
		}
		this.combo_note.setSelectedIndex(node-1);
		
		int b = key.getKeyNum()/12;
		
		/*if(b>8){	//8度上限
			b=8;
		}else if(b<2){
			b=2;
		}*/
		
		this.combo_v.setSelectedIndex(b-2);

		
		if(a==1||a==3||a==6||a==8||a==10){	//半音
			this.combo_b.setSelectedItem("#");				
		}else{
			this.combo_b.setSelectedIndex(0);
		}
	}
	
	public Win_KeySetting(){
		this.setModal(true);
		this.setTitle("键设置");
		this.setSize(200, 180);
		
		this.setResizable(false);
		
		JPanel p = new JPanel();
		
		p.setLayout(null);
		
		JLabel lab_note = new JLabel("唱名：");
		lab_note.setSize(60,25);			
        combo_note.setSize(50,25);
		combo_note.addItem("1");
		combo_note.addItem("2");
		combo_note.addItem("3");
		combo_note.addItem("4");
		combo_note.addItem("5");
		combo_note.addItem("6");
		combo_note.addItem("7");
		
		lab_note.setLocation(5,15);
		combo_note.setLocation(70, 15);
		p.add(lab_note);
		p.add(combo_note);
		
		
		JLabel lab2 = new JLabel("升降号：");
		lab2.setSize(60,25);			
        combo_b.setSize(50,25);
		combo_b.addItem(" ");
		combo_b.addItem("#");
		combo_b.addItem("b");		
		lab2.setLocation(5,45);
		combo_b.setLocation(70, 45);
		p.add(lab2);
		p.add(combo_b);
		
		
		JLabel lab3 = new JLabel("8度：");
		lab3.setSize(60,25);			
        combo_v.setSize(50,25);
        combo_v.addItem("-3");
		combo_v.addItem("-2");
		combo_v.addItem("-1");
		combo_v.addItem("0");
		combo_v.addItem("1");
		combo_v.addItem("2");
		combo_v.addItem("3");
		lab3.setLocation(5,75);
		combo_v.setLocation(70, 75);
		p.add(lab3);
		p.add(combo_v);
		
		
		
		p_show.setBorder(new LineBorder(Color.black,1));
		//p_show.setFont(new Font("",Font.BOLD,20));
		p_show.setHorizontalAlignment(SwingConstants.CENTER);
		p_show.setSize(50, 50);
		p_show.setLocation(135, 20);
		p.add(p_show);
		
		
		JButton bt = new JButton("确定");
		bt.setSize(60, 30);
		bt.setLocation(70, 110);
		p.add(bt);
		
		this.add(p);
		
		
		combo_note.addItemListener(comboListener);
		combo_v.addItemListener(comboListener);
		combo_b.addItemListener(comboListener);
	
		
		//确认按钮 修改键
		bt.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				Key_Keyboard key = Win_KeySetting.this.key;				
				
				key.setKeyNum(comboListener.keyNum,is_b);		//更换图片
				Win_KeySetting.this.setVisible(false);
				key.setImageOff();
				
				//Panel_Keyboard.keys_mapping.put(key.getKeyCode(), key);
				
				/*//修改对应的音符
				if(key.leftOrRight==Key_Keyboard.LEFT){
					//注意要减去当前的8度。
					Panel_Keyboard.keyLayout_left.put(key.getKeyCode(), key.getKeyNum()-DeviceManage.v8_left*12);
				}else{
					Panel_Keyboard.keyLayout_right.put(key.getKeyCode(), key.getKeyNum()-DeviceManage.v8_right*12);					
				}*/
			}
			
		});
		
		
		
		this.addWindowListener(new WindowAdapter(){

			@Override
			public void windowClosed(WindowEvent arg0) {
				Win_KeySetting.this.setVisible(false);
			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				Win_KeySetting.this.key.setBackground(Color.white);
			}
			
			
			
		});
	}
	
	

	
	class KeySettingListener implements ItemListener{


		public int keyNum;
		
		
		@Override
		public void itemStateChanged(ItemEvent e) {
			
			if(!Win_KeySetting.this.isVisible()){	//程序设置选项时也会触发该事件,如果是由程序来设置，则不响应事件。
				return;
			}
			
			//以免itemStateChanged执行两次。
			if(e.getStateChange() == ItemEvent.SELECTED){	
				int note=0,v=0;	
				keyNum=0;
			
				
				switch(combo_note.getSelectedIndex()){
					case 0:	note=0;break;
					case 1:	note=2;break;
					case 2:	note=4;break;
					case 3:	note=5;break;
					case 4:	note=7;break;
					case 5:	note=9;break;
					case 6:	note=11;break;
				}
	            
				//note = combo_note.getSelectedIndex()+1;
				String str_b = combo_b.getSelectedItem().toString();
				
				is_b = false;
				
				if(str_b.equals("#")){
					note++;
				}else if(str_b.equals("b")){
					is_b = true;
					note--;
				}
				v = Integer.parseInt(combo_v.getSelectedItem().toString());
				
				
				keyNum = 60+note+v*12;
				
				if(is_b){
					p_show.setIcon(ImgUtil.getImageIcon("keyboardKeys/"+keyNum+"_b.gif"));
				}else{
					p_show.setIcon(ImgUtil.getImageIcon("keyboardKeys/"+keyNum+".gif"));
				}
				
			}
		}
		
		
	}
	
	
	
	
	
}
