package com.mahallem.entity;

import com.mahallem.constants.ProgressStatus;
import com.mahallem.constants.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task extends BaseEntity {
    @Field("owner_id")
    private ObjectId ownerId;

    private String title;

    private String description;

    private Date deadline;

    private Status status=Status.ACTIVE;

    @Field("progress_status")
    private ProgressStatus progressStatus = ProgressStatus.OPEN;

    private List<Comment> comments;

}
