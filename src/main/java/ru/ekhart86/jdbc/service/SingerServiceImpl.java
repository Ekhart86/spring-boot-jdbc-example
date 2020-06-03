package ru.ekhart86.jdbc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ekhart86.jdbc.entities.Singer;
import ru.ekhart86.jdbc.repository.SingerRepository;

import java.util.List;

@Service
public class SingerServiceImpl implements SingerService {

    private SingerRepository singerRepository;

    @Autowired
    public void setSingerRepository(SingerRepository singerRepository) {
        this.singerRepository = singerRepository;
    }

    @Override
    public Singer findSingerById(Long id) {
        return singerRepository.findSingerById(id);
    }

    @Override
    public List<Singer> findAll() {
        return singerRepository.findAll();
    }

    @Override
    public List<Singer> findSingersByFirstName(String firstName) {
        return singerRepository.findSingersByFirstName(firstName);
    }

    @Override
    public String findFullNameById(Long id) {
        return singerRepository.findFullNameById(id);
    }

    @Override
    public String findLastNameById(Long id) {
        return singerRepository.findLastNameById(id);
    }

    @Override
    public String findFirstNameById(Long id) {
        return singerRepository.findFirstNameById(id);
    }

    @Override
    public int insert(Singer singer) {
        return singerRepository.insert(singer);
    }

    @Override
    public void update(Singer singer) {
        singerRepository.update(singer);
    }

    @Override
    public void delete(Long singerId) {
        singerRepository.delete(singerId);
    }

    @Override
    public List<Singer> findAllWithAlbums() {
        return singerRepository.findAllWithAlbums();
    }
}
