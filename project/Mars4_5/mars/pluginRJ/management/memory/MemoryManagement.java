package mars.pluginRJ.management.memory;

import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import mars.mips.hardware.AccessNotice;
import mars.mips.hardware.Register;
import mars.mips.hardware.RegisterAccessNotice;
import mars.util.SystemIO;

public class MemoryManagement implements Observer{
	
	private static MemoryManagement memoryManagement;
	
	public static void ResetInstance(){
		memoryManagement = null;
	}
	
	public static MemoryManagement getInstace(){
		if(memoryManagement == null){
			memoryManagement = new MemoryManagement(AlgorithmType.FIFO);
		}
		return memoryManagement;
	}
	
	public static void setInstace(AlgorithmType type){
		memoryManagement = new MemoryManagement(type);
	}
	
	// **************** ***************** ******************
	
	/**
	 * Tabela de paginas
	 */
	private PageTable pageTable;
	/**
	 * Tabela de processos mapeados
	 */
	private Vector<String> processMap;
	/**
	 * Quantidade de processos que podem ser mapeados.
	 */
	private int sizeProcess;
	/**
	 * Processo que está sendo executado!
	 */
	private String executeProcessPid;
	/**
	 * Índice do último processo executado;
	 */
	private int lastIndexProcessMap;
	
	private int pcBefore;
	
	private int timeReset;
	
	public MemoryManagement(AlgorithmType type){
		setPageTable(type);
		setSizeProcess(pageTable.getSizeTable()/pageTable.getSizePageProcess());
		setProcessMap(new Vector<String>(sizeProcess, 0));
		for(int i=0; i < sizeProcess; i++){
			processMap.addElement(null);
		}
		setExecuteProcessPid(null);
		timeReset = 0;
	}
	
	public PageTable getTable(){
		return pageTable;
	}

	private void setSizeProcess(int i) {
		this.sizeProcess = i;
	}

	private void setPageTable(AlgorithmType type){
		switch(type){
			case FIFO:
				this.pageTable = new PageTableFIFO();
			case SC:
				this.pageTable = new PageTableSC();
			case NRU:
				this.pageTable = new PageTableNRU();
			case LRU:
				//this.pageTable = new PageTableLRU();
			default: 
				this.pageTable = new PageTableFIFO();
		}
	}
	
	private void setProcessMap(Vector<String> processMap){
		this.processMap = processMap;
	}
	
	// *************************** SYSCALLS ****************************************
	
	/**
	 * Adiciona no mapeamento da memória um novo pid de processo, para identificar seu espaço na tabela
	 */
	public void addToProcessMap(String pid){
		for(int i = 0; i < sizeProcess; i++){
			if(processMap.get(i) == null){
				processMap.set(i, pid);
				SystemIO.printString("\n Memory - Processo mapeado = "+ pid + " index map = " + i);
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
	
	@Override
	public void update(Observable o, Object arg) {
			Register pc = (Register) o;
			RegisterAccessNotice notice = (RegisterAccessNotice) arg;
			
			if(notice.getAccessType() == AccessNotice.WRITE && executeProcessPid != null && pcBefore != pc.getValueNoNotify()){
				System.out.println("Valor do pc(update) = "+pc.getValueNoNotify() + " processo exec = "+ executeProcessPid);
				SystemIO.printString("\nValor do pc(update) = "+pc.getValueNoNotify() + " processo exec = "+ executeProcessPid);
				System.out.println("index mapeado = "+ getIndexProcessMap());
				pcBefore = pc.getValueNoNotify();
				pageTable.checkPageMap(getIndexProcessMap(), pc.getValueNoNotify());
				
				if(++timeReset > 3){
					resetReferenceTable();
					timeReset = 0;
				}
			}
	}
	
	private int getIndexProcessMap(){
		while(true){
			if(processMap.get(lastIndexProcessMap) != null && processMap.get(lastIndexProcessMap).equals(executeProcessPid)){
				return lastIndexProcessMap;
			}else{
				lastIndexProcessMap++;
				if(lastIndexProcessMap == sizeProcess){
					lastIndexProcessMap = 0;
				}
			}
		}
	}
	
	private void resetReferenceTable(){
		Page p;
		for(int i = 0; i < pageTable.getSizeTable(); i++){
			p = pageTable.getTable().get(i);
			
			if(p.isPresent()){
				System.out.println("Referencia -> "+ p.getValue() + " -> "+ p.isReferenced());
				p.setReferenced(false);
			}
		}
		p = null;
	}
}
