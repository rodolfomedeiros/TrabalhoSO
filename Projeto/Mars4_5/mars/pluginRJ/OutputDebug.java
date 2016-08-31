package mars.pluginRJ;

import mars.mips.hardware.Register;
import mars.util.SystemIO;

public class OutputDebug {
	public static void odProcessChange(ProcessControlBlock pcb){
		SystemIO.printString("Output PCB -> " + pcb.getPid());
		SystemIO.printString("\n -- Registradores -- ");
		for(Register p : pcb.getRegisters()){
			SystemIO.printString("\n "+p.getName()+": " + p.getValue());
		}
		SystemIO.printString("\n pc: " + pcb.getPc());
		SystemIO.printString("\n hi: " + pcb.getHi());
		SystemIO.printString("\n lo: " + pcb.getLo());
		SystemIO.printString("\n ---------------------------------------- ");
		SystemIO.printString("\n");
	}
}
