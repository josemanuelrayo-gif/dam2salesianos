package Clases;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class General {

	public static double redondear(double n,int d) {
		double p=Math.pow(10, d);
		return Math.round(n*p)/p;
	}
	
	
	public static int menu(BufferedReader sc,String op[])  {
	// ={"1.-Listar","2.-Hacer compra","3.-Salir"	
	     int n=0;
		do {
			for(String item:op)System.out.println(item);
			System.out.print("Elija una de ellas ");
			try {
			   n=Integer.valueOf(sc.readLine());
			}catch(Exception e) {
				System.out.println("Numero entero ");
			}
			
		}while(n<1||n>op.length);
		return n;
	}
	
	public static int menu(BufferedReader sc,ArrayList<String> op)  {
		     int n=0,c=0;
			do {
				for(String item:op)
				System.out.println((++c)+" "+item);
				
				System.out.print("Elija una de ellas ");
				try {
				   n=Integer.valueOf(sc.readLine());
				}catch(Exception e) {
					System.out.println("Numero entero ");
				}
				
			}while(n<1||n>op.size());
			return n;
		}
	
	
	
}
