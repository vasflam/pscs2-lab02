package com.usarb.pscs2lab02.csvimport;

import com.usarb.pscs2lab02.csvimport.scanner.CSVScanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CsvImportConsoleApplication implements CommandLineRunner {
    private CSVScanner scanner;

    @Autowired
    public CSVScanner csvScanner(CSVScanner scanner) {
        this.scanner = scanner;
        return scanner;
    }

    public static boolean flushDatabase() {
        return "1".equals(System.getenv("FLUSH_DATABASE"));
    }

    public static void main(String[] args) {
        if (flushDatabase()) {
            System.setProperty("spring.jpa.hibernate.ddl-auto", "create-drop");
        }

        new SpringApplicationBuilder(CsvImportConsoleApplication.class)
                .web(WebApplicationType.NONE)
                .bannerMode(Banner.Mode.OFF)
                .logStartupInfo(false)
                .build()
                .run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (flushDatabase()) {
            return;
        }

        String fname = "./docs/orders.csv";
        scanner.scan(fname);
    }
}
