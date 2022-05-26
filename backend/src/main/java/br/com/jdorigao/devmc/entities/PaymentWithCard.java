package br.com.jdorigao.devmc.entities;

import br.com.jdorigao.devmc.entities.enums.StatePayment;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_payment_with_card")
public class PaymentWithCard extends Payment {
    private static final long serialVersionUID = 1L;

    private Integer numberOfInstallments;

    public PaymentWithCard() {
    }

    public PaymentWithCard(Integer id, StatePayment state, Order order, Integer numberOfInstallments) {
        super(id, state, order);
        this.numberOfInstallments = numberOfInstallments;
    }

    public Integer getNumberOfInstallments() {
        return numberOfInstallments;
    }

    public void setNumberOfInstallments(Integer numberOfInstallments) {
        this.numberOfInstallments = numberOfInstallments;
    }
}
