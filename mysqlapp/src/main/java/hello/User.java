package hello;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  private String name;

  private String email;

  private String tags;

  private Long last_updated;

  private boolean is_deleted;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getTags(){
  	return tags;
  }

  public void setTags(String tags){
  	this.tags=tags;
  }

  public Long getLastUpdated() {
    return last_updated;
  }

  public void setLastUpdated(Long date) {
    this.last_updated = date;
  }

  public boolean getIsDeleted(){
  	return  is_deleted;
  }

  public void setIsDeleted(boolean is_deleted){
  	this.is_deleted = is_deleted;
  }
}
