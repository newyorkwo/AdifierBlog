package com.karu.service;

import com.karu.domain.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TypeService {
    Type saveType(Type type);
    Type getType(Long id);
    Type getTypeByName(String name);
    Page<Type> listType(Pageable pageable);
    Type updateType(Long id, Type type);
    void deleteType(Long id);
    List<Type> listType();
}
