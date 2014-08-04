import time

# example data:
# {
# 	'Alice': {
# 		'follows': ['Bob', 'Charlie'],
# 		'posts': {
# 			<insert epoch time here>: 'this is a post',
# 		},
# 	},
# }
# this method unfortunatly does not account for multiple users with the same name
# also in the unlikely event that 2 posts are made at the exact same time by the same user only 1 will be kept

data = {}

def loop():
	while True:
		getInput()

def getInput():
	userInput = raw_input('> ')
	parseInput(userInput)


def parseInput(userInput):
	targets = userInput.split(' ', 2)
	user = targets[0]

	# ugly, could use refactoring
	if len(targets) == 1:
		read(user)
		return

	if len(targets) == 2:
		wall(user)
		return

	cmd = targets[1]
	target = targets[2]

	if cmd == '->':
		post(user, target)
	elif cmd == 'follows':
		follow(user, target)

def post(user, target):
	if user not in data:
		data[user] = {'follows': [], 'posts': {},}

	userPosts = data[user]['posts']
	userPosts[int(time.time())] = target

def read(user):
	 posts = getPosts(user)
	 for post in sorted(posts, None, None, True):
		print posts[post] + timeDiff(post)

# could use refactoring
def wall(user):
	posts = {}
	posts[user] = getPosts(user)
	follows = data[user]['follows']

	# get posts from all follows
	for user in follows:
		posts[user] = getPosts(user)

	wallPosts = {}

	for user in posts:
		for post in posts[user]:
			wallPosts[post] = user + ' - ' + getPosts(user)[post]

	for post in sorted(wallPosts, None, None, True):
		print wallPosts[post] + timeDiff(post)


def follow(user, target):
	data[user]['follows'].append(target)

# Utils
def getPosts(user):
	return data[user]['posts']

def timeDiff(postTime):
	td = int(time.time()) - postTime
	# I assume I dont need to go to greater quantities of time than a minute
	if td > 60:
		# integer devision floors by default
		return ' (' + str(td/60) + ' minutes ago)'

	return ' (' + str(td) + ' seconds ago)'

loop()
