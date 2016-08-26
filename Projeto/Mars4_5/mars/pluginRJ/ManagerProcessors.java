package mars.pluginRJ;

public class ManagerProcessors {
	//tabelas
	private static TableProcessors TableERB;
	//Excalonador
	private static Escalonador escalonador;
	
	public ManagerProcessors(){
		setEscalonador(new Escalonador());
		setTableERB(new TableProcessors());
	}

	public static TableProcessors getTableERB() {
		return TableERB;
	}

	public static void setTableERB(TableProcessors tableERB) {
		TableERB = tableERB;
	}

	public static Escalonador getEscalonador() {
		return escalonador;
	}

	public static void setEscalonador(Escalonador escalonador) {
		ManagerProcessors.escalonador = escalonador;
	}
	
}
