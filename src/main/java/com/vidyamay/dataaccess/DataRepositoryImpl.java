package com.vidyamay.dataaccess;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.springframework.stereotype.Component;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author vikas.kumar3
 *
 */
@Component
public class DataRepositoryImpl implements DataRepository{
	
	/** The pm. */
	PersistenceManager pm = DataAccessFactory.getPersistenceManager();

	@Override
	public <T> Mono<T> save(T resource) {
		return Mono.just(pm.makePersistent(resource));
	}

	@Override
	public <T> Mono<T> update(final T resource) {
		return Mono.just(pm.makePersistent(resource));
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> Flux<T> findAll(final Class<T> clazz) {
		Query query = pm.newQuery(clazz);
		return Flux.fromIterable((List<T>) query.execute());
	}

	@Override
	public <T> Mono<T> findById(Class<T> clazz, Long id) {
		return Mono.justOrEmpty((T)pm.getObjectById(clazz, id));
	}
	
	@Override
	public <T>  T findOne(Class<T> clazz, Long id) {
		return pm.getObjectById(clazz, id);
	}

	@Override
	public <T> void delete(final T resource) {
		pm.deletePersistent(resource);
	}
	
	@Override
	public <T> void delete(Class<T> clazz, Long id) {
		pm.deletePersistent(pm.getObjectById(clazz, id));
	}

}
