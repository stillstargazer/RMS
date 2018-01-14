package window;

import javax.swing.JFrame;
import javax.swing.JList;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import body.Reagent;
import body.ReagentList;

import java.awt.Dimension;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.awt.Font;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.JScrollBar;
import javax.swing.ListSelectionModel;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;

public class MainWindow extends JFrame
{
	
	public MainWindow(ReagentList rl)
	{
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setSize(850, 600);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(1, 4, 0, 0));
		
		JButton add_reagent = new JButton("\u6DFB\u52A0\u836F\u54C1");
		add_reagent.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 30));
		panel.add(add_reagent);
		
		JButton delete_reagent = new JButton("\u5220\u9664\u836F\u54C1");
		delete_reagent.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 30));
		panel.add(delete_reagent);
		
		JButton set_reagent = new JButton("\u4FEE\u6539\u836F\u54C1");
		set_reagent.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 30));
		panel.add(set_reagent);
		
		JButton find_reagent = new JButton("\u67E5\u8BE2\u836F\u54C1");
		find_reagent.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 30));
		panel.add(find_reagent);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		JPanel reagentpanel = new JPanel();
		scrollPane.setViewportView(reagentpanel);
		
		DefaultListModel<String> jlm = new DefaultListModel<String>();
		int rl_length = rl.getlength();
		for(int i = 0; i < rl_length; i++)
		{
			jlm.addElement(rl.getshortterm()[i]);
		}
		
		JList<String> list = new JList<String>();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setModel(jlm);
		reagentpanel.add(list);
		list.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 25));
		
		
		
	}

}
