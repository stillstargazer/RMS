package body;

public class Reagent
{
	private String CHIname;// ҩƷ����
	private String ENGname;// Ӣ������
	private String CAS; // CAS
	private String purity;// ����
	private String specification;// ���
	private int number;// ����
	private String manufacturer;// ��������
	private int date;// ��������
	private int cabinet;// ���

	public Reagent(String str)
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
		if (st[8].equals(""))
			cabinet = -1;
		else
			cabinet = Integer.parseInt(st[8]);
	}

	public void editCHIname(String str)
	{
		CHIname = str;
	}

	public void editENGname(String str)
	{
		ENGname = str;
	}

	public void editCAS(String str)
	{
		CAS = str;
	}

	public void editpurity(String str)
	{
		purity = str;
	}

	public void editspecification(String str)
	{
		specification = str;
	}

	public void editnumber(int n)
	{
		number = n;
	}

	public void editmanufacturer(String str)
	{
		manufacturer = str;
	}

	public void editdate(int d)
	{
		date = d;
	}

	public void editcabinet(int n)
	{
		cabinet = n;
	}

	public String getCHIname()
	{
		return CHIname;
	}

	public String getENGname()
	{
		return ENGname;
	}

	public String getCAS()
	{
		return CAS;
	}

	public String getpurity()
	{
		return purity;
	}

	public String getspecification()
	{
		return specification;
	}

	public int getnumber()
	{
		return number;
	}

	public String getmanufacturer()
	{
		return manufacturer;
	}

	public int getdate()
	{
		return date;
	}

	public int getcabinet()
	{
		return cabinet;
	}

	public String get()
	{
		String str;
		str = CHIname + "	" + ENGname + "	" + CAS + "	" + purity + "	" + specification + "	" + number + "	"
				+ manufacturer + "	" + date + "	" + cabinet;
		return str;
	}

}
