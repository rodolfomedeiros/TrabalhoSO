package mars.pluginRJ.management.memory;

import java.util.Vector;

public class PageTable {

	private final int sizeTable = 16;
	private final int sizePageProcess = 4;
	private Vector<Page> table;
	
	public PageTable() {
		initializer();
	}
	
	private void initializer(){
		this.table = new Vector<Page>(sizeTable, 0);
	}

	public int getSizeTable() {
		return sizeTable;
	}

	public int getSizePageProcess() {
		return sizePageProcess;
	}
}
