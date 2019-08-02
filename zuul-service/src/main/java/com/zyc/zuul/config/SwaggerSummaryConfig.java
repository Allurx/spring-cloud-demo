package com.zyc.zuul.config;

import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.discovery.DiscoveryClientRouteLocator;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zyc
 */
@Primary
@EnableSwagger2
@Configuration
public class SwaggerSummaryConfig implements SwaggerResourcesProvider {

    private static final String API_URI = "/v2/api-docs";

    private static final String VERSION = "1.0";

    private static final String SEPARATOR = "/api/";

    private final DiscoveryClientRouteLocator routeLocator;

    public SwaggerSummaryConfig(DiscoveryClientRouteLocator routeLocator) {
        this.routeLocator = routeLocator;
    }

    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();
        List<Route> routeDefinitions = routeLocator.getRoutes();
        routeDefinitions.stream().filter(this::isApiService).forEach(route -> resources.add(constructSwaggerResource(route)));
        return resources;
    }

    /**
     * @return 是否是提供api的服务
     */
    private boolean isApiService(Route route) {
        String id = route.getId();
        return !"config-service".equalsIgnoreCase(id);
    }

    /**
     * @return 构造swagger资源
     */
    private SwaggerResource constructSwaggerResource(Route route) {
        String name = route.getId();
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(SEPARATOR + name + API_URI);
        swaggerResource.setSwaggerVersion(VERSION);
        return swaggerResource;
    }

}
