package mars.mips.instructions.syscalls;

import mars.ProcessingException;
import mars.ProgramStatement;
import mars.mips.hardware.RegisterFile;
import mars.pluginRJ.ProcessManager;
import mars.util.SystemIO;
import mars.pluginRJ.OutputDebug;
import mars.pluginRJ.ProcessControlBlock;

public class ProcessChange extends AbstractSyscall{

	public ProcessChange() {
		super(20, "ProcessChange");
	}

	@Override
	public void simulate(ProgramStatement statement) throws ProcessingException {
	
		/** 
		  Argumentos usados para gerenciar o contexto dos processos 
		 
		 * */
		
		ProcessControlBlock pcb = ProcessManager.processChange(RegisterFile.getRegsValue(),	RegisterFile.getProgramCounter(), RegisterFile.getValue(33), RegisterFile.getValue(34));
		
		//atualizaÃ§Ã£o do processo no registradores
		
		// Caso não exista processos pcb vai ser nulo.
		if(pcb != null){
			
			// Caso seja a primeira vez rodando o processo, não há informaçõe para serem atualizadas
			if(!pcb.isNull()){
				//debug
				//OutputDebug.odProcessChange(pcb);
			
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
