package com.xunyunedu.sqcomment.util;

import com.xunyunedu.resource.constant.ResourceTypeContans;
import com.xunyunedu.sqcomment.constant.ObjectTypeContans;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class ObjectTypeContansUtil {

    private static Map<String,Integer> OBJECT_RESOURCE_MAPPING = new HashMap<>();

    static {
        OBJECT_RESOURCE_MAPPING.put(ObjectTypeContans.MICRO_TYPE,ResourceTypeContans.MICRO);
        OBJECT_RESOURCE_MAPPING.put(ObjectTypeContans.DESINE_TYPE,ResourceTypeContans.LEARNING_DESIGN);
        OBJECT_RESOURCE_MAPPING.put(ObjectTypeContans.EXAM_TYPE,ResourceTypeContans.EXAM);
        OBJECT_RESOURCE_MAPPING.put(ObjectTypeContans.HOMEWORD_TYPE,ResourceTypeContans.HOMEWORK);
        OBJECT_RESOURCE_MAPPING.put(ObjectTypeContans.METERIAL_TYPE,ResourceTypeContans.MATERIAL);
        OBJECT_RESOURCE_MAPPING.put(ObjectTypeContans.TEACHRING_PLAN_TYPE,ResourceTypeContans.TEACHING_PLAN);
        OBJECT_RESOURCE_MAPPING.put(ObjectTypeContans.RES_PAID,ResourceTypeContans.PAID);
    }


    private static Map<Integer,String> RESOURCE_OBJECT_MAPPING = new HashMap<>();

    static {
        RESOURCE_OBJECT_MAPPING.put(ResourceTypeContans.MICRO,ObjectTypeContans.MICRO_TYPE);
        RESOURCE_OBJECT_MAPPING.put(ResourceTypeContans.LEARNING_DESIGN,ObjectTypeContans.DESINE_TYPE);
        RESOURCE_OBJECT_MAPPING.put(ResourceTypeContans.EXAM,ObjectTypeContans.EXAM_TYPE);
        RESOURCE_OBJECT_MAPPING.put(ResourceTypeContans.HOMEWORK,ObjectTypeContans.HOMEWORD_TYPE);
        RESOURCE_OBJECT_MAPPING.put(ResourceTypeContans.MATERIAL,ObjectTypeContans.METERIAL_TYPE);
        RESOURCE_OBJECT_MAPPING.put(ResourceTypeContans.TEACHING_PLAN,ObjectTypeContans.TEACHRING_PLAN_TYPE);
        RESOURCE_OBJECT_MAPPING.put(ResourceTypeContans.PAID,ObjectTypeContans.RES_PAID);
    }

    /**
     * ObjectType convert ResourceType
     * @see com.xunyunedu.sqcomment.constant.ObjectTypeContans
     * @see com.xunyunedu.resource.constant.ResourceTypeContans
     * @param objectType
     * @return
     */
    public static Integer convertResourceType(String objectType){


        Integer resourceType = OBJECT_RESOURCE_MAPPING.get(objectType);
        if(resourceType != null){
            return resourceType;
        }


        return ResourceTypeContans.OTHER;
    }

    /**
     * ResourceType convert ObjectType
     * @see com.xunyunedu.sqcomment.constant.ObjectTypeContans
     * @see com.xunyunedu.resource.constant.ResourceTypeContans
     * @param resourceType
     * @return
     */
    public static String convertObjectType(Integer resourceType){


        String objectType = RESOURCE_OBJECT_MAPPING.get(resourceType);
        if(objectType != null){
            return objectType;
        }

        return ObjectTypeContans.OTHERS;
    }
}
