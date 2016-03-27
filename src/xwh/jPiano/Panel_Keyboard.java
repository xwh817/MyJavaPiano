package xwh.jPiano;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Panel_Keyboard extends JPanel{
	private static final long serialVersionUID = 1L;


	final int kbw = 830, kbh = 235;	//键盘的大小

	private MyJavaPiano jPiano;
	
	
	//左右手键盘	<keyCode,Keyboard_Key>,通过keyCode和某个Keyboard_Key映射
	public static Map<Integer,Key_Keyboard> keys_mapping = new HashMap<Integer,Key_Keyboard>();

	
	public Panel_Keyboard(MyJavaPiano jPiano){
		this.jPiano = jPiano;
		this.setPreferredSize(new Dimension(kbw, kbh));
		this.setLayout(null);
		this.setBorder(new LineBorder(Color.black,2));
		
		initial();	//初始化keys_mapping
		
		config();	//从配置文件中取出布局，将keys_mapping又刷新一次。
		
	}
	
	
	
	//导入配置信息，将按键与音符keyNum对应起来。
	private void config(){		
		//从配置文件中给keyLayout赋值。
		ConfigManage.parseConfig_url("config/DefaultSetting.xml");
	
	}
	
	//生成左右所有的按键
	private void initial(){

		Dimension size1 = new Dimension(32,30);

		int p_x = 10;
		int p_y = 5;
		int d = 35;	//间距
		
		Key_Keyboard[] keys = {
			new Key_Keyboard(0,"Esc",true,p_x,p_y,size1,0),
			
			new Key_Keyboard(0,"F1",true,p_x+=(d+25),p_y,size1,0),
			new Key_Keyboard(0,"F2",true,p_x+=d,p_y,size1,0),
			new Key_Keyboard(0,"F3",true,p_x+=d,p_y,size1,0),
			new Key_Keyboard(0,"F4",true,p_x+=d,p_y,size1,0),
			new Key_Keyboard(0,"F5",true,p_x+=(d+23),p_y,size1,0),
			new Key_Keyboard(0,"F6",true,p_x+=d,p_y,size1,0),
			new Key_Keyboard(0,"F7",true,p_x+=d,p_y,size1,0),
			new Key_Keyboard(0,"F8",true,p_x+=d,p_y,size1,0),
			new Key_Keyboard(0,"F9",true,p_x+=(d+23),p_y,size1,0),
			new Key_Keyboard(0,"F10",true,p_x+=d,p_y,size1,0),
			new Key_Keyboard(0,"F11",true,p_x+=d,p_y,size1,0),
			new Key_Keyboard(0,"F12",true,p_x+=d,p_y,size1,0),
		};

		for(int i=0;i<keys.length;i++){
			this.add(keys[i]);
		}
		
		p_x = 10;
		p_y = 50;		
		keys_mapping.put(192, new Key_Keyboard(192,"~",true,p_x,p_y,size1,Key_Keyboard.LEFT));
		keys_mapping.put(49, new Key_Keyboard(49,"1",false,p_x+=d,p_y,size1,Key_Keyboard.LEFT));
		keys_mapping.put(50, new Key_Keyboard(50,"2",false,p_x+=d,p_y,size1,Key_Keyboard.LEFT));
		keys_mapping.put(51, new Key_Keyboard(51,"3",false,p_x+=d,p_y,size1,Key_Keyboard.LEFT));
		keys_mapping.put(52, new Key_Keyboard(52,"4",false,p_x+=d,p_y,size1,Key_Keyboard.LEFT));
		keys_mapping.put(53, new Key_Keyboard(53,"5",false,p_x+=d,p_y,size1,Key_Keyboard.LEFT));
		keys_mapping.put(54, new Key_Keyboard(54,"6",false,p_x+=d,p_y,size1,Key_Keyboard.LEFT));
		keys_mapping.put(55, new Key_Keyboard(55,"7",false,p_x+=d,p_y,size1,Key_Keyboard.LEFT));
		keys_mapping.put(56, new Key_Keyboard(56,"8",false,p_x+=d,p_y,size1,Key_Keyboard.LEFT));
		keys_mapping.put(57, new Key_Keyboard(57,"9",false,p_x+=d,p_y,size1,Key_Keyboard.LEFT));
		keys_mapping.put(48, new Key_Keyboard(48,"0",false,p_x+=d,p_y,size1,Key_Keyboard.LEFT));
		keys_mapping.put(45, new Key_Keyboard(45,"-",false,p_x+=d,p_y,size1,Key_Keyboard.LEFT));
		keys_mapping.put(61, new Key_Keyboard(61,"+",false,p_x+=d,p_y,size1,Key_Keyboard.LEFT));
		keys_mapping.put(8, new Key_Keyboard(8,"Back",true,p_x+=d,p_y,new Dimension(68,30),Key_Keyboard.LEFT));
	
		
		p_y=85;
		p_x=10;
		keys_mapping.put(-Key_Keyboard.LEFT, new Key_Keyboard(-Key_Keyboard.LEFT,"Tab",true,p_x,p_y,new Dimension(48,30),Key_Keyboard.LEFT));	p_x=63;
		keys_mapping.put(81, new Key_Keyboard(81,"Q",false,p_x,p_y,size1,Key_Keyboard.LEFT));
		keys_mapping.put(87, new Key_Keyboard(87,"W",false,p_x+=d,p_y,size1,Key_Keyboard.LEFT));
		keys_mapping.put(69, new Key_Keyboard(69,"E",false,p_x+=d,p_y,size1,Key_Keyboard.LEFT));
		keys_mapping.put(82, new Key_Keyboard(82,"R",false,p_x+=d,p_y,size1,Key_Keyboard.LEFT));
		keys_mapping.put(84, new Key_Keyboard(84,"T",false,p_x+=d,p_y,size1,Key_Keyboard.LEFT));
		keys_mapping.put(89, new Key_Keyboard(89,"Y",false,p_x+=d,p_y,size1,Key_Keyboard.LEFT));
		keys_mapping.put(85, new Key_Keyboard(85,"U",false,p_x+=d,p_y,size1,Key_Keyboard.LEFT));
		keys_mapping.put(73, new Key_Keyboard(73,"I",false,p_x+=d,p_y,size1,Key_Keyboard.LEFT));
		keys_mapping.put(79, new Key_Keyboard(79,"O",false,p_x+=d,p_y,size1,Key_Keyboard.LEFT));
		keys_mapping.put(80, new Key_Keyboard(80,"P",false,p_x+=d,p_y,size1,Key_Keyboard.LEFT));
		keys_mapping.put(91, new Key_Keyboard(91,"{",false,p_x+=d,p_y,size1,Key_Keyboard.LEFT));
		keys_mapping.put(93, new Key_Keyboard(93,"}",false,p_x+=d,p_y,size1,Key_Keyboard.LEFT));
		Key_Keyboard  enter1 = new Key_Keyboard(-2,"enter1",true,p_x+=d,p_y,new Dimension(50,30),Key_Keyboard.LEFT);
		enter1.setBorder(null);
		keys_mapping.put(10, enter1);
		
		
		p_y=120;
		p_x=10;
		keys_mapping.put(20, new Key_Keyboard(20,"Caps",true,p_x,p_y,new Dimension(60,30),Key_Keyboard.LEFT));	p_x=75;
		
		keys_mapping.put(65, new Key_Keyboard(65,"A",false,p_x,p_y,size1,Key_Keyboard.LEFT));
		keys_mapping.put(83, new Key_Keyboard(83,"S",false,p_x+=d,p_y,size1,Key_Keyboard.LEFT));
		keys_mapping.put(68, new Key_Keyboard(68,"D",false,p_x+=d,p_y,size1,Key_Keyboard.LEFT));
		keys_mapping.put(70, new Key_Keyboard(70,"F",false,p_x+=d,p_y,size1,Key_Keyboard.LEFT));
		keys_mapping.put(71, new Key_Keyboard(71,"G",false,p_x+=d,p_y,size1,Key_Keyboard.LEFT));
		keys_mapping.put(72, new Key_Keyboard(72,"H",false,p_x+=d,p_y,size1,Key_Keyboard.LEFT));
		keys_mapping.put(74, new Key_Keyboard(74,"J",false,p_x+=d,p_y,size1,Key_Keyboard.LEFT));
		keys_mapping.put(75, new Key_Keyboard(75,"K",false,p_x+=d,p_y,size1,Key_Keyboard.LEFT));
		keys_mapping.put(76, new Key_Keyboard(76,"L",false,p_x+=d,p_y,size1,Key_Keyboard.LEFT));
		keys_mapping.put(59, new Key_Keyboard(59,":",false,p_x+=d,p_y,size1,Key_Keyboard.LEFT));
		keys_mapping.put(222, new Key_Keyboard(222,"\"",false,p_x+=d,p_y,size1,Key_Keyboard.LEFT));
		keys_mapping.put(92, new Key_Keyboard(92,"\\",false,p_x+=d,p_y,size1,Key_Keyboard.LEFT));
		Key_Keyboard  enter2 = new Key_Keyboard(-2,"enter2",true,p_x+=d,p_y-6,new Dimension(38,36),Key_Keyboard.LEFT);
		enter2.setBorder(null);	
		keys_mapping.put(-2, enter2);
		
		p_y=155;
		p_x=10;
		keys_mapping.put(16, new Key_Keyboard(16,"Shift",true,p_x,p_y,new Dimension(80,30),Key_Keyboard.LEFT));	p_x=95;
		
		keys_mapping.put(90, new Key_Keyboard(90,"Z",false,p_x,p_y,size1,Key_Keyboard.LEFT));
		keys_mapping.put(88, new Key_Keyboard(88,"X",false,p_x+=d,p_y,size1,Key_Keyboard.LEFT));
		keys_mapping.put(67, new Key_Keyboard(67,"C",false,p_x+=d,p_y,size1,Key_Keyboard.LEFT));
		keys_mapping.put(86, new Key_Keyboard(86,"V",false,p_x+=d,p_y,size1,Key_Keyboard.LEFT));
		keys_mapping.put(66, new Key_Keyboard(66,"B",false,p_x+=d,p_y,size1,Key_Keyboard.LEFT));
		keys_mapping.put(78, new Key_Keyboard(78,"N",false,p_x+=d,p_y,size1,Key_Keyboard.LEFT));
		keys_mapping.put(77, new Key_Keyboard(77,"M",false,p_x+=d,p_y,size1,Key_Keyboard.LEFT));
		keys_mapping.put(44, new Key_Keyboard(44,"<",false,p_x+=d,p_y,size1,Key_Keyboard.LEFT));
		keys_mapping.put(46, new Key_Keyboard(46,">",false,p_x+=d,p_y,size1,Key_Keyboard.LEFT));
		keys_mapping.put(47, new Key_Keyboard(47,"?",false,p_x+=d,p_y,size1,Key_Keyboard.LEFT));
		
		keys_mapping.put(-16, new Key_Keyboard(-16,"Shift",true,p_x+=d,p_y,new Dimension(88,30),Key_Keyboard.LEFT));
		
		
		p_y=190;
		p_x=10;
		Dimension size2 = new Dimension(45,30);
		keys_mapping.put(17, new Key_Keyboard(17,"Ctrl",true,p_x,p_y,size2,Key_Keyboard.LEFT));
		keys_mapping.put(524, new Key_Keyboard(524,"Win",true,p_x+=49,p_y,size2,Key_Keyboard.LEFT));
		keys_mapping.put(18, new Key_Keyboard(18,"Alt",true,p_x+=49,p_y,size2,Key_Keyboard.LEFT));
		keys_mapping.put(32, new Key_Keyboard(32,"Space",true,p_x+=49,p_y,new Dimension(218,30),Key_Keyboard.LEFT));
		
		keys_mapping.put(-18, new Key_Keyboard(-18,"Alt",true,p_x+=225,p_y,size2,Key_Keyboard.LEFT));
		keys_mapping.put(-524, new Key_Keyboard(-524,"Win",true,p_x+=49,p_y,size2,Key_Keyboard.LEFT));
		keys_mapping.put(-17, new Key_Keyboard(-17,"Ctrl",true,p_x+=49,p_y,new Dimension(53,30),Key_Keyboard.LEFT));

		
		Iterator<Key_Keyboard> it = keys_mapping.values().iterator();
		while(it.hasNext()){
			this.add(it.next());
		}
		
		
		
		
		//右手键盘。。。
		p_x = 555;
		p_y = 5;
		d=35;
		
		keys_mapping.put(154, new Key_Keyboard(154,"Print",true,p_x,p_y,size1,Key_Keyboard.RIGHT));
		keys_mapping.put(145, new Key_Keyboard(145,"Scrol",true,p_x+=d,p_y,size1,Key_Keyboard.RIGHT));
		keys_mapping.put(19, new Key_Keyboard(19,"Paus",true,p_x+=d,p_y,size1,Key_Keyboard.RIGHT));
		
		p_x = 555;
		p_y = 50;
		
		keys_mapping.put(155, new Key_Keyboard(155,"Inst",false,p_x,p_y,size1,Key_Keyboard.RIGHT));
		keys_mapping.put(36, new Key_Keyboard(36,"Home",false,p_x+=d,p_y,size1,Key_Keyboard.RIGHT));
		keys_mapping.put(33, new Key_Keyboard(33,"PgUp",false,p_x+=d,p_y,size1,Key_Keyboard.RIGHT));
		
		p_x = 555;
		p_y = 85;
		
		keys_mapping.put(127, new Key_Keyboard(127,"Del",false,p_x,p_y,size1,Key_Keyboard.RIGHT));
		keys_mapping.put(35, new Key_Keyboard(35,"End",false,p_x+=d,p_y,size1,Key_Keyboard.RIGHT));
		keys_mapping.put(34, new Key_Keyboard(34,"PgDn",false,p_x+=d,p_y,size1,Key_Keyboard.RIGHT));
		
		p_x = 590;
		p_y = 155;
		keys_mapping.put(38, new Key_Keyboard(38,"↑",false,p_x,p_y,size1,Key_Keyboard.RIGHT));
		
		p_x = 555;
		p_y = 190;
		keys_mapping.put(37, new Key_Keyboard(37,"←",false,p_x,p_y,size1,Key_Keyboard.RIGHT));
		keys_mapping.put(40, new Key_Keyboard(40,"↓",false,p_x+=d,p_y,size1,Key_Keyboard.RIGHT));
		keys_mapping.put(39, new Key_Keyboard(39,"→",false,p_x+=d,p_y,size1,Key_Keyboard.RIGHT));
		
		
		
		
		p_x = 678;
		p_y = 50;
		keys_mapping.put(144, new Key_Keyboard(144,"NLock",false,p_x,p_y,size1,Key_Keyboard.RIGHT));
		keys_mapping.put(111, new Key_Keyboard(111,"/",false,p_x+=d,p_y,size1,Key_Keyboard.RIGHT));
		keys_mapping.put(106, new Key_Keyboard(106,"*",false,p_x+=d,p_y,size1,Key_Keyboard.RIGHT));
		keys_mapping.put(109, new Key_Keyboard(109,"-",false,p_x+=d,p_y,size1,Key_Keyboard.RIGHT));
		
		p_x = 678;
		p_y = 85;
		keys_mapping.put(103, new Key_Keyboard(103,"7",false,p_x,p_y,size1,Key_Keyboard.RIGHT));
		keys_mapping.put(104, new Key_Keyboard(104,"8",false,p_x+=d,p_y,size1,Key_Keyboard.RIGHT));
		keys_mapping.put(105, new Key_Keyboard(105,"9",false,p_x+=d,p_y,size1,Key_Keyboard.RIGHT));
		keys_mapping.put(107, new Key_Keyboard(107,"+",false,p_x+=d,p_y,new Dimension(32,65),Key_Keyboard.RIGHT));
		
		p_x = 678;
		p_y = 120;
		keys_mapping.put(100, new Key_Keyboard(100,"4",false,p_x,p_y,size1,Key_Keyboard.RIGHT));
		keys_mapping.put(101, new Key_Keyboard(101,"5",false,p_x+=d,p_y,size1,Key_Keyboard.RIGHT));
		keys_mapping.put(102, new Key_Keyboard(102,"6",false,p_x+=d,p_y,size1,Key_Keyboard.RIGHT));
		
		p_x = 678;
		p_y = 155;
		keys_mapping.put(97, new Key_Keyboard(97,"1",false,p_x,p_y,size1,Key_Keyboard.RIGHT));
		keys_mapping.put(98, new Key_Keyboard(98,"2",false,p_x+=d,p_y,size1,Key_Keyboard.RIGHT));
		keys_mapping.put(99, new Key_Keyboard(99,"3",false,p_x+=d,p_y,size1,Key_Keyboard.RIGHT));
		keys_mapping.put(10, new Key_Keyboard(10,"Enter",false,p_x+=d,p_y,new Dimension(32,65),Key_Keyboard.RIGHT));
		
		p_x = 678;
		p_y = 190;
		keys_mapping.put(96, new Key_Keyboard(96,"0",false,p_x,p_y,new Dimension(66,30),Key_Keyboard.RIGHT));
		keys_mapping.put(110, new Key_Keyboard(110,".",false,p_x+=70,p_y,size1,Key_Keyboard.RIGHT));
		
		
		//按键的鼠标事件监听器。
		MouseListener mouseListner = new KeyMouseListener(jPiano);
		
		
		Iterator<Key_Keyboard> it_right = keys_mapping.values().iterator();
		while(it_right.hasNext()){
			Key_Keyboard key = it_right.next();
			if(!key.isDisable()){
				key.addMouseListener(mouseListner);
			}
			this.add(key);
		}
		
	}

	

	
}
