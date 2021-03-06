package body;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

import body.Reagent;

public class ReagentList
{

	private ArrayList<Reagent> r = new ArrayList<Reagent>();
	private int length;
	private File file;

	public ReagentList(File f) throws NumberFormatException, IOException    //从文件读入，初始化
	{
		file=f;
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

	public int add(Reagent reagent) throws FileNotFoundException            //添加一个reagent，返回插入的行数
	{
		int i;
		for (i = 0; i < length; i++)
		{
			if(r.get(i).getcabinet().equals(reagent.getcabinet()))
				break;
		}
		r.add(i, reagent);
		length++;
		output(file);
		return i;
	}

	public void delete(int i) throws FileNotFoundException                                               //删除一个reagent
	{
		r.remove(i);
		length--;
		output(file);
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
		for (int i = 0; i < length; i++)
			str[i] = r.get(i).getshortterm();
		return str;
	}

	public int getlength()                                //取长度
	{
		return length;
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
	
	public Reagent get(int i)
	{
		return r.get(i);
	}
	
	public String[] getManu()
	{
		int n = r.size();
		Set<String> s = new TreeSet<>();
		for(int i = 0; i < n; i++)
		{
			s.add(r.get(i).getmanufacturer());
		}
		
		return (String[])s.toArray(new String[0]);
	}
	
	
	
}
