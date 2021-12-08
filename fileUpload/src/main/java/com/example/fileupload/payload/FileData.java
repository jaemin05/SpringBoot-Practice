package com.example.fileupload.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FileData {
    private String filename;
    private String url;
    private String size;
}
