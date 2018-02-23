import re
import pandas as pd


def __parse_time_elapsed__(time_line):
    time = float(time_line.split(":")[1][:-len("seconds.")])
    return time


def __file_to_lines__(path):
    l = None
    with open(path, "r") as r:
        l = r.readlines()
    return l


def parse_stats(stats, contains_command=True):
    if type(stats) == str:
        lines = __file_to_lines__(stats)
    else:
        lines = stats

    data_start = 1 if contains_command else 0
    clean = [l[65:] for l in lines[data_start + 3:-1]]
    sts = {}
    for s in clean:
        a = s.split(':')
        sts[a[0]] = int(a[1].strip())
    sts["time elapsed"] = __parse_time_elapsed__(lines[-1])
    return sts


parsed_output = re.compile("([0-9]+\:[0-9]{2}\:[0-9]{2}\.[0-9]{3})\s\[\w+\]\s([A-Z]+)\s+([\w\.]+) - ([\w\s0-9\:]+)")


def parse_indexing(indexing_results, contains_command=True):
    if type(indexing_results) == str:
        lines = __file_to_lines__(indexing_results)
    else:
        lines = indexing_results

    data_start = 1 if contains_command else 0
    clean = lines[data_start + 2:-1]
    sts = {}
    r = []
    for s in clean:
        match = parsed_output.search(s)
        if match:
            time = match.group(1).strip()
            kind = match.group(2).strip()
            clase = match.group(3).strip()
            message = match.group(4).strip()
            r.append([time, kind, clase, message])
    info = pd.DataFrame(r, columns=['time', 'kind', 'clase', 'message'])

    time = __parse_time_elapsed__(lines[-1])
    return info, time


def parse_run_results(results, contains_command=True):
    if type(results) == str:
        lines = __file_to_lines__(results)
    else:
        lines = results
    sts = {}
    to_index = lines[-2].index("to ")
    sts["results"] = lines[-2][to_index + 3:].strip()
    sts["time elapsed"] = __parse_time_elapsed__(lines[-1])
    return sts


def parse_evaluation_results(results, contains_command=True):
    if type(results) == str:
        lines = __file_to_lines__(results)
    else:
        lines = results
    sts = {}
    sts["results"] = lines[-3][85:].strip()
    sts["time elapsed"] = __parse_time_elapsed__(lines[-1])
    return sts
