package ru.vtb.course.lesson5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TppRefProductClassRepo extends JpaRepository<TppRefProductClass, Long> {
    public TppRefProductClass findByInternalId(Long id);
    public TppRefProductClass findByValue(String value);
}
