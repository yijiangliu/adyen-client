package com.github.woki.payments.adyen.client.control;

import com.github.woki.payments.adyen.client.Main;
import com.github.woki.payments.adyen.client.command.Command;
import com.github.woki.payments.adyen.client.model.YAMLAmount;
import com.github.woki.payments.adyen.client.model.YAMLRequest;
import org.apache.commons.cli.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static com.github.woki.payments.adyen.client.control.ControlUtil.*;

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
                if (line.hasOption("auto-merchref")) {
                    String refType = line.getOptionValue("auto-merchref", "timestamp");
                    String merchantReference = makeMerchantReference(refType);
                    if (request.getType().startsWith("authorization")) {
                        request.getPaymentRequest().setReference(request.getType() + "-" + merchantReference);
                    } else {
                        request.getModificationRequest().setReference(request.getType() + "-" + merchantReference);
                    }
                }
                if (line.hasOption("merchref")) {
                    String merchantReference = line.getOptionValue("merchref", "none");
                    if (!merchantReference.equals("none")) {
                        if (request.getType().startsWith("authorization")) {
                            request.getPaymentRequest().setReference(merchantReference);
                        } else {
                            request.getModificationRequest().setReference(merchantReference);
                        }
                    }
                }
                if (!request.getType().startsWith("authorization") && line.hasOption("orig-ref")) {
                    String originalReference = line.getOptionValue("orig-ref", "none");
                    if (!originalReference.equals("none")) {
                        request.getModificationRequest().setOriginalReference(originalReference);
                    }
                    String modificationAmountValue = line.getOptionValue("value", "none");
                    if (!modificationAmountValue.equals("none")) {
                        long value;
                        try {
                            value = Long.valueOf(modificationAmountValue);
                            YAMLAmount amount = new YAMLAmount();
                            amount.setCurrencyCode(request.getModificationRequest().getModificationAmount().getCurrencyCode());
                            amount.setValue(value);
                            request.getModificationRequest().setModificationAmount(amount);
                        } catch (NumberFormatException e) {
                            // do nothing
                        }
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
