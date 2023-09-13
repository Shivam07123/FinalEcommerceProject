package com.Spring;




import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository   // denotes that it contains all crud methods
@EnableJpaRepositories  // to enable all the configurations and methods under jpa repository
public interface productbycategoryRepo extends JpaRepository<productbycategory,Integer>
{

	@Query(value= "{call productbycategory(:catid)}",nativeQuery=true)
	public List<productbycategory> productbycategory(@Param("catid")int catid);
	
	
}


