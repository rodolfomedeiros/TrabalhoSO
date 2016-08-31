package mars.pluginRJ;

import java.util.ArrayList;

import mars.mips.hardware.Register;

public class ProcessControlBlock {
	
	private ArrayList<Register> registers;
	private int hi;
	private int lo;
	private int pc;
	private String pid;
	
	//constructor
	public ProcessControlBlock(int pid, int hi, int lo, int pc, ArrayList<Register> reg){
		setPid(String.valueOf(pid));
		setHi(hi);
		setLo(lo);
		setPc(pc);
		setRegisters(reg);
	}
	
	public ArrayList<Register> getRegisters() {
		return registers;
	}

	public void setRegisters(ArrayList<Register> registers) {
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
	
}
