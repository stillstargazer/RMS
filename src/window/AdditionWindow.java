package window;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import body.ReagentList;
import java.awt.Component;
import java.awt.Dimension;

public class AdditionWindow extends JFrame
{
	private static final long serialVersionUID = 5343379944281497338L;
	private JPanel textFieldPane;
	private JPanel okCancelPane;
	
	private JLabel CHInamelabel;
	private JLabel ENGnamelabel;
	private JLabel CASlabel;
	private JLabel puritylabel;
	private JLabel speclabel;
	private JLabel numberlabel;
	private JLabel manulabel;
	private JLabel datelabel;
	private JLabel cabinetlabel;
	
	
	private JTextField CHInametextfield;
	private JTextField ENGnametextfield;
	private JTextField CAStextfield;
	private JTextField puritytextfield;
	private JTextField spectextfield;
	private JTextField numbertextfield;
	private JTextField manutextfield;
	private JTextField datetextfield;
	private JTextField cabinettextfield;
	
	private Font labelFont = new Font("微软雅黑", Font.PLAIN, 22);
	private Font textFieldFont = new Font("微软雅黑", Font.PLAIN, 20);
	
	public AdditionWindow(ReagentList rl)
	{
		super("添加药品");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1000, 600);
		getContentPane().setLayout(new BorderLayout());
		
		textFieldPane = new JPanel();
		
		Box vBox = Box.createVerticalBox();
		
		
		Box b1 = Box.createHorizontalBox();
		CHInamelabel = new JLabel("中文名(Chinese Name)");
		CHInamelabel.setFont(labelFont);
		CHInametextfield = new JTextField(10);
		CHInametextfield.setFont(textFieldFont);
		CHInametextfield.setToolTipText("Must have");
		CHInamelabel.setDisplayedMnemonic('c');                //快捷键 alt + c
		CHInamelabel.setLabelFor(CHInametextfield);
		
		ENGnamelabel = new JLabel("英文名(English Name)");
		ENGnamelabel.setFont(labelFont);
		ENGnametextfield = new JTextField(10);
		ENGnametextfield.setFont(textFieldFont);
		ENGnametextfield.setToolTipText("Must have");
		ENGnamelabel.setDisplayedMnemonic('e');                //快捷键 alt + e
		ENGnamelabel.setLabelFor(ENGnametextfield);
		
		b1.add(Box.createHorizontalStrut(10));
		b1.add(CHInamelabel);
		b1.add(Box.createHorizontalStrut(20));
		b1.add(CHInametextfield);
		b1.add(Box.createHorizontalStrut(40));
		b1.add(ENGnamelabel);
		b1.add(Box.createHorizontalStrut(20));
		b1.add(ENGnametextfield);
		b1.add(Box.createHorizontalStrut(10));
		
		Box b2 = Box.createHorizontalBox();
		CASlabel = new JLabel("CAS");
		CASlabel.setFont(labelFont);
		CAStextfield = new JTextField(10);
		CAStextfield.setFont(textFieldFont);
		CAStextfield.setToolTipText("Optional");
		CASlabel.setDisplayedMnemonic('a');                  //快捷键 alt + a
		CASlabel.setLabelFor(CAStextfield);
		
		numberlabel = new JLabel("数量(Number)");
		numberlabel.setFont(labelFont);
		numbertextfield = new JTextField(10);
		numbertextfield.setPreferredSize(new Dimension(10, 50));
		numbertextfield.setFont(textFieldFont);
		numbertextfield.setToolTipText("Must have");
		numberlabel.setDisplayedMnemonic('n');
		numberlabel.setLabelFor(numbertextfield);
		
		b2.add(Box.createHorizontalStrut(10));
		b2.add(CASlabel);
		b2.add(Box.createHorizontalStrut(20));
		b2.add(CAStextfield);
		b2.add(Box.createHorizontalStrut(40));
		b2.add(numberlabel);
		b2.add(Box.createHorizontalStrut(20));
		b2.add(numbertextfield);
		b2.add(Box.createHorizontalStrut(10));
		
		
		
		
		
		
		
		
		
		//纵向vBox添加所有的Box
		vBox.add(b1);
		vBox.add(b2);
		
		
		
		textFieldPane.add(vBox);
		getContentPane().add(textFieldPane, BorderLayout.CENTER);
		
		
		this.setVisible(true);
	}

}
