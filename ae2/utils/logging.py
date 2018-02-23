def write_log(file, lines, command = None):
    with open(file, "w") as log_file:
        if command is not None:
            log_file.write("COMMAND: %s \n" % command)
        log_file.write("\n".join(lines))
