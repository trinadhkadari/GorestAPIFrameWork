package com.qa.gorest.tests;

public class GitTest {

  int a, b;

  private int sum(int a, int b){
	  
	int sum=a+b;  
  
	return sum;

  }

  public static void main(String args[]){


	  GitTest gt = new GitTest();
	  
	  int sum=gt.sum(10,20);

	  System.out.println("Sum:"+sum);
	  
	  

	  
    

    

  
      }
}
