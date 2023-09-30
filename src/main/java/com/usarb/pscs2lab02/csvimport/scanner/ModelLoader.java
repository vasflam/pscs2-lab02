package com.usarb.pscs2lab02.csvimport.scanner;

import com.usarb.pscs2lab02.csvimport.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class ModelLoader {
    @Autowired
    private final RegionRepository regionRepository;
    @Autowired
    private final StateRepository stateRepository;
    @Autowired
    private final CityRepository cityRepository;
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

    public ModelLoader(RegionRepository regionRepository,
                      StateRepository stateRepository,
                      CityRepository cityRepository,
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

    @Cacheable(cacheNames = {"regionCache"}, key = "#record.getRegion()")
    Region loadOrSaveRegion(InputRecord record) {
        Region region = regionRepository.findByName(record.getRegion());
        if (region == null) {
            region = new Region();
            region.setName(record.getRegion());
            region = regionRepository.save(region);
        }
        return region;
    }

    @Cacheable(cacheNames = {"stateCache"}, key="#record.getState()")
    State loadOrSaveState(InputRecord record, Region region) {
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
    City loadOrSaveCity(InputRecord record, State state) {
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
    CustomerSegment loadOrSaveCustomerSegment(InputRecord record) {
        CustomerSegment segment = customerSegmentRepository.findByName(record.getCustomerSegment());
        if (segment == null) {
            segment = new CustomerSegment();
            segment.setName(record.getCustomerSegment());
            segment = customerSegmentRepository.save(segment);
        }

        return segment;
    }

    @Cacheable(cacheNames = {"customerCache"}, key = "#record.getCustomerId()")
    Customer loadOrSaveCustomer(InputRecord record, CustomerSegment segment, City city) {
        Customer customer = customerRepository.findById(record.getCustomerId()).orElse(null);
        if (customer == null) {
            customer = new Customer();
            customer.setCustomerId(record.getCustomerId());
            customer.setName(record.getCustomerName());
            customer.setSegment(segment);
            customer.setCity(city);
            customer = customerRepository.save(customer);
        }

        return customer;
    }

    @Cacheable(cacheNames = {"categoryCache"}, key = "#record.getProductCategory()")
    ProductCategory loadOrSaveProductCategory(InputRecord record) {
        ProductCategory category = productCategoryRepository.findByName(record.getProductCategory());
        if (category == null) {
            category = new ProductCategory();
            category.setName(record.getProductCategory());
            category = productCategoryRepository.save(category);
        }
        return category;
    }

    @Cacheable(cacheNames = {"subCategoryCache"}, key = "#record.getProductSubCategory()")
    ProductCategory loadOrSaveProductSubCategory(InputRecord record) {
        ProductCategory category = productCategoryRepository.findByName(record.getProductSubCategory());
        if (category == null) {
            category = new ProductCategory();
            category.setName(record.getProductSubCategory());
            category = productCategoryRepository.save(category);
        }
        return category;
    }

    @Cacheable(value = "productBoxCache", key = "#record.getProductBox()")
    ProductBox loadOrSaveProductBox(InputRecord record) {
        ProductBox box = productBoxRepository.findByName(record.getProductBox());
        if (box == null) {
            box = new ProductBox();
            box.setName(record.getProductBox());
            box = productBoxRepository.save(box);
        }
        return box;
    }

    @Cacheable(value = "productCache", key = "#record.getProductName()")
    Product loadOrSaveProduct(InputRecord record, ProductCategory cat1, ProductCategory cat2, ProductBox box) {
        Product product = productRepository.findByName(record.getProductName());
        if (product == null) {
            product = new Product();
            product.setName(record.getProductName());
            product.setCategory(cat1);
            product.setSubCategory(cat2);
            product.setBox(box);
            product.setPrice(record.getPrice());
            product.setBaseMargin(record.getProductBaseMargin());
            product.setShippingPrice(record.getShippingPrice());
            product = productRepository.save(product);
        }

        return product;
    }

    @Cacheable(cacheNames = {"orderPriorityCache"}, key = "#record.getPriority()")
    OrderPriority loadOrSaveOrderPriority(InputRecord record) {
        OrderPriority priority = orderPriorityRepository.findByName(record.getPriority());
        if (priority == null) {
            priority = new OrderPriority();
            priority.setName(record.getPriority());
            priority = orderPriorityRepository.save(priority);
        }
        return priority;
    }

    @Cacheable(cacheNames = {"orderShippingTypeCache"}, key = "#record.getShippingType()")
    OrderShippingType loadOrSaveOrderShippingType(InputRecord record) {
        OrderShippingType shipping = orderShippingTypeRepository.findByName(record.getShippingType());
        if (shipping == null) {
            shipping = new OrderShippingType();
            shipping.setName(record.getShippingType());
            shipping = orderShippingTypeRepository.save(shipping);
        }
        return shipping;
    }

    @Cacheable(cacheNames = {"orderCache"}, key = "#record.getOrderId()")
    Order loadOrSaveOrder(InputRecord record,
                                  OrderPriority priority,
                                  Customer customer) {
        Order order = orderRepository.findById(record.getOrderId()).orElse(null);
        if (order == null) {
            order = new Order();
            order.setOrderId(record.getOrderId());
            order.setPriority(priority);
            order.setCustomer(customer);

            DateTimeFormatter f = DateTimeFormatter.ofPattern("M/d/yyyy");
            order.setCreatedAt(LocalDate.parse(record.getOrderDate(), f));
            order.setShippedAt(LocalDate.parse(record.getShippingDate(), f));
            order = orderRepository.save(order);
        }

        return order;
    }

    @Cacheable(value = "orderItemCache", key = "{#order.getId(), #product.getId()}")
    OrderItem loadOrSaveOrderItem(InputRecord record,
                                          OrderShippingType shipping,
                                          Order order,
                                          Product product) {
        OrderItem item = orderItemRepository.findByOrderIdAndProductId(order.getId(), product.getId());
        if (item == null) {
            item = new OrderItem();
            item.setOrder(order);
            item.setProduct(product);
            item.setShippingType(shipping);
            item.setDiscount(record.getDiscount());
            item.setQuantity(record.getQuantity());
            item.setProfit(record.getProfit());
            item.setSales(record.getSales());
            item = orderItemRepository.save(item);
        }

        return item;
    }
}
