package mars.mips.instructions.syscalls;

import mars.ProcessingException;
import mars.ProgramStatement;
import mars.mips.hardware.RegisterFile;
import mars.pluginRJ.management.memory.MemoryManagement;
import mars.pluginRJ.management.process.ProcessManager;

public class Fork extends AbstractSyscall{

	public Fork() {
		super(19, "Fork");
	}

	@Override
	public void simulate(ProgramStatement statement) throws ProcessingException {
		//create process
		String pid = ProcessManager.createProcess(null, RegisterFile.getValue(4), 0, 0);
		//map in memory
		MemoryManagement.getInstace().addToMapProcess(pid);
	}
}
