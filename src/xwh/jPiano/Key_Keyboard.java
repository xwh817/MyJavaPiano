package xwh.jPiano;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.sound.midi.ShortMessage;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import xwh.jPiano.util.ImgUtil;

public class Key_Keyboard extends JLabel{
	private static final long serialVersionUID = 1L;	


	private int keyCode;	//键盘对应的keyCode
	
	private int keyNum;		//音高
	
	public static int LEFT=1,RIGHT=2;	//左右 ,0既不是左也不是右
	
	public int leftOrRight;
	
	private boolean disable;	//是否用于弹奏。		
	
	private Color disable_color = new Color(238,238,238);
	
	private Color able_color = new Color(255,255,255);
	
	public boolean is_b = false;	//是否为降半音
	
	public boolean isOn = false;	//是否按下
	
	//public long flag;	//标记。
	
    public int count_sound = 0;		//记录按下的次数，只有最后一次才停止
    public int count_on = 0;		//记录一共按下的次数，为了使on和off时间对称。

	
	public static final Color c_left_on = new Color(162,231,108);
	public static final Color c_right_on = Color.yellow;
	public static final Color c_left_off = Color.white;
	public static final Color c_right_off = Color.white;
	
	private static Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
		
	private static Border bd = new LineBorder(Color.black,1,true);	//边框
		
	

	
	public int getKeyCode() {
		return keyCode;
	}
	
	public int getKeyNum() {
		return keyNum;
	}
	
	//设置该键对应的音符，is_b是否为半音
	public void setKeyNum(int keyNum,boolean is_b) {
		this.keyNum = keyNum;
		this.is_b = is_b;
		
		String imgName = null;
		if(is_b){
			imgName = keyNum+"_b.gif";
		}else{
			imgName = keyNum+".gif";
		}
					
		this.setIcon(ImgUtil.getImageIcon("keyboardKeys/"+imgName));		

	}

	public boolean isDisable() {
		return disable;
	}

	
	/**
	 * 构造函数
	 * keyCode用来唯一标识一个键,是物理键盘上的一个键，区别于keyNum
	 * text用来为disable键取图片
	 * 注：keyNum在构造函数里面并没有赋值，keyNum以及图片是从配置文件里面读取了然后生成的。
	 */
	public Key_Keyboard(int keyCode,String text,boolean disable,int x,int y,Dimension size,int leftOrRight){
		this.keyCode = keyCode;
		this.disable = disable;
		this.leftOrRight = leftOrRight;
		
		this.setOpaque(true);		
		this.setBackground(Color.white);		
		
		if(!disable){
			this.setBackground(able_color);
			this.setCursor(cursor);							
		}else{						
			this.setBackground(disable_color);
			this.setIcon(ImgUtil.getImageIcon("keyboardKeys/"+text+".gif"));	//给disable的键赋图片。					
		}
		
		this.setHorizontalAlignment(SwingConstants.CENTER);	
		this.setLocation(x,y);
		this.setSize(size);
		this.setBorder(bd);
		
	}

    
	//将图片显示与发音分开，由keyDown事件控制
    public void setImageOn(){

		if(this.leftOrRight==LEFT){
			this.setBackground(c_left_on);
		}else{
			this.setBackground(c_right_on);
		}
    }  
    public void setImageOff(){

		if(this.leftOrRight==LEFT){
			this.setBackground(c_left_off);
		}else{
			this.setBackground(c_right_off);
		}
    }
    
    
	 //发音
    public void on(){
    	//System.out.println(this.keyCode+"_"+this.keyNum);
    	
    	isOn = true;
		
    	count_add();
    	
		if(this.leftOrRight==LEFT){
			DeviceManage.nodeOn_left(this.keyNum);
			if(DeviceManage.isRecording){	//是否为录音
	    		RecordFrame.addRecordEvent(ShortMessage.NOTE_ON, LEFT, this.keyCode);
	    	}
		}else{
			DeviceManage.nodeOn_right(this.keyNum);
			if(DeviceManage.isRecording){
	    		RecordFrame.addRecordEvent(ShortMessage.NOTE_ON, RIGHT, this.keyCode);
	    	}
		}
		
		
    }

    //当有延音的时候
    public void off_delay(){

		isOn = false;
		
    	int delay = 0;
    	if(this.leftOrRight==LEFT){
			delay = DeviceManage.delays[DeviceManage.delay_left_index];
			if(DeviceManage.isRecording){	//是否为录音,注意，mjp记录的是键盘按键的事件，而midi记录的是midi的事件。
	    		RecordFrame.addRecordEvent(ShortMessage.NOTE_OFF, LEFT, this.keyCode);
	    	}
		}else{
			delay = DeviceManage.delays[DeviceManage.delay_right_index];
			if(DeviceManage.isRecording){
	    		RecordFrame.addRecordEvent(ShortMessage.NOTE_OFF, LEFT, this.keyCode);
	    	}
		}
    	
    	
		if(delay==0){
			off();
		}else{
			new Thread(new DelayThread(delay)).start();
		}
		
    }

    //按键弹起
	public void off() {

		count_sub();
		
		if(count_sound==0){
			
			isOn = false;
			
			while(count_on>0){
				count_on--;
				if(this.leftOrRight==LEFT){
					DeviceManage.nodeOff_left(this.keyNum);
				}else{
					DeviceManage.nodeOff_right(this.keyNum);
				}

			}
			
		}
			
	}	
    
    //线程同步
    private synchronized void count_add(){
    	count_sound++;
    	count_on++;
    }
    private synchronized void count_sub(){
    	count_sound--;
    }
	
	
	
	

	class DelayThread implements Runnable{
	
		private int delay;
		public DelayThread(int delay){
			this.delay = delay;
		}
		
		@Override
		public void run() {
			
			try {
				Thread.sleep(delay);
				off();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
	
	
	
}


