package com.example.dummy;

import com.example.model.DummyModel;
import com.example.repository.DummyModelRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InitializeDevelData implements CommandLineRunner {

    private Log log = LogFactory.getLog(InitializeDevelData.class);

    @Autowired
    DummyModelRepository dummyModelRepository;

    public void run(String... args) {
        log.info("initialize devel data START");
        DummyModel model1 = new DummyModel("John", "Doe");
        dummyModelRepository.save(model1);

        log.info("size: " + dummyModelRepository.count());
        List<DummyModel> does = dummyModelRepository.findByLastName("Doe");
        log.info("size Does: " + does.size());
        for (DummyModel dummyModel: does) {
            log.debug(dummyModel);
        }

        for (int i = 0; i < 100; i++) {
            DummyModel model = new DummyModel("Name" + i, i + "Surname");
            dummyModelRepository.save(model);
            log.debug("generated: " + model);
        }


        log.info("initialize devel data END");
    }
}