package com.github.woki.payments.adyen.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.FileNotFoundException;

/**
 * Created by Willian Oki on 9/23/15.
 */
@SpringBootApplication
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        SpringApplication.run(Main.class, args);
    }
}
