package ru.vtb.course.lesson4_1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginsRepo extends JpaRepository<Logins, Long> {

}