package com.mkkabi.dev.repository;

import com.mkkabi.dev.model.Discipline;
import com.mkkabi.dev.model.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface DisciplineRepository extends JpaRepository<Discipline, Long> {

//    @Query(value = "select " +
//            "d.id as d_id, d.semester, d.credits, d.discipline_name_id, d.control_form_id, d.education_form_id, " +
//            "g.id as g_id, g.admittance_year, g.graduated, g.course_number, g.group_number, g.alternative_name, " +
//            "g.session_start, g.session_end, g.education_form_id as g_edf_id " +
//            "from disciplines d left outer join groups g on d.group_id=g.id where g.id = ?1", nativeQuery = true)
    @Query("SELECT d FROM Discipline d LEFT JOIN FETCH d.disciplineGroup g " +
            "left join fetch d.name dn WHERE g.id = :groupId")
    List<Discipline> getAllForGroup(long groupId);


//    // Custom query with pagination
//    @Query(value="SELECT distinct " +
//            "d.id, d.semester, d.credits, d.discipline_name_id, d.control_form_id, d.education_form_id, d.group_id, " +
//            "dn.id as dn_id, dn.name, " +
//            "ct.id as ct_id, ct.class_type, ct.show_on_schedule, ct.donotcalculate_hours, ct.color, " +
//            "ef.id as ef_id, ef.title " +
//
//            "from disciplines d " +
//            "LEFT outer JOIN discipline_names dn on dn.id=d.discipline_name_id " +
//            "LEFT outer JOIN classtypes ct on ct.id=d.control_form_id " +
//            "LEFT outer JOIN education_form ef on ef.id=d.education_form_id " +
//            "where d.id=21", nativeQuery = true)
//    Page<Discipline> findAllPages(Pageable pageable);

//    @Query(value="SELECT distinct d.*, ct.*, ef.*, dn.* " +
//            "from disciplines d " +
//            "LEFT outer JOIN discipline_names dn on dn.id=d.discipline_name_id " +
//            "LEFT outer JOIN classtypes ct on ct.id=d.control_form_id " +
//            "LEFT outer JOIN education_form ef on ef.id=d.education_form_id", nativeQuery = true)
//Page<Discipline> findAllPages(Pageable pageable);

//@Query(value = "SELECT distinct " +
//        "d.id, d.semester, d.credits, d.discipline_name_id, d.control_form_id, d.education_form_id, " +
//        "d.group_id,  " +
//        "ct.id as ct_id, ct.show_on_schedule, ct.donotcalculate_hours, ct.color, " +
//        "ef.id as ef_id, ef.title, " +
//        "dn.id as dn_id, dn.name " +
//        "FROM disciplines d " +
//        "LEFT JOIN discipline_names dn ON dn.id = d.discipline_name_id " +
//        "LEFT JOIN classtypes ct ON ct.id = d.control_form_id " +
//        "LEFT JOIN education_form ef ON ef.id = d.education_form_id",
//        nativeQuery = true)
//Page<Discipline> findAllPages(Pageable pageable);



//    @Query(value = "SELECT clients.*, order_checks.* "
//            + " FROM clients, order_checks "
//            + " WHERE clients.id = order_checks.client_id "
//            + " AND clients.card_number = ?1 "
//            , nativeQuery = true)

    // If you want to filter with pagination
    Page<Discipline> findByNameContaining(String name, Pageable pageable);

    @Query("SELECT d FROM Discipline d " +
            "LEFT JOIN FETCH d.name dn " +
            "LEFT JOIN FETCH d.controlForm ct " +
            "LEFT JOIN FETCH d.educationForm ef "
//            "LEFT JOIN FETCH d.disciplineGroup dg " +
//            "LEFT JOIN FETCH d.teachers dt " +
//            "LEFT JOIN FETCH d.lessons dl " +
            )
    Page<Discipline> findAllPages(Pageable pageable);

//@Query(value = "select * from disciplines d where d.id=21", nativeQuery = true)
//Page<Discipline> findAllPages(Pageable pageable);
}


