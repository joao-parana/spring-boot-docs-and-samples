package sample.web.rest.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import sample.web.rest.ResourceNotFoundException;
import sample.web.rest.ServiceIF;

public abstract class GenericService<T> implements ServiceIF<T> {

	@Autowired
	private GenericDao<T> dao;

	public List<T> findAll() {
		return dao.findAll();
	}

	public T findOne(Long id) {
		return dao.findOne(id);
	}

	public Long create(T resource) {
		return dao.create(resource);
	}

	public T getById(Long id) {
		throw new ResourceNotFoundException("Not implemented yet");
	}

	public void update(T resource) {
		throw new ResourceNotFoundException("Not implemented yet");
	}

	public void deleteById(Long id) {
		throw new ResourceNotFoundException("Not implemented yet");
	}
}
