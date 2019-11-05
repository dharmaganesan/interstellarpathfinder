package za.co.discovery.assignment.ganesan.webservice;


import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import za.co.discovery.assignment.ganesan.dto.ShortestPathRequestDto;
import za.co.discovery.assignment.ganesan.dto.ShortestPathResponseDto;

import java.util.ArrayList;

@Endpoint
public class ShortestPathEndpoint {

    @PayloadRoot(namespace = WebServiceConfig.NAMESPACE_URI, localPart = "GetShortestPathRequest")
    @ResponsePayload
    public ShortestPathResponseDto getShortestPath(@RequestPayload ShortestPathRequestDto request) {

        return new ShortestPathResponseDto(new ArrayList<>());
    }

}
