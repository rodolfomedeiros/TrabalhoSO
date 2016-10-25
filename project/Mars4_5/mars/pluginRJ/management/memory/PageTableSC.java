package mars.pluginRJ.management.memory;

public class PageTableSC extends PageTable{
	public PageTableSC() {
		super();
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
					setIndexProcess(indexProcessMap);
					option.get(indexProcessMap).setHit(1);
					break;
				}
			}else{
				break;
			}
		}
		
		//falta de pagina, roda o algoritmo
		if(getPageFault()){
			//miss
			option.get(indexProcessMap).setMiss(1);
			//Atribuições da nova pagina
			Page p = table.get(option.get(indexProcessMap).getIndexPage());
			
			if(p.isPresent()){
				while(true){
					if(p.isReferenced()){
						p.setReferenced(false);
						
						option.get(indexProcessMap).addIndexPage(1); 
						if(option.get(indexProcessMap).getIndexPage() == lastIndex){
							option.get(indexProcessMap).setIndexPage(index);
						}
						
						p = table.get(option.get(indexProcessMap).getIndexPage());
					}else{
						break;
					}
				}
			}
			
			p.setValue(address);
			p.setPresent(true);
			p.setReferenced(true);
			
			setIndexMap(option.get(indexProcessMap).getIndexPage());
			setIndexProcess(indexProcessMap);
			
			//modificação de ultima pagina
			option.get(indexProcessMap).addIndexPage(1); 
			if(option.get(indexProcessMap).getIndexPage() == lastIndex){
				option.get(indexProcessMap).setIndexPage(index);
			}
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
				System.out.println("Referencia -> "+ p.getValue() + " -> "+ p.isReferenced());
				p.setReferenced(false);
			}
		}
		p = null;
	}
}
