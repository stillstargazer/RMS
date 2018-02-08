package window;

import java.awt.Rectangle;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import body.ReagentList;

public class SearchWindow
{
	public SearchWindow(ReagentList rl, JTable tbl)
	{
		Object[] options = { "中文名", "英文名", "CAS" }; // 自定义按钮上的文字
		int m = JOptionPane.showOptionDialog(null, "请选择检索方式？", "提示", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		if (m == 0)
		{
			String temp = JOptionPane.showInputDialog(null, "请输入中文名：\n", "修改药品", JOptionPane.PLAIN_MESSAGE);
			int n = rl.findCHIname(temp);
			if (n == -1)
				JOptionPane.showMessageDialog(null, "没有找到对应药品！", "错误", JOptionPane.ERROR_MESSAGE);
			else
			{	//选择并自动滚动
				tbl.setRowSelectionInterval(n, n);
				int len = tbl.getModel().getRowCount();
				n = (n <= len - 7) ? (n + 6) : (len - 1);
				Rectangle rect = tbl.getCellRect(n, 0, true);
				tbl.scrollRectToVisible(rect);
			}
		}
		if (m == 1)
		{
			String temp = JOptionPane.showInputDialog(null, "请输入英文名：\n", "修改药品", JOptionPane.PLAIN_MESSAGE);
			int n = rl.findENGname(temp);
			if (n == -1)
				JOptionPane.showMessageDialog(null, "没有找到对应药品！", "错误", JOptionPane.ERROR_MESSAGE);
			else
			{	//选择并自动滚动
				tbl.setRowSelectionInterval(n, n);
				int len = tbl.getModel().getRowCount();
				n = (n <= len - 7) ? (n + 6) : (len - 1);
				Rectangle rect = tbl.getCellRect(n, 0, true);
				tbl.scrollRectToVisible(rect);
			}
		}
		if (m == 2)
		{
			String temp = JOptionPane.showInputDialog(null, "请输入CAS：\n", "修改药品", JOptionPane.PLAIN_MESSAGE);
			int n = rl.findCAS(temp);
			if (n == -1)
				JOptionPane.showMessageDialog(null, "没有找到对应药品！", "错误", JOptionPane.ERROR_MESSAGE);
			else
			{	//选择并自动滚动
				tbl.setRowSelectionInterval(n, n);
				int len = tbl.getModel().getRowCount();
				n = (n <= len - 7) ? (n + 6) : (len - 1);
				Rectangle rect = tbl.getCellRect(n, 0, true);
				tbl.scrollRectToVisible(rect);
			}
		}
	}
}
