package com.usarb.pscs2lab02.csvimport.scanner;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class InputRecord {
    private List<String> data;
    private Integer rowId;
    private String priority;
    private Float discount;
    private Float price;
    private Float shippingPrice;
    private Integer customerId;
    private String customerName;
    private String shippingType;
    private String customerSegment;
    private String productCategory;
    private String productSubCategory;
    private String productBox;
    private String productName;
    private Float productBaseMargin;
    private String region;
    private String state;
    private String City;
    private Integer zip;
    private String orderDate;
    private String shipDate;
    private Float profit;
    private Integer quantity;
    private Float sales;
    private Long orderId;
    public InputRecord(String[] input) {
        this.data = Arrays.asList(input);
    }

    public String getPriority() {
        return data.get(1);
    }

    public Float getDiscount() {
        return Float.valueOf(data.get(2));
    }

    public Float getPrice() {
        return Float.valueOf(data.get(3));
    }

    public Float getShippingPrice() {
        return Float.valueOf(data.get(4));
    }

    public Long getCustomerId() {
        return Long.valueOf(data.get(5));
    }

    public String getCustomerName() {
        return data.get(6);
    }

    public String getShippingType() {
        return data.get(7);
    }

    public String getCustomerSegment() {
        return data.get(8);
    }

    public String getProductCategory() {
        return data.get(9);
    }

    public String getProductSubCategory() {
        return data.get(10);
    }

    public String getProductBox() {
        return data.get(11);
    }

    public String getProductName() {
        return data.get(12);
    }

    public Float getProductBaseMargin() {
        return Float.valueOf(data.get(13));
    }

    public String getRegion() {
        return data.get(14);
    }

    public String getState() {
        return data.get(15);
    }

    public String getCity() {
        return data.get(16);
    }

    public Integer getZip() {
        return Integer.valueOf(data.get(17));
    }

    public String getOrderDate() {
        return data.get(18);
    }

    public String getShipDate() {
        return data.get(19);
    }

    public Float getProfit() {
        return Float.valueOf(data.get(20));
    }

    public Integer getQuantity() {
        return Integer.valueOf(data.get(21));
    }

    public Long getOrderId() {
        return Long.valueOf(data.get(22));
    }
}