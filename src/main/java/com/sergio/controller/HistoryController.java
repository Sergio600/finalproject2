package com.sergio.controller;

import com.sergio.converter.HistoryConverter;
import com.sergio.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class HistoryController {

    private HistoryConverter historyConverter;
    private HistoryService historyService;

    @Autowired
    public HistoryController(@Lazy HistoryConverter historyConverter,
                             HistoryService historyService) {
        this.historyConverter = historyConverter;
        this.historyService = historyService;
    }


    @GetMapping(value = "/histories")
    public ResponseEntity getAllComments() {
        return ResponseEntity.ok(historyConverter.toDtoList(historyService.getAllHistories()));
    }


    @GetMapping(value = "/tickets/{id}/history")
    public ResponseEntity getTicketHistory(@PathVariable Long id, Principal principal) {
        return ResponseEntity.ok(historyConverter.toDtoList(historyService.getTicketHistory(id, principal)));
    }

//    @PostMapping(value = "/tickets/{id}/comments")
//    public ResponseEntity addHistoryToTicket(@PathVariable Long id, @RequestBody HistoryDto historyDto, Principal principal){
//        historyService.addHistroryToTicket(id, historyConverter.fromDto(historyDto), principal);
//        return new ResponseEntity(HttpStatus.CREATED);
//    }
}
