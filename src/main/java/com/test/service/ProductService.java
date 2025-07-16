package com.test.service;

import com.test.dto.ProductDTO;
import com.test.entity.Product;
import com.test.exception.NotFoundException;
import com.test.mapper.ProductMapper;
import com.test.repository.ProductRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class ProductService {

    @Inject
    ProductRepository productRepository;

    @Inject
    ProductMapper productMapper;

    public List<ProductDTO> listAll() {
        return productMapper.toDTOList(productRepository.listAll());
    }

    public ProductDTO getById(Long id) {
        Product product = productRepository.findById(id);
        if (product == null) {
            throw new NotFoundException("Product with id " + id + " not found");
        }
        return productMapper.toDTO(product);
    }

    @Transactional
    public ProductDTO create(ProductDTO productDTO) {
        Product product = productMapper.toEntity(productDTO);
        productRepository.persist(product);
        return productMapper.toDTO(product);
    }


    @Transactional
    public ProductDTO update(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id);
        if (product == null) {
            throw new NotFoundException("Product with id " + id + " not found");
        }
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());

        return productMapper.toDTO(product);
    }

    @Transactional
    public boolean deleteById(Long id) {
        Product product = productRepository.findById(id);
        if (product != null) {
            productRepository.delete(product);
            return true;
        } else {
            throw new NotFoundException("Product with id " + id + " not found");
        }
    }
}
