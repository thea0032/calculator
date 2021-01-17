package calc;

public class CalcData {
	private CalcID id;
	private int data;
	private double data2;
	public CalcData(CalcID id, int data, double data2){
		this.id = id;
		this.data = data;
		this.data2 = data2;
	}
	public CalcID getId() {
		return id;
	}
	public void setId(CalcID id) {
		this.id = id;
	}
	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
	public double getData2() {
		return data2;
	}
	public void setData2(double data) {
		this.data2 = data;
	}
	public static CalcData toData(double number) {
		return new CalcData(CalcID.NUMBER, 0, number);
	}
	public String toString() {
		String result = "Calcdata: ";
		switch(id) {
		case NUMBER:
			result += "number: ";
			result += this.data2;
			break;
		case OPERATOR:
			result += "operator: ";
			result += this.data;
			break;
		case LIST:
			result += "list: ";
			result += this.data;
			break;
		case NUMVAR:
		case LISTVAR: 
		case RAWVAR:
			result += "variable: ";
			result += this.data;
			break;
		}
		return result;
	}
}
