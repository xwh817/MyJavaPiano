package xwh.jPiano;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;


//对文件操作事件的监听
public class FileListener implements ActionListener{

	private MyJavaPiano jPiano;
	private String op;	//操作的名称:openMjp,openConfig,saveConfig
	public FileListener(String op,MyJavaPiano jPiano){
		this.op = op;
		this.jPiano = jPiano;
	}
	
	
	public void actionPerformed(ActionEvent e) {

		
	    if(op.equals("openMjp")){

	    	String path_records = ConfigManage.initial_props.get("path_records");		        
	    	if(path_records.equals("")){
	    		path_records = System.getProperty("user.dir")+"/录音";
	    	}
	    	File file = new File(path_records);
	    	if(!file.exists()){
	    		path_records = System.getProperty("user.dir")+"/录音";
	    		file = new File(path_records);
	    	}
	    	
            JFileChooser chooser = new JFileChooser(file);			    
	    	FileNameExtensionFilter filter = new FileNameExtensionFilter("MyJavaPiano录音文件", "mjp");
			chooser.setFileFilter(filter);
		    chooser.setVisible(true);
		    
		    if(chooser.showOpenDialog(jPiano) == JFileChooser.APPROVE_OPTION) {
		    	String path = chooser.getSelectedFile().getPath();		//完整路径
		    	
		    	jPiano.recordFrame.playRecord(path);
		    	
		        ConfigManage.initial_props.put("path_records", path);		        
		    	
		    }
		}
	    else if(op.equals("openConfig")){
	    	
	    	String path_userFiles = ConfigManage.initial_props.get("path_userFiles");		        
	    	if(path_userFiles.equals("")){
	    		path_userFiles = System.getProperty("user.dir")+"/按键设置";
	    	}
	    	File file = new File(path_userFiles);
	    	if(!file.exists()){
	    		path_userFiles = System.getProperty("user.dir")+"/按键设置";
	    		file = new File(path_userFiles);
	    	}
	    	
            JFileChooser chooser = new JFileChooser(file);
		    
	    	FileNameExtensionFilter filter = new FileNameExtensionFilter("MyJavaPiano配置文件", "xml");
			chooser.setFileFilter(filter);
		    chooser.setVisible(true);
		    if(chooser.showOpenDialog(jPiano) == JFileChooser.APPROVE_OPTION) {
		    	//String dir = chooser.getSelectedFile().getParent();		//所在目录
		    	//String name = chooser.getSelectedFile().getName();		//所选文件名
		    	String path = chooser.getSelectedFile().getPath();		//完整路径
		    	
		    	ConfigManage.parseConfig_file(path);
		    	//ConfigManage.setting();
		    			    	
		        ConfigManage.initial_props.put("path_userFiles", path);		        
		    	
		    }
		}else if(op.equals("saveConfig")){
			 try {
				 //File file = new File(System.getProperty("user.dir")+"/userFiles");
				 	String path_userFiles = ConfigManage.initial_props.get("path_userFiles");		        
			    	if(path_userFiles.equals("")){
			    		path_userFiles = System.getProperty("user.dir")+"/按键设置";
			    	}
			    	File file = new File(path_userFiles);
			    	if(!file.exists()){
			    		path_userFiles = System.getProperty("user.dir")+"/按键设置";
			    		file = new File(path_userFiles);
			    	}
			    	
		            JFileChooser chooser = new JFileChooser(file);

				    chooser.setVisible(true);
				    
	                if (chooser.showSaveDialog(jPiano) == JFileChooser.APPROVE_OPTION) {
	                	String path = chooser.getSelectedFile().getAbsolutePath();
	                	if(!path.endsWith(".xml")&&!path.endsWith("XML")){
	                		path = path+".xml";		//添加后缀名
	                	}
	                	String name = chooser.getSelectedFile().getName();
	                    File f_xml = new File(path);
	                	if(f_xml.exists()){
	                		
	                		if(!name.endsWith(".xml")&&!name.endsWith("XML")){
	                			name = name+".xml";		//添加后缀名
		                	}
	                		
	                		int r = JOptionPane.showConfirmDialog(null
	                				,"此文件夹已经包含一个名为"+name+"的文件。\n是否覆盖？","确认文件替换", JOptionPane.YES_NO_OPTION);
	                	
	                		if(r==JOptionPane.OK_OPTION){
	                			ConfigManage.saveConfig(f_xml);
	                		}
	                	}else{
	                		ConfigManage.saveConfig(f_xml);		    		    	
	                	}
	    		    	
				        ConfigManage.initial_props.put("path_userFiles", path);		        
				    	
	                	
	                }
	            } catch (SecurityException ex) { 
	                ex.printStackTrace();
	            } catch (Exception ex) { 
	                ex.printStackTrace();
	            }
		}
	    
	}	
	
	

}
