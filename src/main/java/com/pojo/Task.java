package com.pojo;

/**
 * 对应任务详细表
 * 	id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
 * 	teacherId VARCHAR(20) DEFAULT NULL COMMENT '教师id，同时也是登陆系统用的账号',
 * 	title VARCHAR(20) DEFAULT '' COMMENT '任务名称',
 * 	detail VARCHAR(20) DEFAULT '' COMMENT '任务内容',
 * 	created VARCHAR(20) DEFAULT 0 COMMENT '记录创建日期',
 * 	updated VARCHAR(20) DEFAULT 0 COMMENT '修改日期',
 */
public class Task {

    private Integer id;
    private Long teacherId;
    private String title;
    private String detail;
    private String created;
    private String updated;
    private Long classId;
    private String fileUrl;

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
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
