package window;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridLayout;
import java.awt.Font;

import body.ReagentList;
import window.AdditionWindow;
import window.EditWindow;
import window.SearchWindow;

public class MainWindow extends JFrame
{

	private static final long serialVersionUID = -7467076691460643137L;
	private JPanel functionPane;
	private JButton add_reagent;
	private JButton delete_reagent;
	private JButton set_reagent;
	private JButton find_reagent;
	
	private AdditionWindow aw;
	
	private Font buttonFont = new Font("微软雅黑", Font.BOLD, 30);
	private Font tableFont = new Font("微软雅黑", Font.PLAIN, 25);
	private Font tableHeaderFont = new Font("微软雅黑", Font.PLAIN, 28);
	
	
	class TblModel extends AbstractTableModel implements TableModelListener
	{

		private static final long serialVersionUID = 1L;

		final private String[] columnNames = {"中文名", "英文名", "CAS", "剩余", "柜号"};
		
		final private Object[][] data;
		
		public TblModel(ReagentList rl)
		{
			data = to2Dobj(rl);
		}
		
		private Object[][] to2Dobj(ReagentList rl)
		{
			int num = rl.getlength();
			Object[][] realist = new Object[num][5];
			for(int i = 0; i < num; i++)
			{
				realist[i][0] = rl.get(i).getCHIname();
				realist[i][1] = rl.get(i).getENGname();
				realist[i][2] = rl.get(i).getCAS();
				realist[i][3] = rl.get(i).getnumber();
				realist[i][4] = rl.get(i).getcabinet();
			}
			return realist;
		}			
		
		public int getColumnCount()
		{
			return columnNames.length;
		}
		
		public int getRowCount()
		{
			return data.length;
		}
		
		public Object getValueAt(int row, int col)
		{
			if(row < 0 || row > data.length)
				return null;
			return data[row][col];
		}
		
		public String getColumnName(int col)
		{
			return columnNames[col];
		}
		
		public Class getColumnClass(int col)
		{
			return getValueAt(0, col).getClass();
		}
		
		public boolean isCellEditable(int row, int col)
		{
			if(col == 3)
				return true;
			else
				return false;
		}
		
		public void setValueAt(Object value, int row, int col)
		{
			if(!isCellEditable(row, col))
				return ;
			if(value instanceof Double || value instanceof Integer)
			{
					data[row][col] = value;
					fireTableCellUpdated(row, col);
			}
			else
			{
				try
				{
					data[row][col] = new Float(value.toString());
					fireTableCellUpdated(row, col);
				} catch (NumberFormatException e)
				{
					JOptionPane.showMessageDialog(MainWindow.this, "剩余列只接受数字型数据！");
				}
			}

		}
		
		public void tableChanged(TableModelEvent e)
		{
			if(e.getType() == TableModelEvent.UPDATE)
			{
				//TODO 不知道这里还需不需要写
				//不需要做任何事？？
				//能够直接修改data里面的数值？？？
				//然后显示到jtable里？？？
			}
		}
	}

	
	public MainWindow(ReagentList rl)
	{
		super("试剂管理系统");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1000, 600);
		getContentPane().setLayout(new BorderLayout(0, 0));
		

		
		functionPane = new JPanel();
		getContentPane().add(functionPane, BorderLayout.NORTH);
		functionPane.setLayout(new GridLayout(1, 4, 0, 0));
		
		add_reagent = new JButton("\u6DFB\u52A0\u836F\u54C1");
		add_reagent.setFont(buttonFont);
		functionPane.add(add_reagent);
		add_reagent.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				//System.out.println("hahaha");
				if(e.getSource() == add_reagent)
				{
					
					aw = new AdditionWindow(rl);
				}				
			} 
			
		});
		
		delete_reagent = new JButton("\u5220\u9664\u836F\u54C1");
		delete_reagent.setFont(buttonFont);
		functionPane.add(delete_reagent);
		delete_reagent.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				//TODO 没有想好应该实现成什么样
				
			}
		});
		
		set_reagent = new JButton("\u4FEE\u6539\u836F\u54C1");
		set_reagent.setFont(buttonFont);
		functionPane.add(set_reagent);
		set_reagent.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				EditWindow ew = new EditWindow();				
			}
		});
		
		find_reagent = new JButton("\u67E5\u8BE2\u836F\u54C1");
		find_reagent.setFont(buttonFont);
		functionPane.add(find_reagent);
		find_reagent.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				SearchWindow sw = new SearchWindow();			
			}
		});
		
		TblModel tblmodel = new TblModel(rl);              //表格模型，负责更新reagentTable中的数据
		tblmodel.addTableModelListener(tblmodel);
		
		JTable reagentTable = new JTable(tblmodel);        
		
		reagentTable.setShowVerticalLines(false);
		reagentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		reagentTable.setFont(tableFont);
		reagentTable.setRowHeight(40);
		
		//悬浮窗
		reagentTable.addMouseMotionListener(new MouseAdapter()
		{
			public void mouseMoved(MouseEvent e)
			{
				//TODO 是只显示完整中文名、英文名，还是显示所有字段？？？
				int row = reagentTable.rowAtPoint(e.getPoint());
				int col = reagentTable.columnAtPoint(e.getPoint());
				if(col == 0)
				{
					reagentTable.setToolTipText(rl.get(row).getCHIname());
				}
			}
		});
		
		//数字居中
		DefaultTableCellRenderer reagentTableCellRender = new DefaultTableCellRenderer();
		reagentTableCellRender.setHorizontalAlignment(JLabel.CENTER);;
		reagentTable.setDefaultRenderer(Number.class, reagentTableCellRender);
		
		JScrollPane reagentPane = new JScrollPane(reagentTable);
		reagentPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		getContentPane().add(reagentPane, BorderLayout.CENTER);

		//表头
		JTableHeader reagentPaneHeader = reagentTable.getTableHeader();
		reagentPaneHeader.setReorderingAllowed(false);
		reagentPaneHeader.setResizingAllowed(false);
		reagentPaneHeader.setFont(tableHeaderFont);
		
		
		this.setVisible(true);
		
	}

	
}









