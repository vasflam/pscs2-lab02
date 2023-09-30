package com.usarb.pscs2lab02.csvimport;

import com.usarb.pscs2lab02.csvimport.scanner.CSVScanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CsvimportApplication {
	public static void main(String[] args) {
		SpringApplication.run(CsvimportApplication.class, args);
	}
}
