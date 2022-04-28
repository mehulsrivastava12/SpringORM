package com.spring.orm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.orm.dao.StudentDao;
import com.spring.orm.entities.Student;

public class App {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
		StudentDao studentDao = (StudentDao) context.getBean("studentDao");
//       Student student=new Student(2,"Vipin","Alwar");
//       int r = studentDao.insert(student);
//       System.out.println(r);

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean go=true;
		while (go) {
			System.out.println("PRESS 1 for add new Student");
			System.out.println("PRESS 2 for display all students");
			System.out.println("PRESS 3 to get detail of single student");
			System.out.println("Press 4 for delete Student");
			System.out.println("PRESS 5 for update details");
			System.out.println("PRESS 6 for exit");

			try {
				int input = Integer.parseInt(br.readLine());
				
				switch (input) {
				
				case 1:
					System.out.println("Enter user id : ");
					int uid=Integer.parseInt(br.readLine());
					System.out.println("Enter user city");
					String ucity=br.readLine();
					System.out.println("Enter user name");
					String uname=br.readLine();
					Student s=new Student();
					s.setStudentId(uid);
					s.setStudentCity(ucity);
					s.setStudentName(uname);
					int r=studentDao.insert(s);
					System.out.println("Record Inserted Successfully "+r);
					break;
					
				case 2:
					List<Student> allStudents = studentDao.getAllStudents();
					for(Student st:allStudents) {
						System.out.println("Id : "+st.getStudentId());
						System.out.println("Name : "+st.getStudentName());
						System.out.println("City : "+st.getStudentCity()+"\n");
					}
					break;
					
				case 3:
					System.out.println("Enter user id : ");
					int userid=Integer.parseInt(br.readLine());
					Student student = studentDao.getStudent(userid);
					System.out.println("Id : "+student.getStudentId());
					System.out.println("Name : "+student.getStudentName());
					System.out.println("City : "+student.getStudentCity()+"\n");
					break;
					
				case 4:
					System.out.println("Enter user id : ");
					int id=Integer.parseInt(br.readLine());
					studentDao.deleteStudent(id);
					System.out.println("Done");
					break;
					
				case 5:
					System.out.println("Enter user id : ");
					int ud=Integer.parseInt(br.readLine());
					System.out.println("Enter user city : ");
					String uc=br.readLine();
					System.out.println("Enter user name : ");
					String un=br.readLine();
					Student stu=new Student();
					stu.setStudentId(ud);
					stu.setStudentCity(uc);
					stu.setStudentName(un);
					studentDao.updateStudent(stu);
					System.out.println("Record Updated Successfully !! \n");
					break;
					
				case 6:
					go=false;
					break;

				}
			} catch (Exception e) {
					System.out.println("Invalid Input try another one");
					System.out.println(e.getMessage());
			}
		}

	}
}
