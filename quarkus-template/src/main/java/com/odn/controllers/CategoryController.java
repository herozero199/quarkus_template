package com.odn.controllers;

import com.odn.common.BaseResponse;
import com.odn.common.Constants;
import com.odn.requests.categoryControllerRequests.SellerCreateNewCategoryRequest;
import com.odn.security.jwt.TokenUtils;
import com.odn.services.CategoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/category")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "category", description = "Category Operations")
@AllArgsConstructor
@Slf4j
public class CategoryController {
    private final CategoryService service;
    private final TokenUtils tokenUtils;

    @GET
    @RolesAllowed({Constants.Roles.SELLER_ADMIN, Constants.Roles.SELLER_USER})
    public Response sellerGetListCategoriesPagination(
            @QueryParam("page") @DefaultValue("1") int page,
            @QueryParam("size") @DefaultValue("10") int size,
            @QueryParam("filter") @DefaultValue("") String filter
    ) {
        var sellerId = tokenUtils.getSellerId();
        var result = service.sellerGetListCategoriesPagination(sellerId, page, size, filter);

        return Response.ok().entity(new BaseResponse<>(Response.Status.OK.getStatusCode(), Constants.ErrorMessage.SUCCESS, result)).build();
    }
    @GET
    @Path("/{recordId}")
    @RolesAllowed({Constants.Roles.SELLER_ADMIN, Constants.Roles.SELLER_USER})
    public Response sellerGetACategory(@Parameter(name = "recordId", required = true) @PathParam("recordId") Long recorId) {
        var sellerId = tokenUtils.getSellerId();
        var result = service.sellerGetACategory(sellerId, recorId);

        return Response.ok().entity(new BaseResponse<>(Response.Status.OK.getStatusCode(), Constants.ErrorMessage.SUCCESS, result)).build();
    }

    @POST
    @RolesAllowed({Constants.Roles.SELLER_ADMIN, Constants.Roles.SELLER_USER})
    public Response sellerCreateNewCategory(@NotNull @Valid SellerCreateNewCategoryRequest request, @Context UriInfo uriInfo) {
        var sellerId = tokenUtils.getSellerId();
        var newId = service.sellerCreateNewCategory(sellerId, request);
        var uri = uriInfo.getAbsolutePathBuilder().path(newId.toString()).build();

        return Response.created(uri).entity(new BaseResponse<>(Response.Status.CREATED.getStatusCode(), Constants.ErrorMessage.SUCCESS, newId)).build();
    }

    @PUT
    @Path("/{recordId}")
    @RolesAllowed({Constants.Roles.SELLER_ADMIN, Constants.Roles.SELLER_USER})
    public Response sellerEditACategory(@Parameter(name = "recordId", required = true) @PathParam("recordId") Long recorId, @NotNull @Valid SellerCreateNewCategoryRequest request) {
        var userId = tokenUtils.getUserId();
        var sellerId = tokenUtils.getSellerId();
        service.sellerEditACategory(userId, sellerId, recorId, request);

        return Response.ok().entity(new BaseResponse<>(Response.Status.OK.getStatusCode(), Constants.ErrorMessage.SUCCESS, null)).build();
    }
}
