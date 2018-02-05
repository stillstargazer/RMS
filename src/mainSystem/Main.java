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
		String s = (String) JOptionPane.showInputDialog(null,"请选择您的实验室：\n", "实验室选择", JOptionPane.PLAIN_MESSAGE, null, obj2, "足球");
		File f = new File("./"+s+".txt");
		try
		{
			ReagentList rl = new ReagentList(f);
			MainWindow mw = new MainWindow(rl);
		} catch (NumberFormatException | IOException e)
		{
			// TODO 自动生成的 catch 块
			JOptionPane.showMessageDialog(null, "并没有您实验室的数据！", "错误",JOptionPane.ERROR_MESSAGE);
		}
	}
}
