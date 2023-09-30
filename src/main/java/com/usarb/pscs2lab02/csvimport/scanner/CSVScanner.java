package com.usarb.pscs2lab02.csvimport.scanner;

import com.opencsv.CSVReader;
import com.usarb.pscs2lab02.csvimport.models.*;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

@Component
public class CSVScanner {
    @Autowired
    private final RegionRepository regionRepository;
    @Autowired
    private final StateRepository stateRepository;
    @Autowired
    private final CityRepository cityRepository;
    @Autowired
    private final CustomerAddressRepository customerAddressRepository;
    @Autowired
    private final CustomerSegmentRepository customerSegmentRepository;
    @Autowired
    private final CustomerRepository customerRepository;
    @Autowired
    private final ProductCategoryRepository productCategoryRepository;
    @Autowired
    private final ProductBoxRepository productBoxRepository;
    @Autowired
    private final ProductRepository productRepository;
    @Autowired
    private final OrderShippingTypeRepository orderShippingTypeRepository;
    @Autowired
    private final OrderPriorityRepository orderPriorityRepository;
    @Autowired
    private final OrderItemRepository orderItemRepository;
    @Autowired
    private final OrderRepository orderRepository;

    private Map<String, Region> regionIdentityMap = new HashMap<>();

    public CSVScanner(RegionRepository regionRepository,
                      StateRepository stateRepository,
                      CityRepository cityRepository,
                      CustomerAddressRepository customerAddressRepository,
                      CustomerSegmentRepository customerSegmentRepository,
                      CustomerRepository customerRepository,
                      ProductCategoryRepository productCategoryRepository,
                      ProductBoxRepository productBoxRepository,
                      ProductRepository productRepository,
                      OrderShippingTypeRepository orderShippingTypeRepository,
                      OrderPriorityRepository orderPriorityRepository,
                      OrderItemRepository orderItemRepository,
                      OrderRepository orderRepository) {
        this.regionRepository = regionRepository;
        this.stateRepository = stateRepository;
        this.cityRepository = cityRepository;
        this.customerAddressRepository = customerAddressRepository;
        this.customerSegmentRepository = customerSegmentRepository;
        this.customerRepository = customerRepository;
        this.productCategoryRepository = productCategoryRepository;
        this.productBoxRepository = productBoxRepository;
        this.productRepository = productRepository;
        this.orderShippingTypeRepository = orderShippingTypeRepository;
        this.orderPriorityRepository = orderPriorityRepository;
        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
    }

    public void scan() throws Exception {
        String fname = "/Users/green/IdeaProjects/pscs2-lab02/Orders.csv";
        try (CSVReader reader = new CSVReader(new FileReader(fname))) {
            String[] values = null;
            while ((values = reader.readNext()) != null) {
                if (values[0].equals("Row ID")) {
                    continue;
                }
                InputRecord record = new InputRecord(values);
                Region region = loadOrSaveRegion(record);
                State state = loadOrSaveState(record, region);
                City city = loadOrSaveCity(record, state);
                CustomerSegment segment = loadOrSaveCustomerSegment(record);
                Customer customer = loadOrSaveCustomer(record, segment, city);

                // Product section
                ProductCategory cat1 = loadOrSaveProductCategory(record);
                ProductCategory cat2 = loadOrSaveProductSubCategory(record);
                ProductBox box = loadOrSaveProductBox(record);
                Product product = loadOrSaveProduct(record, cat1, cat2, box);


                System.out.println(values[0]);
                return;
            }
        }
    }

    @Cacheable(cacheNames = {"regionCache"}, key = "#record.getRegion()")
    private Region loadOrSaveRegion(InputRecord record) {
        Region region = regionRepository.findByName(record.getRegion());
        if (region == null) {
            region = new Region();
            region.setName(record.getRegion());
            region = regionRepository.save(region);
        }
        return region;
    }

    @Cacheable(cacheNames = {"stateCache"}, key="#record.getState()")
    private State loadOrSaveState(InputRecord record, Region region) {
        State state = stateRepository.findByName(record.getState());
        if (state == null) {
            state = new State();
            state.setName(record.getState());
            state.setRegion(region);
            state = stateRepository.save(state);
        }
        return state;
    }

    @Cacheable(cacheNames = {"cityCache"}, key="#record.getCity()")
    private City loadOrSaveCity(InputRecord record, State state) {
        City city = cityRepository.findByName(record.getCity());
        if (city == null) {
            city = new City();
            city.setName(record.getCity());
            city.setState(state);
            city = cityRepository.save(city);
        }

        return city;
    }

    @Cacheable(cacheNames = {"customerSegmentCache"}, key = "#record.getCustomerSegment()")
    private CustomerSegment loadOrSaveCustomerSegment(InputRecord record) {
        CustomerSegment segment = customerSegmentRepository.findByName(record.getCustomerSegment());
        if (segment == null) {
            segment = new CustomerSegment();
            segment.setName(record.getCustomerSegment());
            segment = customerSegmentRepository.save(segment);
        }

        return city;
    }

    @Cacheable(cacheNames = {"customerCache"}, key = "#record.getCustomerId()")
    private Customer loadOrSaveCustomer(InputRecord record, CustomerSegment segment, City city) {
        Customer customer = customerRepository.findById(record.getCustomerId()).orElse(null);
        if (customer == null) {
            customer = new Customer();
            customer.setName(record.getCustomerSegment());
            customer.setSegment(segment);
            customer.setCity(city);
            customer = customerRepository.save(customer);
        }

        return customer;
    }

    @Cacheable(cacheNames = {"categoryCache"}, key = "#record.getProductCategory()")
    private ProductCategory loadOrSaveProductCategory(InputRecord record) {
        ProductCategory category = productCategoryRepository.findByName(record.getProductCategory());
        if (category == null) {
            category = new ProductCategory();
            category.setName(record.getProductCategory());
            category = productCategoryRepository.save(category);
        }
        return category;
    }

    @Cacheable(cacheNames = {"subCategoryCache"}, key = "#record.getProductSubCategory()")
    private ProductCategory loadOrSaveProductSubCategory(InputRecord record) {
        ProductCategory category = productCategoryRepository.findByName(record.getProductSubCategory());
        if (category == null) {
            category = new ProductCategory();
            category.setName(record.getProductSubCategory());
            category = productCategoryRepository.save(category);
        }
        return category;
    }

    @Cacheable(cacheNames = {"productBoxCache"}, key = "#record.getProductSubCategory()")
    private ProductBox loadOrSaveProductBox(InputRecord record) {
        ProductBox box = productBoxRepository.findByName(record.getProductBox());
        if (box == null) {
            box = new ProductBox();
            box.setName(record.getProductBox());
            box = productBoxRepository.save(box);
        }
        return box;
    }

    @Cacheable(cacheNames = {"productCache"}, key = "#record.getProductName()")
    private Product loadOrSaveProduct(InputRecord record, ProductCategory cat1, ProductCategory cat2, ProductBox box) {
        Product product = productRepository.findByName(record.getProductName());
        if (product == null) {
            product = new Product();
            product.setName(record.getProductName());
            product.setCategory(cat1);
            product.setSubCategory(cat2);
            product.setBox(box);
            product = productRepository.save(product);
        }

        return product;
    }

    @Cacheable(cacheNames = {"orderPriorityCache"}, key = "#record.getPriority()")
    private OrderPriority loadOrSaveOrderPriority(InputRecord record) {
        OrderPriority priority = orderPriorityRepository.findByName(record.getPriority());
        if (priority == null) {
            priority = new OrderPriority();
            priority.setName(record.getPriority());
            priority = orderPriorityRepository.save(priority);
        }
        return priority;
    }

    @Cacheable(cacheNames = {"orderShippingTypeCache"}, key = "#record.getShippingType()")
    private OrderShippingType loadOrSaveOrderShippingType(InputRecord record) {
        OrderShippingType shipping = orderShippingTypeRepository.findByName(record.getShippingType());
        if (shipping == null) {
            shipping = new OrderShippingType();
            shipping.setName(record.getShippingType());
            shipping = orderShippingTypeRepository.save(shipping);
        }
        return shipping;
    }

    private Order loadOrSaveOrder(InputRecord record) {
        Order order = orderRepository.findById(record.getOrderId()).orElse(null);
        if (order == null) {
            order = new Order();
            order.setId(record.getOrderId());
        }
    }
}
