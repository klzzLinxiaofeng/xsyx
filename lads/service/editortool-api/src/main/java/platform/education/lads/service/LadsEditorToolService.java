package platform.education.lads.service;

import platform.education.lads.model.LadsEditorTool;
import platform.education.lads.vo.editortoolVo.LadsEditorToolCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface LadsEditorToolService {

    LadsEditorTool findLadsEditorToolById(Integer id);

    LadsEditorTool add(LadsEditorTool ladsEditorTool);

    LadsEditorTool modify(LadsEditorTool ladsEditorTool);

    void remove(LadsEditorTool ladsEditorTool);

    List<LadsEditorTool> findLadsEditorToolByCondition(LadsEditorToolCondition ladsEditorToolCondition, Page page, Order order);

    List<LadsEditorTool> findLadsEditorToolByCondition(LadsEditorToolCondition ladsEditorToolCondition);

    List<LadsEditorTool> findLadsEditorToolByCondition(LadsEditorToolCondition ladsEditorToolCondition, Page page);

    List<LadsEditorTool> findLadsEditorToolByCondition(LadsEditorToolCondition ladsEditorToolCondition, Order order);

    Long count();

    Long count(LadsEditorToolCondition ladsEditorToolCondition);
}
