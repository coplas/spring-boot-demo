package com.example.service;

import com.example.model.DummyModelItem;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface DummyService {

    Page<DummyModelItem> getPageForDummyItem(long id, Predicate predicate, Pageable pageable);
}
