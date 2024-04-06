package jdbc_demo1;
/*
 Lab-11 Q) Create a project using jdbc where you need to perform the 
 create, update ,read ,delete
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Std {
	Scanner sc=new Scanner(System.in);
	
	public void jdbc_Opertions() {
		try {
		String url="jdbc:mysql://localhost:3306/jdbc";
		String UserName="root";
		String pass="root";

	Connection 	conn = DriverManager.getConnection(url,UserName,pass);
	while(true) {
	int choise;
	System.out.println("Enter your choise: ");
	System.out.println("1:Create Database");
	System.out.println("2:Create Table");
	System.out.println("3:Insert data");
	System.out.println("4:view all data");
	System.out.println("5:Update table");
	System.out.println("6:delete data");
	System.out.println("7:Exit");
	
	choise=sc.nextInt();
	switch(choise){
	case 1:
		createDatabase(conn);
		break;
		
	case 2:
		createTable(conn);
		break;
	case 3:
		insertData(conn);
		break;
	case 4:
		 viewData(conn);
		break;
	case 5:
		updateData(conn);
		break;
	case 6:
		DeleteRecord(conn);
		break;
	case 7:
		System.exit(6);
		break;
	default:
			System.out.println("invalid input");
			break;
	}}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
	}
	public void createDatabase(Connection conn) throws SQLException {

			Statement smt=conn.createStatement();
			String qur="create database jdbc";
			smt.execute(qur);
			
			System.out.println("Database Created");
		}

	public void createTable(Connection conn) throws SQLException {
		
				String qur="create table std1(S_id int primary key,S_name varchar(30),S_Pno double)";
				Statement st=conn.createStatement();
				st.execute(qur);
				System.out.println("table created :)");
		}
	public void insertData(Connection conn) throws SQLException {
	
			String S_name;
			int S_id;
			double S_Pno;
			String qur="insert into std1 (S_id,S_name,S_Pno)values(?,?,?)";
			PreparedStatement ps=conn.prepareStatement(qur);
			System.out.println("Enter Student id: ");
			S_id=sc.nextInt();
			System.out.println("Enter Student Name: ");
			S_name=sc.next();
			System.out.println("Enter Student Phone No: ");
			S_Pno=sc.nextDouble();
			ps.setInt(1, S_id);
			ps.setString(2,S_name);
			ps.setDouble(3, S_Pno);
			ps.executeUpdate();
			System.out.println("Data inserted");
			
		}
	public void viewData(Connection conn) throws SQLException {

		Statement smt=conn.createStatement();
		String qur="select * from std1";
		ResultSet rs=smt.executeQuery(qur);
		System.out.println("id \t name \t\tNumber ");
		while(rs.next()) {
			
			System.out.println(+rs.getInt(1)+"\t "+rs.getString(2)+"\t\t"+rs.getDouble(3));
		}
		}
		
	public void updateData(Connection conn) throws SQLException {
					
			int S_id;
			String S_name;
			System.out.println("Enter student id: ");
			S_id=sc.nextInt();
			System.out.println("Enter student Name: ");
			S_name=sc.next();

		String qur="update std1 set S_id=? where S_name=?";
		PreparedStatement ps=conn.prepareStatement(qur);
		ps.setInt(1, S_id);
		ps.setString(2, S_name);
		ps.execute();
		
		System.out.println("data updated");
		
		}
	public void DeleteRecord(Connection conn) throws SQLException {
		int S_id;
		System.out.println("Enter student id: ");
		S_id=sc.nextInt();

		String qur="delete from std1 where S_id=? ";
		PreparedStatement ps=conn.prepareStatement(qur);
		ps.setInt(1, S_id);
		ps.execute();
		System.out.println("One row deleted");
	}
		
	}
	
	

