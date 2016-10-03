package mars.mips.instructions.syscalls;

import mars.ProcessingException;
import mars.ProgramStatement;
import mars.mips.hardware.RegisterFile;
import mars.pluginRJ.management.memory.MemoryManagement;
import mars.pluginRJ.management.process.ProcessControlBlock;
import mars.pluginRJ.management.process.ProcessManager;
import mars.util.SystemIO;

public class ProcessChange extends AbstractSyscall{

	public ProcessChange() {
		super(20, "ProcessChange");
	}

	@Override
	public void simulate(ProgramStatement statement) throws ProcessingException {
		ProcessControlBlock pcb = ProcessManager.processChange(		RegisterFile.getRegsValue(),
																	RegisterFile.getProgramCounter(),
																	RegisterFile.getValue(33),
																	RegisterFile.getValue(34)
																);
		
		//atualização do processo no registradores
		
		// Caso nao exista processos pcb vai ser nulo.
		if(pcb != null){
			
			//atribuindo na memory o processo que esta sendo executado
			MemoryManagement.getInstace().setExecuteProcessPid(pcb.getPid());
			
			// Caso seja a primeira vez rodando o processo, nao ha informacoes para serem atualizadas
			if(!pcb.isNull()){

				for(int i = 0; i < 32; i++){
					RegisterFile.updateRegister(i, pcb.getValueReg(i));
				}
				
				RegisterFile.setHi(pcb.getHi());
				RegisterFile.setLo(pcb.getLo());
			}
		
			RegisterFile.setProgramCounter(pcb.getPc());
			
			SystemIO.printString("\n\n********* Pocesso: p" + pcb.getPid() + " Entrando no processador:\n");
		} 
	}
}
