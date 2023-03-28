package in.ineuron.test;

import java.io.IOException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import in.ineuron.model.Employee;
import in.ineuron.util.HibernateUtil;

public class UpdateApp {

	public static void main(String[] args) throws IOException {

		Session session = null;
		boolean flag = false;
		Integer row = 0;
		Transaction transaction = null;
		Employee employee = null;

		try {

			session = HibernateUtil.getSession();
			if (session != null) {
				transaction = session.beginTransaction();
				employee = session.get(Employee.class, 1);

				if (employee != null) {
					employee.setEcity("Mysore");
					flag = true;

				} else
					System.out.println("Record Not Found");
			}

		} catch (HibernateException e) {
			e.printStackTrace();
		}

		finally {
			if (flag) {
				transaction.commit();
				System.out.println(employee);
				System.out.println("Updated successfully");
			} else {
				transaction.rollback();
				System.out.println("Failed");
			}
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}
	}

}
