package ru.vtb.course.lesson5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TppProductRegisterRepo extends JpaRepository<TppProductRegister, Long> {
    public TppProductRegister[] findByProductIdAndType(TppProduct prodId, String type);
}
