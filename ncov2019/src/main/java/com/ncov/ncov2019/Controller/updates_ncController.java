package com.ncov.ncov2019.Controller;


import com.ncov.ncov2019.Repository.updates_ncRepository;
import com.ncov.ncov2019.domain.updates_nc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ncov2019")

public class updates_ncController {

    @Autowired
    private updates_ncRepository repository;

    //获取所有数据
    @CrossOrigin(origins = "*")
    @GetMapping("/data")
    public List<updates_nc> list(){
        return repository.findAll();
    }

    //获取所有省份
    @CrossOrigin(origins = "*")
    @GetMapping("/All_Province")
    public List<String> ProvinceList(){
        List<String> Province=  repository.findAll().stream().map(updates_nc::getProvince).collect(Collectors.toList());
        Province = Province.stream().distinct().collect(Collectors.toList());
        return Province;
    }

    //获取某省份所有城市
    @CrossOrigin(origins = "*")
    @GetMapping("/All_City/{province}")
    public List<String> CityList(@PathVariable("province") String province){
        List<updates_nc> Province=repository.findByProvince(province);
        List<String> City=  Province.stream().map(updates_nc::getCity).collect(Collectors.toList());
        City = City.stream().distinct().collect(Collectors.toList());
        return City;
    }

    //获取所有时间
    @CrossOrigin(origins = "*")
    @GetMapping("/All_Time")
    public List<String> TimeList(){
        List<String> Time=  repository.findAll().stream().map(updates_nc::getReportdate).collect(Collectors.toList());
        Time = Time.stream().distinct().collect(Collectors.toList());
        Collections.reverse(Time);
        return Time;
    }


    //通过日期查询
    @CrossOrigin(origins = "*")
    @GetMapping("/reportdate/{reportdate}")
    public List<updates_nc> listByDate(@PathVariable("reportdate") String reportdate){
        //return repository.findByReportdate(reportdate);
        List<updates_nc> Res= repository.findByReportdate(reportdate);
        Collections.reverse(Res);
        Map<String,updates_nc> map=new LinkedHashMap<String, updates_nc>();
        updates_nc updates_ncMap;
        for(updates_nc Province:Res){
            updates_ncMap=map.get(Province.getProvince());
            if(null!=updates_ncMap){
                updates_ncMap.setAdd(updates_ncMap.getAdd()+Province.getAdd());
                updates_ncMap.setNewdead(updates_ncMap.getNewdead()+Province.getNewdead());
                updates_ncMap.setRecover(updates_ncMap.getRecover()+Province.getRecover());
            }else{
                map.put(Province.getProvince(),Province);
            }
        }
        Collection<updates_nc> valueCollection2 = map.values();
        List<updates_nc> valueList= new ArrayList<updates_nc>(valueCollection2);//map转list

        return valueList;
    }

    //通过省份查询
    @CrossOrigin(origins = "*")
    @GetMapping("/province/{province}")
    public List<updates_nc> listByProvince(@PathVariable("province") String province){
      List<updates_nc> Res= repository.findByProvince(province);
      Collections.reverse(Res);
      //List转Map
      //Map<Integer,updates_nc> updates_ncMap=Res.stream().collect(Collectors.toMap(updates_nc::getId,a->a,(k1,k2)->k1));
      //List以省份分组
      // Map<String,List<updates_nc>> groupBy=Res.stream().collect(Collectors.groupingBy(updates_nc::getReportdate));

      // Map<String,List<updates_nc>> collectNameSumAge = Res.stream().collect(Collectors.groupingBy(updates_nc::getReportdate,Collectors.summingInt(updates_nc::getAdd)));

        Map<String,updates_nc> map=new LinkedHashMap<String, updates_nc>();
        updates_nc updates_ncMap;
        for(updates_nc Province:Res){
            updates_ncMap=map.get(Province.getReportdate());
            if(null!=updates_ncMap){
                updates_ncMap.setAdd(updates_ncMap.getAdd()+Province.getAdd());
                updates_ncMap.setNewdead(updates_ncMap.getNewdead()+Province.getNewdead());
                updates_ncMap.setRecover(updates_ncMap.getRecover()+Province.getRecover());
            }else{
                map.put(Province.getReportdate(),Province);
            }
        }
        Collection<updates_nc> valueCollection2 = map.values();
        List<updates_nc> valueList= new ArrayList<updates_nc>(valueCollection2);//map转list

        return valueList;
    }

    //通过城市
    @CrossOrigin(origins = "*")
    @GetMapping("/city/{city}")
    public List<updates_nc> listByCity(@PathVariable("city") String city){
        List<updates_nc> Res= repository.findByCity(city);
        Collections.reverse(Res);

        Map<String,updates_nc> map=new LinkedHashMap<String, updates_nc>();
        updates_nc updates_ncMap;

        for(updates_nc City:Res){
            updates_ncMap=map.get(City.getReportdate());
            if(null!=updates_ncMap){
                updates_ncMap.setAdd(updates_ncMap.getAdd()+City.getAdd());
                updates_ncMap.setNewdead(updates_ncMap.getNewdead()+City.getNewdead());
                updates_ncMap.setRecover(updates_ncMap.getRecover()+City.getRecover());
            }else{
                map.put(City.getReportdate(),City);
            }
        }
        Collection<updates_nc> valueCollection2 = map.values();
        List<updates_nc> valueList= new ArrayList<updates_nc>(valueCollection2);//map转list
        return valueList;
    }

    //某省份按城市分组
    @CrossOrigin(origins = "*")
    @GetMapping("/ProGroupByCi/{province}")
    public List<updates_nc> listByProGroCi(@PathVariable("province") String province){
        List<updates_nc> Res= repository.findByProvince(province);
        Collections.reverse(Res);
        Map<String,updates_nc> map=new LinkedHashMap<String, updates_nc>();
        updates_nc updates_ncMap;
        for(updates_nc Province:Res){
            updates_ncMap=map.get(Province.getCity());
            if(null!=updates_ncMap){
                updates_ncMap.setAdd(updates_ncMap.getAdd()+Province.getAdd());
                updates_ncMap.setNewdead(updates_ncMap.getNewdead()+Province.getNewdead());
                updates_ncMap.setRecover(updates_ncMap.getRecover()+Province.getRecover());
            }else{
                map.put(Province.getCity(),Province);
            }
        }
        Collection<updates_nc> valueCollection2 = map.values();
        List<updates_nc> valueList= new ArrayList<updates_nc>(valueCollection2);//map转list

        return valueList;
    }

}
