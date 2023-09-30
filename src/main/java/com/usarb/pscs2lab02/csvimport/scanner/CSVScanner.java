package com.usarb.pscs2lab02.csvimport.scanner;

import com.opencsv.CSVReader;
import com.usarb.pscs2lab02.csvimport.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class CSVScanner {
    @Autowired
    private final ModelLoader loader;

    public CSVScanner(ModelLoader loader) {
        this.loader = loader;
    }

    public void scan(String fileName) throws Exception {
        try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
            String[] values = null;
            while ((values = reader.readNext()) != null) {
                if (values[0].equals("Row ID")) {
                    continue;
                }
                InputRecord record = new InputRecord(values);
                Region region = loader.loadOrSaveRegion(record);
                State state = loader.loadOrSaveState(record, region);
                City city = loader.loadOrSaveCity(record, state);
                CustomerSegment segment = loader.loadOrSaveCustomerSegment(record);
                Customer customer = loader.loadOrSaveCustomer(record, segment, city);

                // Product section
                ProductCategory cat1 = loader.loadOrSaveProductCategory(record);
                ProductCategory cat2 = loader.loadOrSaveProductSubCategory(record);
                ProductBox box = loader.loadOrSaveProductBox(record);
                Product product = loader.loadOrSaveProduct(record, cat1, cat2, box);

                // Orders
                OrderPriority priority = loader.loadOrSaveOrderPriority(record);
                OrderShippingType shippingType = loader.loadOrSaveOrderShippingType(record);
                Order order = loader.loadOrSaveOrder(record, priority, customer);
                OrderItem item = loader.loadOrSaveOrderItem(record, shippingType, order, product);
            }
        }
    }

}
