package com.watchdog.business;

/**
 * Created by jmullen on 10/7/16.
 */

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

class SecurityWebApplicationInitializer extends
        AbstractSecurityWebApplicationInitializer {

    public SecurityWebApplicationInitializer() {
        super(SecurityConfig.class);
    }
}
