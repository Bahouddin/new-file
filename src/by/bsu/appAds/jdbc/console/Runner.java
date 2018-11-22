package by.bsu.appAds.jdbc.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.List;

import com.mysql.jdbc.Blob;

import by.bsu.appAds.jdbc.dao.mysql.ConnectionPool;
import by.bsu.appAds.jdbc.domain.Note;
import by.bsu.appAds.jdbc.domain.Role;
import by.bsu.appAds.jdbc.domain.User;
import by.bsu.appAds.jdbc.exceptions.PersistentException;
import by.bsu.appAds.jdbc.service.Service;
import by.bsu.appAds.jdbc.service.ServiceLocator;
import by.bsu.appAds.jdbc.service.UserService;
import by.bsu.appAds.jdbc.service.UserServiceImpl;

public class Runner {// RUNNER
	private static SimpleDateFormat f = new SimpleDateFormat("dd.MM.yyyy");

	private static void init() throws PersistentException {// INIT
		ConnectionPool.getInstance().init();
		ServiceLocator locator = new ServiceLocator();
		locator.registerService(UserService.class, UserServiceImpl.class);
		ServiceLocator.setLocator(locator);
	}// INIT

	public static void printMenu() {// PRINTMENU
		System.out.println("Введите число выбранной операции:");
		System.out.println("0- Выход из приложения");
		System.out.println("1 - Вывести список пользователей");
		System.out.println("2 - Создать пользователя");
		System.out.println("3 - Удалить пользователя");

	}// PRINTMENU

	public static void main(String[] arguments) throws PersistentException {// MAIN
		System.out.println("run app");
		boolean isExit = false;
		init();
		System.out.println("init is completited");
		UserService service = ServiceLocator.getService(UserService.class);

		// Бесконечный цикл, пока не нажали кнопку выход
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		while (!isExit) {// WHILE
			printMenu();
			// TRY
			String line = null;
			try {
				line = reader.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int choice = Integer.parseInt(line);
			switch (choice) {// SWITCH
			case 0:
				isExit = true;
				System.out.println("Complement");
				break;
			case 1:

				List<User> users = service.findAll();
				for (User user : users) {// FOR
					System.out.print(user.getLogin());
					switch (user.getRole()) {// SWITCH2
					case ADMIN:
						System.out.println(" (администратор)");
						break;
					case USER:
						System.out.println(" (пользователь)");
						break;
					}// SWITCH2

					System.out.println("*************************************\n\n");
				} // FOR
				break;
			case 2:
				System.out.println("Введите логин");
				String login = null;

				try {
					login = reader.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				System.out.println("Введите пароль");
				String password = null;
				// User user2 = new User(login,password,Role.USER,lastname,
				// firstname,patronymic,email,phone,city,picture);
				try {
					password = reader.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Введите role");
				String role = null;

				try {
					password = reader.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Lastname");
				String lastname = null;
				try {
					lastname = reader.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Firstname");
				String firstname = null;
				try {
					firstname = reader.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Patronymic");
				String patronymic = null;
				try {
					patronymic = reader.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Введите email");
				String email = null;

				try {
					email = reader.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Введите телефон");
				long phone = 0;
				String phoneString = null;

				try {
					phoneString = reader.readLine();
					phone = Long.valueOf(phoneString);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				System.out.println("city");
				String city = null;
				try {
					city = reader.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("picture");

				Blob picture = null;

				User user = new User(login, password, Role.USER, lastname, firstname, patronymic, email, phone, city,
						picture);

				Integer id = service.create(user);
				// System.out.println("create user12");
				System.out.println("Создан пользвателя с идентификатором =  " + id);

				break;
			case 3:
				System.out.println("Вы хотите удалит пользователя");

				/// Integer id = service.delete(user2);
				// System.out.println("create user12");
				System.out.println(" пользователь удалина  =  ");
			    break;
			case 4:
			   // System.out.println("Ползователь ");
				break;
			}// SWITCH

		} // WHILE
	}// MAIN
}// RUNNER