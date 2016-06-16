package com.example.service;


import com.example.model.DummyModelItem;
import com.example.model.QDummyModelItem;
import com.example.repository.DummyModelItemRepository;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DummyServiceImlp implements DummyService{

    @Autowired
    DummyModelItemRepository repositoryItems;

    @Override
    public Page<DummyModelItem> getPageForDummyItem(long id, Predicate predicate, Pageable pageable) {
        QDummyModelItem item = QDummyModelItem.dummyModelItem;
        BooleanExpression dummyModelItems = item.dummyModel.id.eq(id);

        if (predicate != null) {
            dummyModelItems = dummyModelItems.and(predicate);
        }
        return repositoryItems.findAll(dummyModelItems, pageable);
    }
}
