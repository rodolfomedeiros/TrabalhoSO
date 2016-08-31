package mars.mips.instructions.syscalls;

import mars.ProcessingException;
import mars.ProgramStatement;
import mars.mips.hardware.RegisterFile;
import mars.pluginRJ.ManagerProcessors;

public class Fork extends AbstractSyscall{

	public Fork() {
		super(19, "Fork");
	}

	@Override
	public void simulate(ProgramStatement statement) throws ProcessingException {
		ManagerProcessors.createProcess(	null,
											RegisterFile.getValue(4),
											0,
											0
										);
	}
}
