package com.example.uploadfileandstoretodb.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name="files")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileData implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    @Lob
    private  byte[] data;
}
