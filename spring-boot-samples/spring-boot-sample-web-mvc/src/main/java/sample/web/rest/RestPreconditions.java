package sample.web.rest;

public class RestPreconditions<T> {
	public static <T> T checkFound(final T resource) {
		if (resource == null) {
			throw new MyResourceNotFoundException();
		}
		return resource;
	}

	public static <T> void checkNotNull(final T resource) {
		if (resource == null) {
			throw new MyResourceIsNullException();
		}
	}
}
