package mars.pluginRJ.management.process;

public class ProcessControlBlock {
	
	//Valores dos registradores(0-31)
	private int[] regValue;
	private int pc;
	private int hi;
	private int lo;
	//id de processo
	private String pid;
	
	public ProcessControlBlock(int pid, int[] regValue, int pc, int hi, int lo){
		setPid(String.valueOf(pid));
		setRegisters(regValue);
		setPc(pc);
		setHi(hi); 
		setLo(lo);
	}
	
	public int[] getRegsValue() {
		return regValue;
	}
	
	public int getValueReg(int num){
		return regValue[num];
	}

	public void setRegisters(int[] regValues) {
		regValue = regValues;
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
	
	public int getS0(){
		return regValue[16];
	}

	public boolean isNull() {
		return regValue == null ? true : false;
	}
	
}
