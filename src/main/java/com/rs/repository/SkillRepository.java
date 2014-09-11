package com.rs.repository;

import com.rs.model.Skill;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Wael Jammal on 10/09/2014.
 */
public interface SkillRepository extends CrudRepository<Skill, Long>
{
    List<Skill> findByTitle(String partialTitle);
}
