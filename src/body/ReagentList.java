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

	public ReagentList(File f) throws NumberFormatException, IOException    //���ļ����룬��ʼ��
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

	public void add(Reagent reagent)                                        //���һ��reagent
	{
		int i;
		for (i = 0; i < length; i++)
			if (r.get(i).getcabinet() == reagent.getcabinet() + 1)
				break;
		r.add(i, reagent);
		length++;
	}

	public void delete(int i)                                               //ɾ��һ��reagent
	{
		r.remove(i);
		length--;
	}

	public void output(File f) throws FileNotFoundException                 //������ļ�
	{
		PrintStream out = new PrintStream(f);
		out.println(length);
		for (int i = 0; i < r.size(); i++)
			out.println(r.get(i).show());
		out.close();
	}

	public String[] getshortterm()                        //TODO������Reagent�Ȼ��ֱ�ӵ���
	{
		String[] str = new String[length];
		for (int i = 0; i < length; i++)
			str[i] = r.get(i).getshortterm();
		return str;
	}

	public int getlength()                                //ȡ����
	{
		return length;
	}

	public int findCHIname(String s)                      //��ѯ��������
	{
		for(int i = 0; i < length; i++)
		{
			if(r.get(i).getCHIname().equals(s))
				return i;
		}
		return -1;
	}
	
	public int findENGname(String s)                      //��ѯӢ������
	{
		for(int i = 0; i < length; i++)
		{
			if(r.get(i).getENGname().equals(s))
				return i;
		}
		return -1;
	}
	
	public int findCAS(String s)                          //��ѯCAS
	{
		for(int i = 0; i < length; i++)
		{
			if(r.get(i).getCAS().equals(s))
				return i;
		}
		return -1;
	}
	
	
	
}
