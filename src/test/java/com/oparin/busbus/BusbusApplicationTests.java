package com.oparin.busbus;

import com.oparin.busbus.dao.RouteDao;
import com.oparin.busbus.dao.RouteDaoImpl;
import com.oparin.busbus.dao.TicketDaoImpl;
import com.oparin.busbus.models.Route;
//import com.oparin.busbus.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;



//import com.oparin.busbus.dao.UserDaoImpl;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BusbusApplicationTests {

	@Test
	void contextLoads() {
	}
//	@Test
//	public void testAddUser() {
//		// Создаем нового пользователя для теста
//		User user = new User("Иван", "Иванов", "ivan@example.com", 30, "password", "user");
//
//		UserDaoImpl userDao = new UserDaoImpl();
//
//		// Пытаемся добавить пользователя в базу данных
//		userDao.addUser(user);
//
//		// Получаем пользователя по email
//		User retrievedUser = userDao.getUserByEmail("ivan@example.com");
//
//		// Проверяем, что пользователь успешно добавлен и считан из базы данных
//		assertNotNull(retrievedUser);
//		assertEquals("Иван", retrievedUser.getFirstName());
//		assertEquals("Иванов", retrievedUser.getLastName());
//		assertEquals(30, retrievedUser.getAge());
//		assertEquals("user", retrievedUser.getRole());
//
//		// Не забываем удалить пользователя после теста
//		userDao.deleteUser(retrievedUser);
//	}


	@Test
	public void testGetAvailableSeatNumbers() throws SQLException {
		// Создаем экземпляр класса, содержащего метод getAvailableSeatNumbers
		TicketDaoImpl yourClass = new TicketDaoImpl();

		// Вызываем метод, который хотим протестировать
		List<Integer> result = yourClass.getAvailableSeatNumbers(96);

		System.out.println(result);

		// Проверяем, что результат соответствует ожидаемому
		//assertEquals(15, result.size());
		//assertFalse(result.contains(10));
		//assertTrue(result.contains(2));
	}

	@Test
	public void testGetTicketsByUserId() throws SQLException {
		// Создаем экземпляр класса, содержащего метод getAvailableSeatNumbers
		TicketDaoImpl yourClass = new TicketDaoImpl();

		// Вызываем метод, который хотим протестировать
		List<String[]> result = yourClass.getTicketsByUserId(11);

		for (String[] ticket : result) {
			// Получаем информацию о билете из массива
			String ticket_number = ticket[0];
			String route_number = ticket[1];
			String startPoint = ticket[2];
			String endPoint = ticket[3];
			String duration = ticket[4];
			String departure_datetime = ticket[5];
			String arrival_datetime = ticket[6];
			String seat_number = ticket[7];
			String price = ticket[8];


			// Выводим полученную информацию
			System.out.println("Билет: " + startPoint + " - " + endPoint);
		}
		System.out.println(result);

		// Проверяем, что результат соответствует ожидаемому
		assertEquals(5, result.size());
	}
}
