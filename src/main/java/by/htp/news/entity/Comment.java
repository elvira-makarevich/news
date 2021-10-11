package by.htp.news.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "comments")
public class Comment implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_comment")
	private int id;

	@Column(name = "id_news")
	private int newsId;

	@NotNull(message = "{message.is.required}")
	@Pattern(regexp = "(.){1,200}", message = "{valid.comment}")
	@Column(name = "comment")
	private String comment;

	@Column(name = "date", updatable = false)
	@CreationTimestamp
	private Date publishingDate;

	public Comment() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNewsId() {
		return newsId;
	}

	public void setNewsId(int newsId) {
		this.newsId = newsId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getPublishingDate() {
		return publishingDate;
	}

	public void setPublishingDate(Date publishingDate) {
		this.publishingDate = publishingDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		result = prime * result + id;
		result = prime * result + newsId;
		result = prime * result + ((publishingDate == null) ? 0 : publishingDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		if (comment == null) {
			if (other.comment != null)
				return false;
		} else if (!comment.equals(other.comment))
			return false;
		if (id != other.id)
			return false;
		if (newsId != other.newsId)
			return false;
		if (publishingDate == null) {
			if (other.publishingDate != null)
				return false;
		} else if (!publishingDate.equals(other.publishingDate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getClass().getName() + " [id=" + id + ", newsId=" + newsId + ", comment=" + comment + ", publishingDate="
				+ publishingDate + "]";
	}

}
