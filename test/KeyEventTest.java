
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;


public class KeyEventTest extends JFrame {

	private static final long serialVersionUID = 1L;


	public static void main(String[] args) {
		
		new KeyEventTest();
	}
	
	
	public KeyEventTest() {
		this.setBounds(300, 300, 200, 200);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setVisible(true);
		
		
		this.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent e) {
				
				char keyChar = e.getKeyChar();
				int keyCode = e.getKeyCode();
				int keyLocation = e.getKeyLocation();
				
				System.out.print("keyChar"+keyChar);
				System.out.print(",keyCode"+keyCode);
				System.out.println(",keyLocation"+keyLocation);
				
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		
		});
	}
}
