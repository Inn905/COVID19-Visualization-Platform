package com.ncov.ncov2019.Controller;

import com.ncov.ncov2019.Repository.analysis_Repository;
import com.ncov.ncov2019.domain.analysis;
import com.ncov.ncov2019.domain.updates_nc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/analysis")
public class analysis_Controller {

    @Autowired
    private analysis_Repository repository;

    //获取所有省份
    @CrossOrigin(origins = "*")
    @GetMapping("/All_Province")
    public List<String> ProvinceList(){
        List<String> Province=  repository.findAll().stream().map(analysis::getProvince).collect(Collectors.toList());
        Province = Province.stream().distinct().collect(Collectors.toList());
        return Province;
    }

    //获取某省份所有城市
    @CrossOrigin(origins = "*")
    @GetMapping("/All_City/{province}")
    public List<String> CityList(@PathVariable("province") String province){
        List<analysis> Province=repository.findByProvince(province);
        List<String> City=  Province.stream().map(analysis::getCity).collect(Collectors.toList());
        City = City.stream().distinct().collect(Collectors.toList());
        return City;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/content/{city}")
    public List<analysis> listByProvince(@PathVariable("city") String city){
        List<analysis> Res= repository.findByCity(city);
        //Collections.reverse(Res);
        return Res;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/Buffer")
    public List<analysis> listByBuffer(
            @RequestParam(value = "buffer",required = false,defaultValue = "1000") Double buffer,
            @RequestParam(value = "city") String city,
            @RequestParam(value = "pointlat") String Spointlat,
            @RequestParam(value = "pointlng") String Spointlng){
        List<analysis> Res = repository.findByCity(city);
        List<analysis> Result = new ArrayList<>();
        double pointlat = Double.valueOf(Spointlat);
        double pointlng = Double.valueOf(Spointlng);
        //double buffer=Double.valueOf(tbuffer);
        //double pointlat=Double.valueOf(tpointlat);
        //double pointlng=Double.valueOf(tpointlng);
        for(analysis City:Res){
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
