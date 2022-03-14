package org.travelagency.service;

import org.travelagency.model.Package;
import org.travelagency.repository.PackageRepository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class PackageService
{
    private PackageRepository packageRepo = new PackageRepository();

    public Package addPackage(Package packageObj) throws Exception
    {
        if(packageObj.getStartDate().compareTo(packageObj.getEndDate()) > 0)
            throw new Exception("Start date cannot be after end date");
        if(packageObj.getPrice().compareTo(BigDecimal.ZERO) < 0)
            throw new Exception("Price cannot be lower than 0");
        if(packageObj.getMaxPeople() < 1)
            throw new Exception("Maximum number of people cannot be lower than 1");
        packageRepo.addPackage(packageObj);
        return packageObj;
    }

    public Package editPackage(Package packageObj) throws Exception
    {
        if(packageObj.getStartDate().compareTo(packageObj.getEndDate()) > 0)
            throw new Exception("Start date cannot be after end date");
        if(packageObj.getPrice().compareTo(BigDecimal.ZERO) < 0)
            throw new Exception("Price cannot be lower than 0");
        if(packageObj.getMaxPeople() < 1)
            throw new Exception("Maximum number of people cannot be lower than 1");
        packageRepo.editPackage(packageObj);
        return packageObj;
    }

    public Package deletePackage(Package packageObj) throws Exception
    {
        if(packageObj.getCurrentPeople() == 0)
        {
            packageRepo.deletePackage(packageObj);
            return packageObj;
        }
        else
        {
            throw new Exception("Cannot delete package with bookings");
        }
    }

    public List<Package> getAllPackages()
    {
        return packageRepo.getAllPackages();
    }

    public List<Package> getAllValidPackages()
    {
        return packageRepo.getAllValidPackages();
    }

    public List<Package> filterPackages(Date startDate, Date endDate, BigDecimal price, String destination) throws Exception
    {
        if(startDate.compareTo(endDate) > 0)
            throw new Exception("Start date cannot be after end date");
        if(price.compareTo(BigDecimal.ZERO) < 0)
            throw new Exception("Price cannot be lower than 0");
        return packageRepo.filterPackages(startDate, endDate, price, destination);
    }
}
