package window;

import java.io.FileNotFoundException;

import javax.swing.JOptionPane;

import body.ReagentList;

public class DeleteWindow
{
	public DeleteWindow(ReagentList rl, int i) throws FileNotFoundException
	{
		if (i==-1)
			JOptionPane.showMessageDialog(null, "您并没有选中数据！", "错误",JOptionPane.ERROR_MESSAGE);
		else
		{
			int n = JOptionPane.showConfirmDialog(null, "您确定要删除该药品吗？", "提示",JOptionPane.YES_NO_OPTION); //返回值为0或1
			if (n==0) rl.delete(i);
		}
	}
}
