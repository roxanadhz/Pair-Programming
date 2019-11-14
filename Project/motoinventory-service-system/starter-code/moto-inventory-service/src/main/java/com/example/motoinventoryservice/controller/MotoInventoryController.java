package com.example.motoinventoryservice.controller;

import com.example.motoinventoryservice.model.Motorcycle;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RefreshScope
@RestController
public class MotoInventoryController {

    @Autowired
    private DiscoveryClient discoveryClient;

    private RestTemplate restTemplate = new RestTemplate();

    @Value("${vinLookupServiceName}")
    private String vinLookupServiceName;
    @Value("${serviceProtocol}")
    private String serviceProtocol;
    @Value("${servicePath}")
    private String servicePath;
    @Value("${type}")
    private String type;
    @Value("${make}")
    private String make;
    @Value("${model}")
    private String model;
    @Value("${year}")
    private String year;
    @Value("${color}")
    private String color;



    @RequestMapping(value="/vehicle/{vin}", method = RequestMethod.GET)
    public Map<String, String> getVehicle() {
//        List<ServiceInstance> instances = discoveryClient.getInstances(randomGreetingServiceName);
//        String randomGreetingServiceUri = serviceProtocol + instances.get(0).getHost() + ":" + instances.get(0).getPort() + servicePath;
//        String greeting = restTemplate.getForObject(randomGreetingServiceUri, String.class);
//        return greeting;


    }

    @RequestMapping(value = "/motorcycles", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Motorcycle createMotorcycle(@RequestBody @Valid Motorcycle motorcycle) {
        Random rnd = new Random();

        motorcycle.setId(rnd.nextInt(9999));

        return motorcycle;
    }

    @RequestMapping(value = "/motorcycles/{motoId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Motorcycle getMotorcycle(@PathVariable int motoId) {
        if (motoId < 1) {
           throw new IllegalArgumentException("MotoId must be greater than 0.");
        }

        Motorcycle moto = new Motorcycle();
        moto.setId(motoId);
        moto.setVin("54321");
        moto.setMake("Ducati");
        moto.setModel("Multistrada Enduro");
        moto.setYear("2018");
        moto.setColor("Red");

        return moto;
    }
//    @RequestMapping(value = "/vehicle/{vin}", method = RequestMethod.GET)
//    @ResponseStatus(value = HttpStatus.OK)
//    public Vehicle getMotorcycle(@PathVariable int motoId){
//        return Motorcycle;
//    }



    @RequestMapping(value = "/motorcycles/{motoId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMotorcycle(@PathVariable("motoId") int motoId) {
        // do nothing here - in a real application we would delete the entry from
        // the backing data store.
    }

    @RequestMapping(value = "/motorcycles/{motoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateMotorcycle(@RequestBody @Valid Motorcycle motorcycle, @PathVariable int motoId) {
        // make sure the motoId on the path matches the id of the motorcycle object
        if (motoId != motorcycle.getId()) {
            throw new IllegalArgumentException("Motorcycle ID on path must match the ID in the Motorcycle object.");
        }

        // do nothing here - in a real application we would update the entry in the backing data store

    }
}