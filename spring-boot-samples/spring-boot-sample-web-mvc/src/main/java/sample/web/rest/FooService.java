package sample.web.rest;

import java.util.List;

import org.springframework.stereotype.Service;

import sample.web.rest.base.GenericService;

@Service
public class FooService extends GenericService<Foo> implements ServiceIF<Foo> {

	@Override
	public List<Foo> findAll() {
		return super.findAll();
	}

	@Override
	public Foo findOne(Long id) {
		return super.findOne(id);
	}

	@Override
	public Long create(Foo resource) {
		return super.create(resource);
	}

	@Override
	public Foo getById(Long id) {
		return super.getById(id);
	}

	@Override
	public void update(Foo resource) {
		super.update(resource);
	}

	@Override
	public void deleteById(Long id) {
		super. deleteById( id) ;
	}
}
