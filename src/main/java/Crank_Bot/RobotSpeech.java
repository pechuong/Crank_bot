package Crank_Bot;

public abstract class RobotSpeech {
	public static String robotify(String message) {
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
