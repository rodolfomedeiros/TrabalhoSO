package mars.pluginRJ.management.memory;

import java.util.Observable;
import java.util.Vector;

public abstract class PageTable extends Observable{

	private final int sizeTable = 16;
	private final int sizePageProcess = 4;
	protected Vector<Page> table;
	protected boolean pageFault;
	protected int indexMap;
	
	public PageTable() {
		this.table = new Vector<Page>(sizeTable, 0);
		for(int i=0; i < sizeTable; i++){
			table.addElement(new Page());
		}
		setPageFault(false);
		setIndexMap(-1);
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
