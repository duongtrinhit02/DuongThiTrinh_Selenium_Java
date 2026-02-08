package Railway;

public class PageFactoryManager {
	public static <T extends GeneralPage> T getPage(Class<T> pageClass) {
		try {
			return pageClass.getDeclaredConstructor()
					.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(
	                "Cannot create page: " + pageClass.getSimpleName(), e);		}
	}

}
