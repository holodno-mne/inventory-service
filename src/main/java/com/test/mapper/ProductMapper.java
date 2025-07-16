package com.test.mapper;

import com.test.dto.ProductDTO;
import com.test.entity.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "jakarta")
public interface ProductMapper {

    ProductDTO toDTO(Product product);
    Product toEntity(ProductDTO productDTO);
    List<ProductDTO> toDTOList(List<Product> products);

}
