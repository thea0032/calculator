	package operations;

import java.util.ArrayList;

import calc.*;

public class Parenthesis {
	static int leftLocation = 0;
	static int rightLocation = 0;
	public static boolean exe(ArrayList<CalcData> input, int leftID, int rightID, Calculator calc) {
		findPars(input, leftID, rightID);
		if(leftLocation == -1 && rightLocation == input.size()) {
			return false;
		}
		ArrayList<CalcData> newList = ListExtra.subListClone(input, leftLocation+1, rightLocation);
		System.out.println("Inside parenthesis: " + calc.display(newList));
		int leftLocationHold = leftLocation;//So recursion doesn't change these values
		int rightLocationHold = rightLocation;
		calc.calculateFromCList(newList);
		if(leftLocationHold == -1) {leftLocationHold ++;}//If there's no parenthesis at the beginning, prevents an error
		if(rightLocationHold == input.size()) {rightLocationHold --;}//If there's no parenthesis at the end, prevents an error
		ListExtra.splice(input, leftLocationHold, rightLocationHold+1, newList);
		System.out.println("returning " + calc.display(input));
		return true;
		}
	public static void findPars(ArrayList<CalcData> input, int leftID, int rightID) {
		int parLevel = 0;
		leftLocation = -1;
		rightLocation = input.size();
		for(int x = 0; x < input.size(); x ++) {
			CalcData temp = input.get(x);
			if(temp.getId() == CalcID.OPERATOR) {
				if(temp.getData() == leftID) {
					parLevel ++;
					if(leftLocation == -1) {
						leftLocation = x;
					}
				}
				if(temp.getData() == rightID) {
					parLevel --;
					if(parLevel == 0) {
						rightLocation = x; 
						break;
					} else if(parLevel == -1) {
						rightLocation = x;
						break;
					}
				}
			}
		}
	}
}
