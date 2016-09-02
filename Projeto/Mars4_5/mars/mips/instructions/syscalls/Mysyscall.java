package mars.mips.instructions.syscalls;
import mars.ProcessingException;
import mars.ProgramStatement;
import mars.mips.hardware.RegisterFile;
import mars.util.SystemIO;

public class Mysyscall extends AbstractSyscall{
	
	
		public Mysyscall() {
			super(18, "Mysyscall");
		}

		@Override
		public void simulate(ProgramStatement statement) throws ProcessingException {
			SystemIO.printString("Valor do registrado: " + RegisterFile.getValue(4));
			
			SystemIO.printString("Nome do registrado: " + RegisterFile.getNumber("pc"));
		}
	
}
