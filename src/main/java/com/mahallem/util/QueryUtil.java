package com.mahallem.util;

import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Component
public class QueryUtil {

    @NotNull
    final MongoTemplate mongoTemplate;

    /**
     * <p>The intent of this method is to generate update query
     * with non-null values for patch operations
     * </p>
     * @param entity is a db object
     * @param exclude is a exclusion list for update query
     * @return Update query which consist of non-null values and out of exclusion list
     */
    public  Update generateUpdateQuery(Object entity, String... exclude) {
        Document doc = new Document();
        mongoTemplate.getConverter().write(entity, doc);
        return fromDBObjectExcludeNullFields(doc, exclude);
    }

    private  Update fromDBObjectExcludeNullFields(Document object, String... exclude) {
        Update update = new Update();
        List<String> excludeList = Arrays.asList(exclude);
        for (String key : object.keySet()) {
            Object value = object.get(key);
            if(value!=null && !excludeList.contains(key)){
                update.set(key, value);
            }
        }
        return update;
    }
}
