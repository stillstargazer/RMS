package body;

public class Reagent
{
	private String CHIname; // 药品名称
	private String ENGname; // 英文名称
	private String CAS; // CAS
	private String purity; // 纯度
	private String specification; // 规格
	private int number; // 数量
	private String manufacturer; // 生产厂家
	private int date; // 生产日期
	private String cabinet; // 柜号

	public Reagent(String str) // 构造函数
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
	
 	public void editCHIname(String str) // 中文名称setter
	{
		CHIname = str;
	}

	public void editENGname(String str) // 英文名称setter
	{
		ENGname = str;
	}

	public void editCAS(String str) // CASsetter
	{
		CAS = str;
	}

	public void editpurity(String str) // 纯度setter
	{
		purity = str;
	}

	public void editspecification(String str) // 规格setter
	{
		specification = str;
	}

	public void editnumber(int n) // 数量setter
	{
		number = n;
	}

	public void editmanufacturer(String str) // 生产商setter
	{
		manufacturer = str;
	}

	public void editdate(int d) // 生产日期setter
	{
		date = d;
	}

	public void editcabinet(String n) // 柜号setter
	{
		cabinet = n;
	}

	public String getCHIname() // 中文名称getter
	{
		return CHIname;
	}

	public String getENGname() // 英文名称getter
	{
		return ENGname;
	}

	public String getCAS() // CASgetter
	{
		return CAS;
	}

	public String getpurity() // 纯度getter
	{
		return purity;
	}

	public String getspecification() // 规格getter
	{
		return specification;
	}

	public int getnumber() // 数量getter
	{
		return number;
	}

	public String getmanufacturer() // 生产商getter
	{
		return manufacturer;
	}

	public int getdate() // 生产日期getter
	{
		return date;
	}

	public String getcabinet() // 柜号getter
	{
		return cabinet;
	}

	public String show() // 显示
	{
		String str;
		str = CHIname + "	" + ENGname + "	" + CAS + "	" + purity + "	" + specification + "	" + number + "	"
				+ manufacturer + "	" + date + "	" + cabinet;
		return str;
	}

	private String stringformat(String s, int n) // 中文字符个数转换
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
