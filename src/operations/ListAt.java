package operations;

import java.util.ArrayList;

import calc.*;

public class ListAt {
	public static boolean exe(ArrayList<CalcData> input, int id, VarTable vars, Calculator calc) {
		for (int i = 1; i < input.size() - 1; i++) {
			CalcData temp = input.get(i);
			if (temp.getId() == CalcID.OPERATOR && temp.getData() == id) {
				int id2;
				if (input.get(i - 1).getId() == CalcID.LISTVAR) {
					id2 = input.get(i - 1).getData();
				} else {
					System.err.println("ERROR: the list was not found!");
					System.exit(1);
					return false;
				}
				ArrayList<CalcData> sublist = ListExtra.subListClone(input, i + 1, i + 2);
				calc.calculateFromCList(sublist);
				int pos = (int) calc.getNum(sublist.get(0));
				double x = vars.getListVal(id2, pos);
				ListExtra.splice(input, i - 1, i + 2, ListExtra.toList(new CalcData(CalcID.NUMBER, -1, x)));
				return true;
			}
		}
		return false;
	}
}
