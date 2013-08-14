/* VerticalAlignHandler.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		2013/8/5 , Created by dennis
}}IS_NOTE

Copyright (C) 2013 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.zss.ui.impl.ua;

import org.zkoss.util.resource.Labels;
import org.zkoss.zss.api.CellOperationUtil;
import org.zkoss.zss.api.Range;
import org.zkoss.zss.api.Ranges;
import org.zkoss.zss.api.Rect;
import org.zkoss.zss.api.model.Sheet;
import org.zkoss.zss.api.model.CellStyle.VerticalAlignment;
import org.zkoss.zss.ui.UserActionContext;
import org.zkoss.zss.ui.impl.undo.CellStyleAction;
import org.zkoss.zss.ui.sys.UndoableActionManager;

/**
 * @author dennis
 *
 */
public class VerticalAlignHandler extends AbstractProtectedHandler {

	VerticalAlignment _type;
	
	
	public VerticalAlignHandler(VerticalAlignment type) {
		this._type = type;
	}


	/* (non-Javadoc)
	 * @see org.zkoss.zss.ui.sys.ua.impl.AbstractHandler#processAction(org.zkoss.zss.ui.UserActionContext)
	 */
	@Override
	protected boolean processAction(UserActionContext ctx) {
		Sheet sheet = ctx.getSheet();
		Rect selection = ctx.getSelection();
		Range range = Ranges.range(sheet, selection);
		if(range.isProtected()){
			showProtectMessage();
			return true;
		}
		UndoableActionManager uam = ctx.getSpreadsheet().getUndoableActionManager();
		uam.doAction(new CellStyleAction(Labels.getLabel("zss.undo.cellStyle"),sheet, selection.getRow(), selection.getColumn(), 
			selection.getLastRow(), selection.getLastColumn(), 
			CellOperationUtil.getVerticalAligmentApplier(_type)));
		return true;
	}

}
