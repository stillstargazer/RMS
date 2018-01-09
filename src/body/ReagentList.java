package body;

import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import body.Reagent;

public class ReagentList<Reagant> {
	
	private ArrayList<Reagent> r=new ArrayList<Reagent>();
	private int length;
	
	public ReagentList(File f) throws NumberFormatException, IOException 
	{
		BufferedReader br=new BufferedReader(new FileReader(f));
		String temp=br.readLine();
		length=Integer.parseInt(temp);
		for (int i=0;i<length;i++)
		{
			temp=br.readLine();
			r.add(new Reagent(temp));
		}
	}
	
	public void add(Reagent reagent)
	{
		int i;
		for(i=0;i<length;i++)
			if (r.get(i).getcabinet()==reagent.getcabinet()+1)
				break;
		r.add(i, reagent);
		length++;
	}
	
	public void delete(int i)
	{
		r.remove(i);
		length--;
	}
	
	public void output(File f) throws FileNotFoundException
	{
		PrintStream out=new PrintStream(f);
		out.println(length);
		for(int i=0;i<r.size();i++)
			out.println(r.get(i).get());
	}
	
	public String[] getshortterm()
	{
		String[] str=new String[length];
		String string;
		for (int i=0;i<length;i++)
		{
			string=String.format("%-20s",r.get(i).getCHIname())+String.format("%-20s",r.get(i).getENGname())+String.format("%-20s",r.get(i).getCAS())
			+String.format("%-5s",String.valueOf(r.get(i).getnumber()))+String.format("%-5s",String.valueOf(r.get(i).getcabinet()));//更改字符串显示长度
			str[i]=string;
		}
		return str;
	}

}
