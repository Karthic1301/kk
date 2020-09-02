package com.ast.main;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.ast.HealthCare.utils.CORSFilter;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		System.out.println("getrootconfigclasses method123");
		return new Class[] { AppConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		System.out.println("getservletconfigclasses method");
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		System.out.println("initial syso");
		// System.out.println(new JHades().overlappingJarsReport());
		// System.out.println("jhades method called ");
		return new String[] { "/" };
	}

	@Override
	protected Filter[] getServletFilters() {
		System.out.println("getfilters method");
		Filter[] filters = { new CORSFilter() };
		return filters;
	}

	@Override
	protected void customizeRegistration(ServletRegistration.Dynamic registration) {
		System.out.println("customizeregistration method");
		registration.setMultipartConfig(getMultipartConfigElement());
	}

	private MultipartConfigElement getMultipartConfigElement() {
		System.out.println("getmultipartconfigelement method");
		MultipartConfigElement multipartConfigElement = new MultipartConfigElement(LOCATION, MAX_FILE_SIZE,
				MAX_REQUEST_SIZE, FILE_SIZE_THRESHOLD);
		return multipartConfigElement;
	}

	private static final String LOCATION = System.getProperty("java.io.tmpdir");// "E:/files/"; // Temporary location
																				// where files will be stored

	private static final long MAX_FILE_SIZE = 84000000; // 5MB : Max file size.
														// Beyond that size spring will throw exception.
	private static final long MAX_REQUEST_SIZE = 20971520; // 20MB : Total request size containing Multi part.

	private static final int FILE_SIZE_THRESHOLD = 0; // Size threshold after which files will be written to disk

}
