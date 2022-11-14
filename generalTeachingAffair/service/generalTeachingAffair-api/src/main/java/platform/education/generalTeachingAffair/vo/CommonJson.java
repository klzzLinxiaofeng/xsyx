package platform.education.generalTeachingAffair.vo;

public class CommonJson {
	private String name;
	
	private Double[] data;

	public CommonJson() {
	}

	public CommonJson(String name, Double[] data) {
		this.name = name;
		this.data = data;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double[] getData() {
		return data;
	}

	public void setData(Double[] data) {
		this.data = data;
	}
	
}
