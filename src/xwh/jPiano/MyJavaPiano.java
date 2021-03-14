package xwh.jPiano;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.UIManager;

import xwh.jPiano.util.ImgUtil;

public class MyJavaPiano extends JFrame{
	private static final long serialVersionUID = 1L;
	
	
	public static final float version=3.0f;	//软件版本号,保留1位的小数
	
	//窗口大小（最好为偶数，以免录屏软件出现提示信息。）
	public static final int w = 850;
	public static final int h = 486;
	
	
	public static Win_KeySetting win_KeySet;	//设置键弹出窗口
	public static Panel_Controls controls;
	public Menu menuBar;
	public RecordFrame recordFrame;
	public Panel_Keyboard keyboard;
	public Panel_Piano piano;
	
	
	//界面主题管理
	public GUIProperties guiProps;
	static{
		try {			
			 Properties props = new Properties();	            
	         props.put("logoString", "MyJavaPiano");
	         com.jtattoo.plaf.mcwin.McWinLookAndFeel.setCurrentTheme(props);
	         UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
			 
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}


	public static void main(String[] args) {
		MyJavaPiano jPiano = new MyJavaPiano();
		jPiano.setInitial_props();	//初始化参数
		DeviceManage.initial();		//初始化device
		jPiano.showView();		//显示界面
	}
	
	
	/**
	 * 获取初始配置文件
	 */
	private void setInitial_props(){

		ConfigManage.setInitial_props();	//获取配置
		
		guiProps = new GUIProperties(this);
		
		String theme = ConfigManage.initial_props.get("theme");
		String lookAndFeel = ConfigManage.initial_props.get("lookAndFeel");

		if(!lookAndFeel.equals("")){
			guiProps.updateLookAndFeel(lookAndFeel);
		}
		if(!theme.equals("")){
			guiProps.updateTheme(theme);
		}


	}
	
	
	/**
	 * 显示视图
	 */
	private void showView(){
		
		//设置窗口参数
		this.setTitle("MyJavaPiano_"+version);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        
        this.setLocation(screenSize.width/2 - w/2, screenSize.height/2 - h/2+50);
        this.setSize(w, h);
        this.setIconImage(ImgUtil.getImage("icons/music.png"));
        this.setVisible(true);
        this.setResizable(false);
        setLayout(new FlowLayout());
        
        

		//添加界面组件
        keyboard = new Panel_Keyboard(this);
        piano = new Panel_Piano();        
        controls = new Panel_Controls(this);
        menuBar = new Menu(this);	//菜单
        recordFrame = new RecordFrame(this);	//录音框
        win_KeySet = new Win_KeySetting();	//键设置对话框
        
        this.add(controls);
        this.add(keyboard); 
        this.add(piano);
        this.setJMenuBar(menuBar);
        
        this.validate();

        
        //添加键盘监听器。
        this.addKeyListener(new PianoKeyListener());
        this.addKeyListener(new SpecialKeyListener());
        
        //窗口事件监听
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
            	DeviceManage.close();
            	ConfigManage.saveInitial_props();		//保存初始化信息
            	
            	//让NumLock恢复。    			
    			if(!Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_NUM_LOCK)){
    				Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_NUM_LOCK,true);
    			}
        		System.exit(0);
            }
            
        });
        
        
		
	}
	

}
