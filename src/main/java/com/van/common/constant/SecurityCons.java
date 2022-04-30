package com.van.common.constant;

/**
 * @author wan
 */
public interface SecurityCons {

    String[] AUTH_WHITELIST = {
            "/login",
            "/city/register",
            "/config/**",
            "/action/all",
            "/**.js",
            "/**.css",
            "/**.ico",
            "/**.svg",
            "/**.png",
            "/index.html",
    };

    String AUTH_HEADER="Authorization";

}
