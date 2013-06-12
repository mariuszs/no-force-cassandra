package no.force.cassandra.cli;

import static com.google.common.base.Strings.isNullOrEmpty;

public enum Command {
    QUIT, STAX, HELP, NONE, UNKNOWN;

    public static Command parse(String line) {

        if (!isNullOrEmpty(line)) {
            for (Command c : values()) {
                if (c.name().equalsIgnoreCase(line)) {
                    return c;
                }
            }
            return UNKNOWN;
        } else {
            return NONE;
        }
    }

}
