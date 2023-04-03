package com.bdf.inacap.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;


@Getter
@AllArgsConstructor
public enum FileTypeEnum {
    PDF(1, ".pdf", "application/pdf"),
    EXCEL(2, ".xls", "application/vnd.ms-excel"),
    JPG(3, ".jpg", "image/jpeg");

    private Integer id;
    private String extension;
    private String contentType;

    public static FileTypeEnum getTypeEnumByFileName(String fileName) {
        for (FileTypeEnum type : FileTypeEnum.values()) {
            if (StringUtils.containsIgnoreCase(fileName, type.getExtension())) {
                return type;
            }
        }
        return null;
    }
}
