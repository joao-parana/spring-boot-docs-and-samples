package sample.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
// import  org.springframework.http.*;
import org.springframework.http.HttpStatus;
import sample.web.rest.base.*;

@Controller
@RequestMapping(value = "/foos")
class FooController extends GenericBaseController<Foo> {
	@Autowired()
	protected FooService fooService;

	@RequestMapping(value = "/init", method = RequestMethod.GET)
	@ResponseBody
	public void setupService() {
		super.setupService(fooService);
		Foo resource = new Foo("Excellence","Lindt Excellence Mint Intense Dark Chocolate");
		super.create(resource);
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Foo> findAll() {
		return super.findAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Foo findOne(@PathVariable("id") Long id) {
		return super.findOne(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody Long create(@RequestBody Foo resource) {
		return super.create(resource);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public void update(@PathVariable("id") Long id, @RequestBody Foo resource) {
		super.update(id, resource);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("id") Long id) {
		super.delete(id);
	}
}
