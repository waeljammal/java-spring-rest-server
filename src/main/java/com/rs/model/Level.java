package com.rs.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by Wael Jammal on 10/09/2014.
 *
 * Describes a skill level eg. Java - Spring
 */
@Entity
@Table(name = "level")
@XmlRootElement
public class Level implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "level_id")
    private Long id;

    private String title;

    public Level() { }

    public Level(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((title == null) ? 0 : title.hashCode());
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
