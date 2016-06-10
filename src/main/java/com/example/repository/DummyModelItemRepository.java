package com.example.repository;

import com.example.model.DummyModel;
import com.example.model.DummyModelItem;
import com.example.model.QDummyModelItem;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface DummyModelItemRepository extends PagingAndSortingRepository<DummyModelItem, Long>,
        QueryDslPredicateExecutor<DummyModelItem>,
        QuerydslBinderCustomizer<QDummyModelItem> {

    List<DummyModelItem> findByDummyModel(DummyModel dummyModel, Predicate predicate, Pageable pageable);

    @Override
    default public void customize(QuerydslBindings bindings, QDummyModelItem dummyModel) {

        bindings.bind(String.class).first((StringPath path, String value) -> path.containsIgnoreCase(value));
    }
}