package com.exam.web.service.impl;

import com.exam.web.dto.DiaryDto;
import com.exam.web.model.Diary;
import com.exam.web.repository.DiaryRepository;
import com.exam.web.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiaryServiceImpl implements DiaryService {
    private DiaryRepository diaryRepository;

    @Autowired
    public DiaryServiceImpl(DiaryRepository diaryRepository) {
        this.diaryRepository = diaryRepository;
    }

    @Override
    public List<DiaryDto> findAllDiaries(){
        List<Diary> diaries=diaryRepository.findAll();
        return diaries.stream().map((diary) -> mapToDiaryDto(diary) ).collect(Collectors.toList());

    }

    @Override
    public Diary saveDiary(DiaryDto diaryDto) {
        Diary diary= mapToDiary(diaryDto);
        return diaryRepository.save(diary);
    }

    @Override
    public DiaryDto findDiaryById(long diaryId) {
        Diary diary = diaryRepository.findById(diaryId).get();
        return mapToDiaryDto(diary);

    }

    @Override
    public void updateDiary(DiaryDto diaryDto) {
       Diary diary=mapToDiary(diaryDto);
       diaryRepository.save(diary);
    }

    @Override
    public void delete(Long diaryId) {
        diaryRepository.deleteById(diaryId);
    }

    @Override
    public List<DiaryDto> searchDiaries(String query) {
        List<Diary> diaries =diaryRepository.searchDiaries(query);
        return diaries.stream().map(diary->mapToDiaryDto(diary)).collect(Collectors.toList());
    }

    private Diary mapToDiary(DiaryDto diary) {

        Diary diaryDto = Diary.builder()
                .id(diary.getId())
                .title(diary.getTitle())
                .photoUrl(diary.getPhotoUrl())
                .mediaFile(diary.getMediaFile())
                .content(diary.getContent())
                .tags(diary.getTags())
                .authors(diary.getAuthors())
                .createdOn(diary.getCreatedOn())
                .updatedOn(diary.getUpdatedOn())
                .build();
        return diaryDto;
    }

    private DiaryDto mapToDiaryDto(Diary diary){
        DiaryDto diaryDto=DiaryDto.builder()
                .id(diary.getId())
                .title(diary.getTitle())
                .photoUrl(diary.getPhotoUrl())
                .mediaFile(diary.getMediaFile())
                .content(diary.getContent())
                .tags(diary.getTags())
                .authors(diary.getAuthors())
                .createdOn(diary.getCreatedOn())
                .updatedOn(diary.getUpdatedOn())
                .build();
        return diaryDto;
    }
}
