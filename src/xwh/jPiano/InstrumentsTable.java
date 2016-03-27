package xwh.jPiano;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

/**
 * 乐器列表
 * Table for 128 general MIDI melody instuments.
 */

class InstrumentsTable extends JPanel {
	private static final long serialVersionUID = 1L;


	private MyJavaPiano jPiano;
	
    private String columnNames[] = { 
            "钢琴", "敲击乐器", "风琴", "吉他", 
            "贝司类", "弦乐器", "合奏", "铜管", 
            "簧片", "吹管类", "前导乐类", "音色垫类",
            "效果类", "名族乐器", "敲击类", "音效类" };
         
    
    
    public static String instrumentNames[] = {
    		"钢琴", "亮音钢琴", "电钢琴", "酒吧钢琴", 
            "钢琴1", "钢琴2", "拨弦古钢琴", "击弦古钢琴",
            
            "钢片琴", "铝板琴", "八音盒", "铝板颤音琴",
            "立奏木琴", "木琴", "管钟琴", "杨琴",

    		"拉杆风琴", "敲击风琴", "摇滚风琴", "教堂风琴", 
            "簧风琴", "手风琴", "口琴", "探戈手风琴", 
            
            "尼龙弦吉他", "钢弦吉他", "爵士电吉他", "纯净音吉他",
            "弱音吉他", "过激吉他", "失真吉他", "吉他和音",

    		"贝司", "电贝司手弹", "电贝司拨片", "无品贝司", 
            "击弦贝司1", "击弦贝司2", "合成贝司1", "合成贝司2", 
            
            "小提琴", "中提琴", "大提琴", "低音提琴",
            "颤弓弦乐", "弹拨弦乐", "竖琴", "定音鼓",

    		"弦乐合奏1", "弦乐合奏2", "合成弦乐1", "合成弦乐2", 
            "合唱啊", "合唱喔", "合成人声", "交响乐强奏", 
            
            "小号", "长号", "大号", "弱音小号",
            "圆号", "铜管乐", "合成铜管乐1", "合成铜管乐2",

    		"高音萨克斯", "中音萨克斯", "次中音萨克斯", "低音萨克斯", 
            "双簧管", "英国号", "大管", "单簧管",
            
            "短笛", "长笛", "直笛", "排箫",
            "吹瓶声", "萨古哈奇笛", "口哨", "奥卡利那笛",

    		"方波", "锯齿波", "汽笛", "雪纺主奏", 
            "沙朗主奏", "人声", "五度合音", "低音引导", 
            
            "新时代", "温暖音", "合音", "合唱",
            "弹弓", "金属", "光辉", "刮风",

    		"雨声", "音轨", "水晶", "大气", 
            "明亮", "鬼怪", "回音", "科幻", 
            
            "西他琴", "班卓琴", "三弦", "科多琴",
            "卡林巴琴", "苏格兰风琴", "古提琴", "山奈琴",

    		"叮当琴", "摇摆舞铃", "钢鼓", "响木", 
            "太科鼓", "旋律鼓", "合成鼓", "钹", 
            
            "吉他擦弦", "呼吸", "海岸", "鸟鸣",
            "电话", "直升机", "鼓掌", "枪声"
    		
    };
    
    //乐器是否可以延音
    public static boolean isDelay[] = {
    	true,true,true,true,
    	true,true,true,true,
    	
    	true,true,true,true,
    	true,true,true,true,
    	
    	false,false,false,false,
    	false,false,false,false,

    	true,true,true,true,
    	false,false,false,false,
    	
    	true,true,true,true,
    	true,true,true,true,
    	
    	false,false,false,false,
    	false,false,false,false,
    	
    	false,false,false,false,
    	false,false,false,false,

    	false,false,false,false,
    	false,false,false,false,

    	false,false,false,false,
    	false,false,false,false,   	

    	false,false,false,false,
    	false,false,false,false,

    	false,false,false,false,
    	false,false,false,false,

    	false,false,false,false,
    	false,false,false,false,

    	false,false,false,false,
    	false,false,false,false,

    	false,false,false,false,
    	false,false,false,false,

    	false,false,false,false,
    	false,false,false,false,

    	false,false,false,false,
    	false,false,false,false
    };
    
    private int nRows = 8;
    private int nCols = columnNames.length; // just show 128 instruments

    private char hand;	//左右手标示 'L','R'
    public int row,col;
    
    private JTable table;
    
    public InstrumentsTable(MyJavaPiano jPiano,char left_right) {
    	this.jPiano = jPiano;
    	this.hand = left_right;
    	
        setLayout(new BorderLayout());

        TableModel dataModel = new AbstractTableModel() {	
			private static final long serialVersionUID = 1L;
			public int getColumnCount() { return nCols; }
            public int getRowCount() { return nRows;}
            public Object getValueAt(int r, int c) {
            	
                /*if (MyJavaPiano.instruments != null) {
                    //return MyJavaPiano.instruments[c*nRows+r].getName();
                	return instrumentNames[c*nRows+r];
                	
                } else {
                    return Integer.toString(c*nRows+r);
                }*/
            	return instrumentNames[c*nRows+r];
            }
            public String getColumnName(int c) { 
                return columnNames[c];
            }
            public Class<? extends Object> getColumnClass(int c) {
                return getValueAt(0, c).getClass();
            }
            public boolean isCellEditable(int r, int c) {return false;}
            public void setValueAt(Object obj, int r, int c) {}          
        };

        
        table = new JTable(dataModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        
        // Listener for row changes		更换乐器
        ListSelectionModel lsm = table.getSelectionModel();
        lsm.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
            	if(e.getValueIsAdjusting()){          		
	                ListSelectionModel sm = (ListSelectionModel) e.getSource();
	                if (!sm.isSelectionEmpty()) {
	                	row = sm.getMinSelectionIndex();
	                	//System.out.println(row);
	                	//preSound();
	                }
            	}
            }
        });

        // Listener for column changes
        lsm = table.getColumnModel().getSelectionModel();
        lsm.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
            	if(e.getValueIsAdjusting()){ 
	                ListSelectionModel sm = (ListSelectionModel) e.getSource();
	                if (!sm.isSelectionEmpty()) {
	                	col = sm.getMinSelectionIndex();
	                	//System.out.println(col);
	                	//preSound();
	                }
            	}

            }
        });

        table.setPreferredScrollableViewportSize(new Dimension(nCols*110, 200));
        table.setCellSelectionEnabled(true);
        table.setColumnSelectionAllowed(true);
        for (int i = 0; i < columnNames.length; i++) {
            TableColumn column = table.getColumn(columnNames[i]);
            column.setPreferredWidth(100);
        }
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    
        JScrollPane sp = new JScrollPane(table);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        add(sp);
        
        
        
        table.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e) {
				preSound();
			}      	
        });
    }
    
    

    public Dimension getPreferredSize() {
        return new Dimension(800,170);
    }
    public Dimension getMaximumSize() {
        return new Dimension(800,170);
    }
    
    
    //点击保存
    public void change(){
    	int program = col*nRows+row;
    	if(hand=='L'){  		
        	DeviceManage.changeProgram_left(program);
        	
        	if(!isDelay[program]){
        		MyJavaPiano.controls.combo_delay_left.setSelectedIndex(0);
        	}else{
        		MyJavaPiano.controls.combo_delay_left.setSelectedIndex(3);		//默认1秒
        	}
        	

			/*long millis = System.currentTimeMillis() - MyJavaPiano.startTime;
			RecordFrame.recordEventList.add(new RecordEvent(MyJavaPiano.PROGRAM,"left",DeviceManage.channel_program_left,millis));
*/
    	}else if(hand=='R'){
    		DeviceManage.changeProgram_right(program);
        	if(!isDelay[program]){
        		MyJavaPiano.controls.combo_delay_right.setSelectedIndex(0);
        	}else{
        		MyJavaPiano.controls.combo_delay_right.setSelectedIndex(3);
        	}
        	
			/*long millis = System.currentTimeMillis() - MyJavaPiano.startTime;
			RecordFrame.recordEventList.add(new RecordEvent(MyJavaPiano.PROGRAM,"right",MyJavaPiano.cc_right.program,millis));
*/
        }
    	
    }
    
    
    //预览音色
    public void preSound(){
    	int program = col*nRows+row;
    	if(hand=='L'){
    		if(!jPiano.recordFrame.isPlaying){
    			new Thread(new DelayThread('L')).start();
    		}else{
    			jPiano.recordFrame.cleanKeys();
    		}
    		DeviceManage.changeProgram_left(program);
    		
    	}else{
    		if(!jPiano.recordFrame.isPlaying){
    			new Thread(new DelayThread('R')).start();
    		}else{
    			jPiano.recordFrame.cleanKeys();
    		}
    		DeviceManage.changeProgram_right(program);
    	}
    }
    
    class DelayThread implements Runnable{

    	private char hand;
		public DelayThread(char hand){
    		this.hand = hand;
    	}
		@Override
		public void run() {
			
			if(hand=='L'){
				DeviceManage.nodeOn_left(60);
			}else{
				DeviceManage.nodeOn_right(60);
			}
			
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(hand=='L'){
				DeviceManage.nodeOff_left(60);
			}else{
				DeviceManage.nodeOff_right(60);
			}
		}
    	
    }
    
}




