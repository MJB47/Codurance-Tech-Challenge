import java.util.*;

public class Data {

	static HashMap<String, TreeMap<Long, String>> posts = new HashMap<String, TreeMap<Long, String>>();
	static HashMap<String, ArrayList<String>> following = new HashMap<String, ArrayList<String>>();

	public static void addUser(String user) {
		posts.put(user, new TreeMap<Long, String>());
		following.put(user, new ArrayList<String>());
	}

	public static void addPost(String user, String post) {
		if (posts.get(user) == null) {
			addUser(user);
		}

		long epoch = System.currentTimeMillis()/1000;
		posts.get(user).put(epoch, post);
	}

	public static TreeMap<Long, String> getPosts(String user) {
		return posts.get(user);
	}

	public static void addFollower(String user, String targetUser) {
		following.get(user).add(targetUser);
	}

	public static ArrayList<String> getFollowers(String user) {
		return following.get(user);
	}

	public static TreeMap<Long, String> getWallPosts(String user) {
		TreeMap<Long, String> wallPosts = getPosts(user);
		prependWallPosts(wallPosts, user);

		for (String name : getFollowers(user)) {
			TreeMap<Long, String> temp = prependWallPosts(getPosts(name), name);
			wallPosts.putAll(temp);
		}

		return wallPosts;
	}

	public static TreeMap<Long, String> prependWallPosts(TreeMap<Long, String> wallPosts, String user) {
		Iterator it = wallPosts.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pairs = (Map.Entry)it.next();
	        String newPost = user + " - " + pairs.getValue();
	        wallPosts.put((Long)pairs.getKey(), newPost);
	    }

	    return wallPosts;
	}

}
