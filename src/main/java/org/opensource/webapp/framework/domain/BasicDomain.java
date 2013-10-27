package org.opensource.webapp.framework.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Created with IntelliJ IDEA.
 * User: panmingzhi815
 * Date: 13-10-26
 * Time: 下午5:33
 * 所有数据对象的父类,统一主键生成方式
 */
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

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }
}
