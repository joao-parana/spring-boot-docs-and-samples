package sample.web.rest.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.context.annotation.Configuration;

import sample.web.rest.Identifiable;

@Configuration
public class GenericDao<T> {
	private Map<Long, T> repository = new HashMap<>();
	static Long nextId = 1L;

	public List<T> findAll() {
		List<T> ret = new ArrayList<T>();
		Set<Entry<Long, T>> entries = repository.entrySet();
		for (Entry<Long, T> entry : entries) {
			ret.add(entry.getValue());
		}
		return ret;
	}

	public T findOne(Long id) {
		return repository.get(id);
	}

	public Long create(T resource) {
		// no create o ID é determinado pelo Repositório
		Long ret = nextId++;
		((Identifiable) resource).setId(ret);
		repository.put(ret, resource);
		return ret;
	}
}
