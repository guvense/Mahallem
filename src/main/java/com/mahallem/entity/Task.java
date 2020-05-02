package com.mahallem.entity;

import com.mahallem.constants.ProgressStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task extends BaseEntity {
    private String ownerId;

    private String title;

    private String description;

    private Date deadline;

    private boolean isActive = true;

    private ProgressStatus Status = ProgressStatus.OPEN;

    private List<Comment> comments;

}
