package com.dangvandat.service.impl;

import com.dangvandat.Builder.BuildingSearchBuilder;
import com.dangvandat.Entity.BuildingEntity;
import com.dangvandat.Entity.RentArea;
import com.dangvandat.converter.BuildingConverter;
import com.dangvandat.dto.BuildingDTO;
import com.dangvandat.paging.Pageble;
import com.dangvandat.repository.IRentAreaRepository;
import com.dangvandat.repository.impl.BuildingRepository;
import com.dangvandat.repository.impl.RentAreaRepository;
import com.dangvandat.service.iBuildingService;
import com.dangvandat.repository.iBuildingRepository;
import org.apache.commons.lang.StringUtils;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

public class BuildingService implements iBuildingService{

    //@Inject
	private iBuildingRepository buildingRepository = new BuildingRepository();

    //@Inject
	private BuildingConverter buildingConverter = new BuildingConverter();

    //@Inject
    private IRentAreaRepository rentAreaRepository = new RentAreaRepository();

	/*public BuildingService(){
	    if(buildingRepository == null){
	        buildingRepository = new BuildingRepository();
        }
        if(buildingConverter == null){
            buildingConverter = new BuildingConverter();
        }
    }*/

	/*public BuildingService(){
	    if(buildingRepository == null){
            buildingRepository = new BuildingRepository();
        }
		if(buildingConverter == null){
            buildingConverter = new BuildingConverter();
        }
	}*/

	@Override
	public BuildingDTO save(BuildingDTO newBuildingDTO) {
		@SuppressWarnings("unused")
		BuildingEntity buildingEntity = buildingConverter.convertToEntity(newBuildingDTO);
		buildingEntity.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		buildingEntity.setCreatedBy("");

		buildingEntity.setType(StringUtils.join(newBuildingDTO.getBuildingTypes() , ","));
		Long id =buildingRepository.insert(buildingEntity);

		//save rentArea
        for(String item : newBuildingDTO.getRentArea().split(",")){
            RentArea rentArea = new RentArea();
            rentArea.setValue(item);
            rentArea.setBuildingId(id);
            rentAreaRepository.insert(rentArea);
        }
		return buildingConverter.convertToDTO(buildingRepository.findById(id));
	}

    @Override
    public void update(BuildingDTO updateBuildingDTO, Long id) {
	    BuildingEntity oldBuilding = buildingRepository.findById(id);
	    BuildingEntity  newBuilding = buildingConverter.convertToEntity(updateBuildingDTO);
	    newBuilding.setCreatedBy(oldBuilding.getCreatedBy());
	    newBuilding.setCreatedDate(oldBuilding.getCreatedDate());

	    //rent area
        updateRentArea(updateBuildingDTO.getRentArea() ,id);
        newBuilding.setType(StringUtils.join(updateBuildingDTO.getBuildingTypes() , ","));
        buildingRepository.update(newBuilding);
    }

    @Override
    public void delete(Long[] ids) {
        for (long item : ids){
            rentAreaRepository.deleteByBuildingId(item);
            buildingRepository.delete(item);
        }
    }


    @Override
    public List<BuildingDTO> findAll(BuildingSearchBuilder builder , Pageble pageble) {
        String whereClause = "";
        /*mapSearch.put("name" , builder.getName());
        mapSearch.put("numberofbasement", builder.getNumberOfBasement());*/

        //query data convert to DTO
        List<BuildingEntity> buildingEntities = buildingRepository.findAll(builder , pageble);
        List<BuildingDTO> results = buildingEntities.stream().map(item -> buildingConverter.convertToDTO(item)).collect(Collectors.toList());
        return results;
    }

    @Override
    public int getTotalItems(BuildingSearchBuilder builder) {
        return buildingRepository.countByProperty(builder);
    }

    @Override
    public BuildingDTO finById(Long id) {
        return buildingConverter.convertToDTO(buildingRepository.findById(id));
    }



    /*private Object getValues(Field field, BuildingSearchBuilder builder) {
	    Object result = null;
        Method getter = getGetter(field , builder);
        if(getter != null){
            try{
                result = getter.invoke(builder);
            }catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e){
                e.printStackTrace();
            }
        }
	    return result;
    }

    private Method getGetter(Field field, BuildingSearchBuilder builder) {
	    String getterMethod = "get" + StringUtils.capitalize(field.getName());
	    try{
            return builder.getClass().getMethod(getterMethod);
        }
	    catch (NoSuchMethodException | SecurityException e){
	        e.printStackTrace();
	        return null;
        }
    }*/

    private void updateRentArea(String rentArea, Long buildingId) {
        //delete reant area by buildingid
        rentAreaRepository.deleteByBuildingId(buildingId);

        //insert data in rent area
        for(String item : rentArea.split(",")){
            RentArea rentAreanew = new RentArea();
            rentAreanew.setBuildingId(buildingId);
            rentAreanew.setValue(item);
            rentAreaRepository.insert(rentAreanew);
        }
    }
}
