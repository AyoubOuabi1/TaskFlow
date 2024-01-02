package com.ayoub.taskflow.controller;


import com.ayoub.taskflow.dto.RequestDTO;
import com.ayoub.taskflow.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/requests")
public class RequestController {

    private final RequestService requestService;

    @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping
    public ResponseEntity<RequestDTO> createRequest(@RequestBody RequestDTO requestDTO) {
        RequestDTO createdRequest = requestService.createRequest(requestDTO);
        return ResponseEntity.ok(createdRequest);
    }

}
