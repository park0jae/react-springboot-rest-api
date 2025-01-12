package com.zerozae.exhibition.domain.exhibition.dto;

import com.zerozae.exhibition.domain.exhibition.entity.Exhibition;
import com.zerozae.exhibition.domain.file.entity.Image;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record ExhibitionCreateRequest(

        @Length(min = 2, max = 30, message = "전시회명은 최소 2자, 최대 30자로 설정할 수 있습니다.")
        @NotBlank(message = "전시회명은 필수 항목입니다.")
        String exhibitionName,

        @NotBlank(message = "전시회에 대한 설명은 필수 항목입니다.")
        String description,

        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
        @NotNull(message = "전시회 시작 시간은 필수 입력값 입니다.")
        LocalDateTime startTime,

        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
        @NotNull(message = "전시회 종료 시간은 필수 입력값 입니다.")
        LocalDateTime endTime,

        @Length(min = 2, max = 15, message = "전시회장은 최소 2자, 최대 15자로 설정할 수 있습니다.")
        @NotBlank(message = "전시회장은 필수 항목입니다.")
        String location,

        @Min(value = 1, message = "가격은 1 이상이어야 합니다.")
        @NotNull(message = "가격은 필수 항목입니다.")
        Long price,

        @Nullable
        MultipartFile image
) {
    public Exhibition toEntity(String uniqueName) {
        return new Exhibition(
                exhibitionName,
                description,
                startTime,
                endTime,
                location,
                price,
                image != null ? new Image(image.getOriginalFilename(), uniqueName) : null
        );
    }

    public Exhibition toEntity() {
        return new Exhibition(
                exhibitionName,
                description,
                startTime,
                endTime,
                location,
                price,
                image != null ? new Image(image.getOriginalFilename()) : null
        );
    }
}
