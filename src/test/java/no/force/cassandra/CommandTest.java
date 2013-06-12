package no.force.cassandra;

import no.force.cassandra.cli.Command;
import org.testng.annotations.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class CommandTest {

    @Test
    public void shouldParse() {

        Command command = Command.parse("dupa");
        assertThat(command).isEqualTo(Command.UNKNOWN);

        command = Command.parse("quit");
        assertThat(command).isEqualTo(Command.QUIT);

    }
}
