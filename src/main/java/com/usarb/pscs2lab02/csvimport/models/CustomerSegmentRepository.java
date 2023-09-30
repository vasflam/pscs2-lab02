package com.usarb.pscs2lab02.csvimport.models;

import org.springframework.data.repository.ListCrudRepository;

public interface CustomerSegmentRepository extends ListCrudRepository<CustomerSegment, Long> {
    public CustomerSegment findByName(String name);
}
