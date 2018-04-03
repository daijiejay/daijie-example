package org.daijie.jpa.cloud.service.base;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;

public abstract class BaseSearchService <E,ID extends Serializable,R extends PagingAndSortingRepository<E,ID>> {

    @SuppressWarnings("unused")
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private R repository;

    @Autowired
    public void setRepository(R repository) {
        this.repository = repository;
    }

    protected R getRepository(){
        return repository;
    }

	public E getById(ID id) {
        return getRepository().findById(id).get();
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
        getRepository().deleteById(id);
    }

}
