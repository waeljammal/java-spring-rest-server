package com.rs.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by Wael Jammal on 11/09/2014.
 *
 * Wraps the Skill objects for transport.
 */
@XmlRootElement(name="skills")
@SuppressWarnings("serial")
public class Skills
{
    private List<Skill> skills;

    protected Skills() {}   // Keep JAXB happy

    public Skills(List<Skill> skills)
    {
        this.skills = skills;
    }

    @XmlElement(name="skill")
    public List<Skill> getSkills()
    {
        return skills;
    }
}
