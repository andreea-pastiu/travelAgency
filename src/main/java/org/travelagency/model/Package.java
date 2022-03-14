package org.travelagency.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "package")
public class Package
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private BigDecimal price;

    @Column
    private Date startDate;

    @Column
    private Date endDate;

    @Column
    private String extraDetails;

    @Column
    private int maxPeople;

    @Column
    private int currentPeople;

    @ManyToOne
    @JoinColumn(name = "destination_id")
    private Destination destination;

    public Package(int id, String name, BigDecimal price, Date startDate, Date endDate, String extraDetails, int maxPeople, int currentPeople, Destination destination)
    {
        this.id = id;
        this.name = name;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.extraDetails = extraDetails;
        this.maxPeople = maxPeople;
        this.currentPeople = currentPeople;
        this.destination = destination;
    }

    public Package()
    {
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public BigDecimal getPrice()
    {
        return price;
    }

    public Date getStartDate()
    {
        return startDate;
    }

    public Date getEndDate()
    {
        return endDate;
    }

    public String getExtraDetails()
    {
        return extraDetails;
    }

    public int getMaxPeople()
    {
        return maxPeople;
    }

    public int getCurrentPeople()
    {
        return currentPeople;
    }

    public Destination getDestination()
    {
        return destination;
    }

    public String getStatus()
    {
        if(currentPeople == 0)
            return "NOT_BOOKED";
        if(currentPeople < maxPeople)
            return "IN_PROGRESS";
        else
            return "BOOKED";
    }

    public void incrementCurrentPeople()
    {
        this.currentPeople++;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    public void setStartDate(Date startDate)
    {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }

    public void setExtraDetails(String extraDetails)
    {
        this.extraDetails = extraDetails;
    }

    public void setMaxPeople(int maxPeople)
    {
        this.maxPeople = maxPeople;
    }

    public void setCurrentPeople(int currentPeople)
    {
        this.currentPeople = currentPeople;
    }
}
