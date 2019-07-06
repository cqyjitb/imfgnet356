/*
 * Copyright Hand China Co.,Ltd.
 */

package com.hand.hap.demo.ws;

import com.hand.hap.account.dto.User;

import javax.jws.WebService;
  

@WebService  
public interface HelloWorld {  
      
     User sayHello(String name, User user);
      
} 