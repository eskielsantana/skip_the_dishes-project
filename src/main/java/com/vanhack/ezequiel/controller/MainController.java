package com.vanhack.ezequiel.controller;

import com.vanhack.ezequiel.dto.CustomerDto;
import com.vanhack.ezequiel.dto.OrderDto;
import com.vanhack.ezequiel.dto.restDto.OrderListRestDto;
import com.vanhack.ezequiel.dto.restDto.OrderRestDto;
import com.vanhack.ezequiel.service.CustomerBusinessLogicService;
import com.vanhack.ezequiel.service.OrderBusinessLogicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Service
@RequestMapping("api/v1")
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private OrderBusinessLogicService orderBusinessLogicService;

    @Autowired
    private CustomerBusinessLogicService customerBusinessLogicService;

    @GetMapping("Order/{orderId}")
    public ResponseEntity<OrderDto> getOrderById(@RequestHeader String token, @PathVariable("orderId") Integer orderId) {
        if(!customerBusinessLogicService.validateToken(token)){
            logger.info("Token is invalid: " + token);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        OrderDto orderDto;
        logger.info("Requested a OrderDAO by Id -> Order/" + orderId);
        try{
            orderDto = orderBusinessLogicService.getOrderById(orderId);
        }catch (Exception e){
            logger.info("Error during order requests by id process:");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logger.info("The order request was done successfully");
        return new ResponseEntity<>(orderDto, HttpStatus.OK);
    }

    @PostMapping("Orders")
    public ResponseEntity<String> requestOrders(@RequestHeader String token, @RequestBody OrderListRestDto orderListRestDto) {

        if(!customerBusinessLogicService.validateToken(token)){ return invalidToken(token); }

        logger.info("Requested a multiple Orders Creation");
        try{
            orderBusinessLogicService.requestOrders(orderListRestDto.getOrderRestDtos());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("Error during order requests process: " + e.getMessage());
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        logger.info("The order list was requested successfully");
        return new ResponseEntity<>("The order list was requested successfully", HttpStatus.CREATED);
    }

    @PostMapping("Order")
    public ResponseEntity<String> requestOrder(@RequestHeader String token, @RequestBody OrderRestDto orderRestDto) {

        if(!customerBusinessLogicService.validateToken(token)){ return invalidToken(token); }

        logger.info("Requested a Order Creation -> " + orderRestDto.toString());
        Integer resultId = null;
        try {
            resultId = orderBusinessLogicService.requestOrder(orderRestDto);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Error during order request process: " + e.getMessage());
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        logger.info("The order ("+resultId+") was requested successfully");
        return new ResponseEntity<>("The order ("+resultId+") was requested successfully", HttpStatus.CREATED);
    }

    @PostMapping("cancel/order/{orderId}")
    public ResponseEntity<String> requestOrder(@RequestHeader String token, @RequestHeader Integer orderId) {

        if(!customerBusinessLogicService.validateToken(token)){ return invalidToken(token); }

        logger.info("Requested a Order Cancellation -> Id:" + orderId);
        try {
            orderBusinessLogicService.cancelOrderById(orderId);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Error during order cancellation process: " + e.getMessage());
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        logger.info("The order ("+orderId+") was cancelled successfully");
        return new ResponseEntity<>("The order ("+orderId+") was cancelled successfully", HttpStatus.OK);
    }

    @GetMapping("Order/Status/{orderId}")
    public ResponseEntity<String> getOrderStatusById(@RequestHeader String token, @PathVariable("orderId") Integer orderId) {

        if(!customerBusinessLogicService.validateToken(token)){ return invalidToken(token); }

        logger.info("Requested the orther status by id:" + orderId);
        String status = orderBusinessLogicService.getOrderStatusById(orderId);
        logger.info("The order status was recovered successfully");
        return new ResponseEntity<>(status, HttpStatus.OK);
    }


    // Customer endpoints

    @PostMapping("Customer")
    public ResponseEntity<String> customerAuthenticate(@RequestBody CustomerDto customerDto) {

        logger.info("Requested a Customer Registration");
        try{
            customerBusinessLogicService.registerNewCustomer(customerDto);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("Error during Customer Registration process: " + e.getMessage());
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        logger.info("The customer was registered successfully");
        return new ResponseEntity<>("The customer was registered successfully", HttpStatus.CREATED);
    }

    @PostMapping("Customer/auth")
    public ResponseEntity<String> customerAuthenticate(@RequestHeader String email, @RequestHeader String password) {

        logger.info("Requested a Login process");
        String token;
        try {
            token = customerBusinessLogicService.loginCustomer(email, password);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Error during Customer Registration process: " + e.getMessage());
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        logger.info("The customer was logged successfully");
        return new ResponseEntity<>(token, HttpStatus.CREATED);
    }


    private ResponseEntity<String> invalidToken(String token){
        logger.info("Token is invalid: " + token);
        return new ResponseEntity<>("Error: Token is invalid: " + token, HttpStatus.BAD_REQUEST);
    }
}