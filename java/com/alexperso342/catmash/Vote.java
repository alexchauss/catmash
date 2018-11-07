package com.alexperso342.catmash;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Vote {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

	private String catId;

    private String email;

	protected Vote() {}

	public Vote(String email, String catId) {
        this.email = email;
        this.catId = catId;
    }

	@Override
    public String toString() {
        return String.format(
                "Vote[email=%s, catId='%s']",
                email, catId);
    }

	public String getCatId() {
		return catId;
	}

	public void setCatId(String catId) {
		this.catId = catId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
