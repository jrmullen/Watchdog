package com.watchdog.security;

/**
 * Created by jmullen on 10/8/16.
 */

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * Created by jmullen on 10/7/16.
 */

class SecurityWebApplicationInitializer extends
        AbstractSecurityWebApplicationInitializer {

    public SecurityWebApplicationInitializer() {
        super(SecurityConfig.class);
    }
}
