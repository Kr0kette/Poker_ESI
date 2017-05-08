package esi.atl.g39871.poker.persistence.dto;


/**
 * Abstract class that each dto must extends.
 */
public abstract class EntityDto<T> {

    protected boolean modified;

    /**
     * The persistent object's primary key.
     * The type can depends from one class to another
     */
    protected T id;

    /**
     * Returns the fact that the dto is persistent and was modified since its database extraction
     */
    public boolean isModified(){
        return isPersistant() && modified;
    }

    /**
     * Returns the fact that the dto has or not a primary key's value
     */
    public  boolean isPersistant(){
        return (id!=null);
    }

    /**
     * returns the object's key's value 
     * This value will be null for non persistent object
     * @return id the id 
     */
    public T getId(){
        return id;
    }

    @Override
    public boolean equals(Object dto){
        if ( dto == null || dto.getClass() != getClass()
                         || ((EntityDto)dto).isPersistant() != isPersistant()) {
            return false;
        }
        return ((EntityDto)dto).getId().equals(getId());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }
    
}

