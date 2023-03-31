package com.bdf.inacap.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;

@Getter
@AllArgsConstructor
public enum FileTypeEnum {
    PDF(1,".pdf","application/pdf"),
    EXCEL(2,".xls","application/vnd.ms-excel"),
    JPG(3,".jpg" ,"image/jpeg");

    private Integer id;
    private String extension;
    private String contentType;


    public static FileTypeEnum getTypeEnumByFileName(String fileName){
        for (FileTypeEnum type: FileTypeEnum.values() ){
            if(StringUtils.containsIgnoreCase(fileName, type.getExtension())){
                return type;
            }
        }
        return null;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public void setContentType(String contentType) {
        this.contentType = contentType;
    }


    public void setExtension(String extension) {
        this.extension = extension;
    }

}
