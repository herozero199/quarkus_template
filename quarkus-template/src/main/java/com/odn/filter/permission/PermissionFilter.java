package com.odn.filter.permission;

import io.smallrye.common.constraint.Nullable;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;


import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Provider
public class PermissionFilter implements ContainerRequestFilter {
    @Inject
    Logger log;

    @Context
    ResourceInfo resourceInfo;

    @Inject
    JsonWebToken jwt;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        Method method = resourceInfo.getResourceMethod();
        Permissions methodPermAnnotation = method.getAnnotation(Permissions.class);
        if(methodPermAnnotation == null) {
            log.debug("Don't need check permissions");
        } else if(checkAccess(methodPermAnnotation)) {
            log.debug("Verified permissions");
        } else {
            log.debug("Forbidden permissions");
            requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).build());
        }
    }

    /**
     * Verify if JWT permissions match the API permissions
     */
    private boolean checkAccess(Permissions perm) {
        boolean verified = false;
        if(perm == null) {
            //If no permission annotation verification failed
            verified = false;
        } else if(jwt.getClaim("userId") == null) {
            // Don’t support Anonymous users
            verified = false;
        } else {
            String userId = jwt.getClaim("userId").toString();
            String role = getRolesForUser(userId);
            String[] userPermissions = getPermissionForRole(role);
            if(Arrays.asList(userPermissions).stream()
                    .anyMatch(userPerm -> Arrays.asList(perm.value()).contains(userPerm))) {
                verified = true;
            }
        }
        return verified;
    }

    // role -> permission mapping
    private String[] getPermissionForRole(String role) {
        Map<String, String[]> rolePermissionMap = new HashMap<>();
        rolePermissionMap.put("Admin", new String[] {"task:write", "task:read"});
        rolePermissionMap.put("Member", new String[] {"task:read"});
        return rolePermissionMap.get(role);
    }

    // userId -> role mapping
    private String getRolesForUser(@Nullable String userId) {
        Map<String, String> userMap = new HashMap<>();
        userMap.put("1", "Admin");
        userMap.put("vietdt", "Member");
        return userMap.get(userId);
    }

}