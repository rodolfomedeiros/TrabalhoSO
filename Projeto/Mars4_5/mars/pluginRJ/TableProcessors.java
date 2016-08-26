package mars.pluginRJ;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class TableProcessors {
	
	//processo que está executando
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
		ReadyTable.add(pcb.getPid());
	}

	public void processChange(String pidE) {
		ReadyTable.addLast(getPidExec());		
		setPidExec(pidE);
		ReadyTable.remove(pidE);
	}
	
	public ProcessControlBlock getPcbExec(){
		return PCBTable.get(pidExec);
	}

}
