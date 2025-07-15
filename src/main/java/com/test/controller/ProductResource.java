package com.test.controller;


import com.test.entity.Product;
import com.test.repository.ProductRepository;
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
    ProductRepository productRepository;

    @GET
    public List<Product> list() {
        log.info("Request a list of all products");
        return productRepository.listAll();
    }

    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") Long id) {
        Product product = productRepository.findById(id);
        log.info("Request a get product with id {}", id);
        if (product == null) {
            log.warn("Product not found");
            throw new NotFoundException("Product with id " + id + " not found");
        }
        return Response.ok(product).build();
    }

    @POST
    @Transactional
    public Response create(Product product) {
        log.info("Create product with name {}", product.getName());
        productRepository.persist(product);
        log.info("Product with name {} created", product.getName());
        return Response.status(201).entity(product).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response update(@PathParam("id") Long id, Product updateProduct) {
        log.info("Update product with id {}", id);
        Product product = productRepository.findById(id);
        if (product == null) {
            log.warn("Product not found");
            return Response.status(404).build();
        }

        product.setName(updateProduct.getName());
        product.setQuantity(updateProduct.getQuantity());
        product.setPrice(updateProduct.getPrice());

        log.info("Update is successful");

        return Response.ok(product).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        Product product = productRepository.findById(id);
        log.info("Delete product with id {}", id);
        if (product == null) {
            log.warn("Product not found");
            return Response.status(404).build();
        }
        productRepository.delete(product);
        log.info("Delete is successful");
        return Response.noContent().build();
    }

}
