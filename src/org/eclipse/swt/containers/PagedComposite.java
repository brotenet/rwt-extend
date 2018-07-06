package org.eclipse.swt.containers;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.custom.CTabItem;

public class PagedComposite extends Canvas {
	private CTabFolder tabFolder;

	
	public PagedComposite(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(3, false));
		
		Composite container_pages = new Composite(this, SWT.NONE);
		container_pages.setLayout(new GridLayout(1, false));
		container_pages.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 3, 1));
		
		tabFolder = new CTabFolder(container_pages, SWT.FLAT | SWT.SINGLE);
		tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		tabFolder.setMRUVisible(false);
		
		Composite container_controls_left = new Composite(this, SWT.NONE);
		container_controls_left.setLayout(new FillLayout(SWT.HORIZONTAL));
		container_controls_left.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		
		Composite container_footer = new Composite(this, SWT.NONE);
		container_footer.setLayout(new FillLayout(SWT.HORIZONTAL));
		container_footer.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		
		Composite container_controls_right = new Composite(this, SWT.NONE);
		container_controls_right.setLayout(new FillLayout(SWT.HORIZONTAL));
		container_controls_right.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
	}
	
	public void setSelection(int selection) {
		//tabFolder.setSelection((CTabItem) tabFolder.getChildren()[selection]);
	}
	
	public int getSelection() {
		return tabFolder.getSelectionIndex();
	}
	
	public CTabFolder getPages() {
		return tabFolder;
	}
	

	@Override
	protected void checkSubclass() {}
}
