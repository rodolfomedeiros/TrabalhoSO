package mars.pluginRJ;

import mars.mips.hardware.Register;

public class ProcessControlBlock {
	
	private Register[] registers;
	private Register hi;
	private Register lo;
	private Register pc;
	private int pointer;
	private String pid;
	private StateEnum state;
	
	//constructor
	public ProcessControlBlock(String pid){
		setPid(pid);
	}
	
	public Register[] getRegisters() {
		return registers;
	}

	public void setRegisters(Register[] registers) {
		this.registers = registers;
	}

	public Register getHi() {
		return hi;
	}

	public void setHi(Register hi) {
		this.hi = hi;
	}

	public Register getLo() {
		return lo;
	}

	public void setLo(Register lo) {
		this.lo = lo;
	}

	public Register getPc() {
		return pc;
	}

	public void setPc(Register pc) {
		this.pc = pc;
	}

	public int getPointer() {
		return pointer;
	}

	public void setPointer(int pointer) {
		this.pointer = pointer;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public StateEnum getState() {
		return state;
	}

	public void setState(StateEnum state) {
		this.state = state;
	}
	
}
