package mars.mips.instructions.syscalls;

import mars.ProcessingException;
import mars.ProgramStatement;
import mars.mips.hardware.RegisterFile;
import mars.pluginRJ.ProcessManager;

public class Fork extends AbstractSyscall{

	public Fork() {
		super(19, "Fork");
	}

	@Override
	public void simulate(ProgramStatement statement) throws ProcessingException {
		
		/****************** Criando o processo ******************************************
		
		Classe ProcessManager Gerencia os processos
		
			método createProcess(<Valor dos registradores>, pc, hi, lo)
		
			<Valor dos registradores> = Null -> Processo está sendo criado, então seus registradores não tem valor nenhum
			
			
		*/
		ProcessManager.createProcess(null, RegisterFile.getValue(4), 0,	0);
	
	}
}
