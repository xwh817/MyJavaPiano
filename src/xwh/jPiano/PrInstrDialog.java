package xwh.jPiano;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;


//预设乐器
public class PrInstrDialog extends JDialog{
	private static final long serialVersionUID = 1L;
	
	private static PrInstrDialog instrDlg;
	
	public static void showDlg(){
		if(instrDlg==null){
			instrDlg = new PrInstrDialog();
		}
		instrDlg.setVisible(true);
	}
	
	private PrInstrDialog() {
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