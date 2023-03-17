package sdms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentManagementSystem {
	Scanner s = new Scanner(System.in);
	String url = "jdbc:mysql://localhost:3306/Student1";
	String username = "root";
	String password = "root";
	String d = "com.mysql.cj.jdbc.Driver";

	public void addStudent() {
		try {
			Class.forName(d);
			Connection connection = DriverManager.getConnection(url, username, password);
			PreparedStatement p = connection.prepareStatement("insert into student(name,age,marks) values(?,?,?)");

			System.out.println("enter name");
			String name = s.next();
			System.out.println("enter age");
			int age = s.nextInt();
			System.out.println("enter marks");
			int marks = s.nextInt();
			p.setString(1, name);
			p.setInt(2, age);
			p.setInt(3, marks);
			p.execute();
			System.out.println("data entered");

			connection.close();
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
	}

	public void displayStudent() {
		System.out.println("Get student details by\n1.by ID\n2.by Name\n3.by AGE\n4.by MARKS");
		int choice = s.nextInt();
		int id;
		String name;
		int age;
		int marks;
		PreparedStatement p = null;
		try {
			Class.forName(d);
			Connection connection = DriverManager.getConnection(url, username, password);
			switch (choice) {
			case 1:
				System.out.println("enter id");
				id = s.nextInt();
				p = connection.prepareStatement("select * from student where id=?");
				p.setInt(1, id);
				break;
			case 2:
				System.out.println("enter name");
				name = s.next();
				p = connection.prepareStatement("select * from student where name=?");
				p.setString(1, name);
				break;
			case 3:
				System.out.println("enter age");
				age = s.nextInt();
				p = connection.prepareStatement("select * from student where age=?");
				p.setInt(1, age);
				break;
			case 4:
				System.out.println("enter marks");
				marks = s.nextInt();
				p = connection.prepareStatement("select * from student where marks=?");
				p.setInt(1, marks);
				break;
			}
			ResultSet r = p.executeQuery();
			System.out.printf("%10s%10s%10s%10s", "ID", "NAME", "AGE", "MARKS");
			System.out.println();
			System.out.println("-----------------------------------------------------------------------");
			

			while (r.next()) {
				System.out.printf("%10s%10s%10s%10s", r.getInt(1), r.getString(2), r.getInt(3), r.getInt(4));
				System.out.println();
				System.out.println("___________________________________________________________________");
			}
			connection.close();
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
	}

	public void displayAllStudents() {
		try {
			Class.forName(d);
			Connection connection = DriverManager.getConnection(url, username, password);
			PreparedStatement p = connection.prepareStatement("select * from student");
			System.out.printf("%10s%10s%10s%10s", "ID", "NAME", "AGE", "MARKS");

			System.out.println();
			System.out.println("___________________________________________________________________");
			ResultSet r = p.executeQuery();

			while (r.next()) {
				System.out.printf("%10s%10s%10s%10s", r.getInt(1), r.getString(2), r.getInt(3), r.getInt(4));
				System.out.println();
				System.out.println("___________________________________________________________________");

			}
			connection.close();
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
	}

	public void removeStudent() {
		System.out.println("Remove student details by\n1.by ID\n2.by Name\n3.by AGE\n4.by MARKS");
		int choice = s.nextInt();
		int id;
		String name;
		int age;
		int marks;
		PreparedStatement p = null;
		try {
			Class.forName(d);
			Connection connection = DriverManager.getConnection(url, username, password);
			switch (choice) {
			case 1:
				System.out.println("enter id");
				id = s.nextInt();
				p = connection.prepareStatement("delete from student where id =?");
				p.setInt(1, id);
				break;
			case 2:
				System.out.println("enter name");
				name = s.next();
				p = connection.prepareStatement("delete from student where name =?");
				p.setString(1, name);
				break;
			case 3:
				System.out.println("enter age");
				age = s.nextInt();
				p = connection.prepareStatement("delete from student where age =?");
				p.setInt(1, age);
				break;
			case 4:
				System.out.println("enter marks");
				marks = s.nextInt();
				p = connection.prepareStatement("delete from student where marks =?");
				p.setInt(1, marks);
				break;
			}

			p.execute();
			System.out.println("Deleted successfully!");

			connection.close();
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
	}

	public void removeAllStudent() {
		try {
			Class.forName(d);
			Connection connection = DriverManager.getConnection(url, username, password);
			PreparedStatement p = connection.prepareStatement("delete from student");

			p.execute();
			System.out.println("Deleted successfully!");

			connection.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	public void updateStudent() {
		System.out.println("update student details by\n1.by ID\n2.by Name");
		int choice = s.nextInt();
		int id;
		String name;
		int age;
		int marks;
		PreparedStatement p = null;
		try {
			Class.forName(d);
			Connection connection = DriverManager.getConnection(url, username, password);

			switch (choice) {
			case 1:
				System.out.println("enter id");
				id = s.nextInt();
				System.out.println("Edit Student \n1:name\n2:age\n3:marks");
				int optn = s.nextInt();
				switch (optn) {
				case 1:
					System.out.println("enter name");
					name = s.next();
					p = connection.prepareStatement("update student set name=? where id=?");
					p.setInt(2, id);
					p.setString(1, name);
					break;
				case 2:
					System.out.println("enter age");
					age = s.nextInt();
					p = connection.prepareStatement("update student set age=? where id=?");
					p.setInt(2, id);
					p.setInt(1, age);
					break;
				case 3:
					System.out.println("enter marks");
					marks = s.nextInt();
					p = connection.prepareStatement("update student set marks=? where id=?");
					p.setInt(2, id);
					p.setInt(1, marks);
					break;
				}

				break;
			case 2:
				System.out.println("enter name");
				name = s.next();
				System.out.println("Edit Student \n1:age\n2:marks");
				int optn1 = s.nextInt();
				switch (optn1) {
				case 1:
					System.out.println("enter age");
					age = s.nextInt();
					p = connection.prepareStatement("update student set age=? where name=?");
					p.setString(2, name);
					p.setInt(1, age);
					break;
				case 2:
					System.out.println("enter marks");
					marks = s.nextInt();
					p = connection.prepareStatement("update student set marks=? where name=?");
					p.setString(2, name);
					p.setInt(1, marks);
					break;
				}
				break;
			}

			p.execute();
			System.out.println("data updated");

			connection.close();
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
	}

	public void countStudent() {
		System.out.println("The total number of students present in database");
		try {
			Class.forName(d);
			Connection connection = DriverManager.getConnection(url, username, password);
			PreparedStatement p = connection.prepareStatement("select count(*) from student");
			ResultSet r = p.executeQuery();

			while (r.next()) {
				System.out.println(r.getString(1));
			}
			p.execute();

			connection.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void sortStudent() {
		System.out.println("Sort student details by\n1.by Name\n2.by AGE\n3.by MARKS");
		int choice = s.nextInt();

		PreparedStatement p = null;
		try {
			Class.forName(d);
			Connection connection = DriverManager.getConnection(url, username, password);
			switch (choice) {
			case 1:

				p = connection.prepareStatement("select * from student order by name");
				break;
			case 2:
				p = connection.prepareStatement("select * from student order by age");
				break;
			case 3:
				p = connection.prepareStatement("select * from student order by marks");
				break;
			}

			System.out.printf("%10s%10s%10s%10s", "ID", "NAME", "AGE", "MARKS");
			System.out.println();
			System.out.println("-----------------------------------------------------------------------");
			ResultSet r = p.executeQuery();

			while (r.next()) {
				System.out.printf("%10s%10s%10s%10s", r.getInt(1), r.getString(2), r.getInt(3), r.getInt(4));
				System.out.println();
				System.out.println("___________________________________________________________________");
			}
			connection.close();
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
	}

	public void getStudentwithHighestMarks() {
		try {
			Class.forName(d);
			Connection connection = DriverManager.getConnection(url, username, password);
			PreparedStatement p = connection
					.prepareStatement("select * from student where marks in (select max(marks) from student)");
			System.out.printf("%10s%10s%10s%10s", "ID", "NAME", "AGE", "MARKS");

			System.out.println();
			System.out.println("___________________________________________________________________");
			ResultSet r = p.executeQuery();

			while (r.next()) {
				System.out.printf("%10s%10s%10s%10s", r.getInt(1), r.getString(2), r.getInt(3), r.getInt(4));
				System.out.println();
				System.out.println("___________________________________________________________________");

			}
			connection.close();
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
	}

	public void getStudentwithLowestMarks() {
		try {
			Class.forName(d);
			Connection connection = DriverManager.getConnection(url, username, password);
			PreparedStatement p = connection
					.prepareStatement("select * from student where marks in (select min(marks) from student)");
			System.out.printf("%10s%10s%10s%10s", "ID", "NAME", "AGE", "MARKS");

			System.out.println();
			System.out.println("___________________________________________________________________");
			ResultSet r = p.executeQuery();

			while (r.next()) {
				System.out.printf("%10s%10s%10s%10s", r.getInt(1), r.getString(2), r.getInt(3), r.getInt(4));
				System.out.println();
				System.out.println("___________________________________________________________________");

			}
			connection.close();
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
	}
}
