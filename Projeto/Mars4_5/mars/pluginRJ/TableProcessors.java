package mars.pluginRJ;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class TableProcessors {
	
	//processo que está executanto
	private String pidExec;
	//Todos os processos
	private HashMap<String,ProcessControlBlock> PCBTable;
	//Processos prontos
	private LinkedList<String> ReadyTable;
	//Processos bloqueados
	private ArrayList<String> BlockedTable;
	
	public TableProcessors(){
		setPidExec("");
		setPCBTable(new HashMap<>());
		setReadyTable(new LinkedList<>());
		setBlockedTable(new ArrayList<>());
	}

	public String getPidExec() {
		return pidExec;
	}

	public void setPidExec(String pidExec) {
		this.pidExec = pidExec;
	}

	public HashMap<String, ProcessControlBlock> getPCBTable() {
		return PCBTable;
	}

	public void setPCBTable(HashMap<String, ProcessControlBlock> pCBTable) {
		PCBTable = pCBTable;
	}

	public LinkedList<String> getReadyTable() {
		return ReadyTable;
	}

	public void setReadyTable(LinkedList<String> readyTable) {
		ReadyTable = readyTable;
	}

	public ArrayList<String> getBlockedTable() {
		return BlockedTable;
	}

	public void setBlockedTable(ArrayList<String> blockedTable) {
		BlockedTable = blockedTable;
	}
	
	public void addPCB(ProcessControlBlock pcb){
		PCBTable.put(pcb.getPid(), pcb);
	}
	
	public boolean addReadyTable(String pid){
		return ReadyTable.add(pid);
	}
	
	public boolean addBlockedTable(String pid){
		return BlockedTable.add(pid);
	}

}
