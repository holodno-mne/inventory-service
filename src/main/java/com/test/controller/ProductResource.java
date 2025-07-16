package com.test.controller;


import com.test.dto.ProductDTO;
import com.test.entity.Product;
import com.test.repository.ProductRepository;
import com.test.service.ProductService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {

    private static final Logger log = LoggerFactory.getLogger(ProductResource.class);

    @Inject
    ProductService productService;

    @GET
    public List<ProductDTO> list() {
        log.info("Request a list of all products");
        return productService.listAll();
    }

    @GET
    @Path("/{id}")
    public ProductDTO getById(@PathParam("id") Long id) {
        log.info("Request a product with id " + id);
        return productService.getById(id);

    }

    @POST
    public Response create(ProductDTO productDTO) {
        log.info("Create product with name {}", productDTO.getName());
        ProductDTO created = productService.create(productDTO);
        log.info("Product with name {} created", productDTO.getName());
        return Response.status(Response.Status.CREATED)
                .entity(created)
                .build();
    }

    @PUT
    @Path("/{id}")
    public ProductDTO update(@PathParam("id") Long id, ProductDTO productDTO) {
        log.info("Update product with id {}", id);
        return productService.update(id, productDTO);
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        log.info("Deleting product with id {}", id);
        boolean deleted =  productService.deleteById(id);
        if (!deleted) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Product not found")
                    .build();
        }
        log.info("Product with id {} deleted", id);
        return Response.noContent().build();
    }

}
