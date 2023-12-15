package com.antonsskafferi.projekt_dt142g;


import jakarta.persistence.*;

@NamedQuery(name = "musicEntity.allMusic", query = "select event from MusicEntity event")
@Entity
@jakarta.persistence.Table(name = "EVENT", schema = "asdb" , catalog = "")
public class MusicEntity {
    @Id
    @jakarta.persistence.Column(name = "Title", nullable = false, length = 50)
    private String title;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "Week", nullable = true)
    private int week;

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }


    @Override
    public boolean equals(Object object){
        if(this == object) return true;
        if(object == null || getClass() != object.getClass()) return false;
        MusicEntity that = (MusicEntity) object;

        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        return true;
    }

    @Override
    public int hashCode(){
        int finish = title != null ? title.hashCode() :0;
        return finish;
    }

}
