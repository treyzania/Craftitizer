
counter = 0;

def reset():
    global counter
    counter = 0

def announce_dl(context, url):
    global counter
    counter += 1
    print("[" + str(counter) + "]", context, url)
