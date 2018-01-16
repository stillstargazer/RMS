package body;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import body.Reagent;

public class ReagentList
{

	private ArrayList<Reagent> r = new ArrayList<Reagent>();
	private int length;

	public ReagentList(File f) throws NumberFormatException, IOException    //从文件读入，初始化
	{
		BufferedReader br = new BufferedReader(new FileReader(f));
		String temp = br.readLine();
		length = Integer.parseInt(temp);
		for (int i = 0; i < length; i++)
		{
			temp = br.readLine();
			r.add(new Reagent(temp));
		}
		br.close();
	}

	public void add(Reagent reagent)                                        //添加一个reagent
	{
		int i;
		for (i = 0; i < length; i++)
			if (r.get(i).getcabinet() == reagent.getcabinet() + 1)
				break;
		r.add(i, reagent);
		length++;
	}

	public void delete(int i)                                               //删除一个reagent
	{
		r.remove(i);
		length--;
	}

	public void output(File f) throws FileNotFoundException                 //输出到文件
	{
		PrintStream out = new PrintStream(f);
		out.println(length);
		for (int i = 0; i < r.size(); i++)
			out.println(r.get(i).show());
		out.close();
	}

	public String[] getshortterm()                        //TODO：放在Reagent里，然后直接调用
	{
		String[] str = new String[length];
		String string;
		for (int i = 0; i < length; i++)
		{
			string = String.format("%-30s", r.get(i).getCHIname()) + String.format("%-20s", r.get(i).getENGname())
					+ String.format("%-20s", r.get(i).getCAS())
					+ String.format("%-5s", String.valueOf(r.get(i).getnumber()))
					+ String.format("%-5s", String.valueOf(r.get(i).getcabinet()));// 更改字符串显示长度
			str[i] = string;
		}
		return str;
	}

	public int getlength()                                //取长度
	{
		return length;
	}
	
	private int numofstring(String s)                     //中文字符个数转换
	{
		if (s.equals(""))
			return 0;
		byte[] _byte = s.getBytes();
		return _byte.length;
	}

	public int findCHIname(String s)                      //查询中文名称
	{
		for(int i = 0; i < length; i++)
		{
			if(r.get(i).getCHIname().equals(s))
				return i;
		}
		return -1;
	}
	
	public int findENGname(String s)                      //查询英文名称
	{
		for(int i = 0; i < length; i++)
		{
			if(r.get(i).getENGname().equals(s))
				return i;
		}
		return -1;
	}
	
	public int findCAS(String s)                          //查询CAS
	{
		for(int i = 0; i < length; i++)
		{
			if(r.get(i).getCAS().equals(s))
				return i;
		}
		return -1;
	}
	
	
	
}
