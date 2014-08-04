import java.io.*;
import java.util.*;

public class IO {

	public static String[] getInput() {
		String input = null;

		System.out.print("> ");

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try {
        	input = br.readLine();
        } catch (IOException ioe) {
	        System.out.println("IO error trying to read input");
	        System.exit(1);
        }

        return parseInput(input);
	}

	public static String[] parseInput(String input) {
		String[] splitInput = input.split(" ", 3);
		return splitInput;
	}

	public static void printPosts(TreeMap<Long, String> posts) {
		Iterator it = posts.descendingMap().entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pairs = (Map.Entry)it.next();
	        System.out.println(pairs.getValue() + getTimeDiff((Long)pairs.getKey()));
    	}
	}

	public static String getTimeDiff(Long time) {
		long currTime = System.currentTimeMillis()/1000;
		long timeDiff = currTime - time;

		if (timeDiff >= 60) {
			timeDiff = timeDiff / 60;
			return " (" + Long.toString(timeDiff) + " minute/s ago)";
		} else {
			return " (" + Long.toString(timeDiff) + " second/s ago)";
		}
	}

}
