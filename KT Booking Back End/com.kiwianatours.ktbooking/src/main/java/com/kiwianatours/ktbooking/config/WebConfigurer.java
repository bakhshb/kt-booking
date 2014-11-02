package com.kiwianatours.ktbooking.config;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.servlet.InstrumentedFilter;
import com.codahale.metrics.servlets.MetricsServlet;
import com.kiwianatours.ktbooking.web.filter.CORSFilter;
import com.kiwianatours.ktbooking.web.filter.CachingHttpHeadersFilter;
import com.kiwianatours.ktbooking.web.filter.StaticResourcesProductionFilter;
import com.kiwianatours.ktbooking.web.filter.gzip.GZipServletFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.inject.Inject;
import javax.servlet.*;

import java.io.File;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Configuration of web application with Servlet 3.0 APIs.
 */
@Configuration
@AutoConfigureAfter(CacheConfiguration.class)
public class WebConfigurer implements ServletContextInitializer {

    private final Logger log = LoggerFactory.getLogger(WebConfigurer.class);

    @Inject
    private Environment env;

    @Inject
    private MetricRegistry metricRegistry;

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        log.info("Web application configuration, using profiles: {}", Arrays.toString(env.getActiveProfiles()));
        EnumSet<DispatcherType> disps = EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.ASYNC);
        initMetrics(servletContext, disps);
        if (env.acceptsProfiles(Constants.SPRING_PROFILE_PRODUCTION)) {
            initCachingHttpHeadersFilter(servletContext, disps);
            initStaticResourcesProductionFilter(servletContext, disps);
        }
        initGzipFilter(servletContext, disps);
        initFileUploads(servletContext, disps);
        initCorsFilter(servletContext, disps);
        log.info("Web application fully configured");
    }

    /**
     * Initializes the GZip filter.
     */
    private void initGzipFilter(ServletContext servletContext, EnumSet<DispatcherType> disps) {
        log.debug("Registering GZip Filter");
        FilterRegistration.Dynamic compressingFilter = servletContext.addFilter("gzipFilter", new GZipServletFilter());
        Map<String, String> parameters = new HashMap<>();
        compressingFilter.setInitParameters(parameters);
        compressingFilter.addMappingForUrlPatterns(disps, true, "*.css");
        compressingFilter.addMappingForUrlPatterns(disps, true, "*.json");
        compressingFilter.addMappingForUrlPatterns(disps, true, "*.html");
        compressingFilter.addMappingForUrlPatterns(disps, true, "*.js");
        compressingFilter.addMappingForUrlPatterns(disps, true, "/app/rest/*");
        compressingFilter.addMappingForUrlPatterns(disps, true, "/metrics/*");
        compressingFilter.setAsyncSupported(true);
    }

    /**
     * Initializes the static resources production Filter.
     */
    private void initStaticResourcesProductionFilter(ServletContext servletContext,
                                                     EnumSet<DispatcherType> disps) {

        log.debug("Registering static resources production Filter");
        FilterRegistration.Dynamic staticResourcesProductionFilter =
                servletContext.addFilter("staticResourcesProductionFilter",
                        new StaticResourcesProductionFilter());

        staticResourcesProductionFilter.addMappingForUrlPatterns(disps, true, "/");
        staticResourcesProductionFilter.addMappingForUrlPatterns(disps, true, "/index.html");
        staticResourcesProductionFilter.addMappingForUrlPatterns(disps, true, "/images/*");
        staticResourcesProductionFilter.addMappingForUrlPatterns(disps, true, "/fonts/*");
        staticResourcesProductionFilter.addMappingForUrlPatterns(disps, true, "/scripts/*");
        staticResourcesProductionFilter.addMappingForUrlPatterns(disps, true, "/styles/*");
        staticResourcesProductionFilter.addMappingForUrlPatterns(disps, true, "/views/*");
        staticResourcesProductionFilter.setAsyncSupported(true);
    }

    /**
     * Initializes the cachig HTTP Headers Filter.
     */
    private void initCachingHttpHeadersFilter(ServletContext servletContext,
                                              EnumSet<DispatcherType> disps) {
        log.debug("Registering Cachig HTTP Headers Filter");
        FilterRegistration.Dynamic cachingHttpHeadersFilter =
                servletContext.addFilter("cachingHttpHeadersFilter",
                        new CachingHttpHeadersFilter());

        cachingHttpHeadersFilter.addMappingForUrlPatterns(disps, true, "/images/*");
        cachingHttpHeadersFilter.addMappingForUrlPatterns(disps, true, "/fonts/*");
        cachingHttpHeadersFilter.addMappingForUrlPatterns(disps, true, "/scripts/*");
        cachingHttpHeadersFilter.addMappingForUrlPatterns(disps, true, "/styles/*");
        cachingHttpHeadersFilter.setAsyncSupported(true);
    }

    /**
     * Initializes Metrics.
     */
    private void initMetrics(ServletContext servletContext, EnumSet<DispatcherType> disps) {
        log.debug("Initializing Metrics registries");
        servletContext.setAttribute(InstrumentedFilter.REGISTRY_ATTRIBUTE,
                metricRegistry);
        servletContext.setAttribute(MetricsServlet.METRICS_REGISTRY,
                metricRegistry);

        log.debug("Registering Metrics Filter");
        FilterRegistration.Dynamic metricsFilter = servletContext.addFilter("webappMetricsFilter",
                new InstrumentedFilter());

        metricsFilter.addMappingForUrlPatterns(disps, true, "/*");
        metricsFilter.setAsyncSupported(true);

        log.debug("Registering Metrics Servlet");
        ServletRegistration.Dynamic metricsAdminServlet =
                servletContext.addServlet("metricsServlet", new MetricsServlet());

        metricsAdminServlet.addMapping("/metrics/metrics/*");
        metricsAdminServlet.setAsyncSupported(true);
        metricsAdminServlet.setLoadOnStartup(2);
    }
    
    /**
     * Initializes FileUploads.
     */
    private void initFileUploads(ServletContext servletContext, EnumSet<DispatcherType> disps) {
    	log.debug("Registering FileUploads Servlet");
    	ServletRegistration.Dynamic uploadsAdminServlet =
    			servletContext.addServlet("fileUploadsServlet", new FileUploadServlet());

    	uploadsAdminServlet.addMapping("/uploads/*");
    	uploadsAdminServlet.setAsyncSupported(true);
    	uploadsAdminServlet.setLoadOnStartup(3);
    	// check which evn is running
    	String finalPath = null;
    	if (env.getActiveProfiles().equals(Constants.SPRING_PROFILE_PRODUCTION)){
    		finalPath = System.getenv("OPENSHIFT_DATA_DIR");
    	}else{
    		// files
    		File currentDirFile = new File("");
    		String absolutePath = currentDirFile.getAbsolutePath();	
    		File newDirFile = new File(absolutePath);
    		finalPath = newDirFile.getParent();
    	}
    	// create the file called upload
    	boolean success = new File(finalPath+ "/upload").mkdir();
    	boolean exist = new File (finalPath +"/upload").exists();
    	if (success || exist){
    		uploadsAdminServlet.setMultipartConfig(new MultipartConfigElement(finalPath +"/upload/", 1024*1024*5, 1024*1024*5*5, 1024*1024));
    	}
    }
    
    /**
     * Initializes HTTP access control (CORS).
     */
    private void initCorsFilter (ServletContext servletContext, EnumSet<DispatcherType> disps) {
    	log.debug("Registering HTTP access control (CORS)");
        FilterRegistration.Dynamic corsFilter =
                servletContext.addFilter("corsFilter", new CORSFilter());
        
        Map<String, String> parameters = new HashMap<>();
        corsFilter.setInitParameters(parameters);
        corsFilter.addMappingForUrlPatterns(disps, true, "/app/rest/tours/*");
        corsFilter.addMappingForUrlPatterns(disps, true, "/app/rest/tourschedules/*");
        corsFilter.addMappingForUrlPatterns(disps, true, "/app/rest/tourphotos/*");
        corsFilter.addMappingForUrlPatterns(disps, true, "/app/rest/bookings/*");

        corsFilter.setAsyncSupported(true);
    }
}
