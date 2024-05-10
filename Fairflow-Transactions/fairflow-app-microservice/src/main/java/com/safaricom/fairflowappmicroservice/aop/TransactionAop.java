package com.safaricom.fairflowappmicroservice.aop;

import com.safaricom.fairflowappmicroservice.dtos.Payment.PaymentRequestDto;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@Aspect
public class TransactionAop {

    @Autowired
    private DiscoveryClient discoveryClient;
    @After("execution(* com.safaricom.fairflowappmicroservice.controllers.RepresentativeController.transferPayment(..))")

    public void storeTransaction(JoinPoint joinPoint){

        PaymentRequestDto paymentRequestDto = (PaymentRequestDto) joinPoint.getArgs()[0];
        List<ServiceInstance> instances = discoveryClient.getInstances("fairflow-transactions-microservice");
        if (instances != null && !instances.isEmpty()) {
            ServiceInstance serviceInstance = instances.get(0);
            String url = serviceInstance.getUri().toString();
            url = url + "/api/v1/transactions";
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.postForEntity(url, paymentRequestDto, void.class);
        }

    }

}
