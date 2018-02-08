package window;

import java.awt.Rectangle;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import body.ReagentList;

public class SearchWindow
{
	public SearchWindow(ReagentList rl, JTable tbl)
	{
		Object[] options = { "������", "Ӣ����", "CAS" }; // �Զ��尴ť�ϵ�����
		int m = JOptionPane.showOptionDialog(null, "��ѡ�������ʽ��", "��ʾ", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		if (m == 0)
		{
			String temp = JOptionPane.showInputDialog(null, "��������������\n", "�޸�ҩƷ", JOptionPane.PLAIN_MESSAGE);
			int n = rl.findCHIname(temp);
			if (n == -1)
				JOptionPane.showMessageDialog(null, "û���ҵ���ӦҩƷ��", "����", JOptionPane.ERROR_MESSAGE);
			else
			{	//ѡ���Զ�����
				tbl.setRowSelectionInterval(n, n);
				int len = tbl.getModel().getRowCount();
				n = (n <= len - 7) ? (n + 6) : (len - 1);
				Rectangle rect = tbl.getCellRect(n, 0, true);
				tbl.scrollRectToVisible(rect);
			}
		}
		if (m == 1)
		{
			String temp = JOptionPane.showInputDialog(null, "������Ӣ������\n", "�޸�ҩƷ", JOptionPane.PLAIN_MESSAGE);
			int n = rl.findENGname(temp);
			if (n == -1)
				JOptionPane.showMessageDialog(null, "û���ҵ���ӦҩƷ��", "����", JOptionPane.ERROR_MESSAGE);
			else
			{	//ѡ���Զ�����
				tbl.setRowSelectionInterval(n, n);
				int len = tbl.getModel().getRowCount();
				n = (n <= len - 7) ? (n + 6) : (len - 1);
				Rectangle rect = tbl.getCellRect(n, 0, true);
				tbl.scrollRectToVisible(rect);
			}
		}
		if (m == 2)
		{
			String temp = JOptionPane.showInputDialog(null, "������CAS��\n", "�޸�ҩƷ", JOptionPane.PLAIN_MESSAGE);
			int n = rl.findCAS(temp);
			if (n == -1)
				JOptionPane.showMessageDialog(null, "û���ҵ���ӦҩƷ��", "����", JOptionPane.ERROR_MESSAGE);
			else
			{	//ѡ���Զ�����
				tbl.setRowSelectionInterval(n, n);
				int len = tbl.getModel().getRowCount();
				n = (n <= len - 7) ? (n + 6) : (len - 1);
				Rectangle rect = tbl.getCellRect(n, 0, true);
				tbl.scrollRectToVisible(rect);
			}
		}
	}
}
