package xwh.jPiano;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import xwh.jPiano.util.ImgUtil;

/**
 * A collection of MIDI controllers.
 * 控制面板
 */
class Panel_Controls extends JPanel{
	private static final long serialVersionUID = 1L;	
	
	public JButton recordB;

    MyJavaPiano jPiano = null;
  
    //调
    public JComboBox combo_flat;
    
    //左右8度
    private JLabel jlab_add_left;
    public JLabel jlab_left;
    private JLabel jlab_sub_left;
    private JLabel jlab_add_right;
    public JLabel jlab_right;
    private JLabel jlab_sub_right;
    
    
    //延音
    public JComboBox combo_delay_left;
    public JComboBox combo_delay_right;

    //左右设备
    public JComboBox combo_device_left;
    public JComboBox combo_device_right;
    
    //左右力度
    public JSlider veloS_left,veloS_right;///, presS, revbS	bendS, 
    

    //调值下拉列表
    private void setCombo_flat(){
    		combo_flat = new JComboBox();       
            combo_flat.setPreferredSize(new Dimension(50,25));
            combo_flat.setMaximumSize(new Dimension(50,25));
                   
            combo_flat.addItem("bA");
            combo_flat.addItem("A");
            combo_flat.addItem("bB");
            combo_flat.addItem("B");
            combo_flat.addItem("C");
            combo_flat.addItem("bD");
            combo_flat.addItem("D");
            combo_flat.addItem("bE");
            combo_flat.addItem("E");
            combo_flat.addItem("F");
            combo_flat.addItem("#F");
            combo_flat.addItem("G");
            
            combo_flat.setSelectedIndex(4);
            
            combo_flat.addItemListener(new ItemListener(){

          		@Override
          		public void itemStateChanged(ItemEvent e) {
          			if(e.getStateChange() == ItemEvent.SELECTED){	//以免itemStateChanged执行两次。
          	
          				JComboBox combo = (JComboBox) e.getSource();
          	            int index = combo.getSelectedIndex();
          	            
          	            DeviceManage.flat = index-4;		//调值与顺序对应。
          	
          	    		jPiano.requestFocus();	//恢复窗口焦点
          	    		
          			}
              	}
          		});

    }
    
    private void setJlab_left(){
    	jlab_add_left = new JLabel();
        jlab_add_left.setIcon(ImgUtil.getImageIcon("icons/add.png"));
        jlab_add_left.setToolTipText("升高左8度");
        
        jlab_left = new JLabel(DeviceManage.v8_left+"",SwingConstants.CENTER);
        jlab_left.setPreferredSize(new Dimension(16,16));
        jlab_left.setFont(new Font("Dialog",Font.BOLD,16));
        jlab_left.setForeground(new Color(0,100,165));
        
        jlab_sub_left = new JLabel();
        jlab_sub_left.setIcon(ImgUtil.getImageIcon("icons/delete.png"));
        jlab_sub_left.setToolTipText("降低左8度");
    }
    
    private void setJlab_right(){

        jlab_add_right = new JLabel();
        jlab_add_right.setIcon(ImgUtil.getImageIcon("icons/add.png"));
        jlab_add_right.setToolTipText("升高右8度");
        
        jlab_right = new JLabel(DeviceManage.v8_right+"",SwingConstants.CENTER);
        jlab_right.setPreferredSize(new Dimension(16,16));
        jlab_right.setFont(new Font("Dialog",Font.BOLD,16));
        jlab_right.setForeground(new Color(0,100,165));
        jlab_sub_right = new JLabel();
        jlab_sub_right.setIcon(ImgUtil.getImageIcon("icons/delete.png"));      
        jlab_sub_right.setToolTipText("降低右8度");
        

        Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
        jlab_add_left.setCursor(cursor);
        jlab_sub_left.setCursor(cursor);
        jlab_add_right.setCursor(cursor);
        jlab_sub_right.setCursor(cursor);
        
        
        jlab_add_left.addMouseListener(v8Listener);
        jlab_sub_left.addMouseListener(v8Listener);
        jlab_add_right.addMouseListener(v8Listener);
        jlab_sub_right.addMouseListener(v8Listener);
        
    }
    
    public void setVeloS_left(){
    	 //左音量
        veloS_left = new JSlider(JSlider.HORIZONTAL, 0, 127, DeviceManage.velocity_left);
        veloS_left.addChangeListener(new VelocityListener());
        TitledBorder tb = new TitledBorder(new EtchedBorder());
        tb.setTitle("左手力度（音量） = "+DeviceManage.velocity_left);
        veloS_left.setBorder(tb);
        veloS_left.setPreferredSize(new Dimension(150,42));
        veloS_left.addMouseListener(frameGetFocusListener);
    }
    
    private void setVeloS_right(){
    	//右音量
        veloS_right = new JSlider(JSlider.HORIZONTAL, 0, 127, DeviceManage.velocity_right);
        veloS_right.addChangeListener(new VelocityListener());
        TitledBorder tb2 = new TitledBorder(new EtchedBorder());
        tb2.setTitle("右手力度（音量） = "+DeviceManage.velocity_right);
        veloS_right.setBorder(tb2);
        veloS_right.setPreferredSize(new Dimension(150,42));
        veloS_right.addMouseListener(frameGetFocusListener);
    }
    
    private void setCombo_delay_left(){
    	combo_delay_left = new JComboBox();
        combo_delay_left.setPreferredSize(new Dimension(58,25));
        combo_delay_left.setMaximumSize(new Dimension(58,25));
        for(int i=0;i<DeviceManage.delayNames.length;i++){
        	combo_delay_left.addItem(DeviceManage.delayNames[i]);
        }
        combo_delay_left.addItemListener(delayListener);
    }
    
    private void setCombo_delay_right(){
    	combo_delay_right = new JComboBox();
        combo_delay_right.setPreferredSize(new Dimension(58,25));
        combo_delay_right.setMaximumSize(new Dimension(58,25));
        for(int i=0;i<DeviceManage.delayNames.length;i++){
        	combo_delay_right.addItem(DeviceManage.delayNames[i]);
        }
        combo_delay_right.addItemListener(delayListener);
    }
    
    private void setCombo_device_left(){
    	combo_device_left = new JComboBox();
        combo_device_left.setPreferredSize(new Dimension(180,25));
        combo_device_left.setMaximumSize(new Dimension(180,25));
        combo_device_left.addItem("Windows默认音源");
        combo_device_left.addItem("Java自带音源");
        combo_device_left.addItem("LoopBe Internal MIDI");
        combo_device_left.addItem("YAMAHA MIDI Device 2");
        combo_device_left.addItem("Gervill");

        combo_device_left.addItemListener(deviceListener);
       
    }
    
    private void setCombo_device_right(){
    	combo_device_right = new JComboBox();
        combo_device_right.setPreferredSize(new Dimension(180,25));
        combo_device_right.setMaximumSize(new Dimension(180,25));
        //将音源种类定死了算了，免得搞麻烦了。
        combo_device_right.addItem("Windows默认音源");
        combo_device_right.addItem("Java自带音源");
        combo_device_right.addItem("LoopBe Internal MIDI");
        combo_device_right.addItem("YAMAHA MIDI Device 2");        
        combo_device_right.addItem("Gervill");
        
        combo_device_right.addItemListener(deviceListener);
    }
    
    
    public Panel_Controls(final MyJavaPiano jPiano) {
    	
    	this.jPiano = jPiano;

        this.setView();
        
        this.addMouseListener(new MouseAdapter(){
        	@Override
        	public void mouseEntered(MouseEvent e) {    		
        			jPiano.requestFocus();        		
        	}
        });
        
        //初始化参数
        int flat = Integer.parseInt(ConfigManage.initial_props.get("flat"));
		int v8_left = Integer.parseInt(ConfigManage.initial_props.get("v8_left"));
		int v8_right = Integer.parseInt(ConfigManage.initial_props.get("v8_right"));
		int velocity_left = Integer.parseInt(ConfigManage.initial_props.get("velocity_left"));
		int velocity_right = Integer.parseInt(ConfigManage.initial_props.get("velocity_right"));
		int delay_left = Integer.parseInt(ConfigManage.initial_props.get("delay_left"));
		int delay_right = Integer.parseInt(ConfigManage.initial_props.get("delay_right"));
		
		changeFlat(flat);
		changeV8(v8_left, Key_Keyboard.LEFT);
		changeV8(v8_right, Key_Keyboard.RIGHT);
		changeVelocity_left(velocity_left);
		changeVelocity_right(velocity_right);
		//changeDelay_left(delay_left);
		//changeDelay_right(delay_right);
		combo_delay_left.setSelectedIndex(delay_left);
		combo_delay_right.setSelectedIndex(delay_right);
		
    }
    
    
    
    //显示界面
    private void setView(){
    	
        setLayout(null);
        setPreferredSize(new Dimension(MyJavaPiano.w, 100));
    	
        JPanel p_1 = new JPanel();
        JPanel p_2 = new JPanel();
        
        p_1.setSize(MyJavaPiano.w, 50);
        p_1.setLocation(0, 0);
        p_2.setSize(MyJavaPiano.w, 50);
        p_2.setLocation(0, 50);
        
        int x=10,y=10;
     
     ///////////   
        JLabel lab_major = new JLabel("调：");
        this.setCombo_flat();
        lab_major.setLocation(x,y);
        combo_flat.setLocation(x, y+20);
        p_1.add(lab_major);
        p_1.add(combo_flat);
        
        
        
        
        p_1.add(new JLabel("      "));		//添加间距
        
        JLabel lab_leftV = new JLabel("左8度：");
        this.setJlab_left();
        
        p_1.add(lab_leftV);
        p_1.add(jlab_add_left);
        p_1.add(jlab_left);
        p_1.add(jlab_sub_left);  
        p_1.add(new JLabel("    "));
        

        this.setVeloS_left();
        p_1.add(veloS_left);
        
        p_1.add(new JLabel("    "));      
        this.setVeloS_right();
        p_1.add(veloS_right);

        
        JLabel lab_rightV = new JLabel("    右8度：");
        
        this.setJlab_right();
        p_1.add(lab_rightV);
        p_1.add(jlab_add_right);
        p_1.add(jlab_right);
        p_1.add(jlab_sub_right);      
        p_1.add(new JLabel("    "));
        
        recordB = new JButton("录音");
        final RecordInfDialog reDialog = new RecordInfDialog(jPiano);
        recordB.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
						
				if(recordB.getText().equals("停止录音")){
					
					jPiano.recordFrame.setVisible(true);
					jPiano.recordFrame.toFront();
		            
					recordB.setText("录音");
					jPiano.recordFrame.stopRecord();
				}else{
					
					reDialog.setLocation(jPiano.getX()+200,jPiano.getY()+80);
					reDialog.setVisible(true);
					jPiano.recordFrame.record();
				}
				
				
			}
        	
        });
       
        p_1.add(recordB);
  
        
        
        JLabel lab_delay_left = new JLabel("左延音：");
        this.setCombo_delay_left();
        p_2.add(lab_delay_left);
        p_2.add(combo_delay_left);

        p_2.add(new JLabel("    "));
        
        JLabel lab_leftDe = new JLabel("左手设备：");
        this.setCombo_device_left();
        
        JLabel lab_rightDe = new JLabel("右手设备：");
        this.setCombo_device_right();
        
        p_2.add(lab_leftDe);
        p_2.add(combo_device_left);
        p_2.add(lab_rightDe);
        p_2.add(combo_device_right);
        
        p_2.add(new JLabel("   "));	
        
        JLabel lab_delay_right = new JLabel("右延音：");
        this.setCombo_delay_right();
        p_2.add(lab_delay_right);
        p_2.add(combo_delay_right);
        
       
        add(p_1);
        add(p_2);
    }
 
      
    //延音
      private ItemListener delayListener = new ItemListener(){

  		@Override
  		public void itemStateChanged(ItemEvent e) {
  			
  			if(e.getStateChange() == ItemEvent.SELECTED){	//以免itemStateChanged执行两次。
  				
  				JComboBox combo = (JComboBox) e.getSource();
  				if(combo.equals(combo_delay_left)){
  					DeviceManage.delay_left_index = combo.getSelectedIndex();
  				}else{
  					DeviceManage.delay_right_index = combo.getSelectedIndex();
  				}
  					
  	    		jPiano.requestFocus();	//恢复窗口焦点
  			}
  			
      	}
      };
   
      
      
     //调整八度监听器   
     private MouseAdapter v8Listener = new MouseAdapter(){ 	
	    @Override
	  	public void mouseClicked(MouseEvent e) {
	    		JLabel lab= (JLabel) e.getSource();
	  			if(lab.equals(jlab_add_left)){
	  				changeV8(1,Key_Keyboard.LEFT);
	  			}else if(lab.equals(jlab_sub_left)){
	  				changeV8(-1,Key_Keyboard.LEFT);
	  			}else if(lab.equals(jlab_add_right)){
	  				changeV8(1,Key_Keyboard.RIGHT);
	  			}else if(lab.equals(jlab_sub_right)){
	  				changeV8(-1,Key_Keyboard.RIGHT);
	  			}
	  			
	  		}
	  		
	  	
	      
     };
 	
     //调整八度，提供给外部统一访问的。

      
      
     //更换右手设备
     private ItemListener deviceListener = new ItemListener(){

  		@Override
  		public void itemStateChanged(ItemEvent e) {
  			if(e.getStateChange() == ItemEvent.SELECTED){	//以免itemStateChanged执行两次。
  					
  				JComboBox combo = (JComboBox) e.getSource();
  	            String name = combo.getSelectedItem().toString();    
  	            
            	//专门的。。。没办法。。全名居然是乱码。。
            	if(name.equals("Gervill")){
            		if(combo.equals(combo_device_left)){
            			DeviceManage.openDevice_left("Gervill");
      	            }else{
      	            	DeviceManage.openDevice_right("Gervill");
      	            }          						
            	}else if(name.equals("LoopBe Internal MIDI")){
            		if(combo.equals(combo_device_left)){
            			DeviceManage.openDevice_left("LoopBe Internal MIDI");
      	            }else{
      	            	DeviceManage.openDevice_right("LoopBe Internal MIDI");
      	            } 
            	}else if(name.equals("YAMAHA MIDI Device 2")){
            		if(combo.equals(combo_device_left)){
            			DeviceManage.openDevice_left("YAMAHA Virtual MIDI Device 2");
      	            }else{
      	            	DeviceManage.openDevice_right("YAMAHA Virtual MIDI Device 2");
      	            } 
            	}else if(name.startsWith("Java")){
            		if(combo.equals(combo_device_left)){
            			DeviceManage.openDevice_left("Java");
      	            }else{
      	            	DeviceManage.openDevice_right("Java");
      	            } 
            	}else{
            		if(combo.equals(combo_device_left)){
            			DeviceManage.openDevice_left("Microsoft");
      	            }else{
      	            	DeviceManage.openDevice_right("Microsoft");
      	            } 
            	}

  	
  	    		jPiano.requestFocus();	//恢复窗口焦点
  	    		
  			}
  		}
      };
    
      
      
      //调节左右手力度
      class VelocityListener implements ChangeListener{

  		@Override
  		public void stateChanged(ChangeEvent e) {
  			JSlider slider = (JSlider) e.getSource();
  	        int value = slider.getValue();
  	        TitledBorder tb = (TitledBorder) slider.getBorder();
  			String s = tb.getTitle();
  	        tb.setTitle(s.substring(0, s.indexOf('=')+1) + String.valueOf(value));
  	        if (s.startsWith("左")) {
  	        	DeviceManage.velocity_left = value;
  	        }else if (s.startsWith("右")) {
  	        	DeviceManage.velocity_right = value;
  	        }
  	        
  	        slider.repaint();
  		}	
      	
      }
      

      
    //让各种操作之后，主窗口恢复焦点
	MouseListener frameGetFocusListener = new MouseAdapter(){
		@Override
		public void mouseReleased(MouseEvent e) {
			jPiano.requestFocus();				
		}
  	};


   
    
  	//以下方法提供给外部调用的：
  	
    //调整调值，提供给外部(快捷键)访问的。i为相对的值，+1或-1
	public void changeFlat(int i) {
		int index = this.combo_flat.getSelectedIndex()+i;
		this.combo_flat.setSelectedIndex(index);
	}
    
    //在原8度的基础上修改,注意是相对于原来的,等于0就是不修改。      
    public void changeV8(int v8_change,int leftOrRight){   	    	
	   	int v= 0;
	   	
	   	if(leftOrRight==Key_Keyboard.LEFT){
	   		v = DeviceManage.v8_left+v8_change;
	   	}else{
	   		v = DeviceManage.v8_right+v8_change;
	   	}
	   	if(v8_change==0||v>2||v<-2){		//不能超过范围。
	   		return;
	   	}   	
	   	
	   	if(leftOrRight==Key_Keyboard.LEFT){
	   		DeviceManage.v8_left = v;
	   		jlab_left.setText(""+v);
	   	}else{
	   		DeviceManage.v8_right = v;
	   		jlab_right.setText(""+v);
	   	}
	   	
     	Iterator<Key_Keyboard> it = Panel_Keyboard.keys_mapping.values().iterator();
		while(it.hasNext()){
			Key_Keyboard key = it.next();
			if(key.leftOrRight==leftOrRight&&!key.isDisable()){
				key.setKeyNum(key.getKeyNum()+12*v8_change,key.is_b);
			}
		}
			
     }
  	
  	
    //左右力度
    public void changeVelocity_left(int v){
    	//DeviceManage.velocity_left = v;
    	this.veloS_left.setValue(v);
    }

    public void changeVelocity_right(int v){
    	//DeviceManage.velocity_right = v;
    	this.veloS_right.setValue(v);
    }
    
  /*  //左右延音
    public void changeDelay_left(int index){
    	this.combo_delay_left.setSelectedIndex(index);
    }
    public void changeDelay_right(int index){
    	this.combo_delay_right.setSelectedIndex(index);
    }*/
    
	
    
}

