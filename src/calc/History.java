package calc;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class History {
	private VarTable vars;
	private ArrayList<Character> operators;
	private Processor process;
	private Separator sep;
	private ArrayList<String> lines;
	public void load(String path) {
		FileReader in;
		try {
			in = new FileReader(path);
		} catch (Exception e) {
			System.out.println("Cannot load data!");
			return;
		}
		Scanner scan = new Scanner(in);
		try {
		for(String input = scan.nextLine(); !input.equals(""); input = scan.nextLine())
			process.process(sep.separate(input), false);
		} catch (Exception e) {}
		scan.close();
	}
	public void save(String path) {
		PrintWriter out;
		try {
			FileWriter temp = new FileWriter(path, false);
			out = new PrintWriter(temp);
		} catch (Exception e) {
			System.out.println("Cannot save data!");
			return;
		}
		out.flush();
		for(int i = 0; i < this.lines.size(); i ++)
			out.println(this.lines.get(i));
		out.close();
	}
	public String parseDataList(ArrayList<CalcData> input) {
		String res = "";
		for(int i = 0; i < input.size(); i ++) {
			res += " ";
			res += parseData(input.get(i));
		}
		return res;
	}
	public String parseData(CalcData input) {
		switch(input.getId()) {
		case NUMBER: 
			return Double.toString(input.getData2()); 
		case LIST:
			return "ERROR";
		case LISTVAR:
			return vars.getListName(input.getData());
		case NUMVAR:
			return vars.getSingleName(input.getData());
		case OPERATOR:
			return operators.get(input.getData()) + "";
		case RAWVAR:
			return vars.getStrName(input.getData());
		default:
			return "ERROR";
		}
	}
	public History(VarTable vars, ArrayList<Character> operators, Processor process, Separator sep) {
		this.sep = sep;
		this.vars = vars;
		this.operators = operators;
		this.process = process;
		this.lines = new ArrayList<String>();
	}
	public ArrayList<String> getLines(){
		return this.lines;
	}
}
