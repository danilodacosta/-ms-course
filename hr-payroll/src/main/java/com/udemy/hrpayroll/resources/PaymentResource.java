package com.udemy.hrpayroll.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.udemy.hrpayroll.entities.Payment;
import com.udemy.hrpayroll.services.PaymentService;

@RestController
@RequestMapping(value = "/payments")
public class PaymentResource {

	@Autowired
	private PaymentService service;

	@HystrixCommand(fallbackMethod = "getPaymentAlternative")
	@GetMapping("/{workerId}/days/{days}")
	public ResponseEntity<Payment> getPayment(@PathVariable Long workerId, @PathVariable Integer days) {
		
		Payment payment = this.service.getPayment(workerId, days);
		
		return ResponseEntity.ok(payment);
	}
	
	
	public ResponseEntity<Payment> getPaymentAlternative(Long workerId,Integer days) {
	
		Payment payment = new Payment("Brann", 400.00, days);
		
		return ResponseEntity.ok(payment);
	}
}
