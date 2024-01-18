package com.odn.controllers;

import com.odn.common.BaseResponse;
import com.odn.common.Constants;
import com.odn.requests.categoryControllerRequests.SellerCreateNewCategoryRequest;
import com.odn.requests.inboundControllerRequests.SellerCreateNewInboundRequestRequest;
import com.odn.security.jwt.TokenUtils;
import com.odn.services.CategoryService;
import com.odn.services.InboundRequestService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/inbound-request")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "inbound-request", description = "Inbound Request Operations")
@AllArgsConstructor
@Slf4j
public class InboundRequestController {
    private final InboundRequestService service;
    private final TokenUtils tokenUtils;

    @POST
    @RolesAllowed({Constants.Roles.SELLER_ADMIN, Constants.Roles.SELLER_USER})
    public Response sellerCreateNewInboundRequest(@NotNull @Valid SellerCreateNewInboundRequestRequest request, @Context UriInfo uriInfo) {
        var userId = tokenUtils.getUserId();
        var sellerId = tokenUtils.getSellerId();
        var newId = service.sellerCreateNewInboundRequest(userId, sellerId, request);
        var uri = uriInfo.getAbsolutePathBuilder().path(newId.toString()).build();

        return Response.created(uri).entity(new BaseResponse<>(Response.Status.CREATED.getStatusCode(), Constants.ErrorMessage.SUCCESS, newId)).build();
    }
}
