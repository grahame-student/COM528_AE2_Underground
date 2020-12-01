package uk.ac.solent.lunderground.model.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public final class Station
{
    private Long id;
    private String name;
    private int zone;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId()
    {
        return id;
    }

    public void setId(final Long newId)
    {
        this.id = newId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(final String newNAme)
    {
        this.name = newNAme;
    }

    public int getZone()
    {
        return zone;
    }

    public void setZone(final int newZone)
    {
        this.zone = newZone;
    }

    @Override
    public String toString()
    {
        return "Station{" + "name=" + name + ", zone=" + zone + ", id=" + id + "}";
    }
}
