package mars.pluginRJ.management.memory;

import java.util.Observable;
import java.util.Vector;

public class PageTable extends Observable{

	private final int sizeTable = 16;
	private final int sizePageProcess = 4;
	private Vector<Page> table;
	private boolean pageFault;
	
	//Algoritmo
	private AlgorithmType type;
	
	/*
	 * FIFO dependencias
	 * 
	 * colunm 0 -> ultima pagina referenciada 
	 * colunm 1 -> page fault
	 */
	private int fifo[][] = null;
	
	public PageTable(AlgorithmType type) {
		initializer(type);
	}
	
	private void initializer(AlgorithmType type){
		this.table = new Vector<Page>(sizeTable, 0);
		for(int i=0; i < sizeTable; i++){
			table.addElement(null);
		}
		this.type = type;
		//inicializa as dependencias do tipo de algoritmo
		initializerType();
		setPageFault(false);
	}
	
	private void initializerType(){
		if(type == AlgorithmType.FIFO){
			fifo = new int[sizeTable/sizePageProcess][2];
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
	
	// ********************* Manager Table ***********************************
	/**
	 * Verifica se o endereço está mapeado.
	 * False -> Roda o algoritmo de troca de pagina.
	 * True -> Não faz nada.
	 * Any -> Atualiza a visualização(virtual memory illustrator)
	 */
	public void checkPageMap(int indexProcessMap, String address){
		boolean pageMap = false;
		int index = indexProcessMap * sizePageProcess;
		int lastIndex = index + sizePageProcess;
		
		//verificao de algoritmo utilizado
		if(type == AlgorithmType.FIFO){
			if(fifo[indexProcessMap][1] == 1){
				//page fault
				setPageFault(true);
				
				
				
			}else{
				//verifica as paginas mapeadas para o processo informado.
				for(int i = index; i < lastIndex; i++){
					if(table.get(i) != null && table.get(i).equals(address)){
						//PAREIIIIIIIIIIIIIIIII AQUIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII
					}
				}
			}
			
		}
		
	}
	
	
	
	
}
