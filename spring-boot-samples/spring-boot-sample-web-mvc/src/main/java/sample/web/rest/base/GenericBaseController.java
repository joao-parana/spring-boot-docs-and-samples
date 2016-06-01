package sample.web.rest.base;

import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;

import sample.web.rest.ServiceIF;
import sample.web.rest.RestPreconditions;

public abstract class GenericBaseController<T> {

	protected ServiceIF<T> service;

	public void setupService(ServiceIF<T> service) {
		this.service = service;
	}

	public List<T> findAll() {
		return service.findAll();
	}

	public T findOne(Long id) {
		return (T) RestPreconditions.checkFound(service.findOne(id));
	}

	public Long create(T resource) {
		RestPreconditions.checkNotNull(resource);
		return service.create(resource);
	}

	public void update(Long id, T resource) {
		RestPreconditions.checkNotNull(id);
		RestPreconditions.checkNotNull(resource);
		RestPreconditions.checkNotNull(findOne(id));
		service.update(resource);
	}

	public void delete(Long id) {
		service.deleteById(id);
	}
}
