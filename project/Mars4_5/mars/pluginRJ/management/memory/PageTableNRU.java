package mars.pluginRJ.management.memory;

public class PageTableNRU extends PageTable{
	public PageTableNRU() {
		super();
	}
	
	@Override
	protected void checkPageMap(int indexProcessMap, int address) {
		int index = indexProcessMap * getSizePageProcess();
		int lastIndex = index + getSizePageProcess();
		
		setPageFault(true);
		
		//verifica as paginas mapeadas para o processo informado.
		for(int i = index; i < lastIndex; i++){
			if(table.get(i).isPresent()){
				if(table.get(i).getValue() == address){
					setPageFault(false);
					setIndexMap(i);
					setIndexProcess(indexProcessMap);
					option.get(indexProcessMap).setHit(1);
					break;
				}
			}else{
				break;
			}
		}
		
		if(getPageFault()){
			//miss
			option.get(indexProcessMap).setMiss(1);
			
			int indexSelection;
			
			//Classe Zero
			indexSelection = getIndex(index, lastIndex, false, false);
			if(indexSelection == -1){
				//Classe One
				indexSelection = getIndex(index, lastIndex, false, true);
			}else
			if(indexSelection == -1){
				//Classe Two
				indexSelection = getIndex(index, lastIndex, true, false);
			}else
			if(indexSelection == -1){
				//Classe Three
				indexSelection = getIndex(index, lastIndex, true, true);
			}
			
				
			//Atribuições da nova pagina
			Page p = table.get(indexSelection);
			p.setValue(address);
			p.setPresent(true);
			p.setReferenced(true);
				
			setIndexMap(indexSelection);
			setIndexProcess(indexProcessMap);
		}
		
		//informa aos observers
		setTableChanged();
	}
	
	private int getIndex(int index, int lastIndex, boolean referenced, boolean modified){
		Page p;
		while(index < lastIndex){
			p = table.get(index);
			if(p.isReferenced() == referenced && p.isModified() == modified){
				return index;
			}
			index++;
		}
		return -1;
	}
	
	@Override
	protected void resetReferenceTable(){
		Page p;
		for(int i = 0; i < table.size(); i++){
			p = table.get(i);
			
			if(p.isPresent()){
				System.out.println("Referencia -> "+ p.getValue() + " -> "+ p.isReferenced());
				p.setReferenced(false);
			}
		}
		p = null;
	}
}
