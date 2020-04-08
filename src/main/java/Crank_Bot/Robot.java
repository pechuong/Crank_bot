package Crank_Bot;

public class Robot {
	public static String voice(String message) {
		StringBuilder converted = new StringBuilder();
		for (char c : message.toCharArray()) {
			if (Math.random() >= 0.25) {
				converted.append(c);
			} else {
				converted.append(Character.toUpperCase(c));
			}
		}
		return converted.toString();
	}
}
