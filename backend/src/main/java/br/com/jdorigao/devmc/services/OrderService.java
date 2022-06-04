package br.com.jdorigao.devmc.services;

import br.com.jdorigao.devmc.entities.Order;
import br.com.jdorigao.devmc.entities.OrderItem;
import br.com.jdorigao.devmc.entities.PaymentWithBoleto;
import br.com.jdorigao.devmc.entities.enums.StatePayment;
import br.com.jdorigao.devmc.repositories.OrderItemRepository;
import br.com.jdorigao.devmc.repositories.OrderRepository;
import br.com.jdorigao.devmc.repositories.PaymentRepository;
import br.com.jdorigao.devmc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BoletoService boletoService;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ClientService clientService;

    @Autowired
    private EmailService emailService;

    public Order find(Integer id) {
        Optional<Order> obj = orderRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found! Id: " + id + ", Type: " + Order.class.getName()));
    }

    @Transactional
    public Order insert(Order obj) {
        obj.setId(null);
        obj.setInstant(new Date());
        obj.setClient(clientService.find(obj.getClient().getId()));
        obj.getPayment().setState(StatePayment.PENDING);
        obj.getPayment().setOrder(obj);
        if (obj.getPayment() instanceof PaymentWithBoleto) {
            PaymentWithBoleto paymentWithBoleto = (PaymentWithBoleto) obj.getPayment();
            boletoService.fillBoleto(paymentWithBoleto, obj.getInstant());
        }
        obj = orderRepository.save(obj);
        paymentRepository.save(obj.getPayment());
        for (OrderItem orderItem : obj.getItems()) {
            orderItem.setDiscount(0.0);
            orderItem.setProduct(productService.find(orderItem.getProduct().getId()));
            orderItem.setPrice(orderItem.getProduct().getPrice());
            orderItem.setOrder(obj);
        }
        orderItemRepository.saveAll(obj.getItems());
        emailService.sendOrderConfirmationHtmlEmail(obj);
        return obj;
    }
}
