package calc;

import java.util.ArrayList;

public class Separator {
	private ArrayList<Character> terminators;
	private ArrayList<Character> excluders;
	public Separator(ArrayList<Character> terminators, ArrayList<Character> excluders) {
		this.terminators = terminators;
		this.excluders = excluders;
	}
	public ArrayList<String> separate(String input){
		int len = input.length();
		int i = 0;
		ArrayList<String> result = new ArrayList<String>();
		String runningTotal = "";
		while(i < len) {//Loop from 0 to length
			char thisChar = input.charAt(i);//gets char at i
			if(this.isTerminator(thisChar)) {//if it's a terminator
				if(runningTotal != "") {//So no empty characters are added
					result.add(runningTotal);//adds the running total to the result
					runningTotal = "";//resets the running total
				}
				if(!this.isExcluder(thisChar)) {
					result.add(thisChar + "");//if it isn't an excluder, adds the char to the result
				}
			} else {
				if(!this.isExcluder(thisChar)) {
					runningTotal += thisChar;//if it's not a terminator (or an excluder), adds the char to the running total. 
				}//If it's an excluder but not a terminator, does nothing
			}
			i ++;
		}
		if(runningTotal != "") {//Adds a character if the string isn't empty, one last time
			result.add(runningTotal);//adds the running total to the result
			runningTotal = "";//resets the running total
		}
		return result;
	}
	private boolean isTerminator(char input) {
		int i = 0;
		while(i < terminators.size()) {
			if(input == terminators.get(i)) {
				return true;
			}
			i ++;
		}
		return false;
	}
	private boolean isExcluder(char input) {
		int i = 0;
		while(i < excluders.size()) {
			if(input == excluders.get(i)) {
				return true;
			}
			i ++;
		}
		return false;
	}
}
