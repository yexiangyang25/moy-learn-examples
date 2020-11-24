package org.moy.spring.cache;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class TestDao {

    private final Logger logger = LoggerFactory.getLogger(TestDao.class);
    private final Map<Long, Test> database = new ConcurrentHashMap<>();

    public Test add(Test test) {
        logger.info("add data : {}", test.getId());
        database.put(test.getId(), test);
        return test;
    }

    public Test update(Test test) {
        logger.info("update data : {}", test.getId());
        if (database.containsKey(test.getId())) {
            return database.put(test.getId(), test);
        }
        return null;
    }

    public Test get(Long id) {
        logger.info("get data : {}", id);
        return database.get(id);
    }

    public void delete(Long id) {
        logger.info("delete data : {}", id);
        if (database.containsKey(id)) {
            database.remove(id);
        }
    }

    public List<Test> list() {
        logger.info("list data ");
        Collection<Test> values = database.values();
        return new ArrayList<>(values);
    }
}
