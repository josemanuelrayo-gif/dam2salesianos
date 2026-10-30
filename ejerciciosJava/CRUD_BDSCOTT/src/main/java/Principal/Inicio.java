package Principal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Clases.General;

public class Inicio {
	
	static String bbdd="bd_scott";
	static String password="cfgs";
	static BufferedReader sc=new BufferedReader(new InputStreamReader(System.in));
	static String conexion="jdbc:mysql://localhost:3306/"+bbdd;
	
	public static void Borrar_emp() throws NumberFormatException, IOException {
		String cadena="DELETE FROM EMP WHERE EMPNO=?";
		System.out.print("Dime el nº de EMPNO a borrar ");
		int numero=Integer.valueOf(sc.readLine());
	
			try( Connection conn=DriverManager.getConnection(conexion,"root",password);
				 PreparedStatement sentencia=conn.prepareStatement(cadena); 	
				){
				sentencia.setInt(1, numero);
				int row=sentencia.executeUpdate();
				if (row==0) {
					System.out.println("Empleado inexistente.");
				}else {
					System.out.println("Empleado borrado.");
				}
			}catch(SQLException e) {
				System.out.println(e.getMessage());
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}

	    }

	public static void Borrar_dept() throws NumberFormatException, IOException {
		String cadena="DELETE FROM DEPT WHERE DEPTNO=?";
		System.out.print("Dime el nº de DEPTNO a borrar ");
		int numero=Integer.valueOf(sc.readLine());
	
			try( Connection conn=DriverManager.getConnection(conexion,"root",password);
				 PreparedStatement sentencia=conn.prepareStatement(cadena); 	
				){
				sentencia.setInt(1, numero);
				int row=sentencia.executeUpdate();
				if (row==0) {
					System.out.println("Departamento inexistente.");
				}else {
					System.out.println("Departamento borrado.");
				}
			}catch(SQLException e) {
				System.out.println(e.getMessage());
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}

	    }
	
	public static int Siguiente_deptno() {
		String cadena="SELECT max(deptno)+1 ne from dept";
		int n=-1;	
			try( Connection conn=DriverManager.getConnection(conexion,"root",password);
				 PreparedStatement sentencia=conn.prepareStatement(cadena); 	
				){
				ResultSet conjunto=sentencia.executeQuery();
				while(conjunto.next())n=conjunto.getInt("ne");
			}catch(SQLException e) {
				System.out.println(e.getMessage());
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
	    	return n;
	    }
	
	public static int Siguiente_empno() {
		String cadena="SELECT max(empno)+1 ne from emp";
		int n=-1;	
			try( Connection conn=DriverManager.getConnection(conexion,"root",password);
				 PreparedStatement sentencia=conn.prepareStatement(cadena); 	
				){
				ResultSet conjunto=sentencia.executeQuery();
				while(conjunto.next())n=conjunto.getInt("ne");
			}catch(SQLException e) {
				System.out.println(e.getMessage());
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
	    	return n;
	    }

	public static void Listar_Emp() {
		String cadena="SELECT * FROM EMP ORDER BY EMPNO";
		
		try( Connection conn=DriverManager.getConnection(conexion,"root",password);
			 PreparedStatement sentencia=conn.prepareStatement(cadena); 	
			){
			ResultSet conjunto=sentencia.executeQuery();
			while(conjunto.next()) {
				System.out.printf("%5d %10s %10s %5d %10.2f %10.2f %5d %10s\n",
				conjunto.getInt("EMPNO"),
				conjunto.getString("ENAME"),
				conjunto.getString("JOB"),
				conjunto.getInt("MGR"),
				conjunto.getDouble("SAL"),
				conjunto.getDouble("COMM"),
				conjunto.getInt("DEPTNO"),
				conjunto.getDate("HIREDATE").toString()
				);
			}
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
    	
    }
	public static void Listar_Dept() {
		String cadena="SELECT * FROM dept ORDER BY DNAME";
		
		try( Connection conn=DriverManager.getConnection(conexion,"root",password);
			 PreparedStatement sentencia=conn.prepareStatement(cadena); 	
			){
			ResultSet conjunto=sentencia.executeQuery();
			while(conjunto.next()) {
				System.out.printf("%5d %10s %10s\n",
				conjunto.getInt("DEPTNO"),
				conjunto.getString("DNAME"),
				conjunto.getString("LOC")
				);
			}
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
    	
    }
	public static void Insertar_Dept() throws IOException {
		String cadena="INSERT INTO DEPT VALUES(?,?,?)";
		
		//System.out.print("Dime el nº del departamento ");
		int numero=Siguiente_deptno();   //Integer.valueOf(sc.readLine());;
		System.out.print("Dime el nombre del departamento ");
		String nombre=sc.readLine();
		System.out.print("Dime la ciudad del departamento ");
		String ciudad=sc.readLine();
		
		try( Connection conn=DriverManager.getConnection(conexion,"root",password);
			 PreparedStatement sentencia=conn.prepareStatement(cadena); 	
			){
		      sentencia.setInt(1, numero);
		      sentencia.setString(2, nombre);
		      sentencia.setString(3, ciudad);
		      int row=sentencia.executeUpdate();
			  System.out.println("Departamento añadido.");
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
    	
    }
	public static ArrayList<String> submenuDept() {
		String cadena="SELECT * FROM dept ORDER BY DNAME";
		String registro;
		ArrayList<String> lista=new ArrayList<String>();
		try( Connection conn=DriverManager.getConnection(conexion,"root",password);
			 PreparedStatement sentencia=conn.prepareStatement(cadena); 	
			){
			ResultSet conjunto=sentencia.executeQuery();
			while(conjunto.next()) {
				registro=conjunto.getString("DNAME")+"-"+
				conjunto.getInt("DEPTNO");
				lista.add(registro);
			}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
    	return lista;
    }
	public static void main(String[] args) throws IOException {
		String t[]={"1.-Listar Emp","2.-Insertar Emp","3.-Actualizar Emp",
				"4.-Borrar Emp","5.-Listar Dept","6.-Insertar Dept",
				"7.-Actualizar Dept","8.-Borrar Dept","9.-Submenu",
				"10.-Salir"};
        int op,sop;
        boolean salir=true;
        while(salir) {
			System.out.println("Base de datos "+bbdd);
			op=General.menu(sc,t);
			switch(op) {
			case 1: // listar Emp
				Listar_Emp();
				break;
			case 2: //Insertar Emp
				
				break;
			case 3: //Actualizar Emp	
				
			    break;
			case 4: //Borrar	
				Borrar_emp();
			    break;
			case 5: // listar Dept
				Listar_Dept();				
				break;
			case 6: //Insertar Dept
				Insertar_Dept();
				break;
			case 7: //Actualizar Dept	
				
			    break;
			case 8: //Borrar Dept 	
				Borrar_dept();
			    break;
    
			case 9:
				ArrayList<String> listadep=submenuDept();
				sop=General.menu(sc,listadep);
				String cadena=listadep.get(sop-1);
				
				int ndepartamento=						
				Integer.valueOf(cadena.substring(cadena.indexOf("-")+1));
						
				System.out.println("El departamento elegido es "+ndepartamento);
				
				
				break;
			case 10: // salir
				salir=false;
				break;
			}
		}
				     
	}

}
