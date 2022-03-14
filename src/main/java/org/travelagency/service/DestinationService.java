package org.travelagency.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.travelagency.model.Destination;
import org.travelagency.repository.DestinationRepository;

import java.util.List;

public class DestinationService
{
    private DestinationRepository destinationRepo;

    public DestinationService()
    {
        this.destinationRepo = new DestinationRepository();
    }

    public Destination addDestination(Destination destination) throws Exception
    {
        List<Destination> destinationList = destinationRepo.getByName(destination.getName());
        if(destinationList != null && destinationList.size() > 0)
            throw new Exception("Destination already exists");
        return destinationRepo.addDestination(destination);
    }

    public List<Destination> getDestinations()
    {
        return destinationRepo.getAllDestinations();
    }

    public Destination deleteDestination(Destination destination)
    {
        destinationRepo.deleteDestination(destination);
        return destination;
    }
}
