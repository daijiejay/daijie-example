package org.daijie.elasticsearch.cloud.service.base;

import java.io.Serializable;
import java.util.NoSuchElementException;

import org.daijie.core.controller.ApiController;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public abstract class BaseSearchService <E,ID extends Serializable,R extends BaseSearchRepository<E,ID>> extends ApiController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private R repository;

    @Autowired
    public void setRepository(R repository) {
        this.repository = repository;
    }

    protected R getRepository(){
        return repository;
    }

    public E getById(ID id) {//
        return getRepository().findOne(id);
    }

    public Iterable<E> listAll() {
        return getRepository().findAll();
    }

    public void save(E data){
        getRepository().save(data);
    }

    public void delete(E data){
        getRepository().delete(data);
    }

    public void deleteById(ID id){
        getRepository().delete(id);
    }

    public E getByKey(String fieldName, Object value){
        try{
            return getRepository().search(QueryBuilders.matchQuery(fieldName, value)).iterator().next();
        }catch(NoSuchElementException e){
        	logger.error(e.getMessage());
            return null;
        }
    }

}
