package io.swagger.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.swagger.model.Meeting;
import io.swagger.model.Offer;

@Service
public class MeetingsApiService {

	@Autowired
	private MeetingsApiRepository meetingsApiRepository;
	
	public List<Meeting> getMeetings(){
		List<Meeting> meetings = new ArrayList<>();
		meetingsApiRepository.findAll().forEach(meetings::add);
		return meetings;
	}
	
	public String addMeeting(Meeting meeting) {
		meetingsApiRepository.save(meeting);
		Integer id = meeting.getId();
		String ids = id.toString();
		return ids;
	}
	
	public Meeting getMeetingbyId(Integer id) {
		return meetingsApiRepository.findOne(id);
	}
	
	public String updateMeeting(Integer id, Meeting body) {
		Meeting meeting = meetingsApiRepository.findOne(id);
		meeting.setTitle(body.getTitle());
		meeting.setDescription(body.getDescription());
		final Meeting updatedMeeting = meetingsApiRepository.save(meeting);
		return "OK";
	}
	
	public String deleteMeeting(Integer id) {
		meetingsApiRepository.delete(id);
		return "OK";
	}
}
