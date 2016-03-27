import java.lang.reflect.Field;


public class HotKeyTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			Class c = Class.forName("java.awt.event.KeyEvent");
			
			Field f = c.getField("VK_F1ert");
			
			if(f==null){
				System.out.println(-1);
			}
		
			System.out.println(f.getInt(c));	//通过反射来获取静态常量的值。
			
		} catch (java.lang.NoSuchFieldException e) {
			System.out.println(-1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
