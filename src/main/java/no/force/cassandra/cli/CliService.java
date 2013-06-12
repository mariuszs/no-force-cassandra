package no.force.cassandra.cli;

import no.force.cassandra.DataStaxService;
import org.fusesource.jansi.AnsiConsole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

import static org.fusesource.jansi.Ansi.Color.RED;
import static org.fusesource.jansi.Ansi.ansi;
import static org.fusesource.jansi.AnsiConsole.out;

@Service
public class CliService {

    @Autowired
    private DataStaxService dataStaxService;

    private Help help = new Help();

    public void commandLine() {
        Scanner scanner = new Scanner(System.in);
        AnsiConsole.systemInstall();
        out.println(ansi().bold().a("Enter Some Text ('quit' to exit, 'help' to print help)").reset());

        Command command;

        do {

            String line = scanner.nextLine();

            command = Command.parse(line);

            switch (command) {

                case UNKNOWN:
                    out.println(ansi()
                            .fg(RED).a("Unknown command: ")
                            .bold().a(line).boldOff()
                            .reset());
                    continue;

                case STAX:
                    out.println("Execute invoked!");
                    dataStaxService.execute();
                    continue;

                case HELP:
                    help.printHelp();
                    continue;

                default:

            }


        } while (command != Command.QUIT);

        out.println("Bye!");
        scanner.close();
        AnsiConsole.systemUninstall();

    }

}
