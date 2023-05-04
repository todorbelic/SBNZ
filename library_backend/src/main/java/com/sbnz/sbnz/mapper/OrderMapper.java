package com.sbnz.sbnz.mapper;

import com.sbnz.sbnz.DTO.BookWithAuthorName;
import com.sbnz.sbnz.DTO.OrderDTO;
import com.sbnz.sbnz.DTO.ProcessedOrderDTO;
import com.sbnz.sbnz.model.Book;
import com.sbnz.sbnz.model.Order;
import com.sbnz.sbnz.model.OrderItem;

import java.util.ArrayList;
import java.util.List;

public class OrderMapper {
    public static Order basketToOrder(OrderDTO orderDTO) {
        List<OrderItem> orderItemList = new ArrayList<>();
        for(BookWithAuthorName bookDTO : orderDTO.getOrderItems()) {
            OrderItem item = new OrderItem();
            item.setQuantity(bookDTO.getQuantity());
            Book book = new Book();
            book.setId(bookDTO.getId());
            item.setBook(book);
            orderItemList.add(item);
        }
        Order order = new Order();
        order.setOrderItems(orderItemList);
        return order;
    }
    public static ProcessedOrderDTO getProcessedOrderDTO(Order order) {
        List<BookWithAuthorName> items = new ArrayList<>();
        for(OrderItem item: order.getOrderItems()) {
            items.add(new BookWithAuthorName(item));
        }
        ProcessedOrderDTO orderDTO = new ProcessedOrderDTO();
        orderDTO.orderItems = items;
        orderDTO.discount = order.getDiscount();
        orderDTO.totalPrice = order.getPrice();
        return orderDTO;
    }

    public static Order processedOrderDtoToOrder(ProcessedOrderDTO processedOrderDTO) {
        Order order = new Order();
        order.setDiscount(processedOrderDTO.discount);
        order.setPrice(processedOrderDTO.totalPrice);
        List<OrderItem> orderItems = new ArrayList<>();
        for(BookWithAuthorName bookDTO: processedOrderDTO.getOrderItems()) {
            OrderItem orderItem = new OrderItem();
            Book book = new Book();
            book.setId(bookDTO.getId());
            orderItem.setBook(book);
            orderItem.setDiscount(bookDTO.discount);
            orderItem.setPrice(bookDTO.getTotal());
            orderItem.setQuantity(bookDTO.getQuantity());
            orderItem.setOrder(order);
            orderItems.add(orderItem);
        }
        order.setOrderItems(orderItems);
        return order;
    }
}
