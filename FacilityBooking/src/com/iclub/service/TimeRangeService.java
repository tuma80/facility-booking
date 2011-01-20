package com.iclub.service;

import java.util.List;

import com.iclub.member.vo.TimeRange;

public interface TimeRangeService {
	void createTimeRange(TimeRange c);
	void update(TimeRange p);
	List<TimeRange> getAllTimeRanges();
	TimeRange getTimeRange(long id);
	void delete(long id);
}
