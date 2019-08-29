package cn.hsiangsun.beans;

import lombok.Data;

import javax.persistence.Column;

@Data
public class User {
    private String id;
    private String username;
    private String password;

    @Column(name = "face_img")
    private String faceImage;
}
