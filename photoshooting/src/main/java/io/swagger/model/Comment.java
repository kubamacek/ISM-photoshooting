package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Comment
 */
@Entity
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-03-29T16:53:00.885Z[GMT]")
public class Comment   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("body")
  private String body = null;

  @JsonProperty("author")
  private String author = null;
  
  private Post post;

  public Comment id(Integer id) {
    this.id = id;
    return this;
  }
  
  public Comment() {
	super();
  }

  public Comment(Integer id, String body, String author) {
	super();
	this.id = id;
	this.body = body;
	this.author = author;
  }	

  /**
   * Get id
   * @return id
  **/
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Comment body(String body) {
    this.body = body;
    return this;
  }

  /**
   * Get body
   * @return body
  **/
  @ApiModelProperty(value = "")
  
    public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public Comment author(String author) {
    this.author = author;
    return this;
  }

  /**
   * Get author
   * @return author
  **/
  @ApiModelProperty(value = "")
  
    public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }
  
  @ManyToOne
  @JoinColumn(name = "post_id")
  @JsonIgnore
  public Post getPost() {
	return post;
  }

  public void setPost(Post post) {
	this.post = post;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Comment comment = (Comment) o;
    return Objects.equals(this.id, comment.id) &&
        Objects.equals(this.body, comment.body) &&
        Objects.equals(this.author, comment.author);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, body, author);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Comment {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    body: ").append(toIndentedString(body)).append("\n");
    sb.append("    author: ").append(toIndentedString(author)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
  
  public Boolean validateAllFields() {
	  if (this.getAuthor() != null && this.getBody() != null) {
		  return true;
	  }
	  else return false;
  }
}
