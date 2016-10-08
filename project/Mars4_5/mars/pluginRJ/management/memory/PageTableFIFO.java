package mars.pluginRJ.management.memory;

public class PageTableFIFO extends PageTable {

	/*
	 * FIFO dependencias
	 * 
	 * colunm 0 -> endereço da pagina que deve ser modificada.
	 */
	private int fifo[] = null;
	
	
	public PageTableFIFO() {
		super();
		int line = getSizeTable()/getSizePageProcess();
		fifo = new int[line];
		//default
		for(int i = 0; i < line; i++){
			fifo[i] = i * getSizePageProcess();
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
					break;
				}
			}else{
				break;
			}
		}
		
		//falta de pagina, roda o algoritmo
		if(getPageFault()){
			//Atribuições da nova pagina
			Page p = table.get(fifo[indexProcessMap]);
			p.setValue(address);
			p.setPresent(true);
			p.setReferenced(true);
			
			//modificação de ultima pagina
			fifo[indexProcessMap] += 1; 
			if(fifo[indexProcessMap] == lastIndex){
				fifo[indexProcessMap] = index;
			}
		}
		
		//informa aos observers
		setTableChanged();
	}
}
