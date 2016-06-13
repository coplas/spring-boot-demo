package com.example.controllers;

import com.example.model.DummyModel;
import com.example.model.DummyModelItem;
import com.example.model.QDummyModelItem;
import com.example.repository.DummyModelItemRepository;
import com.example.repository.DummyModelRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Operator;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.BooleanOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DummyModelController {


    @Autowired
    DummyModelRepository repository;

    @Autowired
    DummyModelItemRepository repositoryItems;

    @RequestMapping(value = "/")
    String index(Model model,
                 @QuerydslPredicate(root = DummyModel.class) Predicate predicate,
                 @PageableDefault(sort = { "lastName", "firstName" }) Pageable pageable) {

        model.addAttribute("users", repository.findAll(predicate, pageable));

        return "dummymodel/table";
    }

    @RequestMapping(value = "/{id}")
    String item(Model model, @PathVariable long id,
                @QuerydslPredicate(root = DummyModelItem.class) Predicate predicate,
                @PageableDefault(sort = { "id" }) Pageable pageable) {

        DummyModel dummyModel = repository.findOne(id);

        QDummyModelItem item = QDummyModelItem.dummyModelItem;
        BooleanExpression dummyModelItems = item.dummyModel.eq(dummyModel);

        if (predicate != null) {
            dummyModelItems = dummyModelItems.and(predicate);
        }


        model.addAttribute("user", dummyModel);
        model.addAttribute("items", repositoryItems.findAll(dummyModelItems, pageable));
//        model.addAttribute("items", repositoryItems.findAll(predicate, pageable));

        return "dummymodel/item";
    }
}
