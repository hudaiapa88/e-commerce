package com.uc.ecommerce.model.mapper;

import com.uc.ecommerce.model.dto.account.UserResponse;
import com.uc.ecommerce.model.dto.category.CategoryResponse;
import com.uc.ecommerce.model.dto.order.OrderLineResponse;
import com.uc.ecommerce.model.dto.order.OrderResponse;
import com.uc.ecommerce.model.dto.product.ProductResponse;
import com.uc.ecommerce.model.entity.account.User;
import com.uc.ecommerce.model.entity.category.Category;
import com.uc.ecommerce.model.entity.order.Order;
import com.uc.ecommerce.model.entity.order.OrderLine;
import com.uc.ecommerce.model.entity.product.Product;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-01T20:08:32+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.3 (Amazon.com Inc.)"
)
@Component
public class OrderResponseMapperImpl implements OrderResponseMapper {

    @Override
    public OrderResponse entityToDto(Order entity) {
        if ( entity == null ) {
            return null;
        }

        OrderResponse orderResponse = new OrderResponse();

        orderResponse.setId( entity.getId() );
        orderResponse.setCreatedDateTime( entity.getCreatedDateTime() );
        orderResponse.setUpdatedDateTime( entity.getUpdatedDateTime() );
        orderResponse.setTotalPrice( entity.getTotalPrice() );
        orderResponse.setOrderLines( orderLineListToOrderLineResponseList( entity.getOrderLines() ) );
        orderResponse.setUser( userToUserResponse( entity.getUser() ) );
        orderResponse.setAddress( entity.getAddress() );

        return orderResponse;
    }

    @Override
    public List<OrderResponse> entityListToDtoList(List<Order> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<OrderResponse> list = new ArrayList<OrderResponse>( entityList.size() );
        for ( Order order : entityList ) {
            list.add( entityToDto( order ) );
        }

        return list;
    }

    protected CategoryResponse categoryToCategoryResponse(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryResponse categoryResponse = new CategoryResponse();

        categoryResponse.setId( category.getId() );
        categoryResponse.setTitle( category.getTitle() );
        categoryResponse.setParent( categoryToCategoryResponse( category.getParent() ) );

        return categoryResponse;
    }

    protected ProductResponse productToProductResponse(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductResponse productResponse = new ProductResponse();

        productResponse.setId( product.getId() );
        productResponse.setCreatedDateTime( product.getCreatedDateTime() );
        productResponse.setUpdatedDateTime( product.getUpdatedDateTime() );
        productResponse.setTitle( product.getTitle() );
        productResponse.setCategory( categoryToCategoryResponse( product.getCategory() ) );
        productResponse.setPrice( product.getPrice() );
        productResponse.setQuantity( product.getQuantity() );

        return productResponse;
    }

    protected OrderLineResponse orderLineToOrderLineResponse(OrderLine orderLine) {
        if ( orderLine == null ) {
            return null;
        }

        OrderLineResponse orderLineResponse = new OrderLineResponse();

        orderLineResponse.setId( orderLine.getId() );
        orderLineResponse.setProduct( productToProductResponse( orderLine.getProduct() ) );
        orderLineResponse.setQuantity( orderLine.getQuantity() );
        orderLineResponse.setTotalPrice( orderLine.getTotalPrice() );

        return orderLineResponse;
    }

    protected List<OrderLineResponse> orderLineListToOrderLineResponseList(List<OrderLine> list) {
        if ( list == null ) {
            return null;
        }

        List<OrderLineResponse> list1 = new ArrayList<OrderLineResponse>( list.size() );
        for ( OrderLine orderLine : list ) {
            list1.add( orderLineToOrderLineResponse( orderLine ) );
        }

        return list1;
    }

    protected UserResponse userToUserResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse userResponse = new UserResponse();

        userResponse.setId( user.getId() );
        userResponse.setCreatedDateTime( user.getCreatedDateTime() );
        userResponse.setUpdatedDateTime( user.getUpdatedDateTime() );
        userResponse.setAddress( user.getAddress() );
        userResponse.setFirstName( user.getFirstName() );
        userResponse.setLastName( user.getLastName() );
        userResponse.setUsername( user.getUsername() );
        userResponse.setPhone( user.getPhone() );
        userResponse.setEmail( user.getEmail() );
        userResponse.setIsActive( user.getIsActive() );

        return userResponse;
    }
}
