package com.mkkabi.dev.service.impl;

import com.mkkabi.dev.dto.DisciplineDTO;
import com.mkkabi.dev.dto.DisciplineDtoTransformer;
import com.mkkabi.dev.exception.NullEntityReferenceException;
import com.mkkabi.dev.model.Discipline;
import com.mkkabi.dev.repository.DisciplineRepository;
import com.mkkabi.dev.tools.AppLogger;
import com.mkkabi.dev.service.DisciplineService;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import jakarta.persistence.EntityNotFoundException;
//import jakarta.transaction.Transactional;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import lombok.Data;
import com.mkkabi.dev.exception.NullEntityReferenceException;
import com.mkkabi.dev.tools.AppLogger;
import jakarta.persistence.EntityNotFoundException;


@Service
//@Data is a convenient shortcut annotation that bundles the features of @ToString , @EqualsAndHashCode , @Getter / @Setter and @RequiredArgsConstructor
@Data
public class DisciplineServiceImpl implements DisciplineService {
    private final Logger logger = new AppLogger("DisciplineServiceImpl.class");

    private final DisciplineRepository repository;

    public DisciplineServiceImpl(DisciplineRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public Discipline create(Discipline discipline) {
        try {
            Discipline savedDiscipline = repository.save(discipline);
            logger.info("saved discipline " + savedDiscipline.getId());
            return savedDiscipline;
        } catch (IllegalArgumentException e) {
            logger.warning("discipline was null, no discipline created");
            throw new NullEntityReferenceException("Discipline was not created, try again");
        }
    }

    @Transactional
    @Override
    public Discipline readById(long id) {
        Optional<Discipline> optional = repository.findById(id);
        if (optional.isPresent()) {
            logger.info("reading discipline, found discipline " + optional.get().getName().getName());
            return optional.get();
        }
        logger.warning("could not find discipline with the specified ID " + id);
        throw new EntityNotFoundException("Could not find discipline with ID " + id);
    }

    @Transactional
    @Override
    public Discipline update(Discipline discipline) {
        if (discipline != null) {
            logger.info("updating valid discipline " + discipline.getId());
            Discipline oldDiscipline = readById(discipline.getId());
            if (oldDiscipline != null) {

                logger.info("updating uer id = " + discipline.getId() + " discipline name = " + discipline.getName().getName());
                return repository.save(discipline);
            }
        }
        logger.warning("discipline was not found in DB while updating, discipline does not exist");
        throw new NullEntityReferenceException("Something went wrong, try again");
    }

    @Override
    @Transactional
    public void delete(long id) {
        Discipline discipline = readById(id);
        repository.delete(discipline);

    }

    @Transactional
    @Override
    public List<Discipline> getAll() {
        List<Discipline> disciplines = repository.findAll();
        logger.info("found " + disciplines.size() + " disciplines in DB");
        return disciplines.isEmpty() ? new ArrayList<>() : disciplines;
    }

    @Transactional
    @Override
    public List<DisciplineDTO> getAllAsDto() {
        return getAll().stream().map(DisciplineDtoTransformer::convertToDto).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public List<DisciplineDTO> getAllForGroupAsDto(long id) {
        return repository.getAllForGroup(id).stream().map(DisciplineDtoTransformer::convertToDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<Discipline> getAllForGroup(long id) {
        return repository.getAllForGroup(id);
    }

    @Transactional
    @Override
    public Page<DisciplineDTO> getPaginatedDisciplines(Pageable pageable) {
        Page<Discipline> disciplines = repository.findAllPages(pageable);
        return new PageImpl<>(disciplines.map(DisciplineDtoTransformer::convertToDto)
                .stream()
                .collect(Collectors.toList()), disciplines.getPageable(), disciplines.getTotalElements());
    }

}
