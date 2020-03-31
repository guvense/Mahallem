package com.mahallem.config;


import com.mahallem.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.servlet.Filter;

@Configuration
@RequiredArgsConstructor
public class JWTFilter implements Filter {

    private final JwtUtil jwtUtil;

    private final static String HTTP_OPTIONS_METHOD = "OPTIONS";

    private final static String LOGIN_URI = "/login";

    private final static String REGISTER_URI = "/register";

    private final static String APP_INFO_URI = "/app-info";


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;


        boolean isOptionsRequest = HTTP_OPTIONS_METHOD.equals(httpServletRequest.getMethod());

        if (isOptionsRequest) {
            httpServletResponse.sendError(HttpServletResponse.SC_OK, "SUCCESS");
        }


        if (allowRequestWithoutToken(httpServletRequest)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            String requestURI = httpServletRequest.getRequestURI();
            String token = httpServletRequest.getHeader("Authorization");
            if (token != null) {
                token = token.replace("Bearer ", "");
                boolean isTokenValid = checkTokenValidation(token);
                if (!isTokenValid) {
                    httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                } else {
                    String userIdFromToken = jwtUtil.getUserIdFromToken(token);
                    httpServletRequest.setAttribute("UserId", userIdFromToken);
                    SecurityContextHolder.getContext().setAuthentication(getAuth(userIdFromToken,httpServletRequest));
                    filterChain.doFilter(servletRequest, servletResponse);
                }
            } else {
                httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            }


        }
    }

    private boolean allowRequestWithoutToken(HttpServletRequest httpServletRequest) {

        String requestURI = httpServletRequest.getRequestURI();
        if (isSwaggerRequest(httpServletRequest)) {
            return true;
        }
        return requestURI.contains(LOGIN_URI) || requestURI.contains(REGISTER_URI) || requestURI.contains(APP_INFO_URI);

    }

    private UsernamePasswordAuthenticationToken getAuth(String objectId, HttpServletRequest httpServletRequest) {
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(objectId, null);
        auth.setDetails(new WebAuthenticationDetails(httpServletRequest));
        return auth;
    }

    private boolean checkTokenValidation(String token) throws IOException {

        return jwtUtil.isTokenValid(token);
    }

    private boolean isSwaggerRequest(HttpServletRequest httpServletRequest) {

        List<String> swagger = Arrays.asList("/v1/api-docs",

                "/configuration/ui",
                "/swagger-resources/**",
                "/swagger-resources",
                "/configuration/security",
                "/swagger-ui.html",
                "/v2/api-docs",
                "/api/v1/webjars/");
        String requestURI = httpServletRequest.getRequestURI();
        for (String s : swagger) {
            if (requestURI.contains(s)) {
                return true;
            }
        }
        return false;
    }

}
