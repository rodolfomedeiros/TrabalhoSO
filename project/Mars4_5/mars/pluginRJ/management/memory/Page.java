package mars.pluginRJ.management.memory;

public class Page {
	
	// Bit present/ausente
	private boolean present;
	//Bit de leitura
	private boolean read;
	//Bit de escrita
	private boolean write;
	//Bit de executar
	private boolean execute;
	//Bit Modificada
	private boolean modified;
	//Bit Referenciado
	private boolean referenced;
	//Bit de cache(utilizado pelo SO)
	private boolean cache;
	//Valor do address
	private int value;
	//contador de LRU
	private int count;
	

	public Page() {
		
		setPresent(false);
		setRead(true);
		setWrite(true);
		setExecute(true);
		setModified(false);
		setReferenced(false);
		setCache(false);
		
		setCount(0);
		setValue(0);
	}

	public boolean isPresent() {
		return present;
	}

	public void setPresent(boolean present) {
		this.present = present;
	}

	public boolean isRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
	}

	public boolean isWrite() {
		return write;
	}

	public void setWrite(boolean write) {
		this.write = write;
	}

	public boolean isExecute() {
		return execute;
	}

	public void setExecute(boolean execute) {
		this.execute = execute;
	}

	public boolean isModified() {
		return modified;
	}

	public void setModified(boolean modified) {
		this.modified = modified;
	}

	public boolean isReferenced() {
		return referenced;
	}

	public void setReferenced(boolean referenced) {
		this.referenced = referenced;
	}
	
	public int getReferencedInt(){
		if(isReferenced() == true){
			return 1;
		}
		
		return 0;
	}

	public boolean isCache() {
		return cache;
	}

	public void setCache(boolean cache) {
		this.cache = cache;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public int getCount(){
		return count;
	}
	
	public void setCount(int value){
		count = value;
	}
	
	public void addLeftCount(int r){
		String i = Integer.toBinaryString(count);
		i = r + i;
		count = Integer.parseInt(i,2);
	}

}
