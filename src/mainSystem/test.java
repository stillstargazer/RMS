package mainSystem;

import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;

import body.Reagent;
import body.ReagentList;
import window.MainWindow;

public class test
{

	public static void main(String args[]) throws NumberFormatException, IOException
	{
		File f = new File("./test.txt");
		ReagentList rl = new ReagentList(f);
		System.out.println(rl.getshortterm()[0]);
		MainWindow mw = new MainWindow(rl);

		System.out.println("FINISHED");
	}

}
