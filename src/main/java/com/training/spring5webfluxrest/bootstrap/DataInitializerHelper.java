package com.training.spring5webfluxrest.bootstrap;

import com.training.spring5webfluxrest.domain.Category;
import com.training.spring5webfluxrest.domain.Vendor;

public class DataInitializerHelper {
    // CONSTANTS

    public static final String CATEGORY_API_URL = "/api/v1/categories/";
    public static final String CUSTOMERS_API_URL = "/api/v1/customers/";
    public static final String VENDORS_API_URL = "/api/v1/vendors/";

//    INITIALIZE CATEGORIES DATA

    public static final Category FRUITS = Category.builder()
            .description("Fruits")
            .build();

    public static final Category DRIED = Category.builder()
            .description("Dried")
            .build();

    public static final Category EXOTIC = Category.builder()
            .description("Exotic")
            .build();
    public static final Category NUTS = Category.builder()
            .description("Nuts")
            .build();
    public static final Category FRESH = Category.builder()
            .description("fresh")
            .build();

//    public static final CategoryDTO FRUITS_DTO = CategoryDTO.builder()
//            .name("Fruits")
//            .build();
//
//    public static final CategoryDTO DRIED_DTO = CategoryDTO.builder()
//            .name("Dried")
//            .build();
//
//    public static final CategoryDTO EXOTIC_DTO = CategoryDTO.builder()
//            .name("Exotic")
//            .build();
//    public static final CategoryDTO NUTS_DTO = CategoryDTO.builder()
//            .name("Nuts")
//            .build();
//    public static final CategoryDTO FRESH_DTO = CategoryDTO.builder()
//            .name("fresh")
//            .build();


//    //    INITIALIZE CUSTOMERS DATA
//    public static final Customer CUSTOMER_1 = Customer.builder()
//            .id(1L)
//            .firstName("fn_customer_1")
//            .lastName("ln_customer_1")
//            .build();
//
//    public static final Customer CUSTOMER_2 = Customer.builder()
////            .id(2L)
//            .firstName("fn_customer_2")
//            .lastName("ln_customer_2")
//            .build();
//
//    public static final Customer CUSTOMER_3 = Customer.builder()
////            .id(3L)
//            .firstName("fn_customer_3")
//            .lastName("ln_customer_3")
//            .build();
//
//    public static final Customer CUSTOMER_4 = Customer.builder()
////            .id(4L)
//            .firstName("fn_customer_4")
//            .lastName("ln_customer_4")
//            .build();
//
//    //    Customers DTOS
//    public static final CustomerDTO CUSTOMER_DTO_1 = CustomerDTO.builder()
//            .id(1L)
//            .firstName("fn_customer_1")
//            .lastName("ln_customer_1")
//            .url(CUSTOMERS_API_URL.concat("id/1"))
//            .build();
//
//    public static final CustomerDTO CUSTOMER_DTO_2 = CustomerDTO.builder()
//            .id(2L)
//            .firstName("fn_customer_2")
//            .lastName("ln_customer_2")
//            .url(CUSTOMERS_API_URL.concat("id/2"))
//            .build();
//
//    public static final CustomerDTO CUSTOMER_DTO_3 = CustomerDTO.builder()
//            .id(3L)
//            .firstName("fn_customer_3")
//            .lastName("ln_customer_3")
//            .url(CUSTOMERS_API_URL.concat("id/3"))
//            .build();
//
//    public static final CustomerDTO CUSTOMER_DTO_4 = CustomerDTO.builder()
//            .id(4L)
//            .firstName("fn_customer_4")
//            .lastName("ln_customer_4")
//            .url(CUSTOMERS_API_URL.concat("id/4"))
//            .build();

    // Initializing vendor data

    public static final Vendor VENDOR_1 = Vendor.builder()
            .firstName("vendor_1")
            .lastName("last_vendor_1")
            .build();

    public static final Vendor VENDOR_2 = Vendor.builder()
            .firstName("vendor_2")
            .lastName("last_vendor_2")
            .build();

    public static final Vendor VENDOR_3 = Vendor.builder()
            .firstName("vendor_3")
            .lastName("last_vendor_3")
            .build();

    public static final Vendor VENDOR_4 = Vendor.builder()
            .firstName("vendor_4")
            .lastName("last_vendor_4")
            .build();


    // Vendor Dtos
//
//    public static final VendorDTO VENDOR_DTO_1 = VendorDTO.builder()
//            .id(1L)
//            .name("vendor_DTO_1")
//            .url(VENDORS_API_URL.concat("id/1"))
//            .build();
//
//    public static final VendorDTO VENDOR_DTO_2 = VendorDTO.builder()
//            .id(2L)
//            .name("vendor_DTO_2")
//            .url(VENDORS_API_URL.concat("id/1"))
//            .build();
//
//    public static final VendorDTO VENDOR_DTO_3 = VendorDTO.builder()
//            .id(3L)
//            .name("vendor_DTO_3")
//            .url(VENDORS_API_URL.concat("id/1"))
//            .build();
//
//    public static final VendorDTO VENDOR_DTO_4 = VendorDTO.builder()
//            .id(4L)
//            .name("vendor_DTO_4")
//            .url(VENDORS_API_URL.concat("id/1"))
//            .build();
}
