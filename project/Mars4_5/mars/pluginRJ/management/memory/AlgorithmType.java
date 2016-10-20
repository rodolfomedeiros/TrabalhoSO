package mars.pluginRJ.management.memory;

public enum AlgorithmType {
	FIFO(1), LRU(2), NRU(3), SC(4);
	
	private int value;
	private AlgorithmType(int value){
		this.value = value;
	}
	
	public int getValue(){
		return value;
	}
}
