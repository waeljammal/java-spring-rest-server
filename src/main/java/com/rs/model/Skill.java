package com.rs.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wael Jammal on 10/09/2014.
 *
 * Describes a job entry
 */
@Entity
@Table(name = "skill")
@XmlRootElement(name="skills")
public class Skill implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "skill_id")
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Level> levels = new ArrayList<Level>();

    private String title;
    private String description;

    public Skill() { }

    public Skill(String title, String description)
    {
        this.title = title;
        this.description = description;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public List<Level> getLevels()
    {
        return levels;
    }

    public void setLevels(List<Level> levels)
    {
        this.levels = levels;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;

        return true;
    }
}
