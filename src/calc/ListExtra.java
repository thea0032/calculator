package calc;

import java.util.ArrayList;

public interface ListExtra{
	public static <T> ArrayList<T> splice(ArrayList<T> orig, int min, int max, ArrayList<T> splicer) {
		ArrayList<T> result = new ArrayList<T>();
		for (int i = min; i < max - min; i ++) 
			result.add(orig.remove(min));
		orig.addAll(min, splicer);
		return result;
	}
	public static <T> ArrayList<T> toList(T input){
		ArrayList<T> result = new ArrayList<T>();
		result.add(input);
		return result;
	}
	public static <T> ArrayList<T> subListClone(ArrayList<T> orig, int min, int max){
		ArrayList<T> result = new ArrayList<T>();
		for (int i = min; i < max; i ++) {
			result.add(orig.get(i));
		}
		return result;
	}
	public static <T> ArrayList<T> clone(ArrayList<T> orig){
		ArrayList<T> result = new ArrayList<T>();
		for(int i = 0; i < orig.size(); i ++) {
			result.add(orig.get(i));
		}
		return result;
	}
	public static String concat(ArrayList<String> orig) {
		String first = orig.get(0);
		for(int i = 1; i < orig.size(); i ++)
			first += " " + orig.get(i);
		return first;
	}
}
