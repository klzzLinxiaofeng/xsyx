package platform.education.generalTeachingAffair.vo;

public class StatisticDate {
	
	private String name;
	
	private int number;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "{name:'" + name + "', number:'" + number + "'}";
	}
	
}
