package org.daijie.elasticsearch.cloud.service.base;

import java.io.Serializable;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface BaseSearchRepository<E, ID extends Serializable> extends ElasticsearchRepository<E, ID>, PagingAndSortingRepository<E, ID>{

}