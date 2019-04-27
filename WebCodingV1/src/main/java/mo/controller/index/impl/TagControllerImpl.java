package mo.controller.index.impl;

import com.alibaba.fastjson.JSONObject;
import mo.controller.index.TagController;
import mo.core.Result;
import mo.core.ResultCode;
import mo.entity.po.Tag;
import mo.service.TagService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class TagControllerImpl implements TagController {

    @Resource
    private TagService tagService;

    @Override
    @ResponseBody
    @RequestMapping(value = "/tags/problem/{problem_id}", method = RequestMethod.GET)
    public Result tags(@PathVariable Integer problem_id) {
        JSONObject tags = new JSONObject();
        tags.put("tags", tagService.findTagsByProblemId(problem_id));
        return new Result().setCode(ResultCode.OK).setData(tags);
    }

    @Override
    @ResponseBody
    @RequestMapping(value = "/tags", method = RequestMethod.GET)
    public Result tags(@RequestParam(value = "page", defaultValue = "1") String page,
                       @RequestParam(value = "page", defaultValue = "10") String per_page) {

        JSONObject tags = new JSONObject();
        tags.put("tags", tagService.findTagsByPage(Integer.valueOf(page), Integer.valueOf(per_page)));
        return new Result().setCode(ResultCode.OK).setData(tags);
    }
}
