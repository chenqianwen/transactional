package cn.future.client.support;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Data
public class BaseEntity implements Serializable {

        @Id
        @Column(name = "id")
        private String id;

        private Date createdDate;

        private String createdBy;

        private Date updatedDate;

        private String updatedBy;

        private Integer isDeleted = 0;
}