package com.github.woki.payments.adyen.client.control;

import static com.github.woki.payments.adyen.client.control.ControlUtil.buildOptions;
import static com.github.woki.payments.adyen.client.control.ControlUtil.getCommand;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import com.github.woki.payments.adyen.client.Main;
import com.github.woki.payments.adyen.client.command.Command;
import com.github.woki.payments.adyen.client.model.YAMLRequest;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.yaml.snakeyaml.Yaml;

/**
 * Created by Willian Oki on 9/24/15.
 */
@Controller
public class Commander implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        CommandLineParser parser = new DefaultParser();
        try {
            Options options = buildOptions();
            CommandLine line = parser.parse(options, args);
            HelpFormatter formatter = new HelpFormatter();
            if (line.hasOption("help")) {
                formatter.printHelp(Main.class.getName(), options);
                return;
            }
            String infile = line.getOptionValue("request-file");
            if (StringUtils.isBlank(infile)) {
                System.err.println("missing parameter: request-file");
                formatter.printHelp(Main.class.getName(), options);
                return;
            }
            InputStream is;
            try {
                is = new FileInputStream(infile);
            } catch (FileNotFoundException e) {
                System.err.println("invalid request-file");
                formatter.printHelp(Main.class.getName(), options);
                return;
            }
            try {
                Yaml yaml = new Yaml();
                YAMLRequest request = yaml.loadAs(is, YAMLRequest.class);
                if (!request.getType().startsWith("authorization") && line.hasOption("orig-ref")) {
                    String originalReference = line.getOptionValue("orig-ref", "none");
                    if (!originalReference.equals("none")) {
                        request.getModificationRequest().setOriginalReference(originalReference);
                    }
                }
                Command command = getCommand(request);
                command.execute();
            } catch (Exception e) {
                System.err.println("Request execution failure: " + e.getMessage());
            }
        } catch (ParseException e) {
            System.err.println("Command line parsing failure: " + e.getMessage());
        }
    }
}
