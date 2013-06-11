package no.force.cassandra;

import no.force.cassandra.config.NoForceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

@Component
public class NoForceMain {

    private static final Logger LOG = LoggerFactory.getLogger(NoForceMain.class);

    @Autowired
    private CassandraService cassandraService;

    public static void main(String[] args) throws IOException {
        LOG.info("Start...");
        ApplicationContext ctx = new AnnotationConfigApplicationContext(NoForceConfig.class);

        NoForceMain main = ctx.getBean(NoForceMain.class);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Some Text ('quit' to exit)");

        while (true) {

            String line = scanner.nextLine();
            if ("quit".equalsIgnoreCase(line)) {
                System.out.println("Bye!");
                break;
            }

            if ("execute".equalsIgnoreCase(line)) {
                System.out.println("Execute invoked!");
                main.cassandraService.execute();
                break;
            }

        }


        scanner.close();
        main.cassandraService.disconnect();


        LOG.info("Exit...");
    }

}
