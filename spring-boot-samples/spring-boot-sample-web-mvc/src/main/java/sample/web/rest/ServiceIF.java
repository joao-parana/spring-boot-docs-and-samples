package sample.web.rest;

import java.util.List;

public interface ServiceIF<T> {
	List<T> findAll();

	T findOne(Long id);

	Long create(T resource);

	T getById(Long id);

	void update(T resource);

	void deleteById(Long id);
}
