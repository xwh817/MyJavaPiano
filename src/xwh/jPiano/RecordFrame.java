package xwh.jPiano;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.Sequence;
import javax.sound.midi.ShortMessage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 * 录音对话框
 * A frame that allows for midi capture & saving the captured data.
 */
class RecordFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	
	
	public static LinkedList<RecordEvent> recordEventList = new LinkedList<RecordEvent>();
	
	public JButton playB, saveB, pauseB;
    
    MyJavaPiano jPiano = null;
    public JSlider sld_timer;
    private JPanel lab_musicInf;
    private JLabel lab_musicName;
    private JLabel lab_musicLength;
    private JLabel lab_musicAuthor;
    private JLabel lab_musicDesc;
    private JLabel lab_musicTime;
    
    
    public JSlider sld_speed;
    
    public String musicName;
    public String musicAuthor;
    public String musicDesc;
    public String musicTime;
    
    public boolean isPlaying = false;	//是否正在播放。
    
    private float speed = 1.0f;	//播放速度
    
    private int sld_timer_length = 560;	//进度条长度
    private long mjp_length = 0;		//录音长度
    private boolean moveState = false;	//是否在进行手动拖动
    
    private RecordListener rListener;
    
    public RecordFrame(MyJavaPiano jPiano) {
        super("录音");
        
        this.jPiano = jPiano;
        
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
            	RecordFrame.this.setVisible(false);
            	RecordFrame.this.jPiano.requestFocus();
            	
            	stopPlayRecord();
            }
        });

        rListener = new RecordListener(this);
        
        JPanel p2 = new JPanel();
        p2.setBorder(new EmptyBorder(5,5,5,5));
        p2.setLayout(new FlowLayout(FlowLayout.LEFT));
        playB = new JButton("播放");
        playB.setEnabled(false);
        playB.addActionListener(rListener);
        p2.add(playB);
        
        pauseB = new JButton("暂停");
        pauseB.setEnabled(false);
        pauseB.addActionListener(rListener);
        p2.add(pauseB);
        
        saveB = new JButton("保存");
        saveB.setEnabled(false);
        saveB.addActionListener(rListener);
        p2.add(saveB);
      
        JLabel lab_speed = new JLabel("        播放速度：");
        sld_speed = new JSlider(JSlider.HORIZONTAL, 0, 200, 100);
        sld_speed.setSize(100, 30);
        sld_speed.setMajorTickSpacing(100);
        sld_speed.setMinorTickSpacing(25);
        sld_speed.setPaintTicks(true);
        sld_speed.addMouseListener(new MouseAdapter(){       	
        	
        	@Override
        	public void mouseReleased(MouseEvent e) {
        		int v_speed = sld_speed.getValue();
        		
        		//把刻度与速度比例对应起来
        		if(v_speed<12){
        			speed=0.25f;
        			sld_speed.setValue(0);
        		}else if(v_speed<37){
        			speed=0.3f;
        			sld_speed.setValue(25);
        		}else if(v_speed<62){
        			speed=0.5f;
        			sld_speed.setValue(50);
        		}else if(v_speed<87){
        			speed=0.75f;
        			sld_speed.setValue(75);
        		}else if(v_speed<112){
        			speed=1.0f;
        			sld_speed.setValue(100);
        		}else if(v_speed<137){
        			speed=1.5f;
        			sld_speed.setValue(125);
        		}else if(v_speed<162){
        			speed=2.0f;
        			sld_speed.setValue(150);
        		}else if(v_speed<187){
        			speed=2.5f;
        			sld_speed.setValue(175);
        		}else{
        			speed=3.0f;
        			sld_speed.setValue(200);
        		}
        		
        		sld_speed.setSnapToTicks(true);
        		
        	}
        	
        });
        
        p2.add(lab_speed);
        p2.add(sld_speed);
        
        getContentPane().add("North", p2);

        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setLayout(null);
        EmptyBorder eb = new EmptyBorder(0,5,5,5);
        scrollPane.setBorder(new CompoundBorder(eb,new EtchedBorder()));

        
        sld_timer = new JSlider(JSlider.HORIZONTAL, 0, sld_timer_length, 0);
        
        lab_musicInf = new JPanel();
        lab_musicInf.setSize(200, 132);
        lab_musicName = new JLabel();
        lab_musicLength = new JLabel();
        lab_musicTime = new JLabel();
        lab_musicAuthor = new JLabel();
        lab_musicDesc = new JLabel();
        
        lab_musicInf.setLayout(new GridLayout(4,1));
        lab_musicInf.setBorder(new TitledBorder("录音信息"));
        lab_musicInf.add(lab_musicName);
        lab_musicInf.add(lab_musicLength);
        lab_musicInf.add(lab_musicTime);
        lab_musicInf.add(lab_musicAuthor);
        
        lab_musicDesc.setBorder(new TitledBorder("说明"));
        lab_musicDesc.setVerticalAlignment(JLabel.TOP);
        
        sld_timer.setPaintTicks(true);
        sld_timer.setPaintLabels(true);
        sld_timer.addMouseListener(new MouseAdapter(){       	
        	@Override
        	public void mousePressed(MouseEvent e) {
        		//stopPlay = true;
        		isPlaying = false;
        	}
        	@Override
        	public void mouseReleased(MouseEvent e) {
        		//stopPlay = false;
        		isPlaying = true;
        		int moveTo = sld_timer.getValue();
        		playRecord(moveTo*1000);		
        	}
        	
        });
        
        getContentPane().add("Center", scrollPane);
    	
    	sld_timer.setSize(sld_timer_length, 50);
    	sld_timer.setLocation(20, 20);
    	lab_musicInf.setLocation(620, 10);
    	
    	lab_musicDesc.setLocation(20, 80);
    	lab_musicDesc.setSize(sld_timer_length,72);

        scrollPane.add(sld_timer);
        scrollPane.add(lab_musicInf);
        
        scrollPane.add(lab_musicDesc);
        
    	pack();

    	
        setLocation(jPiano.getX(),jPiano.getY()-240<0?0:jPiano.getY()-240);
        if(jPiano.getY()-240<0){
    		jPiano.setLocation(jPiano.getX(), 240);
    	}
        setSize(MyJavaPiano.w, 240);
        setVisible(false);
        
        
    }


	
	/**
	 * 添加录音事件。
	 * 事件类型、左手还是右手、对应的值
	 */
	public static void addRecordEvent(int type, int leftOrRight, int value){
		long millis = System.currentTimeMillis() - DeviceManage.startTime;
		recordEventList.add(new RecordEvent(type,leftOrRight,value,millis));
		
	}
	
	//录音
	public void record(){
		MyJavaPiano.controls.recordB.setText("停止录音");
		DeviceManage.isRecording = true;
    	try {
    		DeviceManage.sequence = new Sequence(Sequence.PPQ, 10);
		} catch (InvalidMidiDataException e) {
			e.printStackTrace();
		}
		
		DeviceManage.track = DeviceManage.sequence.createTrack();	//启动录音
		DeviceManage.startTime = System.currentTimeMillis();  	
    	
    	recordEventList.clear();
    	recordEventList.add(new RecordEvent(0,0,0,0));		//开始事件


    	//添加设置乐器事件
    	addRecordEvent(ShortMessage.PROGRAM_CHANGE,Key_Keyboard.LEFT,DeviceManage.program_left);
    	DeviceManage.addRecordEvent(ShortMessage.PROGRAM_CHANGE,DeviceManage.channel_index_left,DeviceManage.program_left,0);

    	addRecordEvent(ShortMessage.PROGRAM_CHANGE,Key_Keyboard.RIGHT,DeviceManage.program_right);
    	DeviceManage.addRecordEvent(ShortMessage.PROGRAM_CHANGE,DeviceManage.channel_index_right,DeviceManage.program_right,0);
    	
    	
        playB.setEnabled(false);
        saveB.setEnabled(false);
        
        jPiano.requestFocus();
	}
	
	
	
	public void stopRecord(){
		MyJavaPiano.controls.recordB.setText("录音");   	
		DeviceManage.isRecording = false;
		
        this.mjp_length = System.currentTimeMillis()-DeviceManage.startTime;
        
        recordEventList.add(new RecordEvent(-1,0,0,mjp_length));	//结束事件
        
        playB.setEnabled(true);
        saveB.setEnabled(true);
        
    	lab_musicName.setText("曲名："+musicName);
        lab_musicLength.setText("长度："+mjp_length/1000+"秒");
    	lab_musicAuthor.setText("演奏者："+musicAuthor);
    	lab_musicTime.setText("录音时间："+musicTime);
    	lab_musicDesc.setText("    "+musicDesc);
	}
	

	private Thread thread_play = null;
	 
    
	public void playRecord(int startTime){
		//stopPlay = false;
		isPlaying = true;
		
		playB.setText("停止");
		pauseB.setEnabled(true);
        
		if(thread_play!=null&&thread_play.isAlive()){
			thread_play.interrupt();			
		}

		thread_play = new Thread(new PlayRecordThread(startTime));
        thread_play.start();
        
        
        int time = (int)mjp_length/1000;
        sld_timer.setMaximum(time);
        
        
        int majorTick = 5;	//时间尺上面的刻度       
        
        if(time>240){
        	majorTick=60;
        }else if(time>180){
        	majorTick=30;
        }else if(time>120){
        	majorTick=10;
        }else{
        	majorTick=5;
        }
        
        sld_timer.setMajorTickSpacing(majorTick);
        int minTick = majorTick;
        sld_timer.setMinorTickSpacing(minTick);

        this.setVisible(true);
        this.playB.setEnabled(true);
        this.pauseB.setEnabled(true);
	}
	
	
	
	//播放mjp文件
	public void playRecord(String file_mjp){
		File file = new File(file_mjp);
		mjp_length = rListener.parseMJPFile(file);		//解析mjp录音文件
    	
    	lab_musicName.setText("曲名："+musicName);
        lab_musicLength.setText("长度："+mjp_length/1000+"秒");
    	lab_musicAuthor.setText("演奏者："+musicAuthor);
    	lab_musicTime.setText("录音时间："+musicTime);
    	lab_musicDesc.setText("    "+musicDesc);

    	speed=1.0f;
    	this.sld_speed.setValue(100);
    	
    	playRecord(0);
	}
	
	//private boolean stopPlay = false;
	public void stopPlayRecord(){
		//stopPlay = true;
		isPlaying = false;
		paused = false;
		pauseB.setEnabled(false);
		
		if(thread_play!=null&&thread_play.isAlive()){			
			try {
				thread_play.join();	//合并线程，等待该线程结束，然后再向下运行。
				
				sld_timer.setValue(0);
				cleanKeys();
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	//暂停播放
	public boolean paused = false;
	public void pausePlayRecord(){
		paused = true;
		isPlaying = false;
		this.playB.setEnabled(true);	
	}
	
	
	
	//恢复正在播放的键
	public void cleanKeys(){
		Iterator<Key_Keyboard> it_key_keyboard = Panel_Keyboard.keys_mapping.values().iterator();
		while(it_key_keyboard.hasNext()){
			Key_Keyboard key = it_key_keyboard.next();			
			if(key.isOn){
				key.off();
				key.setImageOff();
			}
		}
		
		//恢复下面的钢琴键盘
		for(Key_Piano key : Panel_Piano.pianoKeys){
			if(key.isDown){
				key.setImage_up();
			}		
		}

	}
	
    
    //播放
    class PlayRecordThread implements Runnable{

    	private int startTime;		//单位是毫秒
    	
    	public PlayRecordThread(int startTime){
    		this.startTime = startTime;
    	}
    	
		@Override
		public void run() {
        	
			cleanKeys();	//清理按下去的键
        	playFromStartTime();

        	//播放结束之后的操作：
        	playB.setText("播放");
        	pauseB.setEnabled(false);	
        	sld_timer.setValue(0);	
        	}
        	
		
		private void playFromStartTime(){

        	long time_pre = 0;	//上一个事件的时间
        	
        	for(int i=0;i<recordEventList.size();i++){
        		
        		if(!isPlaying){
        			break;
        		}       		
        		if(moveState){		//拖动就重新扫描       			
        			moveState = false;
        			return;
        		}
        		
        		if(paused){
        			try {
						Thread.sleep(500);
						i--;
						continue;
					} catch (InterruptedException e) {
						e.printStackTrace();
						return;		//被打断就结束
					}
        		}
        		
        		RecordEvent event = recordEventList.get(i);
        		
        		//System.out.println(event.getTime()/1000+":"+moveTo);
        		
        		if(event.getTime()<startTime){		//如果手动拖动滑块
        			//System.out.println(event.getTime()+"__"+startTime);
        			time_pre = startTime;
        			continue;
        		}

        		try {
        			if(event.getTime()>time_pre){
        				Thread.sleep((long) ((event.getTime()-time_pre)/speed));		//停顿时间
        			}
				} catch (InterruptedException e1) {
					e1.printStackTrace();
					return;		//被打断就结束
				}
        		
				if(event.getType()==ShortMessage.NOTE_ON){

    				Key_Keyboard key = Panel_Keyboard.keys_mapping.get(event.getValue());
    				if(key!=null){
    					key.on();
    					key.setImageOn();
    				}
        	
    				Panel_Piano.pianoKeys[key.getKeyNum()+DeviceManage.flat-21].setImage_down();
        			
//System.out.println(event.getTime()+"/"+mjp_length);					
					
					sld_timer.setValue((int)(sld_timer.getMaximum()*event.getTime()/mjp_length));
				
        			
        			
        		}
        		else if(event.getType()==ShortMessage.NOTE_OFF){
        			Key_Keyboard key = Panel_Keyboard.keys_mapping.get(event.getValue());
    				if(key!=null){
    					key.setImageOff();
    					key.off_delay();
    					Panel_Piano.pianoKeys[key.getKeyNum()+DeviceManage.flat-21].setImage_up();
    				}
        	
        		}
        		else if(event.getType()==ShortMessage.PROGRAM_CHANGE){
        			int program = event.getValue();
        			if(event.getLeftOrRight()==Key_Keyboard.LEFT){       				      	        	
        				DeviceManage.changeProgram_left2(program);     	        	
        			}else{      				
        				DeviceManage.changeProgram_right2(program);
        			}
        		}
        		
        		time_pre=event.getTime();
        	}
		}
		
    	
    }
    
    
   
    
}

