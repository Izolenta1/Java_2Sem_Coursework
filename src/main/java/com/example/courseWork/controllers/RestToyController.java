package com.example.courseWork.controllers;

import com.example.courseWork.entity.Toy;
import com.example.courseWork.service.ToyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/toy")
public class RestToyController {
    @Autowired
    private ToyService toyService;

    @GetMapping
    @ResponseBody
    public List<Toy> getToy(@RequestParam Map<String,String> params) {
        return toyService.getToy(params);
    }
}
