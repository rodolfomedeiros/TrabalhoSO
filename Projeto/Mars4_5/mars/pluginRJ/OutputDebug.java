package mars.pluginRJ;

import mars.util.SystemIO;

public class OutputDebug {
	public static void odProcessChange(ProcessControlBlock pcb){
		SystemIO.printString("Output PCB -> " + pcb.getPid());
		SystemIO.printString("\n -- Registradores -- ");
		for(int i = 0; i < 32; i++){
			SystemIO.printString("\n RegNum "+i+": "+pcb.getValueReg(i));
		}
		SystemIO.printString("\n pc: " + pcb.getPc());
		SystemIO.printString("\n hi: " + pcb.getHi());
		SystemIO.printString("\n lo: " + pcb.getLo());
		SystemIO.printString("\n ---------------------------------------- ");
		SystemIO.printString("\n");
	}
}
