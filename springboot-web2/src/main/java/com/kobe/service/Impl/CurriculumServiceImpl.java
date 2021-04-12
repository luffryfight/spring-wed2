package com.kobe.service.Impl;

import com.kobe.common.ServerResponse;
import com.kobe.dao.CurriculumMapper;
import com.kobe.dao.MiddlerMapper;
import com.kobe.dao.SignMapper;
import com.kobe.pojo.Curriculum;
import com.kobe.service.CurriculumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作用：
 * 2020/11/23
 */
@Service
public class CurriculumServiceImpl  implements CurriculumService {
    @Autowired
    private CurriculumMapper curriculumMapper;
    @Autowired
    private SignMapper signMapper;
    @Autowired
    private MiddlerMapper middlerMapper;

    @Override
    public ServerResponse addCurriculum( Curriculum curriculum) {
        if (curriculum==null||curriculum.getClassname()==null
        ||curriculum.getName()==null||curriculum.getWeek()==null){
            return ServerResponse.createByError("创建课程的信息错误！");
        }
        if (curriculumMapper.getIsAbsent(curriculum)>0){//判断该课程是否已经存在
            return ServerResponse.createByError("你已经创建了同样课，创建失败！");
        }
        curriculumMapper.addCurriculum(curriculum);
        int idc=curriculumMapper.getNewCurriculumId(curriculum);//先创建课程然后再查询

        StringBuilder href=new StringBuilder();
        href.append("localhost:8080/kobe");//虚拟路径，应该获取动态的，后面再改
        href.append("/curriculumInfo/");
        href.append(idc);//这里还需要查询课程的id
        Map res=new HashMap();
        res.put("url",href.toString());
        res.put("info",curriculum);
        return ServerResponse.createBySuccess(res);//按要求改为返回数据
    }

    @Override
    public ServerResponse getCurriculums(int idu) {

        List<Curriculum> curriculums = curriculumMapper.getCurriculums(idu);
        if (curriculums==null||curriculums.size()==0){
            return ServerResponse.createByError("课程数量为零");
        }
        return ServerResponse.createBySuccess(curriculums);
    }

    @Override
    public Curriculum curriculumInfo(int idc) {
        return curriculumMapper.curriculumInfo(idc);
    }

    @Override
    public int getNewCurriculumId(Curriculum curriculum) {
        return curriculumMapper.getNewCurriculumId(curriculum);
    }

    @Override
    public ServerResponse joinCurriculum(int idu, int idc) {
        String info=idc+","+idu;
        //添加middle & sign信息->-1表示没上过课
        int ids=signMapper.getNewSignId(info);
        //在middle添加对应信息
        middlerMapper.addMiddle(idu,idc,ids);

        return ServerResponse.createBySuccessMsg("加入课程成功！");
    }

}
