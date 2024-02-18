package com.demo.preorder.payment.dao;

import com.demo.preorder.payment.entity.Payment;

public interface PaymentDao {

    Payment savePayment(Payment payment);

    Payment getPaymentById(Long paymentId);

    Payment updatePayment(Long paymentId);
}
