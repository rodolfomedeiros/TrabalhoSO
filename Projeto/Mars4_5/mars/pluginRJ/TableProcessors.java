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
	
	public ProcessControlBlock getPcbExec(){
		if(!pidExec.equals("")){
			return PCBTable.remove(pidExec);
		}
		return null;
	}
	
	//adiciona pcb na tabela de processos
	public void addPCB(ProcessControlBlock pcb){
		PCBTable.put(pcb.getPid(), pcb);
		ReadyTable.add(pcb.getPid());
	}

	public void processChange() {
		String pidE = scherdulerAlgoritmQueue();
		if(!pidExec.equals("")){
			ReadyTable.add(pidExec);
		}	
		pidExec = pidE;
	}

	public boolean updatePCB(int[] regValue, int pc , int hi, int lo){
		if(!pidExec.equals("")){
			PCBTable.put(pidExec, new ProcessControlBlock(Integer.valueOf(pidExec), regValue, pc, hi, lo));
		}
		return true;
	}

	public boolean finalizeProcess(){
		pidExec = scherdulerAlgoritmQueue();
		return true;
	}	
	
	/*
	 * Algoritmos do escalonador
	 */

	public String scherdulerAlgoritmQueue(){
		if(!ReadyTable.isEmpty()){
			return ReadyTable.removeFirst();
		}
		return "";
	}

}
