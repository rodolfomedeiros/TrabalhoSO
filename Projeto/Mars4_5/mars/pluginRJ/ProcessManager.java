package mars.pluginRJ;

public class ProcessManager {
	
	public static int pidId = 0;
	//tabelas
	private static TableProcessors tableERB = new TableProcessors();

	public static TableProcessors getTableERB() {
		return tableERB;
	}

	public static void setTableERB(TableProcessors table) {
		tableERB = table;
	}

	public static void createProcess(int[] regValue, int pc , int hi, int lo) {
		//adiciona pcb na tabela
		tableERB.addPCB(new ProcessControlBlock(++pidId, regValue, pc, hi, lo));
	}
	
	public static ProcessControlBlock processChange(int[] regValue, int pc , int hi, int lo){
		if(tableERB.updatePCB(regValue, pc, hi, lo)){
			tableERB.processChange();
			return tableERB.getPcbExec();
		}else return null;
	}

	public static ProcessControlBlock processTerminate() {
		if(tableERB.finalizeProcess()){
			return tableERB.getPcbExec();
		}
		return null;
	}
	
}
