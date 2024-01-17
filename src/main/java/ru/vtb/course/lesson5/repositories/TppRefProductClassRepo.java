package ru.vtb.course.lesson5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TppRefProductClassRepo extends JpaRepository<TppRefProductClass, Long> {
    public TppRefProductClass findByInternalId(Long id);
    public TppRefProductClass findByValue(String value);
}
