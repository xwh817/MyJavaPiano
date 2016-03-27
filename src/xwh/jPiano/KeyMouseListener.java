package xwh.jPiano;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;


//按键的鼠标事件监听器。
public class KeyMouseListener extends MouseAdapter{

	private boolean mouseDown = false;
	
	//private Key_Keyboard key_last;	//最后一个(最后鼠标所在的那个键)
	private MyJavaPiano jPiano;
	
	public KeyMouseListener(MyJavaPiano jPiano){
		this.jPiano = jPiano;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		mouseDown = true;
		
		Key_Keyboard key = (Key_Keyboard) e.getComponent();
		
		if(e.isMetaDown()){		//鼠标右键
			MyJavaPiano.win_KeySet.setLocation(e.getXOnScreen()-100, e.getYOnScreen()-50);
			MyJavaPiano.win_KeySet.setKey(key);
			key.setImageOn();
			MyJavaPiano.win_KeySet.setVisible(true);
		}
		else{
			key.setImageOn();
			key.on();
			
			Panel_Piano.pianoKeys[key.getKeyNum()+DeviceManage.flat-21].setImage_down();
		}
		
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		mouseDown = false;
		
		Key_Keyboard key = (Key_Keyboard) e.getComponent();
		key.setImageOff();
		key.off_delay();
		
		/*if(key_last!=null&&key_last.isOn){  			
			key_last.off_delay();
			key_last.setImageOff();
			Panel_Piano.pianoKeys[key_last.getKeyNum()+DeviceManage.flat-21].setImage_up();
		}
		*/
		Panel_Piano.pianoKeys[key.getKeyNum()+DeviceManage.flat-21].setImage_up();
		
		//clearKeys();
		
		jPiano.requestFocus();
	}
	
	
	/*
	private void clearKeys() {
		Iterator<Key_Keyboard> it_keys = Panel_Keyboard.keys_mapping.values().iterator();
		while(it_keys.hasNext()){
			Key_Keyboard key = it_keys.next();
			key.count_sound=0;				
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if(mouseDown){
			Key_Keyboard key = (Key_Keyboard) e.getComponent();
			if(!key.isOn){
        		key.on();
        		key.setImageOn();
        		Panel_Piano.pianoKeys[key.getKeyNum()+DeviceManage.flat-21].setImage_down();
			}
    		key_last = key;
		}
	}
	
	public void mouseExited(MouseEvent e) {
		if(mouseDown){
			Key_Keyboard key = (Key_Keyboard) e.getComponent();
			if(key.isOn){
        		key.setImageOff();
        		key.off_delay();
        		Panel_Piano.pianoKeys[key.getKeyNum()+DeviceManage.flat-21].setImage_up();
			}
		}
	}

	*/

}
