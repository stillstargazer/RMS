package body;

import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

public class TblModel extends AbstractTableModel implements TableModelListener
{

	private static final long serialVersionUID = 1L;

	final private String[] columnNames = {"������", "Ӣ����", "CAS", "����", "���"};
	
	private Object[][] data;
	
	public TblModel(ReagentList rl)
	{
		data = to2Dobj(rl);
	}
	
	public void update(ReagentList rl)
	{
		data = to2Dobj(rl);
	}
	
	private Object[][] to2Dobj(ReagentList rl)
	{
		int num = rl.getlength();
		Object[][] realist = new Object[num][5];
		for(int i = 0; i < num; i++)
		{
			realist[i][0] = rl.get(i).getCHIname();
			realist[i][1] = rl.get(i).getENGname();
			realist[i][2] = rl.get(i).getCAS();
			realist[i][3] = rl.get(i).getnumber();
			realist[i][4] = rl.get(i).getcabinet();
		}
		return realist;
	}			
	
	public int getColumnCount()
	{
		return columnNames.length;
	}
	
	public int getRowCount()
	{
		return data.length;
	}
	
	public Object getValueAt(int row, int col)
	{
		if(row < 0 || row > data.length)
			return null;
		return data[row][col];
	}
	
	public String getColumnName(int col)
	{
		return columnNames[col];
	}
	
	public Class<?> getColumnClass(int col)
	{
		if(col == 3)
			return Integer.class;
		return getValueAt(0, col).getClass();
	}
	
	public boolean isCellEditable(int row, int col)
	{
		if(col == 3)
			return true;
		else
			return false;
	}
	
	public void setValueAt(Object value, int row, int col)
	{
		if(!isCellEditable(row, col))
			return;
		String s = value.toString();
		if(s.equals(""))
		{
			JOptionPane.showMessageDialog(null, "��������Ϊ�գ�", "����",JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(s.startsWith("-"))
		{
			JOptionPane.showMessageDialog(null, "��������Ϊ����", "����",JOptionPane.ERROR_MESSAGE);
			return;
		}
		try
		{
			Integer.parseInt(s);
			data[row][col] = value;
			fireTableCellUpdated(row, col);
		}
		catch(NumberFormatException e)
		{
			try 
			{
				Double.parseDouble(s);
				JOptionPane.showMessageDialog(null, "��������ΪС����", "����",JOptionPane.ERROR_MESSAGE);
			}
			catch (NumberFormatException nfe)
			{
				JOptionPane.showMessageDialog(null, "�����������", "����",JOptionPane.ERROR_MESSAGE);
			}
		}
}
	
	public void tableChanged(TableModelEvent e)
	{
		if(e.getType() == TableModelEvent.UPDATE)
		{
			//TODO ��֪�����ﻹ�費��Ҫд
			//����Ҫ���κ��£���
			//�ܹ�ֱ���޸�data�������ֵ������
			//Ȼ����ʾ��jtable�����
		}
	}
}