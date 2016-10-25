package mars.pluginRJ.management.memory;

public class Option{
	
	private int indexPage;
	private int hit;
	private int miss;
	
	public Option(int index){
		//utilizado pelo fifo
		setIndexPage(index);
		
		setHit(0);
		setMiss(0);
	}
	
	public int getIndexPage() {
		return indexPage;
	}
	public void setIndexPage(int indexProcess) {
		this.indexPage = indexProcess;
	}
	public void addIndexPage(int add){
		this.indexPage += add;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit += hit;
	}
	public int getMiss() {
		return miss;
	}
	public void setMiss(int miss) {
		this.miss += miss;
	}
	public void addHit(int add){
		this.hit += add;
	}
	public void addMiss(int add){
		this.miss += add;
	}
}
