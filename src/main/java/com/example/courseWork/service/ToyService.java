package com.example.courseWork.service;

import com.example.courseWork.entity.Toy;
import com.example.courseWork.repository.ToyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Service
public class ToyService {
    @Autowired
    private ToyRepository toyRepository;

    public List<Toy> getToy(@RequestParam Map<String,String> params) {
        return toyRepository.getToy(params);
    }
}
