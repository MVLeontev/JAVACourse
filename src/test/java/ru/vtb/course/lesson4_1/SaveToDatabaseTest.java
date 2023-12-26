package ru.vtb.course.lesson4_1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import ru.vtb.course.lesson4_1.repositories.Logins;
import ru.vtb.course.lesson4_1.repositories.LoginsRepo;
import ru.vtb.course.lesson4_1.repositories.Users;
import ru.vtb.course.lesson4_1.repositories.UsersRepo;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@DataJpaTest
public class SaveToDatabaseTest {

    @Autowired
    UsersRepo usersRepo;

    @Autowired
    LoginsRepo loginsRepo;

    @Test
    @DisplayName("Проверка записи в таблицу Users")
    public void saveToUsers() {
        Users users = new Users();
        users.setUsername("ivanov");
        users.setFio("Ivanov Ivan Ivanovich");
        usersRepo.save(users);
        List<Users> listUsers = usersRepo.findAll();
        Assertions.assertFalse(listUsers.isEmpty(), "Ошибка добавления записи в таблицу Users");
    }


    @Test
    @DisplayName("Проверка записи в таблицу Logins")
    public void saveToLogins() {
        Users users = new Users();
        users.setUsername("ivanov");
        users.setFio("Ivanov Ivan Ivanovich");
        usersRepo.save(users);
        Logins logins = new Logins();
        logins.setUsers(users);
        logins.setAccess_date(new Date());
        logins.setApplication("web");
        loginsRepo.save(logins);

        List<Logins> listLogins = loginsRepo.findAll();
        Assertions.assertFalse(listLogins.isEmpty(), "Ошибка добавления записи в таблицу Logins");

    }
}
