package com.iclub.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iclub.dao.GenericDao;
import com.iclub.member.vo.TimeRange;
import com.iclub.service.TimeRangeService;

@Service("timeRangeService")
public class TimeRangeServiceImpl implements TimeRangeService{
	@Autowired
	GenericDao<TimeRange,Long> timeRangeDao;
	
	public List<TimeRange> getAllTimeRanges(){
		return timeRangeDao.getAll(TimeRange.class);
	}

	@Transactional
	public void createTimeRange(TimeRange c) {
		timeRangeDao.create(c);
	}

	@Transactional
	public void update(TimeRange p) {
		timeRangeDao.update(p);
	}

	@Transactional
	public TimeRange getTimeRange(long id) {
		return timeRangeDao.read(id, TimeRange.class);
	}

	@Transactional
	public void delete(long id) {
		TimeRange timeRange = new TimeRange();
		timeRange.setId(id);
		timeRangeDao.delete(timeRange);
	}

}
