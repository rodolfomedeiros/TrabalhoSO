package mars.pluginRJ.management.memory;

public enum AlgorithmType {
	FIFO(1);
	
	private int value;
	private AlgorithmType(int value){
		this.value = value;
	}
	
	public int getValue(){
		return value;
	}
}
