package com.example.activemq.components;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JmsConsumer {

  @JmsListener(destination = "simple-jms-queue")
  public void listener(String msg) {
    System.out.println("Received Message : " + msg);
  }
}