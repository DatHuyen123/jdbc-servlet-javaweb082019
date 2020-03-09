package com.dangvandat.repository.impl;

import com.dangvandat.Builder.BuildingSearchBuilder;
import com.dangvandat.Entity.BuildingEntity;
import com.dangvandat.paging.Pageble;
import com.dangvandat.repository.iBuildingRepository;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuildingRepository extends AbstractJDBC<BuildingEntity> implements iBuildingRepository{

    @Override
    public List<BuildingEntity> findAll(BuildingSearchBuilder builder, Pageble pageble) {
        Map<String , Object> properties = builMapSearch(builder);
        StringBuilder whereClause = builWhereClause(builder);
        return findAll(properties , pageble , whereClause.toString());
    }

    @Override
    public int countByProperty(BuildingSearchBuilder builder) {
        Map<String , Object> properties = builMapSearch(builder);
        StringBuilder whereClause = builWhereClause(builder);
        return countByProperty(properties , whereClause.toString());
    }

    private StringBuilder builWhereClause(BuildingSearchBuilder builder) {
        StringBuilder whereClause = new StringBuilder("");
        if(StringUtils.isNotBlank(builder.getCostRentFrom())){
            whereClause.append(" AND costrent >= "+builder.getCostRentFrom()+"");
        }
        if(StringUtils.isNotBlank(builder.getCostRentTo())){
            whereClause.append(" AND costrent <= "+builder.getCostRentTo()+"");
        }

        //SELECT * FROM building b WHERE
        //EXISTS (SELECT * FROM rentarea ra WHERE b.id = ra.buildingid
        //AND (ra.value >= '100' AND ra.value <= '300'))

        if(StringUtils.isNotBlank(builder.getAreaRentFrom()) || StringUtils.isNotBlank(builder.getAreaRentTo())){
            whereClause.append(" AND EXISTS (SELECT * FROM rentarea ra WHERE (A.id = ra.buildingid");
            if(StringUtils.isNotBlank(builder.getAreaRentFrom())){
                whereClause.append(" AND (ra.value >= "+builder.getAreaRentFrom()+"");
            }
            if(StringUtils.isNotBlank(builder.getAreaRentTo())){
                whereClause.append(" AND ra.value <= "+builder.getAreaRentTo()+"");
            }
            whereClause.append(")))");
        }
        if(builder.getBuildingTypes().length > 0){
            whereClause.append(" AND (A.type LIKE '%"+builder.getBuildingTypes()[0]+"%'");
            //java 7
            /*for (String type : builder.getBuildingTypes()){
                if(!type.equals(builder.getBuildingTypes()[0])){
                    whereClause.append(" OR A.type LIKE '%"+type+"%'");
                }
            }*/

            // java 8
            Arrays.stream(builder.getBuildingTypes()).filter(item -> !item.equals(builder.getBuildingTypes()[0]))
                    .forEach(item -> whereClause.append(" OR A.type LIKE '%"+item+"%'"));
            whereClause.append(")");
        }
        return  whereClause;
    }

    private Map<String, Object> builMapSearch(BuildingSearchBuilder builder) {
        Map<String , Object> result = new HashMap<>();
        try{
            Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
            for(Field field : fields){
                if(!field.getName().equals("buildingTypes")
                        && !field.getName().startsWith("costRent")
                        && !field.getName().startsWith("areaRent")){
                    field.setAccessible(true);
                    if (field.get(builder) != null){
                        if(field.getName().equals("numberOfBasement") || field.getName().equals("buildingArea")){
                            if(StringUtils.isNotEmpty((String) field.get(builder))){
                                result.put(field.getName().toLowerCase() , Integer.parseInt((String) field.get(builder)));
                            }
                        }else{
                            if(!field.get(builder).equals("")){
                                result.put(field.getName().toLowerCase() , field.get(builder));
                            }
                        }
                    }
                }
            }
        }
        catch (IllegalArgumentException | IllegalAccessException e){
            e.printStackTrace();
        }
        return result;
    }
}
