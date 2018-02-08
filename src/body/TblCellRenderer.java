package body;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TblCellRenderer extends DefaultTableCellRenderer
{
	DefaultTableCellRenderer dce;
	
	public TblCellRenderer()
	{
		dce = new DefaultTableCellRenderer();
	}
	
	public Component getTableCellRenderer(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
	{
		Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		if(value != null && value instanceof Integer)
		{
			this.setText(value.toString());
		}
		else if(value instanceof Double)
		{
			this.setText(String.valueOf(Math.floor(Double.parseDouble(value.toString()))));
		}
		
		return c;
	}
}
