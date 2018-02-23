def read_topics(file):
    queries = {}
    lines = None
    with open(file, "r") as topic_file:
        lines = topic_file.readlines()
    i = 0
    number = -1
    query = None
    for line in lines:
        line = line.strip()
        if line == '':
            continue
        i = (i + 1) % 4
        if i == 2 and number == -1:  # number
            number = int(line[line.index(':') + 1:].strip())
        elif i == 3 and number != -1:
            queries[number] = line[line.index('>') + 1:].strip()
            number = -1
    return queries
