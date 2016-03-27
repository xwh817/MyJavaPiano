package xwh.jPiano;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

	//快捷键设置对话框
public class HotKeyDialog extends JDialog{		
		private static final long serialVersionUID = 1L;
		
		
		public HotKeyDialog(){
			this.setSize(350, 300);
			this.setTitle("快捷键设置");
			this.setModal(true);
			JPanel p = new JPanel();
			
			p.setBorder(new TitledBorder("快捷键设置"));
			
			final HotKeyPanel key_left8_in = new HotKeyPanel("左8度（升）："); 
			final HotKeyPanel key_left8_de = new HotKeyPanel("左8度（降）："); 
			final HotKeyPanel key_right8_in = new HotKeyPanel("右8度（升）："); 
			final HotKeyPanel key_right8_de = new HotKeyPanel("右8度（降）："); 
			final HotKeyPanel key_major_in = new HotKeyPanel("移调（升）："); 
			final HotKeyPanel key_major_de = new HotKeyPanel("移调（降）："); 
			
			
			//设置选项。
			key_left8_in.combo_HotKeys.setSelectedItem(SpecialKeyListener.str_key_left8_in);
			key_left8_de.combo_HotKeys.setSelectedItem(SpecialKeyListener.str_key_left8_de);
			key_right8_in.combo_HotKeys.setSelectedItem(SpecialKeyListener.str_key_right8_in);
			key_right8_de.combo_HotKeys.setSelectedItem(SpecialKeyListener.str_key_right8_de);
			key_major_in.combo_HotKeys.setSelectedItem(SpecialKeyListener.str_key_major_in);
			key_major_de.combo_HotKeys.setSelectedItem(SpecialKeyListener.str_key_major_de);
			
			p.add(key_left8_in);
			p.add(key_left8_de);
			p.add(key_right8_in);
			p.add(key_right8_de);
			p.add(key_major_in);
			p.add(key_major_de);
			
			
			JPanel panel_bt = new JPanel();			
			JButton bt_ok = new JButton("确 定");
			JButton bt_cancel = new JButton("取 消");
			
			bt_ok.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {

					SpecialKeyListener.str_key_left8_in = key_left8_in.combo_HotKeys.getSelectedItem().toString();
					SpecialKeyListener.str_key_left8_de = key_left8_de.combo_HotKeys.getSelectedItem().toString();
					SpecialKeyListener.str_key_right8_in = key_right8_in.combo_HotKeys.getSelectedItem().toString();
					SpecialKeyListener.str_key_right8_de = key_right8_de.combo_HotKeys.getSelectedItem().toString();
					SpecialKeyListener.str_key_major_in = key_major_in.combo_HotKeys.getSelectedItem().toString();
					SpecialKeyListener.str_key_major_de = key_major_de.combo_HotKeys.getSelectedItem().toString();
					
					SpecialKeyListener.keyCode_left8_in = ConfigManage.getKeyCode(SpecialKeyListener.str_key_left8_in);
					SpecialKeyListener.keyCode_left8_de = ConfigManage.getKeyCode(SpecialKeyListener.str_key_left8_de);
					SpecialKeyListener.keyCode_right8_in = ConfigManage.getKeyCode(SpecialKeyListener.str_key_right8_in);
					SpecialKeyListener.keyCode_right8_de = ConfigManage.getKeyCode(SpecialKeyListener.str_key_right8_de);
					SpecialKeyListener.keyCode_major_in = ConfigManage.getKeyCode(SpecialKeyListener.str_key_major_in);
					SpecialKeyListener.keyCode_major_de = ConfigManage.getKeyCode(SpecialKeyListener.str_key_major_de);
					
					
					
					SpecialKeyListener.saveUserSetting();
					
					HotKeyDialog.this.setVisible(false);
				}
				
			});
			bt_cancel.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					
					HotKeyDialog.this.setVisible(false);
				}
				
			});
			panel_bt.add(bt_ok);
			panel_bt.add(bt_cancel);
			
			JLabel lab_title = new JLabel(" ");
			this.add(lab_title,BorderLayout.NORTH);
			this.add(p,BorderLayout.CENTER);
			this.add(panel_bt,BorderLayout.SOUTH);
			
		}
		
	
	class HotKeyPanel extends JPanel{
		private static final long serialVersionUID = 1L;
		
		public JComboBox combo_HotKeys;
		Dimension size = new Dimension(200,50);
		public HotKeyPanel(String title){
			this.setSize(size);
			
			combo_HotKeys = new JComboBox();       
			combo_HotKeys.setPreferredSize(new Dimension(50,25));
			combo_HotKeys.addItem("无");
			for(int i=5;i<=12;i++){		//F1~F4已经固定了
				combo_HotKeys.addItem("F"+i);
			}
			
			this.add(new JLabel(title));
			this.add(combo_HotKeys);
		}
	}
	
}
	