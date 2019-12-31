package com.example.test.service;

import com.example.test.domain.Entity.TableEntity;
import com.example.test.domain.repository.TableRepository;
import com.example.test.dto.TableDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TableService {
    private TableRepository tableRepository;

    @Transactional
    public List<TableDTO> selectTablelist() {
        List<TableEntity> tableEntities = tableRepository.findAll();
        List<TableDTO> tableDTOList = new ArrayList<>();

        for ( TableEntity TableEntity : tableEntities) {
            TableDTO tableDTO = TableDTO.builder()
                    .idx(TableEntity.getIdx())
                    .name(TableEntity.getName())
                    .detail(TableEntity.getDetail())
                    .created_at(TableEntity.getCreated_at())
                    .updated_at(TableEntity.getUpdated_at())
                    .build();

            tableDTOList.add(tableDTO);
        }

        return tableDTOList;
    }

    @Transactional
    public TableDTO selectTable(Long idx) {
        Optional<TableEntity> tableEntityWrapper = tableRepository.findById(idx);
        TableEntity TableEntity = tableEntityWrapper.get();

        TableDTO tableDTO = TableDTO.builder()
                .idx(TableEntity.getIdx())
                .name(TableEntity.getName())
                .detail(TableEntity.getDetail())
                .created_at(TableEntity.getCreated_at())
                .updated_at(TableEntity.getUpdated_at())
                .build();

        return tableDTO;
    }

    @Transactional
    public Long insertTable(TableDTO tableDTO) {
        return tableRepository.save(tableDTO.toEntity()).getIdx();
    }

    @Transactional
    public void deleteTable(Long idx) {
        tableRepository.deleteById(idx);
    }
}
