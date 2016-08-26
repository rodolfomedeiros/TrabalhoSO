package mars.pluginRJ;

import mars.mips.hardware.Register;

public class ProcessControlBlock {
	
	private Register[] registers;
	private int hi;
	private int lo;
	private int pc;
	private String pid;
	private StateEnum state;
	
	//constructor
	public ProcessControlBlock(int pid, int hi, int lo, int pc, Register [] reg){
		setPid(String.valueOf(pid));
		setHi(hi);
		setLo(lo);
		setPc(pc);
		setRegisters(reg);
	}
	
	public Register[] getRegisters() {
		return registers;
	}

	public void setRegisters(Register[] registers) {
		this.registers = registers;
	}

	public int getHi() {
		return hi;
	}

	public void setHi(int hi) {
		this.hi = hi;
	}

	public int getLo() {
		return lo;
	}

	public void setLo(int lo) {
		this.lo = lo;
	}

	public int getPc() {
		return pc;
	}

	public void setPc(int pc) {
		this.pc = pc;
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
