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

import body.ReagentList;

import java.awt.Dimension;
import javax.swing.JScrollPane;

public class MainWindow extends JFrame
{
	
	public MainWindow(ReagentList rl)
	{
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setSize(850, 600);
		
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		getContentPane().add(splitPane, BorderLayout.NORTH);
		
		JButton button = new JButton("\u6DFB\u52A0\u836F\u54C1");
		button.setSize(new Dimension(200, 30));
		button.setPreferredSize(new Dimension(200, 30));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		splitPane.setLeftComponent(button);
		
		JSplitPane splitPane_1 = new JSplitPane();
		splitPane.setRightComponent(splitPane_1);
		
		JButton button_1 = new JButton("\u5220\u9664\u836F\u54C1");
		button_1.setPreferredSize(new Dimension(200, 30));
		splitPane_1.setLeftComponent(button_1);
		
		JSplitPane splitPane_2 = new JSplitPane();
		splitPane_1.setRightComponent(splitPane_2);
		
		JButton button_2 = new JButton("\u4FEE\u6539\u836F\u54C1");
		button_2.setPreferredSize(new Dimension(200, 30));
		splitPane_2.setLeftComponent(button_2);
		
		JButton button_3 = new JButton("\u67E5\u627E\u836F\u54C1");
		button_3.setPreferredSize(new Dimension(200, 30));
		splitPane_2.setRightComponent(button_3);
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		JList list = new JList(rl.getshortterm());
		scrollPane.setViewportView(list);
		
		
	}

}
