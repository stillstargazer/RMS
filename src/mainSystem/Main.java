package mainSystem;

import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

import body.ReagentList;
import window.MainWindow;

public class Main
{
	public static void main(String args[])
	{
		Object[] obj2 ={ "413", "411"};  
		String s = (String) JOptionPane.showInputDialog(null,"��ѡ������ʵ���ң�\n", "ʵ����ѡ��", JOptionPane.PLAIN_MESSAGE, null, obj2, "����");
		File f = new File("./"+s+".txt");
		try
		{
			ReagentList rl = new ReagentList(f);
			MainWindow mw = new MainWindow(rl);
		} catch (NumberFormatException | IOException e)
		{
			// TODO �Զ����ɵ� catch ��
			JOptionPane.showMessageDialog(null, "��û����ʵ���ҵ����ݣ�", "����",JOptionPane.ERROR_MESSAGE);
		}
	}
}
