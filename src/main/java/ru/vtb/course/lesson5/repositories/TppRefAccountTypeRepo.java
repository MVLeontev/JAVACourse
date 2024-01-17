package ru.vtb.course.lesson5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TppRefAccountTypeRepo extends JpaRepository<TppRefAccountType, Long> {
    public TppRefAccountType findByValue(String value);
    public TppRefAccountType findByInternalId(Long id);
}
