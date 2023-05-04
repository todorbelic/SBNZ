package com.sbnz.sbnz.service;
import com.sbnz.sbnz.DTO.OrderDTO;
import com.sbnz.sbnz.DTO.ProcessedOrderDTO;
import com.sbnz.sbnz.mapper.OrderMapper;
import com.sbnz.sbnz.model.Order;
import com.sbnz.sbnz.repository.OrderRepository;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private final KieContainer kieContainer;
    private final BookService bookService;
    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(KieContainer kieContainer, BookService bookService, OrderRepository orderRepository) {
        this.kieContainer = kieContainer;
        this.bookService  = bookService;
        this.orderRepository = orderRepository;
    }

    public ProcessedOrderDTO getProcessedOrder(OrderDTO orderRequest) {
        KieSession kieSession = kieContainer.newKieSession();
        Order order = OrderMapper.basketToOrder(orderRequest);
        order.getOrderItems().forEach(item -> item.setBook(bookService.getById(item.getBook().getId())));
        kieSession.insert(order);
        kieSession.fireAllRules();
        kieSession.dispose();
        ProcessedOrderDTO orderDTO = OrderMapper.getProcessedOrderDTO(order);
        return orderDTO;
    }

    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }
}
