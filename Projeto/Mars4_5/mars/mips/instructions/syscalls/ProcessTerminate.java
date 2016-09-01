package mars.mips.instructions.syscalls;

import mars.ProcessingException;
import mars.ProgramStatement;
import mars.mips.hardware.RegisterFile;
import mars.pluginRJ.ProcessManager;
import mars.util.SystemIO;
import mars.pluginRJ.OutputDebug;
import mars.pluginRJ.ProcessControlBlock;

public class ProcessTerminate extends AbstractSyscall{

	public ProcessTerminate() {
		super(21, "ProcessTerminate");
	}

	@Override
	public void simulate(ProgramStatement statement) throws ProcessingException {
		ProcessControlBlock pcb = ProcessManager.processTerminate();
		
		//se existe algum processo para ser executado, as informações será adicionada nos registradores
		if(pcb != null){
			//debug
			//OutputDebug.odProcessChange(pcb);
			
			for(int i = 0; i < 32; i++){
				RegisterFile.updateRegister(i, pcb.getValueReg(i));
			}
			RegisterFile.setProgramCounter(pcb.getPc());
			RegisterFile.setHi(pcb.getHi());
			RegisterFile.setLo(pcb.getLo());
		}else{
			SystemIO.printString("Não possui mais nenhum processo para ser executado! Finalizando...");
			new SyscallExit();
		}
	}
}
