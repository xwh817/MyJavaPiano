package xwh.jPiano;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

/**
 * @author: xwh817
 *@说明：管理配置信息
 */
public class ConfigManage {

	//String rootPath = ConfigManage.class.getClassLoader().getResource(rootPath+path);
	//初始化参数
	public static Map<String,String> initial_props = new HashMap<String,String>();
	
	
	private static final String path_cfg = "config/cfg.xml";
	
	//获取初始配置文件
	public static void setInitial_props(){

		SAXBuilder builder = new SAXBuilder();
		try {
			
			Document doc = null;
			File file_cfg = new File(path_cfg);
			if(file_cfg.exists()){		//如果没有生成配置文件，就从jar里面取。
				doc = builder.build(file_cfg);
			}else{
				URL url = ConfigManage.class.getClassLoader().getResource(path_cfg);
				doc = builder.build(url);
			}
			
			Element root = doc.getRootElement();
			
			String lookAndFeel = root.getChild("lookAndFeel").getTextTrim();
			String theme = root.getChild("theme").getTextTrim();
			String path_records = root.getChild("path_records").getTextTrim();
			String path_userFiles = root.getChild("path_userFiles").getTextTrim();
			String instr_1_index = root.getChild("instr_1_index").getTextTrim();
			String instr_2_index = root.getChild("instr_2_index").getTextTrim();
			String instr_3_index = root.getChild("instr_3_index").getTextTrim();
			String instr_4_index = root.getChild("instr_4_index").getTextTrim();
			
			
			initial_props.put("lookAndFeel", lookAndFeel);
			initial_props.put("theme", theme);
			initial_props.put("path_records", path_records);
			initial_props.put("path_userFiles", path_userFiles);
			initial_props.put("instr_1_index", instr_1_index);
			initial_props.put("instr_2_index", instr_2_index);
			initial_props.put("instr_3_index", instr_3_index);
			initial_props.put("instr_4_index", instr_4_index);
	
			
			//快捷键的配置
			Element hotKeys = root.getChild("hotKeys");
			
			Element left8_in = hotKeys.getChild("left8_in");
			Element left8_de = hotKeys.getChild("left8_de");
			Element right8_in = hotKeys.getChild("right8_in");
			Element right8_de = hotKeys.getChild("right8_de");
			Element major_in = hotKeys.getChild("major_in");
			Element major_de = hotKeys.getChild("major_de");
			
			if(left8_in!=null){
				SpecialKeyListener.str_key_left8_in = left8_in.getTextTrim();
				SpecialKeyListener.keyCode_left8_in = getKeyCode(SpecialKeyListener.str_key_left8_in);
			}
			if(left8_de!=null){
				SpecialKeyListener.str_key_left8_de = left8_de.getTextTrim();
				SpecialKeyListener.keyCode_left8_de = getKeyCode(SpecialKeyListener.str_key_left8_de);
			}
			if(right8_in!=null){
				SpecialKeyListener.str_key_right8_in = right8_in.getTextTrim();
				SpecialKeyListener.keyCode_right8_in = getKeyCode(SpecialKeyListener.str_key_right8_in);
			}
			if(right8_de!=null){
				SpecialKeyListener.str_key_right8_de = right8_de.getTextTrim();
				SpecialKeyListener.keyCode_right8_de = getKeyCode(SpecialKeyListener.str_key_right8_de);
			}

			if(major_in!=null){
				SpecialKeyListener.str_key_major_in = major_in.getTextTrim();
				SpecialKeyListener.keyCode_major_in = getKeyCode(SpecialKeyListener.str_key_major_in);
			}
			if(major_de!=null){
				SpecialKeyListener.str_key_major_de = major_de.getTextTrim();
				SpecialKeyListener.keyCode_major_de = getKeyCode(SpecialKeyListener.str_key_major_de);
			}


			
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//保存初始信息，在退出的时候调用。
	public static void saveInitial_props(){
		//保存用户设置
    	SAXBuilder builder = new SAXBuilder();
		try {
			Document doc = builder.build(new File(path_cfg));
			Element root = doc.getRootElement();
			
			Element e_lookAndFeel = root.getChild("lookAndFeel");
			Element e_theme = root.getChild("theme");
			Element e_path_records = root.getChild("path_records");
			Element e_path_userFiles = root.getChild("path_userFiles");
			Element e_instr_1_index = root.getChild("instr_1_index");
			Element e_instr_2_index = root.getChild("instr_2_index");
			Element e_instr_3_index = root.getChild("instr_3_index");
			Element e_instr_4_index = root.getChild("instr_4_index");
			
			e_lookAndFeel.setText(initial_props.get("lookAndFeel"));
			e_theme.setText(initial_props.get("theme"));
			e_path_records.setText(initial_props.get("path_records"));
			e_path_userFiles.setText(initial_props.get("path_userFiles"));
			e_instr_1_index.setText(initial_props.get("instr_1_index"));
			e_instr_2_index.setText(initial_props.get("instr_2_index"));
			e_instr_3_index.setText(initial_props.get("instr_3_index"));
			e_instr_4_index.setText(initial_props.get("instr_4_index"));
			
			XMLOutputter out = new XMLOutputter();
			FileOutputStream fos = new FileOutputStream(path_cfg);
			
			out.output(doc,fos);
			
			
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
	}
	
	public static void saveUserSetting(){
		SAXBuilder builder = new SAXBuilder();
		try {
			Document doc = builder.build(path_cfg);
			Element root = doc.getRootElement();
			

			Element hotKeys = root.getChild("hotKeys");
			
			Element left8_in = hotKeys.getChild("left8_in");
			Element left8_de = hotKeys.getChild("left8_de");
			Element right8_in = hotKeys.getChild("right8_in");
			Element right8_de = hotKeys.getChild("right8_de");
			Element major_in = hotKeys.getChild("major_in");
			Element major_de = hotKeys.getChild("major_de");
			
			left8_in.setText(SpecialKeyListener.str_key_left8_in);
			left8_de.setText(SpecialKeyListener.str_key_left8_de);
			right8_in.setText(SpecialKeyListener.str_key_right8_in);
			right8_de.setText(SpecialKeyListener.str_key_right8_de);
			major_in.setText(SpecialKeyListener.str_key_major_in);
			major_de.setText(SpecialKeyListener.str_key_major_de);
			
			
			
			XMLOutputter out = new XMLOutputter();
			FileOutputStream fos = new FileOutputStream(path_cfg);
			
			out.output(doc,fos);
			
			
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	//使用反射，根据键的名字来去KeyCode,如VK_F1对应的keyCode
	@SuppressWarnings("unchecked")
	public static int getKeyCode(String key){
		
		try {
			if(key.equals("")||key.equals("无")){
				return -1;
			}
			
			Class c = Class.forName("java.awt.event.KeyEvent");
			
			Field f = c.getField("VK_"+key);//通过反射来获取静态常量的值。
			
			return f.getInt(c);
	
			
		} catch (java.lang.NoSuchFieldException e) {
			return -1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1;

	}
	
	
	//从url中取配置文件，第一次启动的时候使用。
	public static void parseConfig_url(String path){
		URL url = ConfigManage.class.getClassLoader().getResource(path);
		SAXBuilder builder = new SAXBuilder();
		Document doc = null;
		try {
			doc = builder.build(url);
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		parseConfig(doc);	
		
	}
	
	//从文件中取配置，用户选择的文件。
	public static void parseConfig_file(String file_path){
		
		SAXBuilder builder = new SAXBuilder();
		Document doc = null;
		try {
			doc = builder.build(new File(file_path));
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		parseConfig(doc);	
		
	}
	
	//解析配置信息，在第一次启动，或是打开设置文件的时候。
	@SuppressWarnings("unchecked")
	private static void parseConfig(Document doc){

		Element root = doc.getRootElement();
			
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
			
			
			
		Element e_pianoSetting = root.getChild("PianoSetting");
		
		String flat = e_pianoSetting.getChildText("flat");
		String v8_left = e_pianoSetting.getChildText("v8_left");
		String v8_right = e_pianoSetting.getChildText("v8_right");
		String velocity_left = e_pianoSetting.getChildText("velocity_left");
		String velocity_right = e_pianoSetting.getChildText("velocity_right");
		String delay_left = e_pianoSetting.getChildText("delay_left");
		String delay_right = e_pianoSetting.getChildText("delay_right");
				
		initial_props.put("flat", flat);
		initial_props.put("v8_left", v8_left);
		initial_props.put("v8_right", v8_right);
		initial_props.put("velocity_left", velocity_left);
		initial_props.put("velocity_right", velocity_right);
		initial_props.put("delay_left", delay_left);
		initial_props.put("delay_right", delay_right);		
		
		if(MyJavaPiano.controls!=null){
			
			MyJavaPiano.controls.combo_flat.setSelectedIndex(Integer.parseInt(flat)+4);	//flat与index对应，C调flat为0，index为4
			//changeV8(v8_left-DeviceManage.v8_left,Key_Keyboard.LEFT);		//不能调用这个，Iterator<Element> it_keys里面已经对应好了，也不用改键盘的显示了。
			//changeV8(v8_right-DeviceManage.v8_right,Key_Keyboard.RIGHT);
			MyJavaPiano.controls.changeVelocity_left(Integer.parseInt(velocity_left));
			MyJavaPiano.controls.changeVelocity_right(Integer.parseInt(velocity_right));
			
			//MyJavaPiano.controls.changeDelay_left(Integer.parseInt(delay_left));
			//MyJavaPiano.controls.changeDelay_right(Integer.parseInt(delay_right));
			MyJavaPiano.controls.combo_delay_left.setSelectedIndex(Integer.parseInt(delay_left));
			MyJavaPiano.controls.combo_delay_right.setSelectedIndex(Integer.parseInt(delay_right));
			
			DeviceManage.v8_left=Integer.parseInt(v8_left);
			MyJavaPiano.controls.jlab_left.setText(v8_left+"");
	        DeviceManage.v8_right=Integer.parseInt(v8_right);
	        MyJavaPiano.controls.jlab_right.setText(v8_right+"");
	        
			
		}
		
			
	}
	



	//保存配置，当前界面的设置、键设置。
	public static void saveConfig(File f_xml) {
		XMLOutputter xmlOut = new XMLOutputter();
		
		Element root = new Element("MyJavaPianoSetting");
		
		Element PianoSetting = new Element("PianoSetting");
		
		Element flat = new Element("flat");
		Element v8_left = new Element("v8_left");
		Element v8_right = new Element("v8_right");
		Element velocity_left = new Element("velocity_left");
		Element velocity_right = new Element("velocity_right");
		Element delay_left = new Element("delay_left");
		Element delay_right = new Element("delay_right");
		
		flat.setText(DeviceManage.flat+"");
		v8_left.setText(DeviceManage.v8_left+"");
		v8_right.setText(DeviceManage.v8_right+"");
		velocity_left.setText(DeviceManage.velocity_left+"");
		velocity_right.setText(DeviceManage.velocity_right+"");
		delay_left.setText(DeviceManage.delay_left_index+"");
		delay_right.setText(DeviceManage.delay_right_index+"");
		
		PianoSetting.addContent(flat);
		PianoSetting.addContent(v8_left);
		PianoSetting.addContent(v8_right);
		PianoSetting.addContent(velocity_left);
		PianoSetting.addContent(velocity_right);
		PianoSetting.addContent(delay_left);
		PianoSetting.addContent(delay_right);
		

		
		Element keysLayout = new Element("keysLayout");
		//键设置
		Iterator<Key_Keyboard> it_keys = Panel_Keyboard.keys_mapping.values().iterator();
		while(it_keys.hasNext()){
			Key_Keyboard key = it_keys.next();
			
			Element e_key = new Element("key");
			Attribute attr_keyCode = new Attribute("keyCode",key.getKeyCode()+"");
			Attribute attr_pitch = new Attribute("pitch",key.getKeyNum()+"");
			char ch_is_b = key.is_b?'1':'0';
			Attribute attr_is_b = new Attribute("is_b",ch_is_b+"");		//用0/1来标识是否为降半音。
			e_key.setAttribute(attr_keyCode);
			e_key.setAttribute(attr_pitch);
			e_key.setAttribute(attr_is_b);
			
			keysLayout.addContent(e_key);
		}
		
		root.addContent(PianoSetting);
		root.addContent(keysLayout);
		Document doc = new Document(root);
		
		try {
			FileOutputStream fos = new FileOutputStream(f_xml);			
			xmlOut.output(doc, fos);			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	



	
	
}
