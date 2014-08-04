
public class Main {

	public static void main(String[] args) {

		while (true) {
			String[] input = IO.getInput();
			command(input);
		}

	}

	public static void command(String[] input) {

		if (input.length == 1) {
			read(input[0]);
			return;
		}

		String cmd = input[1];

		switch (cmd) {
			case "->": post(input[0], input[2]); break;
			case "follows": follow(input[0], input[2]); break;
			case "wall": wall(input[0]); break;
			default: System.out.println("Invalid command: \"" + cmd + "\""); break;
		}
	}

	public static void post(String user, String post) {
		Data.addPost(user, post);
	}

	public static void read(String user) {
		IO.printPosts(Data.getPosts(user));
	}

	public static void follow(String user, String targetUser) {
		Data.addFollower(user, targetUser);
	}

	public static void wall(String user) {
		IO.printPosts(Data.getWallPosts(user));
	}

}
