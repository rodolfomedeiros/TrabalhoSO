package mars.pluginRJ.management.memory;

import java.util.Vector;

import mars.util.SystemIO;

public class MemoryManagement {
	
	private static MemoryManagement memoryManagement;
	
	public static void ResetInstance(){
		memoryManagement = null;
	}
	
	public static MemoryManagement getInstace(){
		if(memoryManagement == null){
			memoryManagement = new MemoryManagement();
		}
		
		return memoryManagement;
	}
	
	private PageTable pageTable;
	private Vector<String> mapProcess;
	private int sizeProcess;
	
	public MemoryManagement(){
		initilizer();
	}
	
	private void initilizer() {
		setPageTable(new PageTable());
		setSizeProcess(pageTable.getWidthTable()/pageTable.getWidthPageProcess());
		setMapProcess(new Vector<String>(sizeProcess, 0));
		for(int i=0; i < sizeProcess; i++){
			mapProcess.add(i, null);
		}
	}

	private void setSizeProcess(int i) {
		this.sizeProcess = i;
	}

	private void setPageTable(PageTable pageTable){
		this.pageTable = pageTable;
	}
	
	private void setMapProcess(Vector<String> mapProcess){
		this.mapProcess = mapProcess;
	}
	
	public void addToMapProcess(String pid){
		for(int i = 0; i < sizeProcess; i++){
			if(mapProcess.get(i) == null){
				mapProcess.add(i, pid);
				SystemIO.printString(" Process maping in memory = "+ pid + " index map = " + i);
				break;
			}
		}
	}
}
