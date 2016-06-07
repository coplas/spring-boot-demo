package com.example.repository;

import com.example.model.DummyModel;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.model.QDummyModel;

import java.util.List;


public interface DummyModelRepository extends PagingAndSortingRepository<DummyModel, Long>,
        QueryDslPredicateExecutor<DummyModel>,
        QuerydslBinderCustomizer<QDummyModel> {

    List<DummyModel> findByLastName(String lastName);


    @Override
    default public void customize(QuerydslBindings bindings, QDummyModel dummyModel) {

        bindings.bind(String.class).first((StringPath path, String value) -> path.containsIgnoreCase(value));
    }
}