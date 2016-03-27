package xwh.jPiano;

import java.util.HashMap;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.JLabel;

import xwh.jPiano.util.ImgUtil;

//钢琴键，（黑白键）
public class Key_Piano extends JLabel {
	private static final long serialVersionUID = 1L;
	

	public static final int BLACK = 1,WHITE = 2;	//是属于黑键还是白键
	
	
	private static int change = 3;//按下之后长度改变

    
    //将图片存起来，以免每次都去取。（0、1、2、3、#为图片待会）
    private static Map<String,Icon> images = new HashMap<String,Icon>();
    static{
    	images.put("0", ImgUtil.getImageIcon(("pianoKeys/0.png")));
    	images.put("1", ImgUtil.getImageIcon(("pianoKeys/1.png")));
    	images.put("2", ImgUtil.getImageIcon(("pianoKeys/2.png")));
    	images.put("3", ImgUtil.getImageIcon(("pianoKeys/3.png")));
    	images.put("#", ImgUtil.getImageIcon(("pianoKeys/#.png")));
    	
    	images.put("0_down", ImgUtil.getImageIcon(("pianoKeys/0_down.png")));
    	images.put("1_down", ImgUtil.getImageIcon(("pianoKeys/1_down.png")));
    	images.put("2_down", ImgUtil.getImageIcon(("pianoKeys/2_down.png")));
    	images.put("3_down", ImgUtil.getImageIcon(("pianoKeys/3_down.png")));
    	images.put("#_down", ImgUtil.getImageIcon(("pianoKeys/#_down.png")));      
    }
    
    
    
	
    int keyNum;		//音高
    public int blackOrWhite;	//黑白
    public char ch;		//对应的图片
    public boolean isDown = false;
     
    private int length;	//图形长度
    
    
//ch为图片名(0、1、2、3、#, 其中#为黑键)
	public Key_Piano(int x, int y,int width,int height,int num,char ch) {
        keyNum = num;
        setLocation(x,y);
        setSize(width, height);

        this.setAlignmentX(TOP_ALIGNMENT);
		this.setAlignmentY(TOP_ALIGNMENT);
		this.ch = ch;
		this.length = height;
		
		this.blackOrWhite = (ch=='#'?BLACK:WHITE);
        
		this.setIcon(images.get(ch+""));
    }
    

	public void setImage_down() {
		this.setIcon(images.get(ch+"_down"));
		
		if(this.blackOrWhite==WHITE){	//白键长度变
			this.setSize(this.getWidth(), length+change);
		}
		
	}
	public void setImage_up() {		//只改变图片，不发音。
		this.setIcon(images.get(ch+""));
		
		if(this.blackOrWhite==WHITE){	//白键长度变
			this.setSize(this.getWidth(), length);
		}
	}
	

    //由鼠标点击琴键触发
    public void on(){
    	
    	isDown = true;

		this.setImage_down();		//理解这里，将发音和显示分开处理。
		
		DeviceManage.nodeOn_left(this.keyNum);
		
    }

	public void off() {
		
		isDown = false;	
		
		this.setImage_up();	
		
		DeviceManage.nodeOff_left(this.keyNum);
			
	}	
    
}
