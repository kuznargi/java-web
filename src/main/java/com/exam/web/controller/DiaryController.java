package com.exam.web.controller;

import com.exam.web.dto.DiaryDto;
import com.exam.web.model.Diary;
import com.exam.web.service.DiaryService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class DiaryController {
    private final DiaryService diaryService;


    @Autowired
    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;

    }

    @GetMapping("/diaries")
    public String listDiaries(Model model) {
    List<DiaryDto> diaries = diaryService.findAllDiaries();
    model.addAttribute("diaries",diaries);
    return "diaries-list";
    }

    @GetMapping("/diaries/new")
    public String createDiaryForm(Model model){
        Diary diary =new Diary();
        model.addAttribute("diary",diary);
        return "diaries-create";
    }

    @PostMapping("/diaries/new")
    public String saveDiaries(@Valid @ModelAttribute("diary") DiaryDto diaryDto, BindingResult result, Model model){
//        if(result.hasErrors()){
//            model.addAttribute("diary",diaryDto);
//
//            return "diaries-create";
//        }

        diaryService.saveDiary(diaryDto);
        return "redirect:/diaries";
    }

    @GetMapping("/diaries/{diaryId}")
    public String diaryDetail(@PathVariable("diaryId") long diaryId,Model model){
        DiaryDto diaryDto = diaryService.findDiaryById(diaryId);
        model.addAttribute("diary",diaryDto);
        return "diaries-detail";
    }

    @GetMapping("/diaries/{diaryId}/delete")
    public String deleteDiary(@PathVariable("diaryId") Long diaryId){
        diaryService.delete(diaryId);
        return "redirect:/diaries";

    }


    @GetMapping("/diaries/search")
    public String searchDiaries(@RequestParam(value="query")String query, Model model){
        List<DiaryDto> diaries =diaryService.searchDiaries(query);
        model.addAttribute("diaries",diaries);
        return "diaries-list";
    }

    @GetMapping("/diaries/{diaryId}/edit")
    public String editDiaryForm(@PathVariable("diaryId") long diaryId,Model model){
        DiaryDto diary =diaryService.findDiaryById(diaryId);
        model.addAttribute("diary",diary);
        return "diaries-edit";
    }

    @PostMapping("/diaries/{diaryId}/edit")
    public String updateDiary(@PathVariable("diaryId") Long diaryId, @Valid @ModelAttribute("diary") DiaryDto diary,
                              BindingResult result){
        if (result.hasErrors()){
            return "diaries-edit";
        }
        diary.setId(diaryId);
        diaryService.updateDiary(diary);
        return "redirect:/diaries";
    }
}
