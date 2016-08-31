package mars.mips.instructions.syscalls;

import mars.ProcessingException;
import mars.ProgramStatement;
import mars.mips.hardware.RegisterFile;
import mars.pluginRJ.ProcessManager;
import mars.pluginRJ.OutputDebug;
import mars.pluginRJ.ProcessControlBlock;

public class ProcessChange extends AbstractSyscall{

	public ProcessChange() {
		super(20, "ProcessChange");
	}

	@Override
	public void simulate(ProgramStatement statement) throws ProcessingException {
		
		//os argumentos são para guarda o estado do processo a ser mudado, o retorno é o novo processo é que irá utilizar a cpu
		ProcessControlBlock pcb = ProcessManager.processChange(		RegisterFile.getRegsValue(), 
																	RegisterFile.getProgramCounter(),
																	RegisterFile.getValue(33),
																	RegisterFile.getValue(34));
		
		if(pcb != null){
			if(!pcb.isNull()){
				//debug
				OutputDebug.odProcessChange(pcb);
			
				for(int i = 0; i < 32; i++){
					RegisterFile.updateRegister(i, pcb.getValueReg(i));
				}
				RegisterFile.setHi(pcb.getHi());
				RegisterFile.setLo(pcb.getLo());
			}
		
			RegisterFile.setProgramCounter(pcb.getPc());			
		}
	}
}
