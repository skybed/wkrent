package com.wkrent.business.app.base.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.wkrent.business.app.base.dao.AppDataDictValueDao;
import com.wkrent.business.app.base.obj.DataDict;
import com.wkrent.business.app.base.service.AppDataDictValueService;
import com.wkrent.common.entity.vo.BgDataDictValueVO;

@Service
public class AppDataDictValueServiceImpl implements AppDataDictValueService {

    @Autowired
    private AppDataDictValueDao appDataDictValueDao;

    /**
     * 查询枚举值List
     *
     * @param dictType 查询条件
     * @return 符合条件枚举值信息
     */
    @Override
    public List<DataDict> queryDictValueList(String dictType) {
        if(StringUtils.isBlank(dictType)){
            return Lists.newArrayList();
        }
        
        List<BgDataDictValueVO> regionInfos = appDataDictValueDao.queryDictValueByType(dictType);
		
		List<DataDict> dataDicts = new ArrayList<DataDict>();
		if(CollectionUtils.isNotEmpty(regionInfos)) {
			for(int i = 0; i < regionInfos.size(); i++) {
				DataDict dataDict = new DataDict();
				dataDict.setDataDictId(regionInfos.get(i).getBgDataDictValueId());
				dataDict.setDataDictName(regionInfos.get(i).getBgDataDictValue());
				dataDicts.add(dataDict);
			}
		}
		
        return dataDicts;
    }

}
