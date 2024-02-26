package com.demo.preorder.payment.dao.impl;

import com.demo.preorder.exception.CustomException;
import com.demo.preorder.exception.ErrorCode;
import com.demo.preorder.payment.dao.PaymentDao;
import com.demo.preorder.payment.entity.Payment;
import com.demo.preorder.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PaymentDaoImpl implements PaymentDao {

    private final PaymentRepository paymentRepository;

    @Override
    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public Payment getPaymentById(Long paymentId) {
        Optional<Payment> optionalPayment = paymentRepository.findById(paymentId);
        if (optionalPayment.isPresent()) {
            return optionalPayment.get();
        } else throw new CustomException(ErrorCode.INVALID_PAYMENT);
    }

    @Override
    public Payment updatePayment(Long paymentId) {
        Optional<Payment> optionalPayment = paymentRepository.findById(paymentId);
        if (optionalPayment.isPresent()) {
            Payment payment = optionalPayment.get();
            payment.setStatus("cancel");
            return paymentRepository.save(payment);
        } else throw new CustomException(ErrorCode.INVALID_PAYMENT);
    }
}
