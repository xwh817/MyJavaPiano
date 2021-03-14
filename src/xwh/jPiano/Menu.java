package xwh.jPiano;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import xwh.jPiano.util.ImgUtil;

public class Menu extends JMenuBar{
	
	private static final long serialVersionUID = 1L;

	private MyJavaPiano jPiano;
	
	private JMenu jMenu_file = null;

	private JMenuItem jMenuItem_openSetting = null;	
	private JMenuItem jMenuItem_openMjp = null;
	private JMenuItem jMenuItem_saveSetting = null;


	private JMenu jMenu_set = null;
	
	private JMenu jMenu_setLAF = null;
	private JMenuItem jMenuItem_lookAndFeel1 = null;
	private JMenuItem jMenuItem_lookAndFeel2 = null;
	private JMenuItem jMenuItem_lookAndFeel3 = null;
	
	private JMenu jMenu_Theme = null;

	private JMenu jMenu_view = null;
	private JCheckBoxMenuItem jMenuItem_view_controls = null;
	private JCheckBoxMenuItem jMenuItem_view_piano = null;
	private JCheckBoxMenuItem jMenuItem_view_keyboard = null;

	private JMenuItem jMenuItem_other = null;
	private JMenuItem jMenuItem_key = null;		//快捷键
	
	private JMenuItem jMenuItem_pr_instr = null;		//预设乐器
	
	//乐器
	private JMenu jMenu_instrument = null;
	private JMenuItem jMenuItem_instr_left = null;
	private JMenuItem jMenuItem_instr_right = null;
	private JDialog jDialog_instruments = null; 
	
		
	
	private JMenu jMenu_about = null;
	private JMenuItem jMenuItem_help = null;
	private JMenuItem jMenuItem_version = null;
	


	LookAndFeelListener menuListener = new LookAndFeelListener();
	ThemeListener themeListener = new ThemeListener();
	
	
	private Font menu_Font = new Font("",Font.BOLD,12);
	
	//获取图标
	private final String path = "icons/";

	
	private ImageIcon getIcon(String name){
		return new ImageIcon(ImgUtil.getImage((path+name)));
	}
	
	
	
	
	//构造函数
	public Menu(MyJavaPiano jPiano){
		this.jPiano = jPiano;
		this.add(getJMenu_file());
		this.add(getJMenu_set());
		this.add(getJMenu_instrument());
		this.add(getJMenu_about());
		
   		this.setThemeList(jPiano.guiProps.it_themes);
   		
   		
	}
	

	/**
	 * This method initializes jMenu_file	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getJMenu_file() {
		if (jMenu_file == null) {
			jMenu_file = new JMenu();
			jMenu_file.setText("文件");	
			jMenu_file.setIcon(getIcon("file.png"));		
			jMenu_file.setFont(menu_Font);  //设置字体
						
			jMenu_file.add(getJMenuItem_openMjp());

			jMenu_file.add(getJMenuItem_openSetting());
			jMenu_file.add(getJMenuItem_saveSetting());
			jMenu_file.addMouseListener(new MouseAdapter(){
				@Override
				public void mouseClicked(MouseEvent e) {
					jPiano.recordFrame.stopPlayRecord();	//点击文件的时候，把上一次播放的关掉。
				}				
			});
			

		}
		return jMenu_file;
	}
	
	
	public JMenuItem getJMenuItem_openMjp() {
		if (jMenuItem_openMjp == null) {
			jMenuItem_openMjp = new JMenuItem();
			jMenuItem_openMjp.setText("打开录音");	
			jMenuItem_openMjp.setIcon(getIcon("music1.png"));
			jMenuItem_openMjp.setFont(menu_Font);  //设置字体
			
			FileListener lis = new FileListener("openMjp",jPiano);
			
			jMenuItem_openMjp.addActionListener(lis);
			
		}
		return jMenuItem_openMjp;
	}

	/**
	 * This method initializes jMenuItem_open	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	public JMenuItem getJMenuItem_openSetting() {
		if (jMenuItem_openSetting == null) {
			jMenuItem_openSetting = new JMenuItem();
			jMenuItem_openSetting.setText("打开设置");
			jMenuItem_openSetting.setIcon(getIcon("setting.png"));
			jMenuItem_openSetting.setFont(menu_Font);  //设置字体
			
			FileListener lis = new FileListener("openConfig",jPiano);
			
			jMenuItem_openSetting.addActionListener(lis);
			
		}
		return jMenuItem_openSetting;
	}


	/**
	 * This method initializes jMenuItem_open	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	public JMenuItem getJMenuItem_saveSetting() {
		if (jMenuItem_saveSetting == null) {
			jMenuItem_saveSetting = new JMenuItem();
			jMenuItem_saveSetting.setText("保存设置");
			jMenuItem_saveSetting.setIcon(getIcon("setting_save.png"));
			jMenuItem_saveSetting.setFont(menu_Font);  //设置字体
			
			FileListener lis = new FileListener("saveConfig",jPiano);
			
			jMenuItem_saveSetting.addActionListener(lis);
			
		}
		return jMenuItem_saveSetting;
	}
	
	
	/**
	 * This method initializes jMenu_about	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	public JMenu getJMenu_instrument() {
		if (jMenu_instrument == null) {
			jMenu_instrument = new JMenu();
			jMenu_instrument.setText("乐器");
			jMenu_instrument.setIcon(getIcon("piano.png"));
			jMenu_instrument.add(getJMenuItem_instr_left());
			jMenu_instrument.add(getJMenuItem_instr_right());
			jMenu_instrument.setFont(menu_Font);
		}
		return jMenu_instrument;
	}

	/**
	 * This method initializes jMenuItem_about	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	public JMenuItem getJMenuItem_instr_left() {
		if (jMenuItem_instr_left == null) {
			jMenuItem_instr_left = new JMenuItem();
			jMenuItem_instr_left.setText("左手乐器");
			jMenuItem_instr_left.setIcon(getIcon("guitar.png"));
			jMenuItem_instr_left.setFont(menu_Font);
			jMenuItem_instr_left.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					getJDialog_instruments('L').setVisible(true);
				}
				
			});
		}
		return jMenuItem_instr_left;
	}

	/**
	 * This method initializes jMenuItem_version	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	public JMenuItem getJMenuItem_instr_right() {
		if (jMenuItem_instr_right == null) {
			jMenuItem_instr_right = new JMenuItem();
			jMenuItem_instr_right.setText("右手乐器");
			jMenuItem_instr_right.setIcon(getIcon("piano.png"));
			jMenuItem_instr_right.setFont(menu_Font);
			
			jMenuItem_instr_right.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					getJDialog_instruments('R').setVisible(true);
				}
				
			});
		}
		return jMenuItem_instr_right;
	}
	
	private InstrumentsTable instrTable;
	private InstrumentsTable instrTable_left;
	private InstrumentsTable instrTable_right;

	public JDialog getJDialog_instruments(char left_right) {
		if (jDialog_instruments == null) {
			jDialog_instruments = new JDialog();
			jDialog_instruments.setModal(true);
			
			instrTable_left = new InstrumentsTable(jPiano,'L');
			instrTable_right = new InstrumentsTable(jPiano,'R');
			
			JPanel panel_bt = new JPanel();
			jDialog_instruments.add(panel_bt,BorderLayout.SOUTH);
			
			JButton bt_ok = new JButton("确 定");
			
			bt_ok.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {					
					instrTable.change();
					jDialog_instruments.setVisible(false);
				}
				
			});			
			
			panel_bt.add(bt_ok);						
			
			jDialog_instruments.setSize(new Dimension(600, 300));
			jDialog_instruments.setLocation(jPiano.getX()+100,jPiano.getY()+50);
			jDialog_instruments.setResizable(false);
			jDialog_instruments.setName("dialog_instruments");
			jDialog_instruments.setFont(new Font("Dialog", Font.PLAIN, 14));
		}
		
		if(left_right=='L'){
			instrTable = instrTable_left;
			jDialog_instruments.add(instrTable_left,BorderLayout.CENTER);
			jDialog_instruments.remove(instrTable_right);			
			jDialog_instruments.setTitle("左手乐器选择");
			jDialog_instruments.setIconImage(ImgUtil.getImage(path+"guitar.png"));
		}else if(left_right=='R'){
			instrTable = instrTable_right;
			jDialog_instruments.add(instrTable_right,BorderLayout.CENTER);
			jDialog_instruments.remove(instrTable_left);			
			jDialog_instruments.setTitle("右手乐器选择");
			jDialog_instruments.setIconImage(ImgUtil.getImage(path+"piano.png"));			
		}
		
		return jDialog_instruments;
	}
	
	
	/**
	 * This method initializes jMenu_about	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getJMenu_about() {
		if (jMenu_about == null) {
			jMenu_about = new JMenu();
			jMenu_about.setText("帮助");
			jMenu_about.setIcon(getIcon("help.png"));
			jMenu_about.add(getJMenuItem_about());
			jMenu_about.add(getJMenuItem_version());
			jMenu_about.setFont(menu_Font);
		}
		return jMenu_about;
	}

	/**
	 * This method initializes jMenuItem_about	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItem_about() {
		if (jMenuItem_help == null) {
			jMenuItem_help = new JMenuItem();
			jMenuItem_help.setText("帮助");
			jMenuItem_help.setIcon(getIcon("keyboard.png"));
			jMenuItem_help.setFont(menu_Font);
			
			
			final JDialog dlg_help = new JDialog();
			final JEditorPane editorPane = new JEditorPane(); 
			dlg_help.setSize(800, 600);
			dlg_help.setIconImage(ImgUtil.getImage(path+"help.png"));
			dlg_help.setTitle("帮助说明");
			//AppClassLoader urlLoader = (AppClassLoader)MyJavaPiano.class.getClassLoader(); 
			//URL url = urlLoader.findResource("help/readme.html");
			editorPane.setEditable(false);     //请把editorPane设置为只读，不然显示就不整齐   
			URL urlRoot = getClass().getResource("/");
			try {
				editorPane.setPage(urlRoot.toString() + "help/readme.html");
			} catch (IOException e1) {
				e1.printStackTrace();
			}  
			
			JScrollPane scrollPane = new JScrollPane(editorPane);
			scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

			dlg_help.add(scrollPane,BorderLayout.CENTER);
			
			
			jMenuItem_help.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					
					dlg_help.setLocation(jPiano.getX()<100?0:jPiano.getX()-100, jPiano.getY()<100?0:jPiano.getY()-100);					
					dlg_help.setVisible(true);
				}
				
			});
		}
		return jMenuItem_help;
	}

	/**
	 * This method initializes jMenuItem_version	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	public JMenuItem getJMenuItem_version() {
		if (jMenuItem_version == null) {
			jMenuItem_version = new JMenuItem();
			jMenuItem_version.setText("关于");
			jMenuItem_version.setIcon(getIcon("tag_purple.png"));
			jMenuItem_version.setFont(menu_Font);
			jMenuItem_version.addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(null, "MyJavaPiano\n作者：肖文辉\nQQ:540311360", "MyJavaPiano_开源、免费的电子琴", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("/images/piano.png")));
				}
				
			});
		}
		return jMenuItem_version;
	}

	
	private JMenu getJMenu_set() {
		if (jMenu_set == null) {
			jMenu_set = new JMenu();
			jMenu_set.setText("设置");
			jMenu_set.setIcon(getIcon("setting_blue.png"));
			jMenu_set.setFont(menu_Font);
			jMenu_set.add(getJMenu_setLAF());
			jMenu_set.add(getJMenu_Theme());
			jMenu_set.add(getJMenu_view());
			jMenu_set.add(getJMenuItem_key());
			jMenu_set.add(getJMenuItem_pr_instr());
			jMenu_set.add(getJMenuItem_other());
			
		}
		return jMenu_set;
	}
	
	/**
	 * This method initializes jMenu_setLAF	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getJMenu_setLAF() {
		if (jMenu_setLAF == null) {
			jMenu_setLAF = new JMenu();
			jMenu_setLAF.setText("皮肤");
			jMenu_setLAF.setIcon(getIcon("Favorite.png"));
			jMenu_setLAF.setFont(menu_Font);
			
			
			jMenu_setLAF.add(JTattooLookAndFeel("fast"));
			jMenu_setLAF.add(JTattooLookAndFeel("graphite"));
			jMenu_setLAF.add(JTattooLookAndFeel("acryl"));
			jMenu_setLAF.add(JTattooLookAndFeel("aero"));
			jMenu_setLAF.add(JTattooLookAndFeel("bernstein"));
			jMenu_setLAF.add(JTattooLookAndFeel("aluminium"));
			jMenu_setLAF.add(JTattooLookAndFeel("mcwin"));
			jMenu_setLAF.add(JTattooLookAndFeel("mint"));
			jMenu_setLAF.add(JTattooLookAndFeel("hifi"));
			jMenu_setLAF.add(JTattooLookAndFeel("noire"));
			jMenu_setLAF.add(JTattooLookAndFeel("luna"));
			
			
		}
		return jMenu_setLAF;
	}
	public JMenu getJMenu_Theme() {
		if (jMenu_Theme == null) {
			jMenu_Theme = new JMenu();
			jMenu_Theme.setText("色调");
			jMenu_Theme.setFont(menu_Font);
			jMenu_Theme.setIcon(getIcon("Favorite.png"));
		}
		
		return jMenu_Theme;
	}
	
	@SuppressWarnings("unchecked")
	public void setThemeList(Iterator it_themes){
		
		jMenu_Theme.removeAll();
		while(it_themes.hasNext()){	
			String name = it_themes.next().toString();
			if(name.indexOf("-")==-1){
				jMenu_Theme.add(getTheme(name));
			}
	      }
	}	
	
	private JMenuItem getTheme(String name){

		JMenuItem jMenuItem_theme = new JMenuItem();
		jMenuItem_theme.setText(name);
		jMenuItem_theme.setFont(menu_Font);
		jMenuItem_theme.addActionListener(themeListener);

		return jMenuItem_theme;
		
	}

	

	/**
	 * This method initializes jMenuItem_lookAndFeel1	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	public JMenuItem getJMenuItem_lookAndFeel1() {
		if (jMenuItem_lookAndFeel1 == null) {
			jMenuItem_lookAndFeel1 = new JMenuItem();
			jMenuItem_lookAndFeel1.setText("java外观");
			jMenuItem_lookAndFeel1.setFont(menu_Font);
			jMenuItem_lookAndFeel1.addActionListener(menuListener);
		}
		return jMenuItem_lookAndFeel1;
	}


	/**
	 * This method initializes jMenuItem_lookAndFeel2	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	public JMenuItem getJMenuItem_lookAndFeel2() {
		if (jMenuItem_lookAndFeel2 == null) {
			jMenuItem_lookAndFeel2 = new JMenuItem();
			jMenuItem_lookAndFeel2.setText("其他外观");
			jMenuItem_lookAndFeel2.setFont(menu_Font);
			jMenuItem_lookAndFeel2.addActionListener(menuListener);
		}	
		return jMenuItem_lookAndFeel2;
	}
	
	private JMenuItem JTattooLookAndFeel(String name){

		JMenuItem jMenuItem_jTattoolookAndFeel = new JMenuItem();
		jMenuItem_jTattoolookAndFeel.setText(name);
		jMenuItem_jTattoolookAndFeel.setFont(menu_Font);
		jMenuItem_jTattoolookAndFeel.addActionListener(menuListener);

		return jMenuItem_jTattoolookAndFeel;
		
	}


	/**
	 * This method initializes jMenuItem_lookAndFeel3	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	public JMenuItem getJMenuItem_lookAndFeel3() {
		if (jMenuItem_lookAndFeel3 == null) {
			jMenuItem_lookAndFeel3 = new JMenuItem();
			jMenuItem_lookAndFeel3.setText("windows外观");
			jMenuItem_lookAndFeel3.setFont(menu_Font);
			jMenuItem_lookAndFeel3.addActionListener(menuListener);
		}
		return jMenuItem_lookAndFeel3;
	}

	private JMenuItem getJMenuItem_key() {
		if (jMenuItem_key == null) {
			jMenuItem_key = new JMenuItem();
			jMenuItem_key.setText("快捷键");
			jMenuItem_key.setIcon(getIcon("keyboard.png"));
			jMenuItem_key.setFont(menu_Font);
			
			final HotKeyDialog dialog = new HotKeyDialog();
			jMenuItem_key.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					dialog.setLocation(jPiano.getX()+200, jPiano.getY()+50);
					dialog.setVisible(true);
				}
				
			});
		}
		return jMenuItem_key;
	}
	
	private JMenuItem getJMenuItem_pr_instr() {
		if (jMenuItem_pr_instr == null) {
			jMenuItem_pr_instr = new JMenuItem();
			jMenuItem_pr_instr.setText("预设乐器");
			jMenuItem_pr_instr.setIcon(getIcon("guitar.png"));
			jMenuItem_pr_instr.setFont(menu_Font);
			
			final PrInstrDialog dialog = new PrInstrDialog();
			
			jMenuItem_pr_instr.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					dialog.setLocation(jPiano.getX()+200, jPiano.getY()+50);
					dialog.setVisible(true);
				}
				
			});
		}
		return jMenuItem_pr_instr;
	}
	
	private JMenuItem getJMenuItem_other() {
		if (jMenuItem_other == null) {
			jMenuItem_other = new JMenuItem();
			jMenuItem_other.setText("其他");
			jMenuItem_other.setIcon(getIcon("setting2.png"));
			jMenuItem_other.setFont(menu_Font);
		}
		return jMenuItem_other;
	}
	
	
	private JMenu getJMenu_view() {
		if (jMenu_view == null) {
			jMenu_view = new JMenu();
			jMenu_view.setText("显示");
			jMenu_view.setIcon(getIcon("view.png"));
			jMenu_view.setFont(menu_Font);
			
			jMenu_view.add(getJMenuItem_view_controls());
			jMenu_view.add(getJMenuItem_view_piano());
			jMenu_view.add(getJMenuItem_view_keyboard());
			
		}
		return jMenu_view;
	}
	
	private JMenuItem getJMenuItem_view_controls() {
		if (jMenuItem_view_controls == null) {
			jMenuItem_view_controls = new JCheckBoxMenuItem();
			jMenuItem_view_controls.setText("控制面板");
			jMenuItem_view_controls.setFont(menu_Font);
			jMenuItem_view_controls.setSelected(true);
			jMenuItem_view_controls.addActionListener(getViewListener());
		}
		return jMenuItem_view_controls;
	}

	private JMenuItem getJMenuItem_view_piano() {
		if (jMenuItem_view_piano == null) {
			jMenuItem_view_piano = new JCheckBoxMenuItem();
			jMenuItem_view_piano.setText("钢琴键盘");
			jMenuItem_view_piano.setFont(menu_Font);
			jMenuItem_view_piano.setSelected(true);
			
			jMenuItem_view_piano.addActionListener(getViewListener());
		}
		return jMenuItem_view_piano;
	}
	
	private JMenuItem getJMenuItem_view_keyboard() {
		if (jMenuItem_view_keyboard == null) {
			jMenuItem_view_keyboard = new JCheckBoxMenuItem();
			jMenuItem_view_keyboard.setText("电脑键盘");
			jMenuItem_view_keyboard.setFont(menu_Font);
			jMenuItem_view_keyboard.setSelected(true);
			
			jMenuItem_view_keyboard.addActionListener(getViewListener());
		}
		return jMenuItem_view_keyboard;
	}
	

	//外观事件的监听和相应。
	class LookAndFeelListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			try{							
				if(e.getSource().equals(getJMenuItem_lookAndFeel1())){		//java外观		
					javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
				}else if(e.getSource().equals(getJMenuItem_lookAndFeel2())){	//系统外观		
					javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
				}else if(e.getSource().equals(getJMenuItem_lookAndFeel3())){	//windows外观			
					javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
				}else{
					JMenuItem item = (JMenuItem) e.getSource();
					String name = item.getText();
					String name2 = "";
					if(name.equals("hifi")){
						name2="HiFi";
					}else if(name.equals("mcwin")){
						name2="McWin";
					}else{
						name2 = name.substring(0, 1).toUpperCase()+name.substring(1);
					}
					
					
					
					javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf."+name+"."+name2+"LookAndFeel");										
					jPiano.guiProps.updateLookAndFeel("com.jtattoo.plaf."+name+"."+name2+"LookAndFeel");
				
				
				}
				
				jPiano.getRootPane().updateUI();
				
				SwingUtilities.updateComponentTreeUI(jPiano);
				
			}catch(Exception e1) {
				e1.printStackTrace();
			}
			
		}
		
		  
		  
	  }
	
	
	//更新皮肤事件的监听和相应。
	class ThemeListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			JMenuItem item = (JMenuItem) e.getSource();
			String name = item.getText();
			
			jPiano.guiProps.updateTheme(name);
			
		}
		
	}
	
	
	private ViewListener viewListener = null;
	private ViewListener getViewListener(){
		if(viewListener==null){
			viewListener = new ViewListener();
		}
		return viewListener;
	}
	
	//显示或隐藏面板的监听和相应。
	class ViewListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
					

			JCheckBoxMenuItem item = (JCheckBoxMenuItem) e.getSource();
			
			if(item.equals(getJMenuItem_view_controls())){
				
				if(MyJavaPiano.controls.isVisible()){
					item.setSelected(false);
					MyJavaPiano.controls.setVisible(false);
					jPiano.setSize(jPiano.getWidth(),jPiano.getHeight()-MyJavaPiano.controls.getHeight());
				}else{
					item.setSelected(true);
					MyJavaPiano.controls.setVisible(true);
					jPiano.setSize(jPiano.getWidth(),jPiano.getHeight()+MyJavaPiano.controls.getHeight());
				}
				
			}else if(item.equals(getJMenuItem_view_piano())){		
				if(jPiano.piano.isVisible()){
					item.setSelected(false);
					jPiano.piano.setVisible(false);
					jPiano.setSize(jPiano.getWidth(),jPiano.getHeight()-jPiano.piano.getHeight());
				}else{
					item.setSelected(true);
					jPiano.piano.setVisible(true);
					jPiano.setSize(jPiano.getWidth(),jPiano.getHeight()+jPiano.piano.getHeight());
				}
			}else if(item.equals(getJMenuItem_view_keyboard())){		
				if(jPiano.keyboard.isVisible()){
					item.setSelected(false);
					jPiano.keyboard.setVisible(false);
					jPiano.setSize(jPiano.getWidth(),jPiano.getHeight()-jPiano.keyboard.getHeight());
				}else{
					item.setSelected(true);
					jPiano.keyboard.setVisible(true);
					jPiano.setSize(jPiano.getWidth(),jPiano.getHeight()+jPiano.keyboard.getHeight());
				}
			}
			
		}
		  
	  }
	
	
	//预设乐器
	class PrInstrDialog extends JDialog{
		private static final long serialVersionUID = 1L;
		
		
		public PrInstrDialog() {
			this.setSize(400, 320);
			this.setTitle("预设乐器");
			this.setModal(true);
			JPanel p = new JPanel();
			
			p.setBorder(new TitledBorder("选择右手乐器"));

			JPanel p1 = getInstrSet("乐器1：", "instr_1_index","F1");
			JPanel p2 = getInstrSet("乐器2：", "instr_2_index","F2");
			JPanel p3 = getInstrSet("乐器3：", "instr_3_index","F3");
			JPanel p4 = getInstrSet("乐器4：", "instr_4_index","F4");
			
			p.add(p1);
			p.add(p2);
			p.add(p3);
			p.add(p4);
			

			JButton bt_ok = new JButton("确 定");
			bt_ok.setSize(new Dimension(100,30));		
			bt_ok.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					PrInstrDialog.this.setVisible(false);
				}			
			});
			
			p.add(bt_ok);
			
			this.add(p);
			
		}
		

		private JPanel getInstrSet(String title,final String index,String key){
			JPanel p_instr = new JPanel();
			p_instr.setPreferredSize(new Dimension(350, 40));
			p_instr.setBorder(LineBorder.createBlackLineBorder());

			final JLabel lab_key = new JLabel("    快捷键："+key);
		
			final JLabel instr = new JLabel(title);
			JComboBox box = new JComboBox();
			box.setPreferredSize(new Dimension(150,25));
			for(int i=0;i<InstrumentsTable.instrumentNames.length;i++){
				box.addItem(InstrumentsTable.instrumentNames[i]);
			}
			box.setSelectedIndex(Integer.parseInt(ConfigManage.initial_props.get(index)));
			
			box.addItemListener(new ItemListener(){
				@Override
				public void itemStateChanged(ItemEvent e) {
					if(e.getStateChange() == ItemEvent.SELECTED){	//以免itemStateChanged执行两次。
						JComboBox combo = (JComboBox) e.getSource();
						int instr_index = combo.getSelectedIndex();
						
						ConfigManage.initial_props.put(index, instr_index+"");
						
					}
				}
			});			
			
			p_instr.add(instr);
			p_instr.add(box);
			p_instr.add(lab_key);
			
			return p_instr;
		}
		
		
	}

	
	
	
	
}



