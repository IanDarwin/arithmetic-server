package com.darwinsys.arithmetic_server;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * A simple REST / JSON server which accepts a POST containing an arithmetic expression and computes the result.
 * @author Ian Darwin
 */
@RestController
public class ArithmeticController {

	record MathOp(double num1, double num2, String operation) {
		// empty
	}

	@GetMapping("/")
	public String index() {
		return "This is the Spring-based Arithmetic server";
	}

	@GetMapping("/info")
	public String mapping() {
		return "You got here!";
	}

	@PostMapping("/arithmetic")
	public String compute(@RequestBody MathOp op) throws IOException {
		System.out.println("Arithmetic Controller: Got " + op);
		switch(op.operation) {
		case "add": case "+":
			return "The sum is " + (op.num1 + op.num2);
		case "subtract": case "-":
			return "The difference is " + (op.num1 - op.num2);
		case "multiply": case "*":
			return "The product is " + (op.num1 * op.num2);
		case "add": case "/":
			if (op.num2 == 0)
				return "You cannot divide by zero";
			return "The quotient is " + (op.num1 / op.num2);
		case "modulus": case "%":
			if (op.num2 == 0)
				return "You cannot divide by zero";
			return "The remainder is " + (op.num1 % op.num2);
		default:
			throw new IllegalStateException(op.toString());
		}
	}
}
