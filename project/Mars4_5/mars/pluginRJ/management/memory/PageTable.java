package mars.pluginRJ.management.memory;

import java.util.Observable;
import java.util.Vector;

public abstract class PageTable extends Observable{

	private final int sizeTable = 16;
	private final int sizePageProcess = 4;
	protected Vector<Page> table;
	protected boolean pageFault;
	protected int indexMap;
	protected int indexProcess;
	/*
	 * dependencias
	 * 
	 * colunm 0 -> endereço da pagina que deve ser modificada.
	 */
	protected Vector<Option> option;
	
	public PageTable() {
		this.table = new Vector<Page>(sizeTable, 0);
		for(int i=0; i < sizeTable; i++){
			table.addElement(new Page());
		}
		int line = getSizeTable()/getSizePageProcess();
		option = new Vector<Option>(line, 0);
		//default
		for(int i = 0; i < line; i++){
			option.addElement(new Option(i * getSizePageProcess()));
		}
	}
	
	public int getSizeTable() {
		return sizeTable;
	}

	public int getSizePageProcess() {
		return sizePageProcess;
	}
	
	public void setPageFault(boolean value){
		this.pageFault = value;
	}
	
	public boolean getPageFault(){
		return pageFault;
	}
	
	public int getIndexMap(){
		return indexMap;
	}
	
	public void setIndexMap(int index){
		this.indexMap = index;
	}
	public Vector<Page> getTable(){
		return table;
	}
	public Vector<Option> getOption(){
		return option;
	}
	protected void setIndexProcess(int index){
		this.indexProcess = index;
	}
	public int getIndexProcess(){
		return indexProcess;
	}
	
	/**
	 * Verifica se o endereço está mapeado.
	 * False -> Roda o algoritmo de troca de pagina.
	 * True -> Não faz nada.
	 * Any -> Atualiza a visualização(virtual memory illustrator)
	 */
	protected abstract void checkPageMap(int indexProcessMap, int address);
	
	protected void setTableChanged(){
		setChanged();
		notifyObservers();
	}
}
