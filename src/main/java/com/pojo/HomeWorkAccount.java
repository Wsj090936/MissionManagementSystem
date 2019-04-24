package com.pojo;


/**
 * created by wushijia
 * 学生提交作业的记录
 * 	id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
 * 	studentId VARCHAR(20) DEFAULT NULL COMMENT '学生学号，同时也是登陆系统用的账号',
 * 	workUrl VARCHAR(20) DEFAULT '' COMMENT '作业地址',
 * 	created BIGINT DEFAULT 0 COMMENT '记录创建日期',
 * 	updated BIGINT DEFAULT 0 COMMENT '修改日期'
 */
public class HomeWorkAccount {
    private Integer id;
    private String studentId;
    private String workUrl;
    private String created;
    private String updated;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getWorkUrl() {
        return workUrl;
    }

    public void setWorkUrl(String workUrl) {
        this.workUrl = workUrl;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }
}
