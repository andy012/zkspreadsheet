/* SelectionChangeCommand.java
 * 
{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		January 10, 2008 03:10:40 PM , Created by Dennis.Chen
}}IS_NOTE

Copyright (C) 2007 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under Lesser GPL Version 2.1 in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
*/
package org.zkoss.zss.ui.au.in;


import java.util.Map;

import org.zkoss.lang.Objects;
import org.zkoss.poi.ss.usermodel.Cell;
import org.zkoss.poi.ss.usermodel.Row;
import org.zkoss.zk.au.AuRequest;
import org.zkoss.zk.mesg.MZk;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zss.model.sys.XBook;
import org.zkoss.zss.model.sys.XSheet;
import org.zkoss.zss.ui.Spreadsheet;
import org.zkoss.zss.ui.event.SelectionChangeEvent;
import org.zkoss.zss.ui.event.CellSelectionEvent;
import org.zkoss.zss.ui.impl.XUtils;
import org.zkoss.zss.ui.sys.SpreadsheetInCtrl;
/**
 * A Command (client to server) for handling cell selection
 * @author Dennis.Chen
 *
 */
public class SelectionChangeCommand implements Command {

	public void process(AuRequest request) {
		final Component comp = request.getComponent();
		if (comp == null)
			throw new UiException(MZk.ILLEGAL_REQUEST_COMPONENT_REQUIRED, this);
		final Map data = (Map) request.getData();
		if (data == null || data.size() != 10)
			throw new UiException(MZk.ILLEGAL_REQUEST_WRONG_DATA, new Object[] {Objects.toString(data), this});
		
		String sheetId= (String) data.get("sheetId");
		
		XSheet sheet = ((Spreadsheet) comp).getSelectedXSheet();
		if (!XUtils.getSheetUuid(sheet).equals(sheetId))
			return;
		
		final XBook book = (XBook) sheet.getWorkbook();
		final int maxcol = book.getSpreadsheetVersion().getLastColumnIndex();
		final int maxrow = book.getSpreadsheetVersion().getLastRowIndex();
		
		int action = (Integer) data.get("action");
		
		int selectType = action & 0x0F;
		
		int left = (Integer) data.get("left");
		int top = (Integer) data.get("top");
		int right = selectType == CellSelectionEvent.SELECT_ROW ? maxcol : (Integer) data.get("right");
		int bottom = selectType == CellSelectionEvent.SELECT_COLUMN ? maxrow : (Integer) data.get("bottom");
		int orgileft = (Integer) data.get("orgileft");
		int orgitop = (Integer) data.get("orgitop");
		int orgiright = selectType == CellSelectionEvent.SELECT_ROW ? maxcol : (Integer) data.get("orgiright");
		int orgibottom = selectType == CellSelectionEvent.SELECT_COLUMN ? maxrow : (Integer) data.get("orgibottom");
		
		final SelectionChangeEvent evt = new SelectionChangeEvent(
				org.zkoss.zss.ui.event.Events.ON_SELECTION_CHANGE, comp, sheet,
				action, left, top, right, bottom, orgileft, orgitop, orgiright,
				orgibottom);

		SpreadsheetInCtrl ctrl = ((SpreadsheetInCtrl)((Spreadsheet)comp).getExtraCtrl());
		ctrl.setSelectionRect(left, top, right, bottom);	
		
		Events.postEvent(evt);
	}
}