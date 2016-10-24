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

	public Page() {
		
		setPresent(false);
		setRead(true);
		setWrite(true);
		setExecute(true);
		setModified(false);
		setReferenced(false);
		setCache(false);
		
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

}
