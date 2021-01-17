package calc;
import java.util.*;

public class Calculator {
	private Converter convert;
	private Separator separate;
	private VarTable vars;
	private final int LEFT_PAR_ID = 0;
	private final int RIGHT_PAR_ID = 1;
	private final int[] EXP_OP = {0};
	private final int[] EXP_SEARCH = {2};
	private final int[] MD_OP = {1, 2};
	private final int[] MD_SEARCH = {3, 4};
	private final int[] AS_OP = {3, 4};
	private final int[] AS_SEARCH = {5, 6};
	private final int EXP = 7;
	public Calculator(ArrayList<Character> operators, ArrayList<Character> terminators, ArrayList<Character> excluders, VarTable vars) {
		this.vars = vars;
		convert = new Converter(operators, this.vars);
		separate = new Separator(terminators, excluders);
	}
	public double calculate(String input) {
		ArrayList<String> separated = separate.separate(input);
		ArrayList<CalcData> readyToCalc = convert.cvt(separated);
		calculateFromCList(readyToCalc);
		return getNum(readyToCalc.get(0));
	}
	public void calculateFromCList(ArrayList<CalcData> readyToCalc){
		operations.Vars.exe(readyToCalc);
		while(operations.Parenthesis.exe(readyToCalc, LEFT_PAR_ID, RIGHT_PAR_ID, this));
		while(operations.ListAt.exe(readyToCalc, EXP, vars, this));
		while(binarySkeleton(readyToCalc, EXP_OP, EXP_SEARCH));
		while(binarySkeleton(readyToCalc, MD_OP, MD_SEARCH));
		while(binarySkeleton(readyToCalc, AS_OP, AS_SEARCH));
	}
	/**
	 * for opID, 0 = exponent, 1 = multiply, 2 = divide, 3 = add, 4 = subtract
	 * for searching, you'll want to do 2, 3, 4, 5, and 6 w/ opID of 0, 1, 2, 3, and 4, respectively. It's how everything lines up. 
	 */
	public boolean binarySkeleton(ArrayList<CalcData> input, int[] opID, int[] searching) {
		for(int x = 0; x < input.size(); x ++) {
			CalcData comparing = input.get(x);
			for(int y = 0; y < opID.length; y ++) {
				if(comparing.getId() == CalcID.OPERATOR && comparing.getData() == searching[y]) {
					double first = getNum(input.get(x - 1));
					double second = getNum(input.get(x + 1));
					ArrayList<CalcData> result = ListExtra.toList(CalcData.toData(operate(opID[y], first, second)));
					ListExtra.splice(input, x - 1, x + 2, result);
					return true;
				}
			}
		}
		return false;
	}
	public double getNum(CalcData input) {
		if(input.getId() == CalcID.NUMBER) {
			return input.getData2();
		} else {
			System.err.println("Error: " + input + " must be a number!");
			System.exit(1);
			return 0.0;
		}
	}
	public double operate(int opID, double first, double second) {
		switch(opID) {
		case 0: return Math.pow(first, second);
		case 1: return first * second;
		case 2: return first / second;
		case 3: return first + second;
		case 4: return first - second;
		default: System.err.println("Error: invalid operation id: " + opID + " must be 0, 1, 2, 3, or 4!");
		System.exit(1);
		return 0.0;
		}
	}
	public String display(ArrayList<CalcData> input) {
		return convert.display(input);
	}
}
