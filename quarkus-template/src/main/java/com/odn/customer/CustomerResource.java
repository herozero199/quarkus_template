package com.odn.customer;

import com.odn.common.BaseResponse;
import com.odn.common.Constants;
import com.odn.filter.permission.Permissions;
import com.odn.exception.ServiceException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.print.attribute.standard.Media;
import javax.validation.Valid;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "customer", description = "Customer Operations")
@AllArgsConstructor
@Slf4j
public class CustomerResource {

    private final CustomerService customerService;

    @GET
    @APIResponse(
            responseCode = "200",
            description = "Get All Customers",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.ARRAY, implementation = Customer.class)
            )
    )
    @PermitAll
    public Response get() {
        BaseResponse<?> response = new BaseResponse<>(Constants.ErrorCode.SUCCESS, "", customerService.findAll());
        return Response.ok().entity(response).build();
    }

    @GET
    @Path("/{customerId}")
    @APIResponse(
            responseCode = "200",
            description = "Get Customer by customerId",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT, implementation = Customer.class)
            )
    )
    @APIResponse(
            responseCode = "404",
            description = "Customer does not exist for customerId",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    public Response getById(@Parameter(name = "customerId", required = true) @PathParam("customerId") Integer customerId) {
        return customerService.findById(customerId)
                .map(customer -> Response.ok(new BaseResponse<>(Constants.ErrorCode.SUCCESS, "", customer)).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    @APIResponse(
            responseCode = "201",
            description = "Customer Created",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT, implementation = Customer.class)
            )
    )
    @APIResponse(
            responseCode = "400",
            description = "Invalid Customer",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @APIResponse(
            responseCode = "400",
            description = "Customer already exists for customerId",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    //@RolesAllowed({"ODN_ADMIN"})
    //@Permissions({"task:write"})
    public Response post(@NotNull @Valid Customer customer, @Context UriInfo uriInfo) {
        customerService.save(customer);
        URI uri = uriInfo.getAbsolutePathBuilder().path(Integer.toString(customer.getCustomerId())).build();
        return Response.created(uri).entity(new BaseResponse<>(Constants.ErrorCode.SUCCESS, "", customer)).build();
    }

    @PUT
    @Path("/{customerId}")
    @APIResponse(
            responseCode = "204",
            description = "Customer updated",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT, implementation = Customer.class)
            )
    )
    @APIResponse(
            responseCode = "400",
            description = "Invalid Customer",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @APIResponse(
            responseCode = "400",
            description = "Customer object does not have customerId",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @APIResponse(
            responseCode = "400",
            description = "Path variable customerId does not match Customer.customerId",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @APIResponse(
            responseCode = "404",
            description = "No Customer found for customerId provided",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    public Response put(@Parameter(name = "customerId", required = true) @PathParam("customerId") Integer customerId, @NotNull @Valid Customer customer) {
        if (!Objects.equals(customerId, customer.getCustomerId())) {
            throw new ServiceException("Path variable customerId does not match Customer.customerId");
        }
        customerService.update(customer);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    @Path("/search/customers?pageStart={pageStart}&pagesSize={pagesSize}")
    @APIResponse(
            responseCode = "400",
            description = "Cant found customers",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @APIResponse(
            responseCode = "200",
            description = "Found customers",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    public Response search(Customer customer, @QueryParam("pageStart") Integer pageStart, @QueryParam("pagesSize") Integer pagesSize) {
        Optional<List<Customer>> queryResult =  customerService.search(customer, pageStart, pagesSize);
        if (queryResult.isPresent())
            return Response.ok().entity(queryResult.get()).build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }
}