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

    @Column(name = "face_img_big")
    private String faceBigImage;

    @Column(name = "nickname")
    private String nickName;

    private String qrcode;

    private String cid;
}
