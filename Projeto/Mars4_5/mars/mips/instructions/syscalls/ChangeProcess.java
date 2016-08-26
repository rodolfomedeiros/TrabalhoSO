package mars.mips.instructions.syscalls;

import mars.ProcessingException;
import mars.ProgramStatement;
import mars.pluginRJ.ManagerProcessors;

public class ChangeProcess extends AbstractSyscall{

	public ChangeProcess() {
		super(20, "ChangeProcess");
	}

	@Override
	public void simulate(ProgramStatement statement) throws ProcessingException {
		ManagerProcessors.processChange();
	}
	
}
