package xwh.jPiano.util;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.ImageIcon;

/**
 * @author: xwh817
 *@说明：用来获取图片
 */
public class ImgUtil {

	private ImgUtil(){};
	
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static String rootPath = "images/";
	
	/**
	 * 获取图片
	 * @param path 文件路径，相对于rootPath
	 */
	public static Image getImage(String path){
		URL url = ImgUtil.class.getClassLoader().getResource(rootPath+path);
		if(url==null){
			System.out.println("error:"+rootPath+path+"文件不存在!");
			return null;
		}
		return tk.getImage(url);
	}
	
	public static ImageIcon getImageIcon(String path){	
		Image img = getImage(path);
		if(img==null) return null;
		else return new ImageIcon(img);
	}
}
