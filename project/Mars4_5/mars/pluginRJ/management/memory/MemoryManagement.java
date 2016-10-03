package mars.pluginRJ.management.memory;

import java.util.Vector;

import mars.util.SystemIO;

public class MemoryManagement {
	
	private static MemoryManagement memoryManagement;
	
	public static void ResetInstance(){
		memoryManagement = null;
	}
	
	public static MemoryManagement getInstace(){
		if(memoryManagement == null){
			memoryManagement = new MemoryManagement();
		}
		
		return memoryManagement;
	}
	
	// **************** ***************** ******************
	
	private PageTable pageTable;
	private Vector<String> processMap;
	private int sizeProcess;
	private String executeProcessPid;
	
	public MemoryManagement(){
		initilizer();
	}
	
	private void initilizer() {
		setPageTable(new PageTable());
		setSizeProcess(pageTable.getSizeTable()/pageTable.getSizePageProcess());
		setProcessMap(new Vector<String>(sizeProcess, 0));
		for(int i=0; i < sizeProcess; i++){
			processMap.addElement(null);
		}
		setExecuteProcessPid(null);
	}

	private void setSizeProcess(int i) {
		this.sizeProcess = i;
	}

	private void setPageTable(PageTable pageTable){
		this.pageTable = pageTable;
	}
	
	private void setProcessMap(Vector<String> processMap){
		this.processMap = processMap;
	}
	
	// *************************** SYSCALLS ****************************************
	
	/**
	 * Adiciona no mapeamento da memória um novo pid de processo, para identificar seu espaço na tabela
	 */
	public void addToProcessMap(String pid){
		@SuppressWarnings("unused")
		boolean add = false;
		for(int i = 0; i < sizeProcess; i++){
			if(processMap.get(i) == null){
				processMap.set(i, pid);
				SystemIO.printString("\n Memory - Processo mapeado = "+ pid + " index map = " + i);
				add = true;
				break;
			}
		}
	}
	
	/**
	 * Remover processo do mapeamento feito pela memória
	 */
	public void removeProcessMap(String pid){
		for(int i = 0; i < sizeProcess; i++){
			if(processMap.get(i) != null && processMap.get(i).equals(pid)){
				processMap.set(i, null);
				SystemIO.printString("\n Memory - Processo Removido = "+ pid + " index map = " + i);
				break;
			}
		}
	}
	
	/**
	 * Guarda o processo(pid) que está sendo executado no estante momento
	 */
	public void setExecuteProcessPid(String pid){
		this.executeProcessPid = pid;
		if(pid != null){
			SystemIO.printString("\n Memory - Processo executando = " + pid);
		}
	}
	
	// ************************ MMU ****************************************
	
	
	
}
