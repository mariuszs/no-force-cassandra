package no.force.cassandra.cli;


import java.util.EnumMap;
import java.util.Map;

import static org.fusesource.jansi.Ansi.Color.BLUE;
import static org.fusesource.jansi.Ansi.ansi;
import static org.fusesource.jansi.AnsiConsole.out;

public class Help {

    private EnumMap<Command, String> helpMap = new EnumMap<>(Command.class);

    public Help() {
        helpMap.put(Command.QUIT, "Quit");
        helpMap.put(Command.STAX, "Select data with DataStax CQL Driver");
        helpMap.put(Command.HELP, "Print help");
    }

    public void printHelp() {
        out.println(ansi().bold().a("Available commands").reset());

        for (Map.Entry<Command, String> c : helpMap.entrySet()) {
            out.println(ansi().fgBright(BLUE).a(c.getKey().name().toLowerCase()).reset()
                    .a(" - " + c.getValue()).reset());
        }
    }

}
