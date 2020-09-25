package org.moy.spring.cache;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = CacheNameConstant.TEST) // 默认指定该类所有方法注解的cacheNames
public class TestService {

    @Autowired
    TestDao testDao;

    /**
     * 主动放入缓存中
     *
     * @param test
     * @return
     */
    @CachePut(key = "#result.id", unless = "#result == null")
    @Caching(evict = {@CacheEvict(key = "'all'")}) // 有变更删除全部
    public Test add(Test test) {
        return testDao.add(test);
    }

    /**
     * 清除缓存
     *
     * @param test
     * @return
     */
    @CacheEvict(key = "#root.args[0].id")
    @Caching(evict = {@CacheEvict(key = "'all'")}) // 有变更删除全部
    public Test update(Test test) {
        return testDao.update(test);
    }

    /**
     * 清除缓存
     *
     * @param id
     */
    @CacheEvict(key = "#root.args[0]")
    @Caching(evict = {@CacheEvict(key = "'all'")}) // 有变更删除全部
    public void delete(Long id) {
        testDao.delete(id);
    }

    /**
     * 查询缓存，不存在执行原有方法查询，查询结果保存到缓存中
     *
     * @param id
     * @return
     */
    @Cacheable(key = "#root.args[0]", unless = "#result == null")
    public Test get(Long id) {
        return testDao.get(id);
    }

    /**
     * 查询缓存，不存在执行原有方法查询，查询结果保存到缓存中
     *
     * @return
     */
    @Cacheable(key = "'all'", unless = "#result == null") // 缓存全部,有任何变更,应当删除该固定key
    public List<Test> list() {
        return testDao.list();
    }

}
