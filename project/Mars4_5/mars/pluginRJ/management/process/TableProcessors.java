package mars.pluginRJ.management.process;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import mars.util.SystemIO;

public class TableProcessors {
	
	//processo que está executando
	private String pidExec;
	
	//Todos os processos
	private HashMap<String,ProcessControlBlock> PCBTable;
	
	//Processos prontos
	private LinkedList<String> ReadyTable;
	
	//Processos bloqueados
	private ArrayList<String> BlockedTable;
	
	public TableProcessors(){
		setPidExec("");
		setPCBTable(new HashMap<>());
		setReadyTable(new LinkedList<>());
		setBlockedTable(new ArrayList<>());
	}

	public String getPidExec() {
		return pidExec;
	}

	public void setPidExec(String pidExec) {
		this.pidExec = pidExec;
	}

	public HashMap<String, ProcessControlBlock> getPCBTable() {
		return PCBTable;
	}

	public void setPCBTable(HashMap<String, ProcessControlBlock> pCBTable) {
		PCBTable = pCBTable;
	}

	public LinkedList<String> getReadyTable() {
		return ReadyTable;
	}

	public void setReadyTable(LinkedList<String> readyTable) {
		ReadyTable = readyTable;
	}

	public ArrayList<String> getBlockedTable() {
		return BlockedTable;
	}

	public void setBlockedTable(ArrayList<String> blockedTable) {
		BlockedTable = blockedTable;
	}
	
	/**
	 * @return retorna o processo que está executando.
	 */
	public ProcessControlBlock getPcbExec(){
		if(!pidExec.equals("")){
			return PCBTable.remove(pidExec);
		}
		return null;	
	}
	
	/**
	 * @param adiciona o novo processo na tabela.
	 */
	public void addPCB(ProcessControlBlock pcb){
		PCBTable.put(pcb.getPid(), pcb);
		ReadyTable.add(pcb.getPid());
	}

	public void processChange() {
		
		String pidE = scherdulerAlgoritmQueue();
		
		/**
		  Verifica e o retorno foi diferente vazio
		  
		  se sim:
		  	adiciona o pid do processo que vai sair do processador
		  	e o coloca na fila de pronto
		  senao:
		  	coloca o processo direto para ser executado
		 */
		
		if(!pidExec.equals("") && !pidE.equals("")){
			ReadyTable.add(pidExec);
		}
		
		pidExec = pidE;
	}

	/**
	 * Guarda o estado do processo que estava executando.
	 * 
	 * @param regValue
	 * @param pc
	 * @param hi
	 * @param lo
	 * @return
	 */
	
	public boolean updatePCB(int[] regValue, int pc , int hi, int lo){
		/** 
		 	Verifica se o pidExec é vazio, ou seja, se tem algum processo executando
		  	Se ele nao for vazio, deve-se armazenar o contexto do processo 
		 */
		
		if(!pidExec.equals("")){
			// Atualiza informa��es na tabela que cont�m todos os registradores
			PCBTable.put(pidExec, new ProcessControlBlock(Integer.valueOf(pidExec), regValue, pc, hi, lo));
			SystemIO.printString("********* Pocesso: p" + pidExec + " Saindo do processador:\n"
					+ "\t$s0 = " + regValue[16]);
		}
		
		return true;
	}
	
	/**
	 * basicamente modifica o processo que esta executando
	 * 
	 * @return
	 */

	public boolean finalizeProcess(){
		pidExec = scherdulerAlgoritmQueue();
		
		if(!pidExec.equals("")){
			return true;
		}
			
		return false;
	}	
	
	/**
	 * Um algoritmo simples do escalonador, pega o primeiro da fila e informa ao despachante para ser executado.
	 */
	public String scherdulerAlgoritmQueue(){
		/**
			Verifica se a lista que contem os processos prontos est� vazia,
			
			se n�o estiver: 
				Pega o novo processo para ser executado e remore da lista.
			
			sen�o: 
				Retorna vazio;
		*/
		
		if(!ReadyTable.isEmpty()){
			return ReadyTable.removeFirst();
		}
		
		return "";
	}
}
