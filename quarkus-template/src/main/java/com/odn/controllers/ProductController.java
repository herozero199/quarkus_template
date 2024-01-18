package com.odn.controllers;

import com.odn.common.BaseResponse;
import com.odn.common.Constants;
import com.odn.requests.categoryControllerRequests.SellerCreateNewCategoryRequest;
import com.odn.requests.productControllerRequests.OpsEditAProductSizeRequest;
import com.odn.security.jwt.TokenUtils;
import com.odn.services.CategoryService;
import com.odn.services.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/product")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "product", description = "Product Operations")
@AllArgsConstructor
@Slf4j
public class ProductController {
    private final ProductService service;
    private final TokenUtils tokenUtils;

    @GET
    @Path("/get-a-product-size")
    @RolesAllowed({Constants.Roles.ODN_ADMIN, Constants.Roles.ODN_USER})
    public Response opsGetAProductSize(
            @QueryParam("sellerId") @NotNull(message = "Vui lòng chọn đối tác") Long sellerId,
            @QueryParam("sku") @NotBlank(message = "Vui lòng nhập SKU") String sku
    ) {
        var result = service.opsGetAProductSize(sellerId, sku);

        return Response.ok().entity(new BaseResponse<>(Response.Status.OK.getStatusCode(), Constants.ErrorMessage.SUCCESS, result)).build();
    }

    @PUT
    @Path("/edit-a-product-size/{recordId}")
    @RolesAllowed({Constants.Roles.ODN_ADMIN, Constants.Roles.ODN_USER})
    public Response opsEditAProductSize(
            @Parameter(name = "recordId", required = true) @PathParam("recordId") Long recorId,
            @NotNull @Valid OpsEditAProductSizeRequest request
    ) {
        var userId = tokenUtils.getUserId();
        service.opsEditAProductSize(userId, recorId, request);

        return Response.ok().entity(new BaseResponse<>(Response.Status.OK.getStatusCode(), Constants.ErrorMessage.SUCCESS, null)).build();
    }
}
