package me.bjxg.demo.mock.user.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Table;

@Setter
@Getter
@Table(name="tb_user")
public class User {

    private Integer id;

    private String username;

    private String realnae;

    private String phone;
}