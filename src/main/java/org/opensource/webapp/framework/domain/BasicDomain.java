package org.opensource.webapp.framework.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BasicDomain{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj.getClass().getSuperclass() != BasicDomain.class){
			return false;
		}
		
		BasicDomain basicDomain = BasicDomain.class.cast(obj);
		if(id == null || basicDomain.getId() == null){
			return false;
		}
		
		return this.id.equals(basicDomain.getId());
	}
}
