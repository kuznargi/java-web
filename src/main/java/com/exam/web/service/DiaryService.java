package com.exam.web.service;

import com.exam.web.dto.DiaryDto;
import com.exam.web.model.Diary;

import java.util.List;

public interface DiaryService {
    List<DiaryDto> findAllDiaries();
    Diary saveDiary(DiaryDto diaryDto);

    DiaryDto findDiaryById(long diaryId);

    void updateDiary(DiaryDto diary);

    void delete(Long diaryId);
    List<DiaryDto> searchDiaries(String query);
}
