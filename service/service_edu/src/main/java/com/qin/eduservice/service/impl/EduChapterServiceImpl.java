package com.qin.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qin.eduservice.entity.EduChapter;
import com.qin.eduservice.entity.EduVideo;
import com.qin.eduservice.entity.chapter.ChapterVo;
import com.qin.eduservice.entity.chapter.VideoVo;
import com.qin.eduservice.mapper.EduChapterMapper;
import com.qin.eduservice.mapper.EduVideoMapper;
import com.qin.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qin.servicebase.exception.MyException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-07-12
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoMapper eduVideoMapper;

    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {

        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();

        wrapper.eq("course_id",courseId);
        List<EduChapter> eduChapters = baseMapper.selectList(wrapper);


        QueryWrapper<EduVideo> videoWrapper = new QueryWrapper<>();
        videoWrapper.eq("course_id",courseId);
        List<EduVideo> eduVideos = eduVideoMapper.selectList(videoWrapper);

        Map<String,List<VideoVo>> map = new HashMap<>();

        for (EduVideo eduVideo : eduVideos) {
            String cid = eduVideo.getChapterId();
            List<VideoVo> l = map.get(cid);
            if (l==null){
                List<VideoVo> v = new ArrayList<>();
                VideoVo vo = new VideoVo();
                BeanUtils.copyProperties(eduVideo,vo);
                v.add(vo);
                map.put(cid,v);
            }else{
                VideoVo vo = new VideoVo();
                BeanUtils.copyProperties(eduVideo,vo);
                l.add(vo);
            }
        }
        List<ChapterVo> cVo = new ArrayList<>();
        for (EduChapter eduChapter : eduChapters) {
            List<VideoVo> videoVos = map.get(eduChapter.getId());
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(eduChapter,chapterVo);
            chapterVo.setChildren(videoVos);
            cVo.add(chapterVo);
        }
        return cVo;
    }

    /**
     * 删除章节
     * @param chapterId
     */
    @Override
    public boolean deleteChapter(String chapterId) {
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id",chapterId);
        Integer integer = eduVideoMapper.selectCount(wrapper);
        if (integer>0){
            throw new MyException(20001,"该章节不能删除，因为存在课时");
        }
        int i = baseMapper.deleteById(chapterId);
        return i>0;
    }

    @Override
    public void removeChapterByCourseId(String courseId) {
        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        baseMapper.delete(wrapper);
    }

}
