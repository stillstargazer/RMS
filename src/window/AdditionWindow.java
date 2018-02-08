package window;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import body.Reagent;
import body.ReagentList;
import body.TblModel;

import javax.swing.border.EmptyBorder;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class AdditionWindow extends JFrame
{
	private static final long serialVersionUID = 5343379944281497338L;
	private JPanel textPane;
	
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
	private JComboBox<String>  spectextfield;
	private JTextField numbertextfield;
	private JComboBox<String> manutextfield;
	private JTextField datetextfield;
	private JComboBox<Integer> cabinettextfield;
	
	private Font labelFont = new Font("微软雅黑", Font.PLAIN, 22);
	private Font textFieldFont = new Font("微软雅黑", Font.PLAIN, 20);
	
	public JButton okButton;
	
	public AdditionWindow(ReagentList rl, JTable reagentTable)
	{
		super("添加药品");
		setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(1100, 500);
		this.setBounds(600, 400, 1100, 500);
		getContentPane().setLayout(new BorderLayout());
		
		textPane = new JPanel();
		textPane.setBorder(new EmptyBorder(25, 40, 25, 40));

		GridBagLayout gbl_textPane = new GridBagLayout();
		gbl_textPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		textPane.setLayout(gbl_textPane);
		
		CHInamelabel = new JLabel("中文名(Chinese Name)");
		CHInametextfield = new JTextField(10);
		CHInametextfield.setToolTipText("Must have");
		CHInamelabel.setDisplayedMnemonic('c');                //快捷键 alt + c
		setLBTFProperties(CHInamelabel, CHInametextfield);
		
		ENGnamelabel = new JLabel("英文名(English Name)");
		ENGnametextfield = new JTextField(10);
		setLBTFProperties(ENGnamelabel, ENGnametextfield);
		ENGnametextfield.setToolTipText("Must have");
		ENGnamelabel.setDisplayedMnemonic('e');                //快捷键 alt + e
		
		CASlabel = new JLabel("CAS");
		CAStextfield = new JTextField(10);
		CAStextfield.setToolTipText("Optional");
		CASlabel.setDisplayedMnemonic('a');                  //快捷键 alt + a
		setLBTFProperties(CASlabel, CAStextfield);
		
		numberlabel = new JLabel("数量(Number)");
		numbertextfield = new JTextField(10);
		numbertextfield.setToolTipText("Must have");
		numberlabel.setDisplayedMnemonic('n');               //快捷键 alt + n
		setLBTFProperties(numberlabel, numbertextfield);
		
		puritylabel = new JLabel("纯度(Purity)");
		puritytextfield = new JTextField(10);
		puritytextfield.setToolTipText("Must have");
		puritylabel.setDisplayedMnemonic('p');               //快捷键 alt + p
		setLBTFProperties(puritylabel, puritytextfield);
		
		speclabel = new JLabel("规格(Spec)");
		spectextfield = new JComboBox<String>();
		spectextfield.setEditable(true);
		setSpecContents(spectextfield);
		spectextfield.setToolTipText("Must have");
		speclabel.setDisplayedMnemonic('s');                 //快捷键 alt + s
		speclabel.setFont(labelFont);
		spectextfield.setFont(textFieldFont);
		speclabel.setLabelFor(spectextfield);
		
		manulabel = new JLabel("厂商(Manu)");
		manutextfield = new JComboBox<String>();
		manutextfield.setEditable(true);
		setManuContents(manutextfield, rl);
		manutextfield.setToolTipText("Must have");
		manutextfield.setFont(textFieldFont);
		manulabel.setLabelFor(manutextfield);	
		manulabel.setDisplayedMnemonic('m');                //快捷键 alt + m
		manulabel.setFont(labelFont);
		
		
		datelabel = new JLabel("生产日期(Date)");
		datetextfield = new JTextField(10);
		datetextfield.setToolTipText("Must have");
		datelabel.setDisplayedMnemonic('d');                //快捷键 alt + d
		setLBTFProperties(datelabel, datetextfield);
		
		cabinetlabel = new JLabel("柜号(Group)");
		cabinettextfield = new JComboBox<Integer>();
		setCabinetContents(cabinettextfield);
		cabinettextfield.setToolTipText("Must have");
		cabinetlabel.setDisplayedMnemonic('g');             //快捷键 alt + g
		cabinetlabel.setFont(labelFont);
		cabinettextfield.setFont(textFieldFont);
		cabinetlabel.setLabelFor(datetextfield);
		
		
		
		okButton = new JButton("确认");		
		okButton.setFont(labelFont);
		okButton.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				
				String errorstr = checkError();
				if(errorstr.equals(""))
				{
					//纯度加后缀
					String purStr = puritytextfield.getText();
					if(!purStr.endsWith("%"))
					{
						try
						{
							Double.parseDouble(purStr);
							purStr += "%";
						} 
						catch (NumberFormatException nfe)
						{
							
						}
					}
					
					//规格加后缀，默认为g
					String specStr = spectextfield.getSelectedItem().toString();
					if(!(specStr.endsWith("g") || specStr.endsWith("ml")))
						specStr += "g";
					
					Reagent r = new Reagent(CHInametextfield.getText(), ENGnametextfield.getText(), CAStextfield.getText(), 
							Integer.parseInt(numbertextfield.getText()), purStr, specStr, 
							manutextfield.getSelectedItem().toString(), Integer.parseInt(datetextfield.getText()), 
							Integer.parseInt(cabinettextfield.getSelectedItem().toString()));
					try
					{
						int n = rl.add(r);
						((TblModel)reagentTable.getModel()).update(rl);
						((TblModel)reagentTable.getModel()).fireTableDataChanged();
						reagentTable.setRowSelectionInterval(n, n);
						int len = reagentTable.getModel().getRowCount();
						n = (n <= len - 7) ? (n + 6) : (len - 1);
						Rectangle rect = reagentTable.getCellRect(n, 0, true);
						reagentTable.scrollRectToVisible(rect);
						dispose();
					} catch (FileNotFoundException e1)
					{
						e1.printStackTrace();
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, errorstr, "输入错误", JOptionPane.WARNING_MESSAGE, null);
				}
			}
		});
			
		GridBagConstraints gbc_CHInamelabel = new GridBagConstraints();
		gbc_CHInamelabel.insets = new Insets(0, 10, 5, 5);
		gbc_CHInamelabel.anchor = GridBagConstraints.WEST;
		gbc_CHInamelabel.weighty = 1.0;
		gbc_CHInamelabel.weightx = 1.0;
		gbc_CHInamelabel.gridy = 0;
		gbc_CHInamelabel.gridx = 0;
		textPane.add(CHInamelabel, gbc_CHInamelabel);
		GridBagConstraints gbc_CHInametextfield = new GridBagConstraints();
		gbc_CHInametextfield.insets = new Insets(0, 0, 5, 10);
		gbc_CHInametextfield.anchor = GridBagConstraints.EAST;
		gbc_CHInametextfield.weighty = 1.0;
		gbc_CHInametextfield.weightx = 1.0;
		gbc_CHInametextfield.gridy = 0;
		gbc_CHInametextfield.gridx = 0;
		textPane.add(CHInametextfield, gbc_CHInametextfield);
		GridBagConstraints gbc_ENGnamelabel = new GridBagConstraints();
		gbc_ENGnamelabel.insets = new Insets(0, 10, 5, 5);
		gbc_ENGnamelabel.anchor = GridBagConstraints.WEST;
		gbc_ENGnamelabel.weighty = 1.0;
		gbc_ENGnamelabel.weightx = 1.0;
		gbc_ENGnamelabel.gridy = 0;
		gbc_ENGnamelabel.gridx = 1;
		textPane.add(ENGnamelabel, gbc_ENGnamelabel);
		GridBagConstraints gbc_ENGnametextfield = new GridBagConstraints();
		gbc_ENGnametextfield.insets = new Insets(0, 0, 5, 10);
		gbc_ENGnametextfield.anchor = GridBagConstraints.EAST;
		gbc_ENGnametextfield.weighty = 1.0;
		gbc_ENGnametextfield.weightx = 1.0;
		gbc_ENGnametextfield.gridy = 0;
		gbc_ENGnametextfield.gridx = 1;
		textPane.add(ENGnametextfield, gbc_ENGnametextfield);
		GridBagConstraints gbc_CASlabel = new GridBagConstraints();
		gbc_CASlabel.insets = new Insets(0, 10, 5, 5);
		gbc_CASlabel.anchor = GridBagConstraints.WEST;
		gbc_CASlabel.weighty = 1.0;
		gbc_CASlabel.weightx = 1.0;
		gbc_CASlabel.gridy = 1;
		gbc_CASlabel.gridx = 0;
		textPane.add(CASlabel, gbc_CASlabel);
		GridBagConstraints gbc_CAStextfield = new GridBagConstraints();
		gbc_CAStextfield.insets = new Insets(0, 0, 5, 10);
		gbc_CAStextfield.anchor = GridBagConstraints.EAST;
		gbc_CAStextfield.weighty = 1.0;
		gbc_CAStextfield.weightx = 1.0;
		gbc_CAStextfield.gridy = 1;
		gbc_CAStextfield.gridx = 0;
		textPane.add(CAStextfield, gbc_CAStextfield);
		GridBagConstraints gbc_numberlabel = new GridBagConstraints();
		gbc_numberlabel.insets = new Insets(0, 10, 5, 5);
		gbc_numberlabel.anchor = GridBagConstraints.WEST;
		gbc_numberlabel.weighty = 1.0;
		gbc_numberlabel.weightx = 1.0;
		gbc_numberlabel.gridy = 1;
		gbc_numberlabel.gridx = 1;
		textPane.add(numberlabel, gbc_numberlabel);
		GridBagConstraints gbc_numbertextfield = new GridBagConstraints();
		gbc_numbertextfield.insets = new Insets(0, 0, 5, 10);
		gbc_numbertextfield.anchor = GridBagConstraints.EAST;
		gbc_numbertextfield.weighty = 1.0;
		gbc_numbertextfield.weightx = 1.0;
		gbc_numbertextfield.gridy = 1;
		gbc_numbertextfield.gridx = 1;
		textPane.add(numbertextfield, gbc_numbertextfield);
		GridBagConstraints gbc_puritylabel = new GridBagConstraints();
		gbc_puritylabel.insets = new Insets(0, 10, 5, 5);
		gbc_puritylabel.anchor = GridBagConstraints.WEST;
		gbc_puritylabel.weighty = 1.0;
		gbc_puritylabel.weightx = 1.0;
		gbc_puritylabel.gridy = 2;
		gbc_puritylabel.gridx = 0;
		textPane.add(puritylabel, gbc_puritylabel);
		GridBagConstraints gbc_puritytextfield = new GridBagConstraints();
		gbc_puritytextfield.insets = new Insets(0, 0, 5, 10);
		gbc_puritytextfield.anchor = GridBagConstraints.EAST;
		gbc_puritytextfield.weighty = 1.0;
		gbc_puritytextfield.weightx = 1.0;
		gbc_puritytextfield.gridy = 2;
		gbc_puritytextfield.gridx = 0;
		textPane.add(puritytextfield, gbc_puritytextfield);
		GridBagConstraints gbc_speclabel = new GridBagConstraints();
		gbc_speclabel.insets = new Insets(0, 10, 5, 5);
		gbc_speclabel.anchor = GridBagConstraints.WEST;
		gbc_speclabel.weighty = 1.0;
		gbc_speclabel.weightx = 1.0;
		gbc_speclabel.gridy = 2;
		gbc_speclabel.gridx = 1;
		textPane.add(speclabel, gbc_speclabel);
		GridBagConstraints gbc_spectextfield = new GridBagConstraints();
		gbc_spectextfield.insets = new Insets(0, 0, 5, 10);
		gbc_spectextfield.anchor = GridBagConstraints.EAST;
		gbc_spectextfield.weighty = 1.0;
		gbc_spectextfield.weightx = 1.0;
		gbc_spectextfield.gridy = 2;
		gbc_spectextfield.gridx = 1;
		textPane.add(spectextfield, gbc_spectextfield);
		GridBagConstraints gbc_manulabel = new GridBagConstraints();
		gbc_manulabel.insets = new Insets(0, 10, 5, 5);
		gbc_manulabel.anchor = GridBagConstraints.WEST;
		gbc_manulabel.gridwidth = 2;
		gbc_manulabel.weighty = 1.0;
		gbc_manulabel.weightx = 1.0;
		gbc_manulabel.gridy = 3;
		gbc_manulabel.gridx = 0;
		textPane.add(manulabel, gbc_manulabel);
		GridBagConstraints gbc_manutextfield = new GridBagConstraints();
		gbc_manutextfield.ipadx = 20;
		gbc_manutextfield.insets = new Insets(0, 0, 5, 10);
		gbc_manutextfield.anchor = GridBagConstraints.EAST;
		gbc_manutextfield.weighty = 1.0;
		gbc_manutextfield.weightx = 1.0;
		gbc_manutextfield.gridy = 3;
		gbc_manutextfield.gridx = 0;
		textPane.add(manutextfield, gbc_manutextfield);
		GridBagConstraints gbc_datelabel = new GridBagConstraints();
		gbc_datelabel.insets = new Insets(0, 10, 0, 5);
		gbc_datelabel.anchor = GridBagConstraints.WEST;
		gbc_datelabel.gridy = 4;
		gbc_datelabel.gridx = 0;
		textPane.add(datelabel, gbc_datelabel);
		GridBagConstraints gbc_datetextfield = new GridBagConstraints();
		gbc_datetextfield.insets = new Insets(0, 0, 0, 10);
		gbc_datetextfield.anchor = GridBagConstraints.EAST;
		gbc_datetextfield.weighty = 1.0;
		gbc_datetextfield.weightx = 1.0;
		gbc_datetextfield.gridy = 4;
		gbc_datetextfield.gridx = 0;
		textPane.add(datetextfield, gbc_datetextfield);
		GridBagConstraints gbc_cabinetlabel = new GridBagConstraints();
		gbc_cabinetlabel.insets = new Insets(0, 10, 0, 5);
		gbc_cabinetlabel.anchor = GridBagConstraints.WEST;
		gbc_cabinetlabel.weighty = 1.0;
		gbc_cabinetlabel.weightx = 1.0;
		gbc_cabinetlabel.gridy = 4;
		gbc_cabinetlabel.gridx = 1;
		textPane.add(cabinetlabel, gbc_cabinetlabel);
		GridBagConstraints gbc_cabinettextfield = new GridBagConstraints();
		gbc_cabinettextfield.insets = new Insets(0, 0, 0, 10);
		gbc_cabinettextfield.anchor = GridBagConstraints.EAST;
		gbc_cabinettextfield.weighty = 1.0;
		gbc_cabinettextfield.weightx = 1.0;
		gbc_cabinettextfield.gridy = 4;
		gbc_cabinettextfield.gridx = 1;
		textPane.add(cabinettextfield, gbc_cabinettextfield);
		GridBagConstraints gbc_okLabel = new GridBagConstraints();
		gbc_okLabel.weighty = 1.0;
		gbc_okLabel.weightx = 1.0;
		gbc_okLabel.ipady = 10;
		gbc_okLabel.gridy = 5;
		gbc_okLabel.gridx = 1;
		textPane.add(okButton, gbc_okLabel);		
		
		
		
		
		
		
		getContentPane().add(textPane, BorderLayout.CENTER);
		//getContentPane().add(okCancelPane, BorderLayout.SOUTH);
		
		this.setVisible(true);
	}
	
	
	private void setLBTFProperties(JLabel label, JTextField textfield)
	{
		label.setFont(labelFont);
		textfield.setFont(textFieldFont);
		label.setLabelFor(textfield);
	}
	
	private void setSpecContents(JComboBox<String> spec)
	{
		spec.addItem("500g");
		spec.addItem("250g");
		spec.addItem("100g");
		spec.addItem("50g");
		spec.addItem("25g");
		spec.addItem("10g");
		spec.addItem("5g");
		spec.addItem("1g");
		spec.addItem("1000g");
		spec.addItem("500ml");
		spec.addItem("250ml");
		spec.addItem("100ml");
		spec.addItem("25ml");
	}
	
	private void setManuContents(JComboBox<String> manu, ReagentList rl)
	{
		String[] str = rl.getManu();
		int len = str.length;
		for(int i = 0;  i < len; i++)
		{
			manu.addItem(str[i]);
		}
	}

	private void setCabinetContents(JComboBox<Integer> cab)
	{
		for(int i = 1; i < 13; i++)
			cab.addItem(i);
	}
	
	private String checkError()
	{
		StringBuilder errstr = new StringBuilder(""); 
		if(CHInametextfield.getText().equals(""))
			errstr.append("中文名不能为空\n");
		if(ENGnametextfield.getText().equals(""))
			errstr.append("英文名不能为空\n");
		if(CAStextfield.getText().equals(""))
			errstr.append("CAS号不能为空\n");
		if(numbertextfield.getText().equals(""))
			errstr.append("数量不能为空\n");
		else
		{
			try
			{
				int num = Integer.parseInt(numbertextfield.getText());
				if(num <= 0)
					errstr.append("数量不能小于0\n");
			}
			catch (NumberFormatException e)
			{
				try
				{
					Double d = Double.parseDouble(numbertextfield.getText());
					if(d <= 0.0)
						errstr.append("数量不能小于0\n");
				}
				catch (NumberFormatException nfe)
				{
					errstr.append("数量必须为数字\n");
				}
			}
		}
		if(puritytextfield.getText().equals(""))
			errstr.append("纯度不能为空\n");
		if(spectextfield.getSelectedItem().toString().equals(""))
			errstr.append("规格不能为空\n");
		if(manutextfield.getSelectedItem().toString().equals(""))
			errstr.append("厂商不能为空\n");
		if(datetextfield.getText().equals(""))
			errstr.append("生产日期不能为空\n");
		else
		{
			try
			{
				int dt = Integer.parseInt(datetextfield.getText());
				if(dt < 0)
					errstr.append("生产日期不能小于0\n");
			}
			catch (NumberFormatException e)
			{
				try
				{
					Double d = Double.parseDouble(numbertextfield.getText());
					errstr.append("生产日期不能为小数\n");
				}
				catch (NumberFormatException nfe)
				{
					errstr.append("生产日期必须为数字\n");
				}
			}
		}
		if(cabinettextfield.getSelectedItem().equals(""))
			errstr.append("柜号不能为空");
		
		return errstr.toString();
	}
}
