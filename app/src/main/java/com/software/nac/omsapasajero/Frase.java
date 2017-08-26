package com.software.nac.omsapasajero;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Frase implements Serializable
{
     private Long id;
     private String category;
    private String phrase;
     private Long likes;
     private Long dislikes;
     private String image;
     private WebUser user;

    public Frase()
    {
    }

    public Frase(String category, String phrase, WebUser user, String image)
    {
        this.category = category;
        this.phrase = phrase;
        this.user = user;
        this.image = image;
        this.likes = 0L;
        this.dislikes = 0L;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public String getPhrase()
    {
        return phrase;
    }

    public void setPhrase(String phrase)
    {
        this.phrase = phrase;
    }

    public Long getLikes()
    {
        return likes;
    }

    public void setLikes(Long likes)
    {
        this.likes = likes;
    }

    public Long getDislikes()
    {
        return dislikes;
    }

    public void setDislikes(Long dislikes)
    {
        this.dislikes = dislikes;
    }

    public WebUser getUser()
    {
        return user;
    }

    public void setUser(WebUser user)
    {
        this.user = user;
    }

    public String getImage()
    {
        return image;
    }

    public void setImage(String image)
    {
        this.image = image;
    }

    @Override
    public String toString()
    {
        return "Frase{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", phrase='" + phrase + '\'' +
                ", likes=" + likes +
                ", dislikes=" + dislikes +
                ", user=" + user +
                '}';
    }
}
