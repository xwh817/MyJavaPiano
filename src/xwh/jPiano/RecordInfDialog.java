package xwh.jPiano;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

//录音信息对话框
public class RecordInfDialog extends JDialog{
		private static final long serialVersionUID = 1L;
  	
		private JPanel p = new JPanel();

		public RecordInfDialog(final MyJavaPiano jPiano){			
			
			this.setTitle("录音信息");
			this.setSize(400, 300);
			this.setLocationRelativeTo(jPiano);			
			this.setModal(true);
			
			p.setLayout(null);
			
			final JLabel lab_musicName = new JLabel("曲 名：");lab_musicName.setSize(80,30);lab_musicName.setLocation(20, 20);
			final JTextField txt_musicName = new JTextField();txt_musicName.setSize(250,30);txt_musicName.setLocation(100, 20);
			
			final JLabel lab_musicAuthor = new JLabel("演奏者：");lab_musicAuthor.setSize(80,30);lab_musicAuthor.setLocation(20, 60);
			final JTextField txt_musicAuthor = new JTextField();txt_musicAuthor.setSize(250,30);txt_musicAuthor.setLocation(100, 60);
			
			final JLabel lab_musicDesc = new JLabel("说 明：");lab_musicDesc.setSize(80,30);lab_musicDesc.setLocation(20, 120);
			final JTextArea txt_musicDesc = new JTextArea();txt_musicDesc.setSize(250,100);txt_musicDesc.setLocation(100, 100);
			txt_musicDesc.setLineWrap(true);
			txt_musicDesc.setAutoscrolls(true);
			
			final JButton bt_start = new JButton("开 始");
			bt_start.setSize(60,30);
			bt_start.setLocation(160, 220);
			bt_start.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					jPiano.recordFrame.record();
					RecordInfDialog.this.setVisible(false);
					
					jPiano.recordFrame.musicName = txt_musicName.getText();
					jPiano.recordFrame.musicAuthor = txt_musicAuthor.getText();
					jPiano.recordFrame.musicDesc = txt_musicDesc.getText();
					
					Date timeNow = new Date();
					jPiano.recordFrame.musicTime = getDateTime(timeNow);
				}
				//获取格式化的日期、时间。
				private String getDateTime(Date date){
					Calendar calendar = Calendar.getInstance();
					
					calendar.setTime(date);
					int year = calendar.get(Calendar.YEAR);
					int month = calendar.get(Calendar.MONTH)+1;
					int d = calendar.get(Calendar.DATE);
					int hour = calendar.get(Calendar.HOUR_OF_DAY);
					int minute = calendar.get(Calendar.MINUTE);
					
					String formatDateTime = year+"-"+month+"-"+d+" "+hour+":"+(minute<10?("0"+minute):minute);
					return formatDateTime;				
					
				}
				
			});
			
			
			p.add(lab_musicName);
			p.add(txt_musicName);
			p.add(lab_musicAuthor);
			p.add(txt_musicAuthor);
			p.add(lab_musicDesc);
			p.add(txt_musicDesc);
			p.add(bt_start);
			
			this.add(p);
			
		}
		
  }
  