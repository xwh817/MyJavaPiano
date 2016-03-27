package xwh.jPiano;

/**
 * 录音对应的每个Event
 * @author: xwh817
 *@创建日期：2011-4-20
 *@说明：
 */

public class RecordEvent {

	private int type;	//事件类型	,0表示开始，-1表示结束。其他用ShortMessage中的常量表示。
	private int leftOrRight;	//左右
	private int value;	//值,发音就是keyCode,program就是对应的序号。
	private long time;		//发生时间
	
	
	public RecordEvent(int type, int leftOrRight, int value,long time) {
		this.type = type;
		this.leftOrRight = leftOrRight;
		this.value = value;
		this.time = time;
	}

	public int getType() {
		return type;
	}	
	public int getLeftOrRight() {
		return leftOrRight;
	}
	
	public long getTime() {
		return time;
	}
	public int getValue() {
		return value;
	}
	
}


