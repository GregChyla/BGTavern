package com.wj.bgtavern.controllers;

import com.wj.bgtavern.models.BoardGameDescription;
import com.wj.bgtavern.models.dtos.BoardGameDescriptionDto;
import com.wj.bgtavern.models.dtos.BoardGameDescriptionRequestDto;
import com.wj.bgtavern.models.dtos.mappers.BoardGameDescriptionMapper;
import com.wj.bgtavern.services.BoardGameDescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class BoardGameDescriptionController {

    private final BoardGameDescriptionService boardGameDescriptionService;

    @PutMapping("/board_game_descriptions/{id}")
    public BoardGameDescriptionDto editBoardGameDescription(@PathVariable Long id,
                     @RequestBody @Valid BoardGameDescriptionRequestDto boardGameDescriptionRequestDto) {
        return boardGameDescriptionService.editBoardGameDescription(id, boardGameDescriptionRequestDto);
    }

}