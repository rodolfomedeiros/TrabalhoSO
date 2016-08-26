package mars.pluginRJ;

import java.util.ArrayList;

import mars.mips.hardware.Register;

public class ManagerProcessors {
	
	public static int pidId = 0;
	//tabelas
	private static TableProcessors tableERB = new TableProcessors();
	//Escalonador
	private static Escalonador escalonador = new Escalonador();
	
	public ManagerProcessors(){
		setEscalonador(new Escalonador());
		setTableERB(new TableProcessors());
	}

	public static TableProcessors getTableERB() {
		return tableERB;
	}

	public static void setTableERB(TableProcessors table) {
		tableERB = table;
	}

	public static Escalonador getEscalonador() {
		return escalonador;
	}

	public static void setEscalonador(Escalonador escalonador) {
		ManagerProcessors.escalonador = escalonador;
	}

	public static void createProcess(ArrayList<Register> reg, int hi, int lo, int pc) {
		//adiciona pcb na tabela
		tableERB.addPCB(new ProcessControlBlock(++pidId, hi, lo, pc, reg));
	}
	
	public static ProcessControlBlock processChange(){
		String pidE = escalonador.algoritmQueue(tableERB.getReadyTable());
		tableERB.processChange(pidE);
		return tableERB.getPcbExec();
	}
	
}
