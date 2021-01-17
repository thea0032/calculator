package calc;

import java.util.ArrayList;

public class VarTable {
	private ArrayList<String> singles;
	private ArrayList<Double> singleVals;
	private ArrayList<String> lists;
	private ArrayList<ArrayList<Double>> listVals;
	private ArrayList<String> strs;
	private ArrayList<ArrayList<CalcData>> strVals;

	public VarTable() {
		singles = new ArrayList<String>();
		lists = new ArrayList<String>();
		singleVals = new ArrayList<Double>();
		listVals = new ArrayList<ArrayList<Double>>();
		strs = new ArrayList<String>();
		strVals = new ArrayList<ArrayList<CalcData>>();
	}

	public void setSingle(String name, double initVal) {
		boolean addNew = true;
		for (int x = 0; x < singles.size(); x++) {
			if (singles.get(x).equals(name)) {
				addNew = false;
				singleVals.set(x, initVal);
			}
		}
		if (addNew) {
			singles.add(name);
			singleVals.add(initVal);
		}
	}

	public int getSingleID(String name) {
		for (int x = 0; x < singles.size(); x++) {
			if (singles.get(x).equals(name)) {
				return x;
			}
		}
		return -1;
	}

	public double getSingleVal(int id) {
		return singleVals.get(id);
	}

	public String getSingleName(int id) {
		return singles.get(id);
	}

	public int getSingleLen() {
		return singles.size();
	}

	public void setStr(String name, ArrayList<CalcData> initVal) {
		boolean addNew = true;
		for (int x = 0; x < strs.size(); x++) {
			if (strs.get(x).equals(name)) {
				addNew = false;
				strVals.set(x, initVal);
			}
		}
		if (addNew) {
			strs.add(name);
			strVals.add(initVal);
		}
	}

	public int getStrID(String name) {
		for (int x = 0; x < strs.size(); x++) {
			if (strs.get(x).equals(name)) {
				return x;
			}
		}
		return -1;
	}

	public ArrayList<CalcData> getStrVal(int id) {
		return strVals.get(id);
	}

	public String getStrName(int id) {
		return strs.get(id);
	}

	public int getStrLen() {
		return strs.size();
	}

	public void setList(String name, double initVal, int pos) {
		boolean addNew = true;
		for (int x = 0; x < lists.size(); x++) {
			if (lists.get(x).equals(name)) {
				addNew = false;
				setList2(initVal, pos, x);
			}
		}
		if (addNew) {
			lists.add(name);
			listVals.add(new ArrayList<Double>());
			setList2(initVal, pos, lists.size() - 1);
		}
	}

	private void setList2(double initVal, int pos, int pos2) {
		ArrayList<Double> temp = listVals.get(pos2);
		if (pos < temp.size())
			temp.set(pos2, initVal);
		else {
			for (int x = temp.size(); x < pos; x++) {
				temp.add(0.0);
			}
			temp.add(initVal);
		}
	}

	public int getListID(String name) {
		for (int x = 0; x < lists.size(); x++) {
			if (lists.get(x).equals(name)) {
				return x;
			}
		}
		return -1;
	}

	public double getListVal(int id, int pos) {
		return listVals.get(id).get(pos);
	}

	public ArrayList<Double> getListValRaw(int id) {
		return listVals.get(id);
	}

	public String getListName(int id) {
		return lists.get(id);
	}

	public int getListLen() {
		return lists.size();
	}
}
