package body;

public class Reagent
{
	private String CHIname; // ҩƷ����
	private String ENGname; // Ӣ������
	private String CAS; // CAS
	private String purity; // ����
	private String specification; // ���
	private int number; // ����
	private String manufacturer; // ��������
	private int date; // ��������
	private String cabinet; // ���

	public Reagent(String str) // ���캯��
	{
		String[] st = str.split("	");
		CHIname = st[0];
		ENGname = st[1];
		CAS = st[2];
		purity = st[3];
		specification = st[4];
		if (st[5].equals(""))
			number = -1;
		else
			number = Integer.parseInt(st[5]);
		manufacturer = st[6];
		if (st[7].equals(""))
			date = -1;
		else
			date = Integer.parseInt(st[7]);
		cabinet = st[8];
	}

	public Reagent(String cHIname, String eNGname, String cAS, int num, String pur, String spec, String manu, int dat, String cab)
	{
		CHIname = cHIname;
		ENGname = eNGname;
		CAS = cAS;
		number = num;
		purity = pur;
		specification = spec;
		manufacturer = manu;
		date = dat;
		cabinet = cab;		
	}
	
 	public void editCHIname(String str) // ��������setter
	{
		CHIname = str;
	}

	public void editENGname(String str) // Ӣ������setter
	{
		ENGname = str;
	}

	public void editCAS(String str) // CASsetter
	{
		CAS = str;
	}

	public void editpurity(String str) // ����setter
	{
		purity = str;
	}

	public void editspecification(String str) // ���setter
	{
		specification = str;
	}

	public void editnumber(int n) // ����setter
	{
		number = n;
	}

	public void editmanufacturer(String str) // ������setter
	{
		manufacturer = str;
	}

	public void editdate(int d) // ��������setter
	{
		date = d;
	}

	public void editcabinet(String n) // ���setter
	{
		cabinet = n;
	}

	public String getCHIname() // ��������getter
	{
		return CHIname;
	}

	public String getENGname() // Ӣ������getter
	{
		return ENGname;
	}

	public String getCAS() // CASgetter
	{
		return CAS;
	}

	public String getpurity() // ����getter
	{
		return purity;
	}

	public String getspecification() // ���getter
	{
		return specification;
	}

	public int getnumber() // ����getter
	{
		return number;
	}

	public String getmanufacturer() // ������getter
	{
		return manufacturer;
	}

	public int getdate() // ��������getter
	{
		return date;
	}

	public String getcabinet() // ���getter
	{
		return cabinet;
	}

	public String show() // ��ʾ
	{
		String str;
		str = CHIname + "	" + ENGname + "	" + CAS + "	" + purity + "	" + specification + "	" + number + "	"
				+ manufacturer + "	" + date + "	" + cabinet;
		return str;
	}

	private String stringformat(String s, int n) // �����ַ�����ת��
	{
		String str;
		int numofspace;
		byte[] _byte = s.getBytes();
		if (s.equals(""))
			numofspace = n;
		else
			numofspace = n - _byte.length;
		byte[] temp = null;
		if (numofspace < 0)
		{
			System.arraycopy(_byte, 0, temp, 0, n);
			str = new String(temp);
			return str;
		}
		str = s;
		for (int i = 0; i < numofspace; i++)
			str = str + "  ";
		return str;
	}

	public String getshortterm()
	{
		String str;
		str = stringformat(CHIname, 40) + stringformat(ENGname, 60) + stringformat(CAS, 12) + number + "   " + cabinet;
		System.out.println(str);
		return str;
	}

}
