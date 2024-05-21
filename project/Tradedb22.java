package project;

public class Tradedb22 {

	private int id;
	private String countryid;
	private String name;
	private Long year;
	private Long income;
	private Long expense;
	private String imgoods;
	private String exgoods;

	public Tradedb22() { // 메소드를 불러오기 위한 용도로 사용하는 디폴트 생성자

	}

	public void setId(int id) {
		this.id = id;
	}

	public void setCountryid(String countryid) {
		this.countryid = countryid;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setYear(Long year) {
		this.year = year;
	}

	public void setIncome(Long income) {
		this.income = income;
	}

	public void setExpense(Long expense) {
		this.expense = expense;
	}

	public void setImgoods(String imgoods) {
		this.imgoods = imgoods;
	}

	public void setExgoods(String exgoods) {
		this.exgoods = exgoods;
	}

	// 데이터를 insert 할때 쓸 수 있는 생성자
	public Tradedb22(int id, String countryid, String name, long year, String imgoods, String exgoods, long income,
			long expense) {
		this.id = id;
		this.countryid = countryid;
		this.name = name;
		this.exgoods = exgoods;
		this.imgoods = imgoods;
		this.income = income;
		this.expense = expense;
		this.year = year;
	}

	public int getid() {
		return id;
	}

	public String getcountryid() {
		return countryid;
	}

	public String getname() {
		return name;
	}

	public Long getyear() {
		return year;
	}

	public Long getincome() {
		return income;
	}

	public Long getexpense() {
		return expense;
	}

	public String getimgoods() {
		return imgoods;
	}

	public String getexgoods() {
		return exgoods;
	}

	@Override
	public String toString() {
		return id + countryid + name + year + income + expense + imgoods + exgoods;

	}

}
