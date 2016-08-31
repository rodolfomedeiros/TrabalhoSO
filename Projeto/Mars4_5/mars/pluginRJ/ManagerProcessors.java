package mars.pluginRJ;

import java.util.ArrayList;

import mars.mips.hardware.Register;
import mars.mips.hardware.RegisterFile;

public class ManagerProcessors {
	
	public static int pidId = 0;
	//tabelas
	private static TableProcessors tableERB = new TableProcessors();

	public static TableProcessors getTableERB() {
		return tableERB;
	}

	public static void setTableERB(TableProcessors table) {
		tableERB = table;
	}

	public static void createProcess(ArrayList<Register> reg, int hi, int lo, int pc) {
		//adiciona pcb na tabela
		tableERB.addPCB(new ProcessControlBlock(++pidId, hi, lo, pc, reg));
	}
	
	public static ProcessControlBlock processChange(int hi, int lo, int pc, ArrayList<Register> reg){
		if(tableERB.updatePCB(RegisterFile.getValue(33), RegisterFile.getValue(34), RegisterFile.getProgramCounter(), RegisterFile.getRegistersArray())){
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
