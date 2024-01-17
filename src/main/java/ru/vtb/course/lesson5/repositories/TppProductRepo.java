package ru.vtb.course.lesson5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TppProductRepo extends JpaRepository<TppProduct, Long> {

}
