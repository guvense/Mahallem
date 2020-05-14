package com.mahallem.dto.Response;

import com.mahallem.constants.ProgressStatus;
import com.mahallem.constants.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskResponse {

    private String id;

    private String ownerId;

    private String title;

    private String description;

    private Date createDate;

    private Date deadline;

    private Status status;

    private ProgressStatus progressStatus;

    private int commentNumber;
}
