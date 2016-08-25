package pluginRJ;

import mars.mips.hardware.RegisterFile;

public class ProcessControlBlock {
	
	private RegisterFile registerFile;
	private int pointer;
	private int pid;
	private StateEnum state;
	
	//constructor
	public ProcessControlBlock(){
		
	}

	public RegisterFile getRegisterFile() {
		return registerFile;
	}

	public void setRegisterFile(RegisterFile registerFile) {
		this.registerFile = registerFile;
	}

	public int getPointer() {
		return pointer;
	}

	public void setPointer(int pointer) {
		this.pointer = pointer;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public StateEnum getState() {
		return state;
	}

	public void setState(StateEnum state) {
		this.state = state;
	}
	
}
