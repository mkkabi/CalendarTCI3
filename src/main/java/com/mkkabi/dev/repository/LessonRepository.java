package com.mkkabi.dev.repository;

import com.mkkabi.dev.model.Discipline;
import com.mkkabi.dev.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {

//    @Query(value = "select * from lessons l " +
//            "left join teachers t2 on l.teacher_id = t2.id " +
//            "where l.id = ?1", nativeQuery = true)
    @Query("SELECT l FROM Lesson l " +
            "LEFT JOIN FETCH l.teacher t " +
            "WHERE l.id = :id")
    Optional<Lesson> getByIdWithTeacher(long id);

    Optional<Lesson> getById(long id);


    @Query(value = "select * from lessons where teacher_id = ?1 and DATE(start_date_time) = ?2", nativeQuery = true)
    List<Lesson> getLessonByTeacherAndStartDate(long id, LocalDate date);


//    @Query(value = "select distinct l.* from lessons l " +
//            "left OUTER join lesson_group lg on l.id = lg.lesson_id " +
//            "left OUTER join groups g on g.id = lg.group_id " +
//            "where lg.group_id = :groupId and DATE(l.start_date_time) = :date", nativeQuery = true)

    @Query("SELECT DISTINCT l FROM Lesson l " +
            "JOIN l.groups g " +
            "JOIN l.discipline d " +
            "WHERE g.id = :groupId AND " +
            "FUNCTION('DATE', l.startDateTime) = :date")
    List<Lesson> getLessonByGroupIdAndStartDate(@Param("groupId") long groupId, @Param("date") LocalDate date);



    @Query(value = "select distinct l.* from lessons l " +
            "left outer join lesson_group lg on l.id = lg.lesson_id " +
            "where lg.group_id in (:groupIds) and DATE(l.start_date_time) = :date", nativeQuery = true)
    List<Lesson> getLessonsByGroupIdsAndStartDate(@Param("groupIds") List<Long> groupIds, @Param("date") LocalDate date);


//@Query(value = "select distinct " +
//        "l.id as d_id, l.discipline_id, l.teacher_id, l.online, l.classtype_id, l.week_number," +
//        "l.day_of_week, l.auditorium_number, l.month, l.year, l.comment, l.start_date_time, l.end_date_time, " +
//        "lg.* " +
//        "from lessons l " +
//        "left outer join lesson_group lg on l.id = lg.lesson_id " +
//        "where lg.group_id = :groupId and l.week_number = :weekNumber and l.year = :year", nativeQuery = true)
    @Query("SELECT DISTINCT l FROM Lesson l " +
            "JOIN l.groups lg " +
            "WHERE lg.id = :groupId AND l.weekNumber = :weekNumber AND l.year = :year")
    List<Lesson> getLessonByGroupAndWeekNumberAndYear(@Param("groupId") long groupId,
                                                      @Param("weekNumber") int weekNumber,
                                                      @Param("year") int year);


    @Query(value = "select distinct l.* from lessons l " +
            "left OUTER join lesson_group lg on l.id = lg.lesson_id " +
            "left OUTER join groups g on g.id = lg.group_id " +
            "where l.teacher_id = ?1 and l.week_number = ?2 and l.year = ?3", nativeQuery = true)
    List<Lesson> getLessonByTeacherAndWeekNumberAndYear(long id, int weekNumber, int year);

    @Query(value = "select distinct l.* from lessons l " +
            "left OUTER join lesson_group lg on l.id = lg.lesson_id " +
            "left OUTER join groups g on g.id = lg.group_id " +
            "where l.teacher_id = ?1 and l.month = ?2 and l.year = ?3", nativeQuery = true)
    List<Lesson> getLessonByTeacherAndMonthAndYear(long id, int month, int year);


}
