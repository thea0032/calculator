package calc;

import java.util.ArrayList;

public class Processor {
	public Calculator calc;
	public VarTable vars;
	public Converter cvt;
	public Separator sep;
	public History history;
	public String defaultPath = "save.calc";
	public Processor(){
	}
	public void init(Calculator calc, VarTable vars, Converter cvt, Separator sep, History history) {
		this.calc = calc;
		this.vars = vars;
		this.cvt = cvt;
		this.sep = sep;
		this.history = history;
	}
	public String process(ArrayList<String> input, boolean recursive) {
		switch(input.get(0)){
		case "h": case "help":
			return Help.exe();
		case "sv":
			if(!recursive)
				history.getLines().add(ListExtra.concat(input));
			input.remove(0);
			return makeVariableSingle(input);   
		case "sr":
			if(!recursive)
				history.getLines().add(ListExtra.concat(input));
			input.remove(0);
			return makeVariableStr(input);
		case "se":
			if(!recursive)
				history.getLines().add(ListExtra.concat(input));
			input.remove(0);
			return storeElement(input);
		case "save":
			input.remove(0);
			return save(input);
		case "load":
			input.remove(0);
			return load(input);
		case "l":
			if(!recursive)
				history.getLines().add(ListExtra.concat(input));
			input.remove(0);
			return list(input);
		case "dbg": 
			input.remove(0);
			return debug(input);
		default:
			return def(input);
		}
	}
	private String makeVariableSingle(ArrayList<String> input) {
		String name = input.get(0);
		input.remove(0);
		ArrayList<CalcData> parsed = cvt.cvt(input);
		calc.calculateFromCList(parsed);
		double val = calc.getNum(parsed.get(0));
		vars.setSingle(name, val);
		return "stored!";
	}
	private String makeVariableStr(ArrayList<String> input) {
		String name = input.get(0);
		input.remove(0);
		ArrayList<CalcData> parsed = cvt.cvt(input);
		vars.setStr(name, parsed);
		return "stored!";
	}
	private String storeElement(ArrayList<String> input) {
		String name = input.get(0);
		input.remove(0);//gets name
		
		ArrayList<String> subInput = ListExtra.subListClone(input, 0, 1);
		ArrayList<CalcData> posData = cvt.cvt(subInput);
		calc.calculateFromCList(posData);
		int pos = (int) calc.getNum(posData.get(0));
		input.remove(0);//gets position in list
		
		ArrayList<CalcData> parsed = cvt.cvt(input);
		calc.calculateFromCList(parsed);
		double val = calc.getNum(parsed.get(0));//gets value
		
		vars.setList(name, val, pos);
		return "stored!";
	}
	private String def(ArrayList<String> input) {
		ArrayList<CalcData> parsed = cvt.cvt(input);
		calc.calculateFromCList(parsed);
		double val = calc.getNum(parsed.get(0));
		vars.setSingle("ans", val);
		return val + "";
	}
	private String save(ArrayList<String> input) {
		history.save(defaultPath);
		return "saved!";
	}
	private String load(ArrayList<String> input) {
		history.load(defaultPath);
		return "loaded!";
	}
	private String list(ArrayList<String> input) {
		String incrVar = input.get(0);
		input.remove(0);
		int maxNum = Integer.parseInt(input.get(0));
		input.remove(0);
		for(int i = 0; i < maxNum; i ++) {
			System.out.println("Processing " + input);
			vars.setSingle(incrVar, i);
			ArrayList<String> input2 = ListExtra.clone(input);
			System.out.println(this.process(input2, true));
		}
		return "list done " + maxNum + " times!";
	}
	private String debug(ArrayList<String> input) {
		System.out.println("Input: " + input);
		System.out.println("Vars: ");
		for(int i = 0; i < vars.getSingleLen(); i ++) {
			System.out.println("Single variable " + vars.getSingleName(i) + ": " + vars.getSingleVal(i));
		}
		for(int i = 0; i < vars.getListLen(); i ++) {
			System.out.println("List variable " + vars.getListName(i) + ": " + vars.getListValRaw(i));
		}
		for(int i = 0; i < vars.getStrLen(); i ++) {
			System.out.println("Str variable " + vars.getStrName(i) + ": " + vars.getStrVal(i));
		}
		return "";
	}
}
