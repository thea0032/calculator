package calc;
import java.util.*;
public class Main {
	private static Scanner scan = new Scanner(System.in); 
	private static final Character[] OPERATORS = {'(', ')', '^', '*', '/','+', '-', '@'};
	private static final Character[] TERMINATORS = {'(', ')', '^', '*', '/','+', '-', ' ', ',', '@'};
	private static final Character[] EXCLUDERS = {' ', ','};
	private static ArrayList<Character> operators;
	private static ArrayList<Character> terminators;
	private static ArrayList<Character> excluders;
	private static VarTable vars;
	private static Converter cvt;
	private static Separator separate;
	private static Calculator calculate;
	private static Processor process;
	private static History history;
	public static void main(String[] args) {
		initialize();
		System.out.println("Enter a thing to calculate! Press h for help.");
		while(true) {
			String input = scan.nextLine();
			System.out.println(process.process(separate.separate(input), false));
		}
	}
	public static void initialize() {
		operators = new ArrayList<Character>();
		terminators = new ArrayList<Character>();
		excluders = new ArrayList<Character>();
		operators.addAll(Arrays.asList(OPERATORS));
		terminators.addAll(Arrays.asList(TERMINATORS));
		excluders.addAll(Arrays.asList(EXCLUDERS));
		vars = new VarTable();
		calculate = new Calculator(operators, terminators, excluders, vars);
		cvt = new Converter(operators, vars);
		separate = new Separator(terminators, excluders);
		process = new Processor();
		history = new History(vars, operators, process, separate);
		process.init(calculate, vars, cvt, separate, history);
		operations.Vars.init(vars);
	}
}
