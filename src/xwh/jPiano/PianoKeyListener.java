package xwh.jPiano;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PianoKeyListener implements KeyListener{

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = getNumKeyCode(e.getKeyCode(),e.getKeyLocation());	
		
		if(keyCode==10&&e.getKeyLocation()==1){	//屏蔽左边的回车键，以免和右边的重复。
			return;
		}
			
		Key_Keyboard key = this.getKey(keyCode);
		
		if(key!=null&&!key.isDisable()&&!key.isOn){		//isDown解决keyDown事件多次重复的问题				
			//System.out.println("keyPress");
			key.setImageOn();
			key.on();				
			Panel_Piano.pianoKeys[key.getKeyNum()+DeviceManage.flat-21].setImage_down();	//这里不是on,不然会发两次音。	      
		}
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = getNumKeyCode(e.getKeyCode(),e.getKeyLocation());


		if(keyCode==10&&e.getKeyLocation()==1){	//屏蔽左边的回车键，以免和右边的重复。
			return;
		}
		
		Key_Keyboard key = this.getKey(keyCode);
		if(key!=null&&!key.isDisable()){
			key.setImageOff();
	        key.off_delay();	      
	        Panel_Piano.pianoKeys[key.getKeyNum()+DeviceManage.flat-21].setImage_up();
		}
		
	}
	
	
	
	//键盘与按键的对应
	private Key_Keyboard getKey(int keyCode){
		return Panel_Keyboard.keys_mapping.get(keyCode);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	//NumLock按下之后还原keyCode
	private int getNumKeyCode(int keyEventCode,int keyLocation){

		//如果按下了NumLock,并且不是中间的键触发（keyLocation来识别）
		if(keyLocation!=1){			
			switch(keyEventCode){
				case 36:	keyEventCode = 103;	break;
				case 38:	keyEventCode = 104;	break;
				case 33:	keyEventCode = 105;	break;
				case 37:	keyEventCode = 100;	break;
				case 12:	keyEventCode = 101;	break;
				case 39:	keyEventCode = 102;	break;
				case 35:	keyEventCode = 97;	break;
				case 40:	keyEventCode = 98;	break;
				case 34:	keyEventCode = 99;	break;
				case 155:	keyEventCode = 96;	break;
				case 127:	keyEventCode = 110;	break;
			}
		}
		
		return keyEventCode;
	}
	
	
}
