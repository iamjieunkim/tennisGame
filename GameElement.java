package Project1;

// 이름, 세트점수, 단/복식을 선언한 슈퍼클래스

public abstract class GameElement {

	private String [] name;
	private int setNum;
	private int soloDuo;
	
	
	public GameElement(String[] name, int setNum, int soloDuo) {
		super();
		this.name = name;
		this.setNum = setNum;
		this.soloDuo = soloDuo;
	}


	public GameElement() {
		
	}


	public String[] getName() {
		return name;
	}


	public void setName(String[] name) {
		this.name = name;
	}


	public int getSetNum() {
		return setNum;
	}


	public void setSetNum(int setNum) {
		this.setNum = setNum;
	}


	public int getSoloDuo() {
		return soloDuo;
	}


	public void setSoloDuo(int soloDuo) {
		this.soloDuo = soloDuo;
	}
}
