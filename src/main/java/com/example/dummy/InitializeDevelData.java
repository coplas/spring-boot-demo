package com.example.dummy;

import com.example.model.DummyModel;
import com.example.model.DummyModelItem;
import com.example.repository.DummyModelItemRepository;
import com.example.repository.DummyModelRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class InitializeDevelData implements CommandLineRunner {

    private Log log = LogFactory.getLog(InitializeDevelData.class);

    @Autowired
    DummyModelRepository dummyModelRepository;

    @Autowired
    DummyModelItemRepository dummyModelItemRepository;

    public void run(String... args) {
        log.info("initialize devel data START");
        DummyModel model1 = new DummyModel("John", "Doe");
        for (int j = 0; j < 20; j++) {
            DummyModelItem item = new DummyModelItem("123", "test1@test1.sk");
            model1.addItem(item);
        }
        dummyModelRepository.save(model1);

        log.info("size: " + dummyModelRepository.count());
        List<DummyModel> does = dummyModelRepository.findByLastName("Doe");
        log.info("size Does: " + does.size());
        for (DummyModel dummyModel: does) {
            log.debug(dummyModel);
        }

        for (int i = 0; i < 100; i++) {
            DummyModel model = new DummyModel("Name" + i, i + "Surname");
            for (int j = 0; j < 20; j++) {
                DummyModelItem item = new DummyModelItem(UUID.randomUUID().toString(), "test"+i+"@"+UUID.randomUUID().toString());
                model.addItem(item);
            }
            dummyModelRepository.save(model);
            log.debug("generated: " + model);
            for (int j = 0; j < 20; j++) {
                DummyModelItem item = new DummyModelItem(UUID.randomUUID().toString(), "test"+i+"@"+UUID.randomUUID().toString());
                model.addItem(item);
                dummyModelItemRepository.save(item);
            }
        }


        log.info("initialize devel data END");
    }
}
