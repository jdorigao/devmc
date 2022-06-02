package br.com.jdorigao.devmc.services;

import br.com.jdorigao.devmc.entities.PaymentWithBoleto;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class BoletoService {

    public void fillBoleto(PaymentWithBoleto paymentWithBoleto, Date orderTime) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(orderTime);
        cal.add(Calendar.DAY_OF_MONTH, 7);
        paymentWithBoleto.setDueDate(cal.getTime());
    }
}
