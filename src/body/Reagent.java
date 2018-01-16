package body;

public class Reagent
{
	private String CHIname;                   // 药品名称
	private String ENGname;                   // 英文名称
	private String CAS;                       // CAS
	private String purity;                    // 纯度
	private String specification;             // 规格
	private int number;                       // 数量
	private String manufacturer;              // 生产厂家
	private int date;                         // 生产日期
	private int cabinet;                      // 柜号

	public Reagent(String str)                //构造函数
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

	public void editCHIname(String str)       //中文名称setter
	{
		CHIname = str;
	}

	public void editENGname(String str)       //英文名称setter
	{
		ENGname = str;
	}

	public void editCAS(String str)           //CASsetter
	{
		CAS = str;
	}

	public void editpurity(String str)        //纯度setter
	{
		purity = str;
	}

	public void editspecification(String str) //规格setter
	{
		specification = str;
	}

	public void editnumber(int n)             //数量setter
	{
		number = n;
	}

	public void editmanufacturer(String str)  //生产商setter
	{
		manufacturer = str;
	}

	public void editdate(int d)               //生产日期setter
	{
		date = d;
	}

	public void editcabinet(int n)            //柜号setter
	{
		cabinet = n;
	}

	public String getCHIname()                //中文名称getter
	{
		return CHIname;
	}

	public String getENGname()                //英文名称getter
	{
		return ENGname;
	}

	public String getCAS()                    //CASgetter
	{
		return CAS;
	}

	public String getpurity()                 //纯度getter
	{
		return purity;
	}

	public String getspecification()          //规格getter
	{
		return specification;
	}

	public int getnumber()                    //数量getter
	{
		return number;
	}

	public String getmanufacturer()           //生产商getter
	{
		return manufacturer;
	}

	public int getdate()                      //生产日期getter
	{
		return date;
	}

	public int getcabinet()                   //柜号getter
	{
		return cabinet;
	}

	public String show()                       //显示
	{
		String str;
		str = CHIname + "	" + ENGname + "	" + CAS + "	" + purity + "	" + specification + "	" + number + "	"
				+ manufacturer + "	" + date + "	" + cabinet;
		return str;
	}

}
