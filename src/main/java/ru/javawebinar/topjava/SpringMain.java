package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.web.SecurityUtil;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            User admin = adminUserController.create(new User(null, "admin", "email@mail.ru", "password", Role.ROLE_ADMIN));
            User user = adminUserController.create(new User(null, "user", "user1@gmail.com","password", Role.ROLE_USER));
            adminUserController.getAll().forEach(System.out::println);
            System.out.println("=============================");
            System.out.println(adminUserController.getByMail("user1@gmail.com"));
            System.out.println("=============================");
            MealRestController mealRestController = appCtx.getBean(MealRestController.class);
            SecurityUtil.setAuthUserId(1);
            System.out.println(mealRestController.get(1));
        }
    }
}
