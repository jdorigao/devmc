package br.com.jdorigao.devmc.entities;

import br.com.jdorigao.devmc.entities.enums.StatePayment;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "tb_payment_with_boleto")
public class PaymentWithBoleto extends Payment {
    private static final long serialVersionUID = 1L;

    private Date dueDate;
    private Date paymentDate;

    public PaymentWithBoleto() {
    }

    public PaymentWithBoleto(Integer id, StatePayment state, Order order, Date dueDate, Date paymentDate) {
        super(id, state, order);
        this.dueDate = dueDate;
        this.paymentDate = paymentDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
}
