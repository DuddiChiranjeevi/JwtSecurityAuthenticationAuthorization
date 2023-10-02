package com.mindgraph.exception;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class ProductNotFoundException extends RuntimeException {
private String message;


public String getMessage(String message) {
	
return message;

}
}