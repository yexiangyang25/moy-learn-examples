package org.moy.spring.cache;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {
    @Autowired
    TestService testService;
    @Autowired
    CacheManagerService cacheManagerService;

    @RequestMapping("/add")
    public void add(Test test) {
        testService.add(test);
    }

    @RequestMapping("/update")
    public void update(Test test) {
        testService.update(test);
    }

    @RequestMapping("/get")
    public Test get(Long id) {
        return testService.get(id);
    }

    @RequestMapping("/delete")
    public void delete(Long id) {
        testService.delete(id);
    }

    @RequestMapping("/list")
    public List<Test> list() {
        return testService.list();
    }

    @RequestMapping("/clearCache")
    public void clear() {
        cacheManagerService.clearCacheByName(CacheNameConstant.TEST);
    }
}
