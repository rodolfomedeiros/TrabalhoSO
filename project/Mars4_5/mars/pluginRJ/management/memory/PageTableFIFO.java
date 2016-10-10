package mars.pluginRJ.management.memory;

import java.util.Vector;

public class PageTableFIFO extends PageTable {

	/*
	 * FIFO dependencias
	 * 
	 * colunm 0 -> endereço da pagina que deve ser modificada.
	 */
	private Vector<OptionFIFO> optionFifo;
	
	public Vector<OptionFIFO> getOptionFifo(){
		return optionFifo;
	}
	
	public PageTableFIFO() {
		super();
		int line = getSizeTable()/getSizePageProcess();
		optionFifo = new Vector<OptionFIFO>(line, 0);
		//default
		for(int i = 0; i < line; i++){
			optionFifo.addElement(new OptionFIFO(i * getSizePageProcess()));
		}
	}
	
	@Override
	public void checkPageMap(int indexProcessMap, int address) {
		int index = indexProcessMap * getSizePageProcess();
		int lastIndex = index + getSizePageProcess();
		
		setPageFault(true);
		
		//verifica as paginas mapeadas para o processo informado.
		for(int i = index; i < lastIndex; i++){
			if(table.get(i).isPresent()){
				if(table.get(i).getValue() == address){
					setPageFault(false);
					setIndexMap(i);
					optionFifo.get(indexProcessMap).setHit(1);
					break;
				}
			}else{
				break;
			}
		}
		
		//falta de pagina, roda o algoritmo
		if(getPageFault()){
			//miss
			optionFifo.get(indexProcessMap).setMiss(1);
			//Atribuições da nova pagina
			Page p = table.get(optionFifo.get(indexProcessMap).getIndexPage());
			p.setValue(address);
			p.setPresent(true);
			p.setReferenced(true);
			
			//modificação de ultima pagina
			optionFifo.get(indexProcessMap).addIndexPage(1); 
			if(optionFifo.get(indexProcessMap).getIndexPage() == lastIndex){
				optionFifo.get(indexProcessMap).setIndexPage(index);
			}
		}
		
		//informa aos observers
		setTableChanged();
	}
}
	
class OptionFIFO{
	
	private int indexPage;
	private int hit;
	private int miss;
	
	public OptionFIFO(int index){
		setIndexPage(index);
		setHit(0);
		setMiss(0);
	}
	
	public int getIndexPage() {
		return indexPage;
	}
	public void setIndexPage(int indexProcess) {
		this.indexPage = indexProcess;
	}
	public void addIndexPage(int add){
		this.indexPage += add;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public int getMiss() {
		return miss;
	}
	public void setMiss(int miss) {
		this.miss = miss;
	}
	public void addHit(int add){
		this.hit += add;
	}
	public void addMiss(int add){
		this.miss += add;
	}
}
