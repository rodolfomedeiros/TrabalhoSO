package mars.pluginRJ.management.memory;

public class PageTableLRU extends PageTable{
	public PageTableLRU() {
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
			
			int indexSelection = index;
			
			for(int i = index+1; i < lastIndex; i++){
				if(table.get(i-1).isPresent()){
					if(table.get(indexSelection).getCount() > table.get(i).getCount()){
						indexSelection = i;
					}
				}
			}
			
			Page p = table.get(indexSelection);
			p.setPresent(true);
			p.setReferenced(true);
			p.setValue(address);
				
			setIndexMap(indexSelection);
			setIndexProcess(indexProcessMap);
		}
		
		//informa aos observers
		setTableChanged();
	}
	
	
	@Override
	protected void resetReferenceTable(){
		Page p;
		for(int i = 0; i < table.size(); i++){
			p = table.get(i);
			
			if(p.isPresent()){
				//System.out.println("Referencia -> "+ p.getValue() + " -> "+ p.isReferenced());
				p.addLeftCount(p.getReferencedInt());
				//System.out.println("Referencia -> "+ p.getValue() + " -> "+ p.getCount());
				p.setReferenced(false);
			}
		}
	}
}
