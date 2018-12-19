package com.longfor.gaia.gfs.demo.dorado.controller;

import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.data.provider.Page;
import com.google.common.collect.Lists;
import com.longfor.gaia.gfs.demo.dorado.dto.DictDTO;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Controller;

import java.util.List;


@Controller
public class DictionaryController {

	@DataProvider
	public void load(Page<DictDTO> page) {
		page.setEntities(mockPage());
		page.setEntityCount(100);
	}

	private List<DictDTO> mockPage() {
		List<DictDTO> list = Lists.newLinkedList();
		list.add(mockDict());
		list.add(mockDict());
		list.add(mockDict());
		list.add(mockDict());
		list.add(mockDict());
		return list;
	}

	private DictDTO mockDict() {
		DictDTO mock = new DictDTO();
		mock.setCode(RandomUtils.nextInt() + "");
		mock.setId(RandomUtils.nextLong());
		mock.setName(RandomUtils.nextInt() + "");
		return mock;
	}

}
