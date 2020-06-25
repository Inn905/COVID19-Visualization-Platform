package com.ncov.ncov2019.Controller;


import com.ncov.ncov2019.Repository.addressRepository;
import com.ncov.ncov2019.Repository.historydataRepository;
import com.ncov.ncov2019.domain.address;
import com.ncov.ncov2019.domain.historydata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/address")
public class addressController {

    @Autowired
    private addressRepository repository;
    @Autowired
    private historydataRepository repository1;

    //加载所有数据
    @CrossOrigin(origins = "*")
    @GetMapping("/all")
    public List<address> all(){
        return repository.findAll();
    }

    //dangerV
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/danger")
    public double getDangerV(@RequestParam("city") String city,
                             @RequestParam("date") Date strDate,
                             @RequestParam("lat") double lat,
                             @RequestParam("lng") double lng){
        return  dangerousValue(city,strDate,lat,lng);
    }

    //求危险系数
    public double dangerousValue(String city, Date time,Double pointlat, Double pointlng){
        List<historydata> Res = listByBuffer(1.0,city,pointlat,pointlng);
        double DValue = 0;
        for(historydata P:Res){
            double lat = P.getLat();
            double lng = P.getLng();
            float Social_dis = P.getDistance();//社交距离
            java.util.Date Time = P.getReport_date();//报道时间
            //java.util.Date NowTime = String2Date(time);//现在时间
            java.util.Date NowTime = time;
            int day = (int) ((NowTime.getTime()-Time.getTime()) / (1000 * 60 * 60 * 24)); // 计算天
            double tdis = distance(lat,pointlat,lng,pointlng);//实际距离
            //补一个×10取整
            int dis = (int)(tdis/100+0.5);
            if(dis==0){
                dis=1;
            }
            if(day<=0||dis<0){//dis=0 赋值最大
                DValue+=0;
            }
            else {
                if(day>14){
                    DValue+=0;
                }
                else{
                    DValue+=100/(dis*day*Social_dis);
                }
            }
        }
        return DValue;
    }

    public List<historydata> listByBuffer(Double buffer, String city, Double pointlat, Double pointlng){
        List<historydata> Res = repository1.findByCity(city);
        List<historydata> Result = new ArrayList<>();
        for(historydata City:Res){
            double lat=City.getLat();
            double lng=City.getLng();
            double R=6371;
            double dlng=2*Math.asin(Math.sin(buffer/(2*R))/Math.cos(Dec2Rad(pointlat)));
            double dlat=buffer/R;
            dlat=Rad2Dec(dlat);
            dlng=Rad2Dec(dlng);
            if(lat>pointlat-dlat&&lat<pointlat+dlat){
                if(lng>(pointlng-dlng)&&lng<(pointlng+dlng)){
                    double dis=distance(pointlat,lat,pointlng,lng);
                    if(dis<(buffer*1000)) {
                        Result.add(City);
                    }
                }
            }
        }
        return Result;
    }

    //利用经纬度计算距离需要用到的一些函数
    public static double Dec2Rad(double m){
        return m/180*Math.PI;
    }
    public static double Rad2Dec(double m){
        return m*180/Math.PI;
    }
    public static double distance(double lat1, double lat2, double lon1, double lon2) {
        final int R = 6371; // 地球半径
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // 单位转换成米
        distance = Math.pow(distance, 2);
        return Math.sqrt(distance);
    }

}



