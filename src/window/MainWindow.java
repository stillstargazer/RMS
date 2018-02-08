package window;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.ToolTipManager;
import javax.swing.ListSelectionModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.awt.GridLayout;
import java.awt.Font;

import body.ReagentList;
import body.TblCellRenderer;
import body.TblModel;
import window.AdditionWindow;
import window.EditWindow;
import window.SearchWindow;
import window.DeleteWindow;

public class MainWindow extends JFrame
{

	private static final long serialVersionUID = -7467076691460643137L;
	
	private JTable reagentTable;
	
	private JPanel functionPane;
	private JButton add_reagent;
	private JButton delete_reagent;
	private JButton edit_reagent;
	private JButton find_reagent;

	private AdditionWindow aw;

	private Font buttonFont = new Font("微软雅黑", Font.BOLD, 30);
	private Font tableFont = new Font("微软雅黑", Font.PLAIN, 25);
	private Font tableHeaderFont = new Font("微软雅黑", Font.PLAIN, 28);
	private Font editorFont = new Font("微软雅黑", Font.PLAIN, 25);

	public MainWindow(ReagentList rl)
	{
		super("试剂管理系统");
		setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1000, 600);
		this.setBounds(100, 100, 1000, 600);
		getContentPane().setLayout(new BorderLayout(0, 0));

		functionPane = new JPanel();
		getContentPane().add(functionPane, BorderLayout.NORTH);
		functionPane.setLayout(new GridLayout(1, 4, 0, 0));

		add_reagent = new JButton("\u6DFB\u52A0\u836F\u54C1");
		add_reagent.setFont(buttonFont);
		functionPane.add(add_reagent);

		delete_reagent = new JButton("\u5220\u9664\u836F\u54C1");
		delete_reagent.setFont(buttonFont);
		functionPane.add(delete_reagent);

		edit_reagent = new JButton("\u4FEE\u6539\u836F\u54C1");
		edit_reagent.setFont(buttonFont);
		functionPane.add(edit_reagent);

		find_reagent = new JButton("\u67E5\u8BE2\u836F\u54C1");
		find_reagent.setFont(buttonFont);
		functionPane.add(find_reagent);

		TblModel tblmodel = new TblModel(rl); // 表格模型，负责更新reagentTable中的数据
		tblmodel.addTableModelListener(tblmodel);

		reagentTable = new JTable(tblmodel);

		reagentTable.setShowVerticalLines(false);
		reagentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		reagentTable.setFont(tableFont);
		reagentTable.setRowHeight(40);

		// 悬浮窗
		ToolTipManager ttm = ToolTipManager.sharedInstance();
		ttm.setInitialDelay(300);
		ttm.setDismissDelay(2500);
		ttm.setReshowDelay(0);
		reagentTable.addMouseMotionListener(new MouseAdapter()
		{
			
			public void mouseMoved(MouseEvent e)
			{
				// TODO 是只显示完整中文名、英文名，还是显示所有字段？？？
				int row = reagentTable.rowAtPoint(e.getPoint());
				int col = reagentTable.columnAtPoint(e.getPoint());
				reagentTable.setToolTipText(rl.get(row).getCHIname());			
				if (col == 0)
					ttm.setEnabled(true);
				else
					ttm.setEnabled(false);
			}
		});

		// 编辑时字体
		JTextField numberfield = new JTextField();
		numberfield.setFont(editorFont);
		numberfield.setHorizontalAlignment(JTextField.CENTER);
		DefaultCellEditor reagentTableCellEditor = new DefaultCellEditor(numberfield);
		reagentTable.setDefaultEditor(Number.class, reagentTableCellEditor);
		
		// 数字居中
		TblCellRenderer reagentTableCellRender = new TblCellRenderer();
		reagentTableCellRender.setHorizontalAlignment(JLabel.CENTER);
		reagentTable.setDefaultRenderer(Number.class, reagentTableCellRender);
		reagentTable.getColumn("数量").setCellRenderer(reagentTableCellRender);
		
		// 加入JScrollPane
		JScrollPane reagentPane = new JScrollPane(reagentTable);
		reagentPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		getContentPane().add(reagentPane, BorderLayout.CENTER);

		// 表头
		JTableHeader reagentPaneHeader = reagentTable.getTableHeader();
		reagentPaneHeader.setReorderingAllowed(false);
		reagentPaneHeader.setResizingAllowed(false);
		reagentPaneHeader.setFont(tableHeaderFont);
		
		// 设置列宽
		TableColumnModel reagentTableColumn = reagentTable.getColumnModel();
		reagentTableColumn.getColumn(0).setPreferredWidth(300);
		reagentTableColumn.getColumn(1).setPreferredWidth(300);
		reagentTableColumn.getColumn(2).setPreferredWidth(200);
		reagentTableColumn.getColumn(3).setPreferredWidth(100);
		reagentTableColumn.getColumn(4).setPreferredWidth(100);
		

		// ActionListener
		add_reagent.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (e.getSource() == add_reagent)
				{
					aw = new AdditionWindow(rl, reagentTable);
				}
			}
		});

		delete_reagent.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO 没有想好应该实现成什么样
				if (e.getSource() == delete_reagent)
				{
					try
					{
						DeleteWindow dw = new DeleteWindow(rl, reagentTable.getSelectedRow());
						tblmodel.update(rl);
						reagentTable.updateUI();
					} catch (FileNotFoundException e1)
					{
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}

				}
			}
		});

		edit_reagent.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (e.getSource() == edit_reagent)
				{
					int n = reagentTable.getSelectedRow();
					if(n == -1)
					{
						JOptionPane.showMessageDialog(null, "您并没有选中数据！", "错误",JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						EditWindow ew = new EditWindow(rl, n, reagentTable);
					}
				}
			}
		});

		find_reagent.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				SearchWindow sw = new SearchWindow(rl, reagentTable);
			}
		});

		this.setVisible(true);

	}
		
}
