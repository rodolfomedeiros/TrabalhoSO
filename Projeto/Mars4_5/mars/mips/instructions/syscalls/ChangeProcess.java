package mars.mips.instructions.syscalls;

import mars.ProcessingException;
import mars.ProgramStatement;
import mars.mips.hardware.Register;
import mars.mips.hardware.RegisterFile;
import mars.pluginRJ.ManagerProcessors;
import mars.pluginRJ.ProcessControlBlock;

public class ChangeProcess extends AbstractSyscall{

	public ChangeProcess() {
		super(20, "ChangeProcess");
	}

	@Override
	public void simulate(ProgramStatement statement) throws ProcessingException {
		
		//os argumentos são para guarda o estado do processo a ser mudado, o retorno é o novo processo é que irá utilizar a cpu
		ProcessControlBlock pcb = ManagerProcessors.processChange(	RegisterFile.getValue(33), 
																	RegisterFile.getValue(34),
																	RegisterFile.getProgramCounter(),
																	RegisterFile.getRegistersArray());
		
		for(Register i : pcb.getRegisters()){
			RegisterFile.updateRegister(i.getNumber(), i.getValue());
		}
		
		RegisterFile.setProgramCounter(pcb.getPc());
		RegisterFile.setHi(pcb.getHi());
		RegisterFile.setLo(pcb.getLo());
				
	}
	
}
