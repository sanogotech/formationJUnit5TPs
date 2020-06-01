package com.formation.spring5mvcjunit.service;

import com.formation.spring5mvcjunit.model.Widget;
import com.formation.spring5mvcjunit.repository.WidgetRepository;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WidgetServiceImpl implements WidgetService {

    private WidgetRepository repository;

    public WidgetServiceImpl(WidgetRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Widget> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Widget> findAll() {
        return Lists.newArrayList(repository.findAll());
    }

    @Override
    public Widget save(Widget widget) {
        // Increment the version number
        widget.setVersion(widget.getVersion()+1);

        // Save the widget to the repository
        return repository.save(widget);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
