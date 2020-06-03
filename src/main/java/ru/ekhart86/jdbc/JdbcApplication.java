package ru.ekhart86.jdbc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.ekhart86.jdbc.entities.Singer;
import ru.ekhart86.jdbc.service.SingerService;

import java.sql.Date;
import java.util.List;

@SpringBootApplication
public class JdbcApplication implements CommandLineRunner {

    final SingerService singerService;

    public JdbcApplication(SingerService singerService) {
        this.singerService = singerService;
    }

    public static void main(String[] args) {
        SpringApplication.run(JdbcApplication.class, args);
    }

    @Override
    public void run(String... args){
        System.out.println("Run ...");
        System.out.println("Получение всех исполнителей из таблицы:");
        List<Singer> allSingers = singerService.findAll();
        allSingers.forEach(System.out::println);
        testSingerData();
    }

    private void testSingerData() {

        Long singerNameById = 3L;
        System.out.println("Имя исполнителя с ID = " + singerNameById + " " + singerService.findFirstNameById(singerNameById));
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Фамилия исполнителя с ID = " + singerNameById + " " + singerService.findLastNameById(singerNameById));
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Полное имя исполнителя с ID = " + singerNameById + " " + singerService.findFullNameById(singerNameById));
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Получение всех данных исполнителя по ID:");
        Long singerById = 2L;
        Singer singer = singerService.findSingerById(singerById);
        System.out.println("Исполнитель с ID = " + singerById + " " + singer.toString());
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Получение всех исполнителей из таблицы:");
        List<Singer> allSingers = singerService.findAll();
        allSingers.forEach(System.out::println);
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Получение всех исполнителей из таблицы с определённым именем:");
        String name = "Eric";
        List<Singer> allSingersByFirstName = singerService.findSingersByFirstName(name);
        System.out.println("Исполнитель с именем " + name + " имеет фамилию - " + allSingersByFirstName.get(0).getLastName());
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Добавляем нового исполнителя David Pattinson 1986-06-5:");
        Singer newSinger = new Singer();
        newSinger.setFirstName("David");
        newSinger.setLastName("Pattinson");
        newSinger.setBirthDate(Date.valueOf("1986-06-5"));
        singerService.insert(newSinger);
        System.out.println("Проверяем что исполнитель добавился:");
        allSingers = singerService.findAll();
        allSingers.forEach(System.out::println);
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Обновляем данные исполнителя David Pattinson: ");
        allSingersByFirstName = singerService.findSingersByFirstName("David");
        Singer david = allSingersByFirstName.get(0);
        david.setFirstName("Updated David");
        david.setLastName("Updated Pattinson");
        singerService.update(david);
        System.out.println("Проверяем что исполнитель обновился:");
        allSingers = singerService.findAll();
        allSingers.forEach(System.out::println);
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Удаляем обновлённого исполнителя David Pattinson: ");
        singerService.delete(david.getId());
        System.out.println("Проверяем что исполнитель удалился:");
        allSingers = singerService.findAll();
        allSingers.forEach(System.out::println);
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Получаем список исполнителей имеющих альбомы: ");
        List<Singer> singersWithAlbums = singerService.findAllWithAlbums();
        System.out.println("Проверяем названия альбомов у полученных исполнителей: ");
        for (Singer singerWithAlbum : singersWithAlbums) {
            System.out.println("===========================================================");
            System.out.println("Исполнитель: " + singerWithAlbum.getFirstName() + " " + singerWithAlbum.getLastName());
            System.out.println();
            System.out.println("Альбомы: ");
            System.out.println();
            singerWithAlbum.getAlbums().forEach(e -> System.out.println(e.getTitle()));
        }
    }

}
