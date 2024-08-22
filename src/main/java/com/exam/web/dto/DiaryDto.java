package com.exam.web.dto;


import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
public class DiaryDto {
        private long id;
        @NotEmpty(message = "Diary title should not be empty" )
        private String title;
        @NotEmpty(message = "Photo link should not be empty" )
        private String photoUrl;
        @NotEmpty(message = "Media file link should not be empty" )
        private String mediaFile;
        @NotEmpty(message = "Content should not be empty" )
        private String content;
        private String tags;
        private String authors;
        private LocalDateTime createdOn;
        private LocalDateTime updatedOn;
}
