// Editado Rodolfo

package mars.mips.instructions.syscalls;

import mars.ProcessingException;
import mars.ProgramStatement;
import mars.mips.hardware.RegisterFile;
import mars.util.SystemIO;

public class MySyscall extends AbstractSyscall{
	
	public MySyscall() {
		super(18, "MySyscall");
	}

	@Override
	public void simulate(ProgramStatement statement) throws ProcessingException {
		SystemIO.printString("Olá Jerfferson, tudo bem? garotinho!!!!");
		//System.out.println("Minuno bunito!");
		SystemIO.printString(RegisterFile.getProgramCounter()+"");
	}
		
}
