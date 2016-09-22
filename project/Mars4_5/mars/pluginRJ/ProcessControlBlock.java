package mars.pluginRJ;

public class ProcessControlBlock {
	
	private int[] regValue;
	private int pc;
	private int hi;
	private int lo;
	private String pid;
	
	//constructor
	public ProcessControlBlock(int pid, int[] regValue, int pc, int hi, int lo){
		
		
		// Seta os valores do pid; dos registradores; do pc; do hi e do lo
		
		setPid(String.valueOf(pid));
		setRegisters(regValue);
		setPc(pc);
		
		/**************** Não são usados nesse trabahlo************/
		
		setHi(hi); 
		setLo(lo);
		
		
		/***********************************************************/
	
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
