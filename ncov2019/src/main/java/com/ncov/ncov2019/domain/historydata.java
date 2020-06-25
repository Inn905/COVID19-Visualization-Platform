package com.ncov.ncov2019.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class historydata {
    @Id
    @GeneratedValue
    private Integer id;
    private String province;
    private String city;
    private String district;
    private String place;
    private String source;
    private String link;
    private Integer patient_id;
    private double lng;
    private double lat;
    private java.sql.Date report_date;
    private float distance;
    private double dangerV;


    public java.sql.Date getReport_date() {
        return report_date;
    }

    public void setReport_date(java.sql.Date report_date) {
        this.report_date = report_date;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(Integer patient_id) {
        this.patient_id = patient_id;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }



    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public double getDangerV() {
        return dangerV;
    }

    public void setDangerV(double dangerV) {
        this.dangerV = dangerV;
    }
}
