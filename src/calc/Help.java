package calc;

public class Help {
	public static String exe() {
		return "Type in an operation to perform it. Basic operations supported.\n"
			 + "Parenthesis are supported. There are also commands, as follows:\n"
			 + "sv - takes the second word, computes the rest of the line, \n"
			 + "and stores the result of that line on the second word..\n"
			 + "Example: sv n 5+5 will store the result of 5+5, which is 10, to the variable n.\n"
			 + "You can call n whenever - you can type n*5, and you'll get 50. \n"
			 + "sr - takes the second word and stores the rest of the line on that word.\n"
			 + "For example, sr n 5+5 would literally store \"5+5\" on n. \n"
			 + "This means a different thing - typing n*5 will simplify to 5+5*5, which turns to 30.\n"
			 + "You can also store operations here: sr plus + allows you to use the word plus as a plus sign.\n"
			 + "l - This can't be explained on one line. It has three inputs - variable, number of times, rest of line.\n"
			 + "The variable specified is set to 0 at the beginning. It repeatedly runs the rest of the line,\n"
			 + "increasing i by 1 each time, the number of times specified.\n"
			 + "Save saves all of the variables you declared. Load loads them from the same file that is saved to.\n"
			 + "Beware that saving overwrites the file. Loading doesn't overwrite your variables unless there are duplicate names. \n"
			 + "h - prints this menu. ";
	}
}
