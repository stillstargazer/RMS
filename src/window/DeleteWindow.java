package window;

import java.io.FileNotFoundException;

import javax.swing.JOptionPane;

import body.ReagentList;

public class DeleteWindow
{
	public DeleteWindow(ReagentList rl, int i) throws FileNotFoundException
	{
		if (i==-1)
			JOptionPane.showMessageDialog(null, "����û��ѡ�����ݣ�", "����",JOptionPane.ERROR_MESSAGE);
		else
		{
			int n = JOptionPane.showConfirmDialog(null, "��ȷ��Ҫɾ����ҩƷ��", "��ʾ",JOptionPane.YES_NO_OPTION); //����ֵΪ0��1
			if (n==0) rl.delete(i);
		}
	}
}
