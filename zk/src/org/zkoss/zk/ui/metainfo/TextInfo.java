/* TextInfo.java

	Purpose:
		
	Description:
		
	History:
		Sat Sep  1 01:14:10     2007, Created by tomyeh

Copyright (C) 2007 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under LGPL Version 2.1 in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
*/
package org.zkoss.zk.ui.metainfo;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.xel.ExValue;
import org.zkoss.zk.xel.EvaluatorRef;

/**
 * Represents a text.
 *
 * @author tomyeh
 * @since 3.0.0
 */
public class TextInfo extends LeafInfo {
	private final ExValue _text;

	/** Constructor
	 * @since 6.0.0
	 */
	public TextInfo(String text) {
		_text = text != null ? new ExValue(text, String.class): null;
	}
	public TextInfo(NodeInfo parent, String text) {
		super(parent);
		_text = text != null ? new ExValue(text, String.class): null;
	}

	/** Returns the raw value (text).
	 */
	public String getRawValue() {
		return _text.getRawValue();
	}

	/** Returns the value after evaluation (might be null).
	 */
	public String getValue(Page page) {
		return _text != null ? (String)_text.getValue(_evalr, page): null;
	}
	/** Returns the value after evaluation.
	 */
	public String getValue(Component comp) {
		return _text != null ? (String)_text.getValue(_evalr, comp): null;
	}

	public String toString() {
		return "[TextInfo: " + _text + ']';
	}
}
