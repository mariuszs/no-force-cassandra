package no.force.cassandra;

import no.force.cassandra.cli.CliService;
import no.force.cassandra.config.NoForceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class NoForceMain {

    private static final Logger LOG = LoggerFactory.getLogger(NoForceMain.class);

    @Autowired
    CliService cli;

    public static void main(String[] args) throws IOException {
        LOG.info("Start...");
        final AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(NoForceConfig.class);

        ctx.registerShutdownHook();

        NoForceMain main = ctx.getBean(NoForceMain.class);

        main.cli.commandLine();

        LOG.info("Exit...");
    }


}
