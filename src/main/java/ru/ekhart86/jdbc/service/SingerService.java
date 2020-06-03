package ru.ekhart86.jdbc.service;

import ru.ekhart86.jdbc.entities.Singer;

import java.util.List;

public interface SingerService {

    Singer findSingerById(Long id);

    List<Singer> findAll();

    List<Singer> findSingersByFirstName(String firstName);

    String findFullNameById(Long id);

    String findLastNameById(Long id);

    String findFirstNameById(Long id);

    int insert(Singer singer);

    void update(Singer singer);

    void delete(Long singerId);

    List<Singer> findAllWithAlbums();
}
