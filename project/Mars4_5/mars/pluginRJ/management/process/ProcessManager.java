package mars.pluginRJ.management.process;

import mars.util.SystemIO;

public class ProcessManager {
	
	//numero do prox. pid de processo ou quanti
	public static int pidId = 0;
	
	//tabela de processos
	private static TableProcessors tableERB = new TableProcessors();

	/**
	 * @param informações iniciais para criar um novo processo
	 */
	public static void createProcess(int[] regValue, int pc , int hi, int lo) {
		/**
		* Cria um PCB e inseri nas tabelas que contem todos os processos e na fila de processos prontos.
		*/
		tableERB.addPCB(new ProcessControlBlock(++pidId, regValue, pc, hi, lo));
		SystemIO.printString("\nPocesso: p" + pidId + " Criado\n");
	}
	
	/**
	 * @param informações de guarda, ou seja, são as informações do processo que está executando
	 * @return retorna um processo que irá executar.
	 */
	public static ProcessControlBlock processChange(int[] regValue, int pc , int hi, int lo){
		if(tableERB.updatePCB(regValue, pc, hi, lo)) {
			tableERB.processChange();
			return tableERB.getPcbExec();
		}
		
		return null;
	}
	
	/**
	 * @return retorno um novo processo para ser executado, caso tenha um pronto.
	 */
	public static ProcessControlBlock processTerminate() {
		if(tableERB.finalizeProcess()){
			return tableERB.getPcbExec();
		}
		return null;
	}
	
	public static TableProcessors getTableERB() {
		return tableERB;
	}

	public static void setTableERB(TableProcessors table) {
		tableERB = table;
	}
	
	public static void resetPid(){
		pidId = 0;
	}
}
