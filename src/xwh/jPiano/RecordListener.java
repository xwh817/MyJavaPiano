package xwh.jPiano;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.sound.midi.MidiSystem;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

public class RecordListener implements ActionListener{

	private RecordFrame rFrame;
	
	public RecordListener(RecordFrame rFrame) {
		this.rFrame = rFrame;
	}
	
	@Override	
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        if (button.equals(rFrame.playB)) {        	
            if (rFrame.playB.getText().startsWith("播放")) {
            	
            	//System.out.println("play....");
            	                
            	rFrame.playRecord(0);
                
            } else {
            	rFrame.playB.setText("播放");
            	rFrame.pauseB.setText("暂停");
            	rFrame.stopPlayRecord();
            } 
        } if (button.equals(rFrame.pauseB)) {
        	if (rFrame.pauseB.getText().startsWith("暂停")) {
        		rFrame.pausePlayRecord();
            	rFrame.pauseB.setText("继续");
        	}else{
        		rFrame.paused=false;
        		rFrame.pauseB.setText("暂停");
        	}
        } else if (button.equals(rFrame.saveB)) {	//保存录音
            try {
                File file = new File(System.getProperty("user.dir")+"/录音");
                JFileChooser fc = new JFileChooser(file);
                
                fc.setSelectedFile(new File(rFrame.musicName));
                
        		fc.setFileFilter(new FileNameExtensionFilter("保存为mid文件", "mid"));
        		fc.setFileFilter(new FileNameExtensionFilter("保存为mjp文件", "mjp"));
                
                
                if (fc.showSaveDialog(rFrame) == JFileChooser.APPROVE_OPTION) {	//弹出文件选择框
                	String path = fc.getSelectedFile().getAbsolutePath();
                	File f = null;
                	
                	
                	if(fc.getFileFilter().getDescription().equals("保存为mjp文件")){
                		
                		if(path.endsWith(".mjp")){
	                		f = new File(path);
	                	}else{
	                		f = new File(path+".mjp");		//添加后缀名
	                	}
                		
                		String name = fc.getSelectedFile().getName();
	                	if(f.exists()){
	                		
	                		int r = JOptionPane.showConfirmDialog(null
	                				,"此文件夹已经包含一个名为"+name+"的文件。\n是否覆盖？","确认文件替换", JOptionPane.YES_NO_OPTION);
	                	
	                		if(r==JOptionPane.OK_OPTION){
	                			saveMJPFile(f);
	                		}
	                	}else{             		
	                		saveMJPFile(f);
	                	}
                		
                	}
                	else{
	                		
	                	if(path.endsWith(".midi")||path.endsWith(".mid")||path.endsWith(".MIDI")||path.endsWith(".MID")){
	                		f = new File(path);
	                	}else{
	                		f = new File(path+".mid");		//添加后缀名
	                	}
	                	String name = fc.getSelectedFile().getName();
	                	if(f.exists()){
	                		
	                		int r = JOptionPane.showConfirmDialog(null
	                				,"此文件夹已经包含一个名为"+name+"的文件。\n是否覆盖？","确认文件替换", JOptionPane.YES_NO_OPTION);
	                	
	                		if(r==JOptionPane.OK_OPTION){
	                			saveMidiFile(f);
	                		}
	                	}else{             		
	                		saveMidiFile(f);
	                	}
                	}
                	
                	
                	
                }
            } catch (SecurityException ex) { 
                ex.printStackTrace();
            } catch (Exception ex) { 
                ex.printStackTrace();
            }
        }
    }


	//将录音文件保存为midi文件
    public void saveMidiFile(File file) {
        try {
            int[] fileTypes = MidiSystem.getMidiFileTypes(DeviceManage.sequence);
            if (fileTypes.length == 0) {
                System.out.println("Can't save sequence");
            } else {
                if (MidiSystem.write(DeviceManage.sequence, fileTypes[0], file) == -1) {
                    throw new IOException("Problems writing to file");
                } 
            }
        } catch (SecurityException ex) { 
        } catch (Exception ex) { 
            ex.printStackTrace(); 
        }
    }
    
    //将录音文件保存为mjp文件
    public void saveMJPFile(File file) {
    	XMLOutputter xmlOut = new XMLOutputter();
		
		Element root = new Element("Record");
		root.setAttribute("version", MyJavaPiano.version+"");
		
		Element PianoSetting = new Element("Setting");
		Element musicInf = new Element("musicInf");
		Element keysLayout = new Element("keysLayout");
		Element events = new Element("events");
		
		Element flag_index = new Element("flat");
		Element v8_left = new Element("v8_left");
		Element v8_right = new Element("v8_right");
		Element velocity_left = new Element("velocity_left");
		Element velocity_right = new Element("velocity_right");
		Element delay_left = new Element("delay_left");
		Element delay_right = new Element("delay_right");
		
		
		Element musicName = new Element("musicName");
		Element musicAuthor = new Element("musicAuthor");
		Element musicTime = new Element("musicTime");
		Element musicDesc = new Element("musicDesc");
		
		musicName.setText(rFrame.musicName);
		musicAuthor.setText(rFrame.musicAuthor);
		musicTime.setText(rFrame.musicTime);
		musicDesc.setText(rFrame.musicDesc);

		
		
		flag_index.setText(DeviceManage.flat+"");
		v8_left.setText(DeviceManage.v8_left+"");
		v8_right.setText(DeviceManage.v8_right+"");
		velocity_left.setText(DeviceManage.velocity_left+"");
		velocity_right.setText(DeviceManage.velocity_right+"");
		delay_left.setText(DeviceManage.delay_left_index+"");
		delay_right.setText(DeviceManage.delay_right_index+"");
		
		PianoSetting.addContent(flag_index);
		PianoSetting.addContent(v8_left);
		PianoSetting.addContent(v8_right);
		PianoSetting.addContent(velocity_left);
		PianoSetting.addContent(velocity_right);
		PianoSetting.addContent(delay_left);
		PianoSetting.addContent(delay_right);
		
		musicInf.addContent(musicName);
		musicInf.addContent(musicAuthor);
		musicInf.addContent(musicTime);
		musicInf.addContent(musicDesc);		
		
		//键设置
		Iterator<Key_Keyboard> it = Panel_Keyboard.keys_mapping.values().iterator();
		while(it.hasNext()){
			Key_Keyboard key = it.next();			
			
			Element e_key = new Element("key");
			Attribute attr_keyCode = new Attribute("keyCode",key.getKeyCode()+"");
			Attribute attr_pitch = new Attribute("pitch",key.getKeyNum()+"");
			Attribute attr_is_b = new Attribute("is_b",key.is_b?"1":"0");
			
			e_key.setAttribute(attr_keyCode);
			e_key.setAttribute(attr_pitch);	
			e_key.setAttribute(attr_is_b);

			keysLayout.addContent(e_key);
			
			
		}

		
		
		
		//事件
		Iterator<RecordEvent> it_events = RecordFrame.recordEventList.iterator();
		while(it_events.hasNext()){
			RecordEvent event = it_events.next();
			Element e_event = new Element("event");
			
			Attribute attr_type = new Attribute("type",event.getType()+"");
			Attribute attr_hand = new Attribute("hand",event.getLeftOrRight()+"");
			Attribute attr_keyCode = new Attribute("keyCode",event.getValue()+"");
			Attribute attr_time = new Attribute("time",event.getTime()+"");
			
			e_event.setAttribute(attr_type);
			e_event.setAttribute(attr_hand);
			e_event.setAttribute(attr_keyCode);
			e_event.setAttribute(attr_time);
			
			events.addContent(e_event);
		}		
		
		root.addContent(PianoSetting);
		root.addContent(musicInf);
		root.addContent(keysLayout);
		root.addContent(events);
		
		Document doc = new Document(root);
		
		try {
			FileOutputStream fos = new FileOutputStream(file);		
			xmlOut.output(doc, fos);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
			
		ConfigManage.initial_props.put("path_records", file.getAbsolutePath());	//记住上次的路径
        
    }
    
    

	@SuppressWarnings("unchecked")
	public long parseMJPFile(File file_mjp) {
		SAXBuilder builder = new SAXBuilder();

		long mjp_length=0;	//录音文件，毫秒数
		
		try {
			Document doc = builder.build(file_mjp);
			Element root = doc.getRootElement();
			
			float version = 0.0f;
			if(root.getAttribute("version")!=null){
				version = root.getAttribute("version").getFloatValue();
			}
			if(version==0.0f){
				//旧版本。
				JOptionPane.showMessageDialog(null, "对不起，文件打开失败。该录音文件为旧版本格式的文件,\n如果您需要转换到新版本，请将该文件发送到：xwh817@163.com\n,我会将转好的文件及时发送给您。"
						, "录音文件旧版本文件", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("/images/icons/tag_purple.png")));
				return 0;
			}
			
			Element musicInf = root.getChild("musicInf");
			if(musicInf!=null){
				Element musicName = musicInf.getChild("musicName");
				Element musicAuthor = musicInf.getChild("musicAuthor");
				Element musicTime = musicInf.getChild("musicTime");
				Element musicDesc = musicInf.getChild("musicDesc");
				
				rFrame.musicName = musicName.getText();
				rFrame.musicAuthor = musicAuthor.getText();
				rFrame.musicTime = musicTime.getText();
				rFrame.musicDesc = musicDesc.getText();		
			}
			
			
			
			Element e_keysLayout = root.getChild("keysLayout");
			
			List<Element> e_keys = e_keysLayout.getChildren("key");				
			
			Iterator<Element> it_keys = e_keys.iterator();			
			while(it_keys.hasNext()){
					Element e_key = it_keys.next();
					Integer keyCode = Integer.parseInt(e_key.getAttributeValue("keyCode"));
					Integer pitch = Integer.parseInt(e_key.getAttributeValue("pitch"));
					String str_is_b = e_key.getAttributeValue("is_b");
					boolean is_b = false;
					if(str_is_b!=null){
						is_b = str_is_b.equals("1")?true:false;
					}
					
					Key_Keyboard key = Panel_Keyboard.keys_mapping.get(keyCode);
					if(!key.isDisable()){
						key.setKeyNum(pitch,is_b);
					}

				}								
				
			Element e_pianoSetting = root.getChild("Setting");
			
			String flat = e_pianoSetting.getChildText("flat");
			String v8_left = e_pianoSetting.getChildText("v8_left");
			String v8_right = e_pianoSetting.getChildText("v8_right");
			String velocity_left = e_pianoSetting.getChildText("velocity_left");
			String velocity_right = e_pianoSetting.getChildText("velocity_right");
			String delay_left = e_pianoSetting.getChildText("delay_left");
			String delay_right = e_pianoSetting.getChildText("delay_right");
					
			ConfigManage.initial_props.put("flat", flat);
			ConfigManage.initial_props.put("v8_left", v8_left);
			ConfigManage.initial_props.put("v8_right", v8_right);
			ConfigManage.initial_props.put("velocity_left", velocity_left);
			ConfigManage.initial_props.put("velocity_right", velocity_right);
			ConfigManage.initial_props.put("delay_left", delay_left);
			ConfigManage.initial_props.put("delay_right", delay_right);
			
				
			//MyJavaPiano.controls.updateProps();		//将参数的更新反应到控件。
			MyJavaPiano.controls.combo_flat.setSelectedIndex(Integer.parseInt(flat)+4);	//flat与index对应，C调flat为0，index为4
			//changeV8(v8_left-DeviceManage.v8_left,Key_Keyboard.LEFT);		//不能调用这个，Iterator<Element> it_keys里面已经对应好了，也不用改键盘的显示了。
			//changeV8(v8_right-DeviceManage.v8_right,Key_Keyboard.RIGHT);
			MyJavaPiano.controls.changeVelocity_left(Integer.parseInt(velocity_left));
			MyJavaPiano.controls.changeVelocity_right(Integer.parseInt(velocity_right));
			
			MyJavaPiano.controls.combo_delay_left.setSelectedIndex(Integer.parseInt(delay_left));
			MyJavaPiano.controls.combo_delay_right.setSelectedIndex(Integer.parseInt(delay_right));
			
			DeviceManage.v8_left=Integer.parseInt(v8_left);
			MyJavaPiano.controls.jlab_left.setText(v8_left+"");
	        DeviceManage.v8_right=Integer.parseInt(v8_right);
	        MyJavaPiano.controls.jlab_right.setText(v8_right+"");
			
	
			
			Element e_events = root.getChild("events");
			Iterator it_events = e_events.getChildren().iterator();
			//事件
			RecordFrame.recordEventList.clear();	
		
			while(it_events.hasNext()){

				Element e_event = (Element) it_events.next();
				
				Attribute attr_type = e_event.getAttribute("type");
				Attribute attr_hand = e_event.getAttribute("hand");
				Attribute attr_keyCode = e_event.getAttribute("keyCode");
				Attribute attr_time = e_event.getAttribute("time");
				
				int type = attr_type.getIntValue();
				int leftOrRight = attr_hand.getIntValue();
				int keyCode = attr_keyCode.getIntValue();
				long time = attr_time.getLongValue();

				RecordEvent event = new RecordEvent(type, leftOrRight, keyCode, time);
				
				RecordFrame.recordEventList.add(event);
				
				if(type==-1){	//结束
					mjp_length = time;
				}
			}			
			
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return mjp_length;
	}
    
    
}
