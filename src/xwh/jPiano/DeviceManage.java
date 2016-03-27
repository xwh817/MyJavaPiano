package xwh.jPiano;

import javax.sound.midi.Instrument;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Soundbank;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.Track;


public class DeviceManage {
	
	
	public static int flat;		//调值,注意：DeviceManage.flat = index-4;	//调值与顺序对应。C调则flat=0
	
    public static Sequencer sequencer;
    public static Sequence sequence;
    public static Synthesizer synthesizer;
    public static Instrument instruments[];
	public static Track track;
    public static long startTime;
      
	public static MidiDevice device_left;    
	public static MidiDevice device_right;  
	
    public static MidiChannel channel_left;    //声道
    public static MidiChannel channel_right; 
    public static int channel_index_left = 0;	//声道对应的index
    public static int channel_index_right = 1;	
    
    public static int program_left;    //乐器编号
    public static int program_right;    

    public static int velocity_left = 75;	//左力度
    public static int velocity_right = 90;	//右力度
    
    
    public static int v8_left=0;	//左八度
    public static int v8_right=0;	//右八度
	

	
    public static String[] delayNames = {"0","0.2秒","0.5秒","1秒","1.5秒","2秒"};
    public static int[] delays = {0,200,500,1000,1500,2000};
    
	public static int delay_left_index;
	public static int delay_right_index;
	
	public static boolean isRecording = false;	//是否正在录音。
	
	/*
	public static void main(String[] args) {
		
		initial();
		
		//changeProgram(midiDevice_left,10);
		changeProgram(device_left,channel_index_left,60);
		//changeProgram(midiDevice_right,channel_right_index,60);
		
		nodeOn_right(60);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		nodeOff_right(60);	
		
		for(int i=0;i<5;i++){
			
			nodeOn_left(60+i);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
			nodeOff_left(60+i);
		}
		
		nodeOn_right(60);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}			
		nodeOff_right(70);
		
	}*/
	
	/*public static void initial(){
		try {
			//midiDevice_left = openDevice("YAMAHA Virtual MIDI Device 1");
			//midiDevice_right = openDevice("YAMAHA Virtual MIDI Device 1");
			midiDevice_left = openDevice("Microsoft");
			midiDevice_right = openDevice("Microsoft");
			
			System.out.println(midiDevice_left==midiDevice_right);
			
		} catch (MidiUnavailableException e) {
			e.printStackTrace();
		}
	}*/
	
	
    public static void initial(){

		openDevice_left("Microsoft");
		openDevice_right("Microsoft");   		

    	
    	try {
            if (synthesizer == null) {
                if ((synthesizer = MidiSystem.getSynthesizer()) == null) {
                    System.out.println("getSynthesizer() failed!");
                    return;
                }
            } 
            synthesizer.open();
            sequencer = MidiSystem.getSequencer();
            sequence = new Sequence(Sequence.PPQ, 10);
        } catch (Exception ex) { ex.printStackTrace(); return; }

        Soundbank sb = synthesizer.getDefaultSoundbank();
		if (sb != null) {
	            instruments = synthesizer.getDefaultSoundbank().getInstruments();
	            synthesizer.loadInstrument(instruments[0]);
	    }
	    MidiChannel midiChannels[] = synthesizer.getChannels();
	        
	    channel_left = midiChannels[channel_index_left];
	    channel_right = midiChannels[channel_index_right];		//两个channel	    
	    
    }
    
    
    public static void nodeOn_left(int num){
    	num = num+flat;
    	nodeOn(device_left,channel_index_left, num, velocity_left);
    	if(isRecording){
    		addRecordEvent(ShortMessage.NOTE_ON,channel_index_left, num, velocity_left);
    	}
    } 
    public static void nodeOn_right(int num){
    	num = num+flat;
    	nodeOn(device_right,channel_index_right, num, velocity_right);
    	if(isRecording){
    		addRecordEvent(ShortMessage.NOTE_ON,channel_index_right, num, velocity_right);
    	}
    }

    public static void nodeOff_left(int num){
    	num = num+flat;
    	nodeOff(device_left,channel_index_left, num, velocity_left);
    	if(isRecording){
    		addRecordEvent(ShortMessage.NOTE_OFF,channel_index_left, num, velocity_left);
    	}
    }    
    public static void nodeOff_right(int num){
    	num = num+flat;
    	nodeOff(device_right,channel_index_right, num, velocity_right);
    	if(isRecording){
    		addRecordEvent(ShortMessage.NOTE_OFF,channel_index_right, num, velocity_right);
    	}
    }
    
    /**
     * 添加midi录音事件
     * @param type	事件类型
     * @param channel_index
     * @param data1 音高
     * @param data2	力度
     */
    public static void addRecordEvent(int type,int channel_index,int data1,int data2){
    	ShortMessage message = new ShortMessage();
		long millis = System.currentTimeMillis() - startTime;
        long tick = millis * sequence.getResolution() / 500;
        try {
        	message.setMessage(type,channel_index,data1,data2);
		} catch (InvalidMidiDataException e) {
			e.printStackTrace();
		} 
        MidiEvent event = new MidiEvent(message, tick);
        track.add(event);
        
    }
    
    private static void nodeOn(MidiDevice midiDevice,int channel_index,int num,int pressure){

    	System.out.println("nodeOn"+num);
    	ShortMessage message = new ShortMessage();
    	try {
			message.setMessage(ShortMessage.NOTE_ON,channel_index, num, pressure);				
			long timeStamp = -1;
			midiDevice.getReceiver().send(message, timeStamp);
		} catch (MidiUnavailableException e) {
			e.printStackTrace();
		} catch (InvalidMidiDataException e1) {
			e1.printStackTrace();
		}
    }
    private static void nodeOff(MidiDevice midiDevice,int channel_index,int num,int pressure){
    	System.out.println("nodeOff"+num);
    	ShortMessage message = new ShortMessage();
    	try {
			message.setMessage(ShortMessage.NOTE_OFF,channel_index, num, pressure);				
			long timeStamp = -1;
			midiDevice.getReceiver().send(message, timeStamp);
		} catch (MidiUnavailableException e) {
			e.printStackTrace();
		} catch (InvalidMidiDataException e1) {
			e1.printStackTrace();
		}
    }
    
    public static void openDevice_left(String deviceName){
			device_left = openDevice(deviceName);
    }
    public static void openDevice_right(String deviceName){
		device_right = openDevice(deviceName);
    }
    
	public static MidiDevice openDevice(String deviceName){
    	
    	MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();
        for (int i=0; i < infos.length; i++)
        {
        	MidiDevice.Info info = infos[i];
            String infoString = info.getName();
          	
			System.out.println(infoString);
        
			if(infoString.startsWith(deviceName)){	//对于YAMAHA。。最好用全名
				
				MidiDevice device = null;
				try {
					device = MidiSystem.getMidiDevice(info);
				} catch (MidiUnavailableException e1) {
					e1.printStackTrace();
				}				
				
				if(!device.isOpen()){
					try{
						device.open();								
					}catch(Exception e){	//如果失败就下一个(这个地方要改,但MidiInDevice和MidiOutDevice居然区分不出来)
						//e.printStackTrace();
						continue;
					}
								
				}
				
				
				return device;	
			}			

        }
        
        return null;
        
    }
    
	public static void close(){
		if(device_left!=null&&device_left.isOpen()){
			device_left.close();
		}
		if(device_right!=null&&device_right.isOpen()){
			device_right.close();
		}
		
	}


	//自动判断乐器是否延音
	public static void changeProgram_left(int program){
		program_left = program;
		changeProgram(device_left,channel_index_left,program);
		

		//如果乐器不适合延音，就将右手乐器设为0
    	if(!InstrumentsTable.isDelay[program]){
    		if(MyJavaPiano.controls!=null) MyJavaPiano.controls.combo_delay_left.setSelectedIndex(0);	
    	}else{
    		if(MyJavaPiano.controls!=null) MyJavaPiano.controls.combo_delay_left.setSelectedIndex(3);	
    	}
    	
	}
	public static void changeProgram_right(int program){
		program_right = program;
		changeProgram(device_right,channel_index_right,program);

		//如果乐器不适合延音，就将右手乐器设为0
    	if(!InstrumentsTable.isDelay[program]){
    		if(MyJavaPiano.controls!=null) MyJavaPiano.controls.combo_delay_right.setSelectedIndex(0);	
    	}else{
    		if(MyJavaPiano.controls!=null) MyJavaPiano.controls.combo_delay_right.setSelectedIndex(3);	
    	}  	
		
	}	
	
	public static void changeProgram_left2(int program){
		program_left = program;
		changeProgram(device_left,channel_index_left,program);		
	}
	public static void changeProgram_right2(int program){
		program_right = program;
		changeProgram(device_right,channel_index_right,program);
	}
	
	
	//更换乐器
	private static void changeProgram(MidiDevice device,int channel_index,int program){
	
		
		ShortMessage message = new ShortMessage();
		try {
			message.setMessage(ShortMessage.PROGRAM_CHANGE,channel_index,program, 127);				
			long timeStamp = -1;
			device.getReceiver().send(message, timeStamp);		
			
		} catch (MidiUnavailableException e) {
			e.printStackTrace();
		} catch (InvalidMidiDataException e1) {
			e1.printStackTrace();
		}
		
		
	}



    
}
