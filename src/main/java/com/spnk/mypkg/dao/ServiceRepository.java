package com.spnk.mypkg.dao;

import java.util.List;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.hibernate.boot.jaxb.mapping.EntityOrMappedSuperclass;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.spnk.mypkg.entity.ModelClass;

@Service
public interface ServiceRepository extends CrudRepository<ModelClass, Integer>{

	public ModelClass findById(int id);
	
	public default boolean find(ModelClass m) {
		
		try {
			if(findById(m.getId())!=null) {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
		
	}
	public default boolean find(ModelClass m,int id) {
		
		try {
			if(findById(id)!=null) {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
		
	}
//	@Query
//	public static boolean find(ModelClass m) {
//		boolean f=false;
//		
//		boolean mList= find(m);
//		
//		
//		return f;
//		
//	}
}
