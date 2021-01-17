package calc;

import java.util.ArrayList;

public class Converter {
	public ArrayList<Character> operators;
	public VarTable vars;
	public Converter(ArrayList<Character> operators, VarTable vars) {
		this.operators = operators;
		this.vars = vars;
	}
	public ArrayList<CalcData> cvt(ArrayList<String> input) {
		ArrayList<CalcData> result = new ArrayList<CalcData>();
		
		int i = 0;
		while(i < input.size()) {
			result.add(convert(input.get(i)));
			i ++;
		}
		
		return result;
	}
	public CalcData convert(String input) {
		char temp = input.charAt(0);
		for(int i = 0; i < operators.size(); i ++) {
			if(temp == operators.get(i)) {
				return new CalcData(CalcID.OPERATOR, i, -1);
			}
		}//returns an operator with the right ID if it matches
		try {
			double value = Double.parseDouble(input);//Tries to parse it into a double
			return new CalcData(CalcID.NUMBER, 0, value);
		} catch (Exception e) {
			//just continues
		}
		//Finds that it's not an operator or a number, so we try to set a variable name for it:
		int x = vars.getSingleID(input);
		if(x != -1)
			return new CalcData(CalcID.NUMVAR, x, -1);
		x = vars.getListID(input);
		if(x != -1)
			return new CalcData(CalcID.LISTVAR, x, -1);
		x = vars.getStrID(input);
		if(x != -1) 
			return new CalcData(CalcID.RAWVAR, x, -1);
		System.err.println("Couldn't find any possible value for \"" + input + "\"!");
		System.exit(1);
		return null;
	}
	public String display(ArrayList<CalcData> input) {
		String result = "";
		for(int x = 0; x < input.size(); x ++) {
			result += displayOne(input.get(x));
			result += " ";
		}
		return result;
	}
	public String displayOne(CalcData input) {
		switch(input.getId()) {
		case NUMBER:
			return input.getData2() + "";
		case OPERATOR:
			return "" + operators.get(input.getData());
		default: return "ERROR";
		}
	}
}
