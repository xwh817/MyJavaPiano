package xwh.jPiano;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

/**
 * 其他按键监听（快捷键）
 * @author xwh817
 *
 */
public class SpecialKeyListener implements KeyListener {
	

	//快捷键对应的keyCode(键盘按键值)
	public static int keyCode_yanyin=32,	//延音踏板的效果(空格键)
				keyCode_left8_in,		//8度
				keyCode_left8_de,
				keyCode_right8_in,
				keyCode_right8_de,
				keyCode_major_in,		//移调
				keyCode_major_de,
				keyCode_instr_1=112,		//四个预设乐器，这里定死了算了F1~F4
				keyCode_instr_2=113,
				keyCode_instr_3=114,
				keyCode_instr_4=115;
	
	//快捷键对应的按键(键盘按键值如F1~F12)
	public static String
				str_key_left8_in,		//8度
				str_key_left8_de,
				str_key_right8_in,
				str_key_right8_de,
				str_key_major_in,		//移调
				str_key_major_de;
	
	
	
	private int delay_left_old = 3;			//保存之前的延音index
	private int delay_right_old = 3;
	

	
	private static final String path = "config/cfg.xml";
	


	public static void saveUserSetting(){
		SAXBuilder builder = new SAXBuilder();
		try {
			File file_cfg = new File("config");
			if(!file_cfg.exists()){
				file_cfg.mkdirs();
			}
			
			Document doc = builder.build(path);
			Element root = doc.getRootElement();
			

			Element hotKeys = root.getChild("hotKeys");
			
			Element left8_in = hotKeys.getChild("left8_in");
			Element left8_de = hotKeys.getChild("left8_de");
			Element right8_in = hotKeys.getChild("right8_in");
			Element right8_de = hotKeys.getChild("right8_de");
			Element major_in = hotKeys.getChild("major_in");
			Element major_de = hotKeys.getChild("major_de");
			
			left8_in.setText(str_key_left8_in);
			left8_de.setText(str_key_left8_de);
			right8_in.setText(str_key_right8_in);
			right8_de.setText(str_key_right8_de);
			major_in.setText(str_key_major_in);
			major_de.setText(str_key_major_de);
			
			
			
			XMLOutputter out = new XMLOutputter();
			FileOutputStream fos = new FileOutputStream("config/cfg.xml");
			
			out.output(doc,fos);
			
			
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
				
		//F5为升高左八度，F6降左八度
		if(keyCode==keyCode_left8_in){
			MyJavaPiano.controls.changeV8(1,Key_Keyboard.LEFT);
		}else if(keyCode==keyCode_left8_de){
			MyJavaPiano.controls.changeV8(-1,Key_Keyboard.LEFT);
		}
		
		//F12为升高右八度，F11降右八度
		else if(keyCode==keyCode_right8_in){
			MyJavaPiano.controls.changeV8(1,Key_Keyboard.RIGHT);
		}else if(keyCode==keyCode_right8_de){
			MyJavaPiano.controls.changeV8(-1,Key_Keyboard.RIGHT);
		}
		
		//移调
		else if(keyCode==keyCode_major_in){
			MyJavaPiano.controls.changeFlat(1);
		}else if(keyCode==keyCode_major_de){
			MyJavaPiano.controls.changeFlat(-1);
		}
		
		//延音（踏板效果）
		else if(keyCode==keyCode_yanyin){

			if(DeviceManage.delay_left_index==0){
				MyJavaPiano.controls.combo_delay_left.setSelectedIndex(delay_left_old);
				MyJavaPiano.controls.combo_delay_right.setSelectedIndex(delay_right_old);
			}else{
				delay_left_old = MyJavaPiano.controls.combo_delay_left.getSelectedIndex();
				delay_right_old = MyJavaPiano.controls.combo_delay_right.getSelectedIndex();
				MyJavaPiano.controls.combo_delay_left.setSelectedIndex(0);
				MyJavaPiano.controls.combo_delay_right.setSelectedIndex(0);
			}						
		}
		
		//更换乐器
		else if(keyCode==keyCode_instr_1){
			int program = Integer.parseInt(ConfigManage.initial_props.get("instr_1_index"));
        	DeviceManage.changeProgram_right(program);			
		}
		else if(keyCode==keyCode_instr_2){
			int program = Integer.parseInt(ConfigManage.initial_props.get("instr_2_index"));
        	DeviceManage.changeProgram_right(program);			
		}
		else if(keyCode==keyCode_instr_3){
			int program = Integer.parseInt(ConfigManage.initial_props.get("instr_3_index"));
        	DeviceManage.changeProgram_right(program);			
		}
		else if(keyCode==keyCode_instr_4){
			int program = Integer.parseInt(ConfigManage.initial_props.get("instr_4_index"));
        	DeviceManage.changeProgram_right(program);			
		}
		

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

}
