package operations;

import java.util.ArrayList;

import calc.*;

public class Vars {
	public static VarTable table;
	public static void init(VarTable table) {
		Vars.table = table;
	}
	public static void exe(ArrayList<CalcData> input) {
		for(int i = 0; i < input.size(); i ++) {
			CalcData placeholder = input.get(i);
			if(placeholder.getId() == CalcID.NUMVAR) {
				double value = table.getSingleVal(placeholder.getData());
				CalcData temp = CalcData.toData(value);
				input.set(i, temp);
			}
			if(placeholder.getId() == CalcID.RAWVAR) {
				ArrayList<CalcData> val = table.getStrVal(placeholder.getData());
				ListExtra.splice(input, i, i+1, val);
				i --;
			}
		}
	}
}
